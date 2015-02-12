package org.cgiar.ccafs.csa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the regions database table.
 */
@Entity
@Table(name = "regions")
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String code;

    private String name;

    @OneToMany(mappedBy = "region")
    private List<Country> countries;

    @OneToMany(mappedBy = "region")
    private List<FarmingSystem> farmingSystems;

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

    public List<Country> getCountries() {
        if (this.countries == null) {
            this.countries = new ArrayList<>();
        }
        return this.countries;
    }

    public Country addCountry(Country country) {
        getCountries().add(country);
        country.setRegion(this);

        return country;
    }

    public Country removeCountry(Country country) {
        getCountries().remove(country);
        country.setRegion(null);

        return country;
    }

    public List<FarmingSystem> getFarmingSystems() {
        if (this.farmingSystems == null) {
            this.farmingSystems = new ArrayList<>();
        }
        return this.farmingSystems;
    }

    public FarmingSystem addCountry(FarmingSystem farmingSystem) {
        getFarmingSystems().add(farmingSystem);
        farmingSystem.setRegion(this);

        return farmingSystem;
    }

    public FarmingSystem removeCountry(FarmingSystem farmingSystem) {
        getFarmingSystems().remove(farmingSystem);
        farmingSystem.setRegion(null);

        return farmingSystem;
    }
}
