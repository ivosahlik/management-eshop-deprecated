package com.eshopweb.controller;

import com.eshopweb.domain.Category;
import com.eshopweb.domain.Product;
import com.eshopweb.domain.SubCategory;
import com.eshopweb.domain.Type;
import com.eshopweb.domain.dto.ProductDto;
import com.eshopweb.exceptions.NotFoundException;
import com.eshopweb.service.CategoryService;
import com.eshopweb.service.DBFileService;
import com.eshopweb.service.ProductService;
import com.eshopweb.service.SubCategoryService;
import com.eshopweb.service.TypeService;
import com.eshopweb.service.impl.DataSlowDataAccessor;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-01-27
 */
@Slf4j
@Controller
public class ProductController {


    public static final String EMPTY_LIST = "emptyList";
    public static final String PRODUCT_LIST = "productList";
    public static final String LINK_TO_PRODUCT_DETAIL = "linkToProductDetail";
    public static final String URL_FORWARD = "urlForward";
    public static final String CATEGORY = "category/";
    public static final String PRODUCT = "/product/";
    public static final String SUB_CATEGORY = "/subCategory/";
    public static final String TYPE = "/type/";

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private DBFileService dbFileService;

    @GetMapping("/categories")
    public String listCategories(Model model, HttpServletRequest request) {

        List<Category> categoryList = categoryService.findAllActive();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("linkToCategory", "/category/");

        return "category/categoryList";
    }


    @GetMapping("/products/all")
    public String productAllList(Model model, HttpServletRequest request) {
        log.info("Get mapping - {}", request.getRequestURI());
        List<ProductDto> productDtoList = productService.findAllSeoByProduct(true, DataSlowDataAccessor.getApp());
        if (productDtoList.isEmpty()) {
            model.addAttribute(EMPTY_LIST, true);
        }
        model.addAttribute(PRODUCT_LIST, productDtoList);
        model.addAttribute(LINK_TO_PRODUCT_DETAIL, request.getServletPath() + PRODUCT);
        model.addAttribute(URL_FORWARD, request.getRequestURI());

        return "product/productAllList";
    }

    @GetMapping("category/{categorySeo}")
    public String subCategoryList(@PathVariable("categorySeo") String categorySeo,
                                  Model model, HttpServletRequest request) {

        Category categoryByCategorySeo = categoryService.findOneByCategorySeo(categorySeo);
        if (!categoryByCategorySeo.isActive()) {
            throw new NotFoundException("Not Found!");
        }

        List<SubCategory> subCategoryList = subCategoryService.findAllSubCategoryByCategorySeo(categorySeo, DataSlowDataAccessor.getApp());
        model.addAttribute("subCategoryList", subCategoryList);

        getBreadcrumb(model, categorySeo, null, null, null);
        model.addAttribute("linkToSubCategory", request.getServletPath() + SUB_CATEGORY);

        if (subCategoryList.isEmpty()) {
            Category category = categoryService.findOneByCategorySeo(categorySeo);
            final List<Product> allProductsByCategoryId = productService.findAllProductsByCategoryId(category.getId());

            if (!allProductsByCategoryId.isEmpty()) {
                model.addAttribute(PRODUCT_LIST, allProductsByCategoryId);
                model.addAttribute(LINK_TO_PRODUCT_DETAIL, request.getServletPath() + PRODUCT);
                return "product/productList";
            } else {
                model.addAttribute("category", category);
                return "category/categoryOne";
            }
        }
        return "subCategory/subCategoryList";
    }

    @GetMapping("category/{categorySeo}/subCategory/{subCategorySeo}")
    public String typeList(@PathVariable("categorySeo") String categorySeo,
                           @PathVariable("subCategorySeo") String subCategorySeo,
                           Model model, HttpServletRequest request) {

        Category category = categoryService.findOneByCategorySeo(categorySeo);
        if (!category.isActive()) {
            throw new NotFoundException("Not Found!");
        }

        List<Type> typeList = typeService.findAllTypesBySubCategorySeo(subCategorySeo);
        model.addAttribute("typeList", typeList);

        getBreadcrumb(model, categorySeo, subCategorySeo, null, null);
        model.addAttribute("linkToProducts", request.getServletPath() + TYPE);

        return "type/typeList";
    }

    @GetMapping("category/{categorySeo}/subCategory/{subCategorySeo}/type/{typeSeo}")
    public String productList(@PathVariable("categorySeo") String categorySeo,
                           @PathVariable("subCategorySeo") String subCategorySeo,
                           @PathVariable("typeSeo") String typeSeo,
                           Model model, HttpServletRequest request) throws Exception {

        Category category = categoryService.findOneByCategorySeo(categorySeo);
        if (!category.isActive()) {
            throw new NotFoundException("Not Found!");
        }

        List<Product> productList = productService.findAllProductsByType(typeSeo);
        if (productList.isEmpty()) {
            model.addAttribute(EMPTY_LIST, true);
        }
        model.addAttribute(PRODUCT_LIST, productList);
        model.addAttribute(LINK_TO_PRODUCT_DETAIL, request.getServletPath() + PRODUCT);
        model.addAttribute(URL_FORWARD, request.getRequestURI());

        getBreadcrumb(model, categorySeo, subCategorySeo, typeSeo, null);

        return "product/productList";
    }

    @GetMapping({
            "category/{categorySeo}/subCategory/{subCategorySeo}/type/{typeSeo}/product/{productSeo}",
            "category/{categorySeo}/product/{productSeo}"
    })
    public String productDetail(@PathVariable("categorySeo") String categorySeo,
                                @PathVariable("productSeo") String productSeo,
                                @PathVariable(value = "subCategorySeo", required = false) String subCategorySeo,
                                @PathVariable(value = "typeSeo", required = false) String typeSeo,
                                Model model, HttpServletRequest request) {

        Category category = categoryService.findOneByCategorySeo(categorySeo);
        if (!category.isActive()) {
            throw new NotFoundException("Not Found!");
        }

        Product product = productService.findOneByProduct(productSeo);

        if (Objects.isNull(product)) {
            return "redirect:/";
        }

        model.addAttribute("qtyNumber", product.getInStockNumber());
        model.addAttribute("product", product);
        model.addAttribute("mapPdfs", dbFileService.findAllDBFilesByProductId(product.getId(), request));
        model.addAttribute(URL_FORWARD, CATEGORY +categorySeo+ SUB_CATEGORY +subCategorySeo+ TYPE +typeSeo+ PRODUCT +productSeo);

        getBreadcrumb(model, categorySeo, subCategorySeo, typeSeo, productSeo);

        return "product/productDetail";
    }

    @PostMapping("/searchProduct")
    public String searchProduct(@ModelAttribute("keyword") String keyword,
                                Principal principal,
                                Model model,
                                HttpServletRequest request) {

        List<ProductDto> productDtoList = productService.findAllByKeyword(keyword, DataSlowDataAccessor.getApp());
        if (productDtoList.isEmpty()) {
            model.addAttribute(EMPTY_LIST, true);
        }
        model.addAttribute(PRODUCT_LIST, productDtoList);
        model.addAttribute(LINK_TO_PRODUCT_DETAIL, request.getServletPath() + PRODUCT);
        model.addAttribute(URL_FORWARD, request.getRequestURI());

        return "product/productAllList";
    }

    private void getBreadcrumb(Model model, String categorySeo, String subCategorySeo, String typeSeo, String productSeo) {

        String typeLinkSeo = "";
        String productLinkSeo = "";
        String subCategoryLinkSeo = "";
        String categoryLinkSeo = "";

        if (!Strings.isNullOrEmpty(categorySeo)) {
            model.addAttribute("categorySeo", categoryService.findOneByCategorySeo(categorySeo).getTitle());
            categoryLinkSeo = categoryService.findOneByCategorySeo(categorySeo).getCategoryTitlePath();
            model.addAttribute("categoryLinkSeo", CATEGORY + categoryLinkSeo);
        }
        if (!Strings.isNullOrEmpty(subCategorySeo)) {
            model.addAttribute("subCategorySeo", subCategoryService.findOneBySubCategorySeo(subCategorySeo).getTitle());
            subCategoryLinkSeo = subCategoryService.findOneBySubCategorySeo(subCategorySeo).getSubCategorytitlePath();
            model.addAttribute("subCategoryLinkSeo", CATEGORY + categoryLinkSeo + SUB_CATEGORY + subCategoryLinkSeo);
        }
        if (!Strings.isNullOrEmpty(typeSeo)) {
            model.addAttribute("typeSeo", typeService.findOneByTypeSeo(typeSeo).getTitle());
            typeLinkSeo = typeService.findOneByTypeSeo(typeSeo).getTypeTitlePath();
            model.addAttribute("typeLinkSeo", CATEGORY + categoryLinkSeo + SUB_CATEGORY + subCategoryLinkSeo + TYPE + typeLinkSeo);
        }
        if (!Strings.isNullOrEmpty(productSeo)) {
            model.addAttribute("productSeo", productService.findOneByProductSeo(productSeo).getProductName());
            productLinkSeo = productService.findOneByProductSeo(productSeo).getProductNamePath();
            model.addAttribute("productLinkSeo", CATEGORY + categoryLinkSeo + SUB_CATEGORY + subCategoryLinkSeo + TYPE + typeLinkSeo + PRODUCT + productLinkSeo);
        }
    }
}
