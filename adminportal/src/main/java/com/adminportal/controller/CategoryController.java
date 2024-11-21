package com.adminportal.controller;

import com.adminportal.domain.Category;
import com.adminportal.domain.CategoryOrder;
import com.adminportal.service.CategoryService;
import com.adminportal.utility.UrlUtility;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("category")
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final UrlUtility urlUtility;

    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("currentPage","category");
        return "categories/addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategoryPost(@ModelAttribute("category") Category category, Principal principal,
                                  @RequestParam("file") MultipartFile file,
                                  @RequestParam(value = "apps", required = false) String[] apps,
                                  RedirectAttributes redirectAttributes) throws IOException {

        if (Objects.isNull(category)) {
            log.info("Category is empty.");
            return null;
        }

        if (categoryService.isCategoryName(category.getTitle())) {
            redirectAttributes.addFlashAttribute("message", "Category with name " + category.getTitle() + " is existing in db.");
            return "redirect:addCategory";
        }

        byte[] contents;
        contents = IOUtils.toByteArray(file.getInputStream());

        LocalDateTime localDateTime = LocalDateTime.now();
        String userCreated = principal.getName();

        category.setCreated(localDateTime);
        category.setUserCreated(userCreated);
        category.setUpdated(localDateTime);
        category.setUserUpdated(userCreated);

        category.setImage(contents);
        category.setOriginalFileName(file.getOriginalFilename());
        category.setContentType(file.getContentType());
        category.setSize(file.getSize());

        category.setCategoryTitlePath(urlUtility.getRequestPath(category.getTitle()));

        if (apps != null) {
            category.setApp(String.join(",", apps));
        } else {
            category.setApp("");
        }

        categoryService.save(category);
        return "redirect:/category/categoryList";
    }

    @GetMapping("/categoryList")
    public String categoryList(Model model) {
        List<Category> categoryList = categoryService.findAll().stream()
                .sorted(Comparator.comparing(Category::getOrderBy)).collect(Collectors.toList());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("currentPage","category");
        return "categories/categoryList";

    }

    @GetMapping(value="/imageDisplays/{id}")
    public void getImage(@PathVariable("id") long id , HttpServletResponse response)  {

        OutputStream oImage;

        try{
            Category category = categoryService.findOne(id);
            response.setContentType(category.getContentType());
            oImage=response.getOutputStream();
            if (Objects.nonNull(category.getImage())) {
                oImage.write(category.getImage());
                oImage.flush();
                oImage.close();
            } else {
                log.error("Get images is null!");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/categoryInfo")
    public String categoryInfo(@RequestParam("id") Long id, Model model) {
        Category category = categoryService.findOne(id);
        model.addAttribute("category", category);
        model.addAttribute("currentPage","category");
        return "categories/categoryInfo";
    }

    @GetMapping("/updateCategory")
    public String updateCategory(@RequestParam("id") Long id, Model model) {
        Category category = categoryService.findOne(id);
        model.addAttribute("category", category);

        if (category.getApp().contains("membrania")) {
            model.addAttribute("membrania", Boolean.TRUE);
        }

        if (category.getApp().contains("dcsolutions")) {
            model.addAttribute("dcsolutions", Boolean.TRUE);
        }

        model.addAttribute("currentPage","category");
        return "categories/updateCategory";
    }

    @PostMapping("/updateCategory")
    public String updateCategoryPost(@ModelAttribute("category") Category category,
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam(value = "apps", required = false) String[] apps,
                                 Principal principal,
                                 RedirectAttributes redirectAttributes) throws IOException {

        byte[] contents;
        Category categoryOld = categoryService.findOne(category.getId());

        if (!category.getTitle().equals(categoryOld.getTitle()) && categoryService.isCategoryName(category.getTitle())) {
            redirectAttributes.addFlashAttribute("message", "Category with name " + category.getTitle() + " is existing in db.");
            return "redirect:/category/updateCategory?id=" + category.getId();
        }

        if (Objects.isNull(categoryOld.getCreated())) {
            category.setCreated(LocalDateTime.now());
        } else {
            category.setCreated(categoryOld.getCreated());
        }

        category.setUserCreated(categoryOld.getUserCreated());
        category.setUpdated(LocalDateTime.now());
        category.setUserUpdated(principal.getName());

        if (apps != null) {
            category.setApp(String.join(",", apps));
        } else {
            category.setApp("");
        }

        if (Strings.isNullOrEmpty(category.getOriginalFileName())) {
            category.setCategoryTitlePath(urlUtility.getRequestPath(category.getTitle()));
        }

        if (file.isEmpty()) {
            category.setImage(categoryOld.getImage());
            category.setOriginalFileName(categoryOld.getOriginalFileName());
            category.setContentType(categoryOld.getContentType());
            category.setSize(categoryOld.getSize());
        } else {
            contents = IOUtils.toByteArray(file.getInputStream());
            category.setImage(contents);
            category.setOriginalFileName(file.getOriginalFilename());
            category.setContentType(file.getContentType());
            category.setSize(file.getSize());
        }
        categoryService.save(category);

        log.info("Product was updated. Product id {}, user updated {}, day updated {}.",
                category.getId(),
                principal.getName(),
                LocalDateTime.now()
        );

        return "redirect:categoryInfo?id="+category.getId();
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute("id") String id, Model model) {
        if (Strings.isNullOrEmpty(id)) {
            return "redirect:/";
        }
        categoryService.removeCategory(Long.parseLong(id.substring(12)));
        model.addAttribute("categoryList", categoryService.findAll());

        return "redirect:/category/categoryList";
    }

    @ResponseBody
    @PostMapping(value = "/sortabledatatable", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sortableDatatable(@RequestBody List<CategoryOrder> orderJsonList) {
        orderJsonList.forEach(s -> {
            categoryService.updateCategoryOrder(s.getCategoryId(), s.getOrder());
            log.info(s.getCategoryId() + " " + s.getOrder());
        });
    }

}
