package com.eshopweb.service.impl;

import com.eshopweb.domain.Category;
import com.eshopweb.domain.Countries;
import com.eshopweb.domain.CustomSettings;
import com.eshopweb.domain.Navigation0;
import com.eshopweb.domain.Navigation1;
import com.eshopweb.domain.Navigation2;
import com.eshopweb.domain.Navigation3;
import com.eshopweb.domain.Product;
import com.eshopweb.domain.SubCategory;
import com.eshopweb.domain.Type;
import com.eshopweb.domain.VideoBanner;
import com.eshopweb.domain.dto.ProductDto;
import com.eshopweb.service.CategoryService;
import com.eshopweb.service.CountriesService;
import com.eshopweb.service.CustomSettingsService;
import com.eshopweb.service.ProductService;
import com.eshopweb.service.SubCategoryService;
import com.eshopweb.service.TypeService;
import com.eshopweb.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-23
 */
@Component
@ManagedResource(objectName = "bean:name=dataSlowDataAccessor")
@Slf4j
public class DataSlowDataAccessor implements ServletContextAware {

    private static final String DEPLOYMENT_APPLICATION = "deployment.application";

    private static String app =  System.getProperty(DEPLOYMENT_APPLICATION);

    @Autowired
    private TypeService typeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CountriesService countriesService;

    @Autowired
    private CustomSettingsService customSettingsService;

    @Autowired
    private VideoService videoService;

    private List<Type> typeList;
    private List<Category> categoryList;
    private List<SubCategory> subCategoryList;
    private List<Product> productList;
    private List<VideoBanner> videoBannerList;
    private List<Countries> countriesList;
    private Map<String, Navigation0> navigation0Map;
    private List<ProductDto> seoListByProduct;
    private Optional<CustomSettings> oneByActiveCustomSettings;

    @PostConstruct
    private void init() {
        getLoadNavigation(getApp());
        getData();
    }

    /**
     * Is executed periodically every 120 000 millis.
     */
    @ManagedOperation
    @Scheduled(fixedRate = 240_000, initialDelay = 240_000)
    private void refreshDataHolders() {
        getData();
    }

    /**
     * Is executed periodically every 480 000 millis.
     */
    @ManagedOperation
    @Scheduled(fixedRate = 480_000, initialDelay = 480_000)
    private void refreshNavigationHolders() {
        getLoadNavigation(app);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<VideoBanner> getVideoBannerList() {
        return videoBannerList;
    }

    public List<ProductDto> getSeoListByProduct() {
        return seoListByProduct;
    }

    public List<Countries> getCountriesList() {
        return countriesList;
    }

    public Map<String, Navigation0> getNavigationMap() {
        return navigation0Map;
    }

    public Optional<CustomSettings> getOneByActiveCustomSettings() {
        return oneByActiveCustomSettings;
    }

    private void getData() {
        log.info("loaded new data from db - {}", LocalDateTime.now());
        typeList = typeService.findAll();
        categoryList = categoryService.findAll();
        subCategoryList = subCategoryService.findAll();
        productList = productService.findAll();
        countriesList = countriesService.findAllByActive();
        videoBannerList = videoService.findAll();
        seoListByProduct = productService.findAllSeoByProduct(true, getApp());
        oneByActiveCustomSettings = customSettingsService.getOneByActiveCustomSettings();
    }

    private void getLoadNavigation(String app) {
        log.info("loaded new navigation from db - {}", LocalDateTime.now());
        navigation0Map = getNavigationOrderByCategory(getNavigation(app));
    }

    private Map<String, Navigation0> getNavigationOrderByCategory(Map<String, Navigation0> navigation) {
        return navigation.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue().getOrderby()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Map<String, Navigation0> getNavigation(String app) {
        Map<String, Navigation0> navigation = new HashMap<>();

        categoryService.findAllByApp(app).forEach(category -> {
                List<Navigation1> navigation1List = new ArrayList<>();

                subCategoryService.findAll().forEach(subCategory -> {
                    if (category.getId().equals(subCategory.getCategoryId())) {
                        Navigation1 navigation1 = Navigation1.builder()
                            .id(subCategory.getId())
                            .categoryId(subCategory.getCategoryId())
                            .title(subCategory.getTitle())
                            .subCategorytitlePath(subCategory.getSubCategorytitlePath())
                            .build();


                        List<Navigation2> navigation2List = new ArrayList<>();

                        typeService.findAll().forEach(type -> {
                            if (subCategory.getId().equals(type.getSubCategoryId())) {
                                Navigation2 navigation2 = Navigation2.builder()
                                    .id(type.getId())
                                    .subCategoryId(subCategory.getCategoryId())
                                    .categoryId(subCategory.getCategoryId())
                                    .title(type.getTitle())
                                    .typeTitlePath(type.getTypeTitlePath())
                                    .build();
                                navigation2List.add(navigation2);
                                navigation1.setNavigation2List(navigation2List);

                                List<Navigation3> navigation3List = new ArrayList<>();

                                productService.findAll().forEach(product -> {
                                    if (type.getId() == product.getTypeId()) {
                                        Navigation3 navigation3 = Navigation3.builder()
                                            .id(product.getId())
                                            .typeId(product.getTypeId())
                                            .productName(product.getProductName())
                                            .productNamePath(product.getProductNamePath())
                                            .build();
                                        navigation3List.add(navigation3);
                                        navigation2.setNavigation3List(navigation3List);
                                    }
                                });
                            }
                        });

                        navigation1List.add(navigation1);
                    }
                    if (category.isMainNavigation()) {
                        Navigation0 navigation0 = Navigation0.builder()
                                .id(category.getId())
                                .title(category.getTitle())
                                .categoryTitlePath(category.getCategoryTitlePath())
                                .orderby(category.getOrderBy())
                                .navigation1List(navigation1List)
                                .build();

                        navigation.put(category.getCategoryTitlePath(), navigation0);
                    }
                });
        });

        return navigation;
    }

    public static String getApp() {
        return app;
    }

}
