package com.adminportal.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-28
 */
@Data
public class CategoryOrder implements Serializable {

    private int categoryId;

    private  int order;

    private String title;

}
