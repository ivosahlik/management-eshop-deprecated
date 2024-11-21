package com.adminportal.utility;

import org.springframework.stereotype.Component;

import java.text.Normalizer;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-01-12
 */
@Component
public class UrlUtility {

    public String getRequestPath(String path) {
        return Normalizer
                .normalize(path, Normalizer.Form.NFD)
                .trim()
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9 -]", "")
                .replaceAll("\\s{1,}", "-")
                .toLowerCase();
    }

}
