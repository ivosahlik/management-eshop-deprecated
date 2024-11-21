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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Navigation0 {

    private Long id;

    private String title;

    private String categoryTitlePath;

    private int orderby;

    List<Navigation1> navigation1List;

}
