package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the initial_conditions database table.
 * 
 */
@Entity
@Table(schema = "public", name="initial_conditions")
public class InitialCondition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INITIAL_CONDITIONS_ID_GENERATOR", sequenceName="INITIAL_CONDITIONS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INITIAL_CONDITIONS_ID_GENERATOR")
	private Integer id;

	protected String state;

	protected float value;

	@ManyToOne
	@JoinColumn(name="condition_id")
	protected Condition condition;

	@ManyToOne
	@JoinColumn(name="experiment_id")
	protected ExperimentArticle experimentArticle;

	@ManyToOne
	@JoinColumn(name="unit_id")
	protected MeasureUnit measureUnit;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Condition getCondition() {
		return this.condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public ExperimentArticle getExperiment() {
		return this.experimentArticle;
	}

	public void setExperiment(ExperimentArticle experimentArticle) {
		this.experimentArticle = experimentArticle;
	}

	public MeasureUnit getMeasureUnit() {
		return this.measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

}