package com.adminportal.service.impl;

import com.adminportal.domain.DBFile;
import com.adminportal.domain.UploadFile;
import com.adminportal.exception.FileStorageException;
import com.adminportal.repository.DBFileRepository;
import com.adminportal.repository.UploadFileRepository;
import com.adminportal.utility.UrlUtility;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DBFileStorageService {

    private final DBFileRepository dbFileRepository;
    private final UploadFileRepository uploadFileRepository;
    private final UrlUtility urlUtility;

    public DBFile storeFile(MultipartFile file, Long productId) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            String[] fileNameSplit = StringUtils.split(fileName ,".");
            String firstFileName = fileNameSplit[0];

            DBFile dbFile = new DBFile(productId, urlUtility.getRequestPath(firstFileName), file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public void storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            String[] fileNameSplit = StringUtils.split(fileName ,".");
            String firstFileName = fileNameSplit[0];

            UploadFile uploadFile = new UploadFile();
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            uploadFile.setFileName(firstFileName);
            uploadFile.setFile(bytes);
            uploadFile.setOriginalFileName(file.getOriginalFilename());
            uploadFile.setContentType(file.getContentType());
            uploadFile.setSize(file.getSize());

            uploadFileRepository.save(uploadFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<UploadFile> findAll() {
        return uploadFileRepository.findAll();
    }

    public UploadFile findFileById(String id) {
        return uploadFileRepository.findOne(id);
    }

    public void removeFileOne(String id) {
        dbFileRepository.delete(id);
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findOne(fileId);
    }

    public boolean isFilesDuplicate(String name) {
        return dbFileRepository.isFilesDuplicate(name) > 0;
    }

    public List<DBFile> findOneById(Long id) {
        return dbFileRepository.findOneById(id);
    }

    public void removeOne(String id) {
        dbFileRepository.delete(id);
    }
}
