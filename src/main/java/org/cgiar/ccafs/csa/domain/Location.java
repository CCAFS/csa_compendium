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

    public void setLatitude(String latitude) {
        setLatitude(convertCoordinate(latitude.trim(), "S"));
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        setLongitude(convertCoordinate(longitude.trim(), "W"));
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

    private float convertCoordinate(String coordinate, String negativeString) {

        float[] members = new float[3];
        char[] chars = coordinate.toCharArray();
        boolean scanning = true;

        int i, n, p = 0;
        int total = 0;
        while (scanning) {
            boolean noDigits = false;
            StringBuilder part = new StringBuilder();

            for (i = 0, n = chars.length; i < n; i++) {
                char c = chars[i];
                if (Character.isDigit(c) || c == '.') {
                    if (noDigits) break;
                    part.append(c);
                } else {
                    noDigits = true;
                }
            }

            String value = part.toString();
            if (value.length() != 0 && p < 3) {
                members[p++] = Float.valueOf(value);
            }

            total += i;
            if (total < coordinate.length() - 1) {
                chars = coordinate.substring(total).toCharArray();
            } else {
                scanning = false;
            }
        }

        float degrees = members[0];
        float minutes = members[1] / 60f;
        float seconds = members[2] / 3600f;
        degrees += minutes + seconds;

        if (coordinate.endsWith(negativeString)) {
            degrees *= -1;
        }

        return degrees;
    }

}