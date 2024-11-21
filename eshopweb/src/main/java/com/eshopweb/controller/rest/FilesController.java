package com.eshopweb.controller.rest;

import com.eshopweb.domain.UploadFile;
import com.eshopweb.service.DBFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 13/05/2020
 */
@Slf4j
@RestController
public class FilesController {

    @Autowired
    private DBFileService dbFileService;

    @GetMapping("/file/{id}")
    public void displayFile(@PathVariable("id") String id, HttpServletResponse response)  {
        try{
            getFiles(response, id);
        }catch(Exception e){
            log.error("Images of type are not display: {} ", e.getMessage());
        }
    }

    private void getFiles(HttpServletResponse response, String id) throws IOException {
        UploadFile fileById = dbFileService.findFileById(id);

        if (fileById == null) {
            throw new FileNotFoundException();
        }

        response.setContentType(fileById.getContentType());
        OutputStream oImage = response.getOutputStream();
        if (Objects.nonNull(fileById.getFile())) {
            oImage.write(fileById.getFile());
            getFlush(oImage);
        }
    }

    private void getFlush(OutputStream oImage) throws IOException {
        oImage.flush();
        oImage.close();
    }

}
