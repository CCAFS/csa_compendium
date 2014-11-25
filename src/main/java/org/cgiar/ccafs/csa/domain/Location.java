package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the locations database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Location implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCATIONS_ID_GENERATOR", sequenceName="LOCATIONS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATIONS_ID_GENERATOR")
	private Integer id;

	protected float latitude;

	protected float longitude;
	
	protected float altitude;
	
	protected String place;

	@ManyToOne
	@JoinColumn(name="country_code")
	protected Country country;

	public Integer getId() {
		return this.id;
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