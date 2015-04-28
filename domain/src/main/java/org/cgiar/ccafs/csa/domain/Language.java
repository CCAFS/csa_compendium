package org.cgiar.ccafs.csa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The persistent class for the languages database table.
 */
@Entity
@Table(name = "languages")
public class Language implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String code;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "original_name")
    private String originalName;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

}