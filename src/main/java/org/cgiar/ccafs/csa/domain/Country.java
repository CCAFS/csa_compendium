package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the countries database table.
 */
@Entity
@Table(name = "countries")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "region_code")
    private Region region;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return name;
    }
}