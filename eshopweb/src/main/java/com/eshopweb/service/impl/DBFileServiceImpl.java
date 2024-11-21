package com.eshopweb.service.impl;

import com.eshopweb.domain.DBFile;
import com.eshopweb.domain.UploadFile;
import com.eshopweb.repository.DBFileRepository;
import com.eshopweb.repository.UploadFileRepository;
import com.eshopweb.service.DBFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-02-25
 */

@RequiredArgsConstructor
@Service
public class DBFileServiceImpl implements DBFileService {

    private final static String URL_PDF_SLASH = "/pdf/";

    private final DBFileRepository dbFileRepository;
    private final UploadFileRepository uploadFileRepository;

    @Override
    public Map<String, String> findAllDBFilesByProductId(Long productId, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        List<DBFile> dbFileList = dbFileRepository.findAllDBFilesByProductId(productId);
        String url = request.getServletPath();
        dbFileList.forEach(dbFile -> {
            String fileName = dbFile.getFileName();
            String[] fileType = dbFile.getFileType().split("/");
            map.put(fileName, url +URL_PDF_SLASH+ fileName +"."+ fileType[1]);
        });
        return map;
    }

    @Override
    public DBFile findOneByName(String fileName) {
        return dbFileRepository.findOneByName(fileName);
    }

    @Override
    public UploadFile findFileById(String id) {
        return uploadFileRepository.findOne(id);
    }


}
