package com.adminportal.controller;

import com.adminportal.domain.Type;
import com.adminportal.service.SubCategoryService;
import com.adminportal.service.TypeService;
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
@Slf4j
@RequiredArgsConstructor
@RequestMapping("type")
@Controller
public class TypeController {

    private final TypeService typeService;
    private final SubCategoryService subCategoryService;
    private final UrlUtility urlUtility;

    @GetMapping("/addType")
    public String addCategory(Model model) {
        Type type = new Type();
        model.addAttribute("type", type);
        model.addAttribute("currentPage","type");
        model.addAttribute("subCategory", subCategoryService.findAllActive());
        return "types/addType";
    }

    @PostMapping("/addType")
    public String addCategoryPost(@ModelAttribute("type") Type type,
                                  @RequestParam("file") MultipartFile file,
                                  Principal principal, Model model, RedirectAttributes redirectAttributes) throws IOException {

        if (Objects.isNull(type)) {
            log.info("Type is empty.");
            return null;
        }

        if (typeService.isTypeName(type.getTitle())) {
            redirectAttributes.addFlashAttribute("message", "Type with name " + type.getTitle() + " is existing in db.");
            return "redirect:addType";
        }

        byte[] contents;
        contents = IOUtils.toByteArray(file.getInputStream());

        LocalDateTime localDateTime = LocalDateTime.now();
        String userCreated = principal.getName();

        type.setCreated(localDateTime);
        type.setUserCreated(userCreated);
        type.setUpdated(localDateTime);
        type.setUserUpdated(userCreated);

        type.setImage(contents);
        type.setOriginalFileName(file.getOriginalFilename());
        type.setContentType(file.getContentType());
        type.setSize(file.getSize());

        type.setTypeTitlePath(urlUtility.getRequestPath(type.getTitle()));

        model.addAttribute("currentPage","type");

        typeService.save(type);
        return "redirect:/type/typeList";
    }

    @GetMapping("/typeList")
    public String productList(Model model) {
        List<Type> typeList = typeService.findAll();
        model.addAttribute("typeList", typeList);
        model.addAttribute("currentPage","type");

        return "types/typeList";
    }

    @GetMapping(value="/imageDisplays/{id}")
    public void getImage(@PathVariable("id") long id , HttpServletResponse response)  {

        OutputStream oImage;

        try{
            Type type = typeService.findOne(id);
            response.setContentType(type.getContentType());
            oImage=response.getOutputStream();
            if (Objects.nonNull(type.getImage())) {
                oImage.write(type.getImage());
                oImage.flush();
                oImage.close();
            } else {
                log.error("Get images is null!");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/typeInfo")
    public String productInfo(@RequestParam("id") Long id, Model model) {
        Type type = typeService.findOne(id);
        model.addAttribute("type", type);
        model.addAttribute("currentPage","type");
        return "types/typeInfo";
    }

    @GetMapping("/updateType")
    public String updateType(@RequestParam("id") Long id, Model model) {
        Type type = typeService.findOne(id);
        model.addAttribute("type", type);
        model.addAttribute("currentPage","type");
        model.addAttribute("subCategory", subCategoryService.findAllActive());

        return "types/updateType";
    }

    @PostMapping("/updateType")
    public String updateProductPost(@ModelAttribute("type") Type type, Principal principal,
                                    @RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) throws IOException {
        if (Objects.isNull(type)) {
            return null;
        }

        byte[] contents;
        Type typeOld = typeService.findOne(type.getId());

        if (!type.getTitle().equals(typeOld.getTitle()) && typeService.isTypeName(type.getTitle())) {
            redirectAttributes.addFlashAttribute("message", "Type with name " + type.getTitle() + " is existing in db.");
            return "redirect:/type/updateType?id=" + type.getId();
        }

        if (Objects.isNull(typeOld.getCreated())) {
            type.setCreated(LocalDateTime.now());
        } else {
            type.setCreated(typeOld.getCreated());
        }

        type.setUserCreated(typeOld.getUserCreated());
        type.setUpdated(LocalDateTime.now());
        type.setUserUpdated(principal.getName());

        if (Strings.isNullOrEmpty(type.getTypeTitlePath())) {
            type.setTypeTitlePath(urlUtility.getRequestPath(type.getTitle()));
        }

        if (file.isEmpty()) {
            type.setImage(typeOld.getImage());
            type.setOriginalFileName(typeOld.getOriginalFileName());
            type.setContentType(typeOld.getContentType());
            type.setSize(typeOld.getSize());
        } else {
            contents = IOUtils.toByteArray(file.getInputStream());
            type.setImage(contents);
            type.setOriginalFileName(file.getOriginalFilename());
            type.setContentType(file.getContentType());
            type.setSize(file.getSize());
        }

        typeService.save(type);

        log.info("Product was updated. Product id {}, user updated {}, day updated {}.",
                type.getId(),
                principal.getName(),
                LocalDateTime.now()
        );

        return "redirect:typeInfo?id="+type.getId();
    }

    @PostMapping(value="/remove")
    public String remove(@ModelAttribute("id") String id, Model model) {
        if (Strings.isNullOrEmpty(id)) {
            return "redirect:/";
        }
        typeService.removeType(Long.parseLong(id.substring(8)));
        model.addAttribute("typeList", typeService.findAll());

        return "redirect:/type/typeList";
    }


}
