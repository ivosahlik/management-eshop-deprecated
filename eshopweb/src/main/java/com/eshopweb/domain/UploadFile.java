package com.eshopweb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Intellij Idea
 * Created by ivosahlik on 12/05/2020
 */

@NoArgsConstructor
@Data
@Entity
@Table
public class UploadFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    @Lob
    private byte[] file;

    private String originalFileName;

    private String contentType;

    private long size;

}

