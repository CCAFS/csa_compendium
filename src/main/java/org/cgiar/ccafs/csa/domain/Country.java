package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(schema = "public", name="countries")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	protected String code;

	protected String name;

	@ManyToOne
	@JoinColumn(name="region_code")
	protected Region region;

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