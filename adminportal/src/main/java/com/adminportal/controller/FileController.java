package com.adminportal.controller;

import com.adminportal.domain.DBFile;
import com.adminportal.domain.UploadFile;
import com.adminportal.domain.UploadFileResponse;
import com.adminportal.service.impl.DBFileStorageService;
import com.adminportal.utility.UrlUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-02-24
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class FileController {

    private final DBFileStorageService dbFileStorageService;
    private final UrlUtility urlUtility;

    private UploadFileResponse uploadFile(MultipartFile file, Long productId) {
        DBFile dbFile = dbFileStorageService.storeFile(file, productId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    private boolean isFilesDuplicate(MultipartFile file) {
        String[] arr = StringUtils.split(file.getOriginalFilename().toLowerCase(), ".");
        String firstPart = arr[0];
        return dbFileStorageService.isFilesDuplicate(urlUtility.getRequestPath(firstPart));
    }

    @ResponseBody
    @PostMapping("/uploadMultipleFiles/{productId}")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                                        @PathVariable("productId") Long productId) {
        return Arrays.stream(files)
                .filter(file -> !isFilesDuplicate(file))
                .map(file -> uploadFile(file, productId))
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @GetMapping("/settings/files")
    public String getFiles(Model model) {
        List<UploadFile> uploadFiles = dbFileStorageService.findAll();
        model.addAttribute("files", uploadFiles);
        return "settings/files/list";
    }

    @GetMapping("/settings/files/add")
    public String addFiles(Model model) {
        UploadFile uploadFile = new UploadFile();
        model.addAttribute("uploadFile", uploadFile);
        return "settings/files/add";
    }

    @PostMapping("/settings/files/add")
    public String addFiles(@RequestParam("file") MultipartFile file) {
        if (file != null && file.getSize() != 0) {
            log.info("MultipartFile is empty!");
            dbFileStorageService.storeFile(file);
        }
        return "redirect:/settings/files";
    }

    @GetMapping("/file/{id}")
    public void getDisplayFile(@PathVariable("id") String id , HttpServletResponse response)  {
        try{
            getFiles(response, id);
        }catch(Exception e){
            log.error("Images of category are not display: {} ", e.getMessage());
        }
    }

    @PostMapping("/settings/removefile")
    public String remove(@ModelAttribute("id") String id) {
        if (Objects.isNull(id)) {
            return "redirect:/settings/files";
        }
        dbFileStorageService.removeFileOne(id);

        return "redirect:/product/productList";
    }

    private void getFiles(HttpServletResponse response, String id) throws IOException {
        UploadFile fileById = dbFileStorageService.findFileById(id);
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
