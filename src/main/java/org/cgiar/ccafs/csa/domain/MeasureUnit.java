package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * The persistent class for the units database table.
 * 
 */
@Entity
@Table(schema = "public", name="units")
public class MeasureUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UNITS_ID_GENERATOR", sequenceName="UNITS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNITS_ID_GENERATOR")
	private Integer id;

	protected String name;
	
	private String options;
	
	@Transient protected List<String> optionsList;
	
	private String symbols;
	
	@Transient protected List<String> symbolsList;

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getOptionsList() {
		if (this.optionsList == null) {
			this.optionsList = new ArrayList<String>();
		}
		return this.optionsList;
	}

	public void addOption(String option) {
		getOptionsList().add(option);
	}

	public List<String> getSymbolsList() {
		if (this.symbolsList == null) {
			this.symbolsList = new ArrayList<String>();
		}
		return this.symbolsList;
	}

	public void addSymbol(String symbol) {
		getSymbolsList().add(symbol);
	}
	
	@PrePersist
	@PreUpdate
	private void recordSaved() {
		options = StringUtils.join(getOptionsList(), ";");
		symbols = StringUtils.join(getSymbolsList(), ";");
	}
	
	@PostLoad
	private void recordLoaded() {
		if (options != null && options.trim().length() == 0) {
	        String[] data = options.split(";");
	        optionsList = Arrays.asList(data);
		}
		
		if (symbols != null && symbols.trim().length() == 0) {
	        String[] data = symbols.split(";");
	        symbolsList = Arrays.asList(data);
		}
	}

}