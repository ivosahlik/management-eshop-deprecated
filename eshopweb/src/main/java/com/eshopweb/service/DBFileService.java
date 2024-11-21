package com.eshopweb.service;

import com.eshopweb.domain.DBFile;
import com.eshopweb.domain.UploadFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-02-25
 */
public interface DBFileService {

    Map<String, String> findAllDBFilesByProductId(Long productId, HttpServletRequest request);

    DBFile findOneByName(String name);

    UploadFile findFileById(String id);

}
