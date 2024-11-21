package com.eshopweb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-01-26
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Navigation2 {

    private Long id;

    private String title;

    private String typeTitlePath;

    private long categoryId;

    private long subCategoryId;

    List<Navigation3> navigation3List;

}
