package com.eshopweb.controller.rest;

import com.eshopweb.constants.Constants;
import com.eshopweb.domain.Type;
import com.eshopweb.service.impl.DataSlowDataAccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-01-28
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class ImageController {

    public static final String IMAGES = "images";

    @Autowired
    private DataSlowDataAccessor dataSlowDataAccessor;

    @GetMapping("/displayProductImage/{id}")
    public void getDisplayProductImage(@PathVariable("id") long id , HttpServletResponse response)  {
        try{
            getRestImageProduct(response, id);
        }catch(Exception e){
            log.error("Images of product are not display: {}", e.getMessage());
        }
    }

    @GetMapping("/displaySubCategoryImage/{id}")
    public void getDisplaySubCategoryImage(@PathVariable("id") long id , HttpServletResponse response)  {
        try{
            getRestImageSubCategory(response, id);
        }catch(Exception e){
            log.error("Images of subCategory are not display: {} ", e.getMessage());
        }
    }

    @GetMapping("/displayCategoryImage/{id}")
    public void getDisplayCategoryImage(@PathVariable("id") long id , HttpServletResponse response)  {
        try{
            getRestImageCategory(response, id);
        }catch(Exception e){
            log.error("Images of category are not display: {} ", e.getMessage());
        }
    }

    @GetMapping("/displayTypeImage/{id}")
    public void getDisplayTypeImage(@PathVariable("id") long id , HttpServletResponse response)  {
        try{
            getRestImageType(response, id);
        }catch(Exception e){
            log.error("Images of type are not display: {} ", e.getMessage());
        }
    }

    private void getImage(HttpServletResponse response, Type type) throws IOException {
        response.setContentType(type.getContentType());
        OutputStream oImage = response.getOutputStream();
        if (Objects.nonNull(type.getImage())) {
            oImage.write(type.getImage());
            getFlush(oImage);
        }
    }

    @Cacheable(value = Constants.CACHE_IMAGES, key = IMAGES)
    public void getRestImageCategory(HttpServletResponse response, long id) throws IOException {
        Type type = new Type();
        dataSlowDataAccessor.getCategoryList().forEach(res -> setData(id, type, res.getId(), res.getContentType(), res.getImage()));
        getImage(response, type);
    }

    private void setData(long id, Type type, Long id2, String contentType, byte[] image) {
        if (id2 == id) {
            type.setContentType(contentType);
            type.setImage(image);
        }
    }

    @Cacheable(value = Constants.CACHE_IMAGES, key = IMAGES)
    public void getRestImageType(HttpServletResponse response, long id) throws IOException {
        Type type = new Type();
        dataSlowDataAccessor.getTypeList().forEach(res -> setData(id, type, res.getId(), res.getContentType(), res.getImage()));
        getImage(response, type);
    }

    @Cacheable(value = Constants.CACHE_IMAGES, key = IMAGES)
    public void getRestImageSubCategory(HttpServletResponse response, long id) throws IOException {
        Type type = new Type();
        dataSlowDataAccessor.getSubCategoryList().forEach(res -> setData(id, type, res.getId(), res.getContentType(), res.getImage()));
        getImage(response, type);
    }

    @Cacheable(value = Constants.CACHE_IMAGES, key = IMAGES)
    public void getRestImageProduct(HttpServletResponse response, long id) throws IOException {
        Type type = new Type();
        dataSlowDataAccessor.getProductList().forEach(res -> setData(id, type, res.getId(), res.getContentType(), res.getImage()));
        getImage(response, type);
    }

    private void getFlush(OutputStream oImage) throws IOException {
        oImage.flush();
        oImage.close();
    }

}
