package com.eshopweb.domain;

import com.eshopweb.domain.dto.ProductDto;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-09-28
 */
@Data
@Entity
public class Banners {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String bannerId;

    private String title;

    private boolean active;

    private String description;

    private LocalDateTime created;
    private String userCreated;
    private LocalDateTime updated;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    private List<ProductDto> productDtoList;

}
