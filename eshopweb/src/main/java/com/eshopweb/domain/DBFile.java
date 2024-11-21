package com.eshopweb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "files")
public class DBFile {
    @Id
    private String id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    private Long productId;

}
