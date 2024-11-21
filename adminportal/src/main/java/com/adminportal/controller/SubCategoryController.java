package com.adminportal.controller;

import com.adminportal.domain.SubCategory;
import com.adminportal.service.CategoryService;
import com.adminportal.service.SubCategoryService;
import com.adminportal.utility.UrlUtility;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequestMapping("subCategory")
@Slf4j
@RequiredArgsConstructor
@Controller
public class SubCategoryController {

    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;
    private final UrlUtility urlUtility;

    @GetMapping("/addSubCategory")
    public String addCategory(Model model) {
        SubCategory subCategory = new SubCategory();
        model.addAttribute("subCategory", subCategory);
        model.addAttribute("currentPage","subCategory");
        model.addAttribute("category", categoryService.findAllActive());
        return "subCategories/addSubCategory";
    }

    @PostMapping("/addSubCategory")
    public String addCategoryPost(@ModelAttribute("subCategory") SubCategory subCategory, Principal principal,
                                  @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        if (Objects.isNull(subCategory)) {
            log.info("SubCategory is empty.");
            return null;
        }

        if (subCategoryService.isSubCategoryName(subCategory.getTitle())) {
            redirectAttributes.addFlashAttribute("message", "SubCategory with name " + subCategory.getTitle() + " is existing in db.");
            return "redirect:addSubCategory";
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:addSubCategory";
        }

        byte[] contents;
        contents = IOUtils.toByteArray(file.getInputStream());

        LocalDateTime localDateTime = LocalDateTime.now();
        String userCreated = principal.getName();

        subCategory.setCreated(localDateTime);
        subCategory.setUserCreated(userCreated);
        subCategory.setUpdated(localDateTime);
        subCategory.setUserUpdated(userCreated);

        subCategory.setImage(contents);
        subCategory.setOriginalFileName(file.getOriginalFilename());
        subCategory.setContentType(file.getContentType());
        subCategory.setSize(file.getSize());

        subCategory.setSubCategorytitlePath(urlUtility.getRequestPath(subCategory.getTitle()));

        subCategoryService.save(subCategory);
        return "redirect:/subCategory/subCategoryList";
    }

    @GetMapping("/subCategoryList")
    public String subCategoryList(Model model) {
        List<SubCategory> subCategoryList = subCategoryService.findAll();
        model.addAttribute("subCategoryList", subCategoryList);
        model.addAttribute("currentPage","subCategory");
        return "subCategories/subCategoryList";

    }

    @GetMapping(value="/imageDisplays/{id}")
    public void getImage(@PathVariable("id") long id , HttpServletResponse response)  {

        OutputStream oImage;

        try{
            SubCategory subCategory = subCategoryService.findOne(id);
            response.setContentType(subCategory.getContentType());
            oImage=response.getOutputStream();
            if (Objects.nonNull(subCategory.getImage())) {
                oImage.write(subCategory.getImage());
                oImage.flush();
                oImage.close();
            } else {
                log.error("Get images is null!");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/subCategoryInfo")
    public String subCategoryInfo(@RequestParam("id") Long id, Model model) {
        SubCategory subCategory = subCategoryService.findOne(id);
        model.addAttribute("subCategory", subCategory);
        model.addAttribute("currentPage","subCategory");
        return "subCategories/subCategoryInfo";
    }

    @GetMapping("/updateSubCategory")
    public String updateSubCategory(@RequestParam("id") Long id, Model model) {
        SubCategory subCategory = subCategoryService.findOne(id);
        model.addAttribute("subCategory", subCategory);
        model.addAttribute("currentPage","subCategory");
        model.addAttribute("category", categoryService.findAllActive());

        return "subCategories/updateSubCategory";
    }

    @PostMapping("/updateSubCategory")
    public String updateSubCategoryPost(@ModelAttribute("subCategory") SubCategory subCategory,
                                 @RequestParam("file") MultipartFile file,
                                 Principal principal,
                                 HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {

        byte[] contents;
        SubCategory subCategoryOld = subCategoryService.findOne(subCategory.getId());

        if (!subCategory.getTitle().equals(subCategoryOld.getTitle()) && subCategoryService.isSubCategoryName(subCategory.getTitle())) {
            redirectAttributes.addFlashAttribute("message", "SubCategory with name " + subCategory.getTitle() + " is existing in db.");
            return "redirect:/subCategory/updateSubCategory?id=" + subCategory.getId();
        }

        if (Objects.isNull(subCategoryOld.getCreated()) || Objects.isNull(subCategoryOld.getUserCreated())) {
            subCategory.setCreated(LocalDateTime.now());
            subCategory.setUserCreated(principal.getName());
        } else {
            subCategory.setCreated(subCategoryOld.getCreated());
            subCategory.setUserCreated(subCategoryOld.getUserCreated());
        }

        subCategory.setUpdated(LocalDateTime.now());
        subCategory.setUserUpdated(principal.getName());

        if (file.isEmpty()) {
            subCategory.setImage(subCategoryOld.getImage());
            subCategory.setOriginalFileName(subCategoryOld.getOriginalFileName());
            subCategory.setContentType(subCategoryOld.getContentType());
            subCategory.setSize(subCategoryOld.getSize());
        } else {
            contents = IOUtils.toByteArray(file.getInputStream());
            subCategory.setImage(contents);
            subCategory.setOriginalFileName(file.getOriginalFilename());
            subCategory.setContentType(file.getContentType());
            subCategory.setSize(file.getSize());
        }

        if (Strings.isNullOrEmpty(subCategory.getSubCategorytitlePath())) {
            subCategory.setSubCategorytitlePath(urlUtility.getRequestPath(subCategory.getTitle()));
        }

        subCategoryService.save(subCategory);

        log.info("Product was updated. Product id {}, user updated {}, day updated {}.",
                subCategory.getId(),
                principal.getName(),
                LocalDateTime.now()
        );

        return "redirect:subCategoryInfo?id="+subCategory.getId();
    }

    @PostMapping(value="/remove")
    public String remove(@ModelAttribute("id") String id, Model model) {
        if (Strings.isNullOrEmpty(id)) {
            return "redirect:/";
        }
        subCategoryService.removeCategory(Long.parseLong(id.substring(15)));
        model.addAttribute("subCategoryList", subCategoryService.findAll());

        return "redirect:/subCategory/subCategoryList";
    }

}
