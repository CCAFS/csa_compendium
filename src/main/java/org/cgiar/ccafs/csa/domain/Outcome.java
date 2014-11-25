package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;


/**
 * The persistent class for the outcomes database table.
 * 
 */
@Entity
@Table(schema = "public", name="outcomes")
public class Outcome implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="OUTCOMES_ID_GENERATOR", sequenceName="OUTCOMES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OUTCOMES_ID_GENERATOR")
	private Integer id;
		
	@Column(name="start_date")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
	protected LocalDate startDate;
	
	
	@Column(name="end_date")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
	protected LocalDate endDate;

	@Column(name="initial_value")
	protected float initialValue;
	
	@Column(name="final_value")
	protected float finalValue;

	protected String result;
	
	protected int change;
	
	@ManyToOne
	@JoinColumn(name="treatment_id")
	protected Treatment treatment;

	@ManyToOne
	@JoinColumn(name="indicator_id")
	protected Indicator indicator;

	@ManyToOne
	@JoinColumn(name="unit_id")
	protected MeasureUnit measureUnit;

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public float getInitialValue() {
		return this.initialValue;
	}

	public void setInitialValue(float initialValue) {
		this.initialValue = initialValue;
	}
	
	public float getFinalValue() {
		return this.finalValue;
	}

	public void setFinalValue(float finalValue) {
		this.finalValue = finalValue;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public Indicator getIndicator() {
		return this.indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	public MeasureUnit getUnit() {
		return this.measureUnit;
	}

	public void setUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

}