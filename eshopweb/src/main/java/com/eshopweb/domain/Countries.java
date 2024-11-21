package com.eshopweb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-28
 */
@Data
@NoArgsConstructor
@Entity
public class Countries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String country;

    private String code;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
