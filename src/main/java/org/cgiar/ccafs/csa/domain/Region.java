package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(schema = "public", name="regions")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	protected String code;

	protected String name;

	@OneToMany(mappedBy="region")
	protected List<Country> countries;

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
			this.countries = new ArrayList<Country>();
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
	
	@Override
	public String toString() {
		return name;
	}
}