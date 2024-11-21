package com.eshopweb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-01-26
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Navigation3 {

    private Long id;

    private String productName;

    private String productNamePath;

    private long typeId;

}
