package com.adminportal.controller;

import com.adminportal.domain.Category;
import com.adminportal.domain.Product;
import com.adminportal.domain.Type;
import com.adminportal.service.CategoryService;
import com.adminportal.service.ProductService;
import com.adminportal.service.SubCategoryService;
import com.adminportal.service.TypeService;
import com.adminportal.service.impl.DBFileStorageService;
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
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
@Controller
public class ProductController {

    private static final String CURRENT_PAGE = "currentPage";
    private static final String PRODUCT = "product";
    private static final String MESSAGE = "message";
    private static final String CATEGORY = "category";
    private static final String SUB_CATEGORY = "subCategory";
    private static final String TYPE = "type";
    private static final String REDIRECT_PRODUCT_PRODUCT_LIST = "redirect:/product/productList";

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final TypeService typeService;
    private final DBFileStorageService dbFileStorageService;
    private final UrlUtility urlUtility;

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute(CURRENT_PAGE, PRODUCT);
        Product product = new Product();
        model.addAttribute(PRODUCT, product);
        model.addAttribute(CATEGORY, categoryService.findAllActive());
        model.addAttribute(SUB_CATEGORY, subCategoryService.findAllActive());
        model.addAttribute(TYPE, typeService.findAllActive());
        return "products/addProduct";
    }

    @PostMapping("addProduct")
    public String addProductPost(@ModelAttribute(PRODUCT) Product product,
                                 Principal principal,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) throws IOException {
        if (Objects.isNull(product)) {
            log.info("Product is empty.");
            return null;
        }

        if (productService.isProductName(product.getProductName())) {
            redirectAttributes.addFlashAttribute(MESSAGE, "Product with name " + product.getProductName() + " is existing in db.");
            return "redirect:addProduct";
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute(MESSAGE, "Please select a file to upload");
            return "redirect:addProduct";
        }

        byte[] contents;
        contents = IOUtils.toByteArray(file.getInputStream());

        LocalDateTime localDateTime = LocalDateTime.now();
        String userCreated = principal.getName();

        product.setCreated(localDateTime);
        product.setUserCreated(userCreated);
        product.setUpdated(localDateTime);
        product.setUserUpdated(userCreated);

        final Type typeId = typeService.findOne(product.getTypeId());
        if (Objects.nonNull(typeId) && !Strings.isNullOrEmpty(typeId.getTitle())) {
            product.setType(typeId.getTitle());
            product.setTypePath(urlUtility.getRequestPath(typeService.findOne(product.getTypeId()).getTitle()));
        }

        final Category categoryId = categoryService.findOne(product.getCategoryId());
        if (Objects.nonNull(categoryId) && !Strings.isNullOrEmpty(categoryId.getTitle())) {
            product.setCategory(categoryId.getTitle());
            product.setCategoryPath(urlUtility.getRequestPath(typeService.findOne(product.getCategoryId()).getTitle()));
        }

        product.setProductNamePath(urlUtility.getRequestPath(product.getProductName()));

        product.setImage(contents);
        product.setOriginalFileName(file.getOriginalFilename());
        product.setContentType(file.getContentType());
        product.setSize(file.getSize());

        productService.save(product);

        return REDIRECT_PRODUCT_PRODUCT_LIST;
    }

    @GetMapping("productList")
    public String productList(Model model) {
        model.addAttribute(CURRENT_PAGE, PRODUCT);
        model.addAttribute("productList", productService.findAll());
        return "products/productList";
    }

    @GetMapping("/imageDisplays/{id}")
    public void getImage(@PathVariable("id") long id,
                         HttpServletResponse response)  {
        OutputStream oImage;

        try {
            Product product = productService.findOne(id);
            response.setContentType(product.getContentType());
            oImage=response.getOutputStream();
            oImage.write(product.getImage());
            oImage.flush();
            oImage.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/productInfo")
    public String productInfo(@RequestParam("id") Long id,
                              Model model) {
        model.addAttribute(CURRENT_PAGE, PRODUCT);
        Product product = productService.findOne(id);
        model.addAttribute(PRODUCT, product);
        return "products/productInfo";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam("id") Long id,
                                Model model) {
        model.addAttribute(CURRENT_PAGE, PRODUCT);
        Product product = productService.findOne(id);
        model.addAttribute(PRODUCT, product);
        model.addAttribute(CATEGORY, categoryService.findAllActive());
        model.addAttribute(SUB_CATEGORY, subCategoryService.findAllActive());
        model.addAttribute(TYPE, typeService.findAllActive());
        model.addAttribute("files", dbFileStorageService.findOneById(id));

        return "products/updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProductPost(@ModelAttribute(PRODUCT) Product product,
                                    @RequestParam("file") MultipartFile file,
                                    Principal principal,
                                    RedirectAttributes redirectAttributes) throws IOException {

        byte[] contents;
        Product productOld = productService.findOne(product.getId());

        if (!product.getProductName().equals(productOld.getProductName()) && productService.isProductName(product.getProductName())) {
            redirectAttributes.addFlashAttribute(MESSAGE, "Product with name " + product.getProductName() + " is existing in db.");
            return "redirect:/product/updateProduct?id=" + product.getId();
        }

        product.setCreated(productOld.getCreated());
        product.setUserCreated(productOld.getUserCreated());
        product.setUpdated(LocalDateTime.now());
        product.setUserUpdated(principal.getName());

        if (Strings.isNullOrEmpty(product.getProductNamePath())) {
            product.setProductNamePath(urlUtility.getRequestPath(product.getProductName()));
        }

        final Type typeId = typeService.findOne(product.getTypeId());
        if (Objects.nonNull(typeId) && !Strings.isNullOrEmpty(typeId.getTitle())) {
            product.setType(typeId.getTitle());
            product.setTypePath(urlUtility.getRequestPath(typeService.findOne(product.getTypeId()).getTitle()));
        }

        final Category categoryId = categoryService.findOne(product.getCategoryId());
        if (Objects.nonNull(categoryId) && !Strings.isNullOrEmpty(categoryId.getTitle())) {
            product.setCategory(categoryId.getTitle());
            product.setCategoryPath(urlUtility.getRequestPath(typeService.findOne(product.getCategoryId()).getTitle()));
        }

        if (file.isEmpty()) {
            product.setImage(productOld.getImage());
            product.setOriginalFileName(productOld.getOriginalFileName());
            product.setContentType(productOld.getContentType());
            product.setSize(productOld.getSize());
        } else {
            contents = IOUtils.toByteArray(file.getInputStream());
            product.setImage(contents);
            product.setOriginalFileName(file.getOriginalFilename());
            product.setContentType(file.getContentType());
            product.setSize(file.getSize());
        }
        productService.save(product);

        log.info("Product was updated. Product id {}, user updated {}, day updated {}.",
                product.getId(),
                principal.getName(),
                LocalDateTime.now()
        );

        return "redirect:productInfo?id="+product.getId();
    }

    @PostMapping(value="/remove")
    public String remove(@ModelAttribute("id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return "redirect:/";
        }
        productService.removeOne(Long.parseLong(id.substring(11)));

        return REDIRECT_PRODUCT_PRODUCT_LIST;
    }

    @PostMapping(value="/removefile")
    public String removeFile(@ModelAttribute("id") String id) {
        if (Objects.isNull(id)) {
            return "redirect:/";
        }
        dbFileStorageService.removeOne(id);

        return REDIRECT_PRODUCT_PRODUCT_LIST;
    }

}
