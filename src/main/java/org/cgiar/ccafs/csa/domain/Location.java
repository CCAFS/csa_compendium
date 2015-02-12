package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

/**
 * The persistent class for the locations database table.
 */
@Entity
@Table(name = "locations")
public class Location {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float latitude;

    private float longitude;

    private float altitude;

    private String place;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    public Integer getId() {
        return id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}