package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the languages database table.
 * 
 */
@Entity
@Table(schema = "public", name="languages")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	protected String code;

	@Column(name="english_name")
	protected String englishName;

	@Column(name="original_name")
	protected String originalName;

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