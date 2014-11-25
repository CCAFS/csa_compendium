package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the implementations database table.
 * 
 */
@Entity
@Table(schema = "public", name="treatments")
public class Treatment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TREATMENTS_ID_GENERATOR", sequenceName="TREATMENTS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TREATMENTS_ID_GENERATOR")
	private Integer id;
	
	@Column(name = "control_for_treatments")
	protected boolean controlForTreatments;
	
	@Column(name = "block_number")
	protected int blockNumber;

	@ManyToMany
	@JoinTable(
		name="treatment_production_systems"
		, joinColumns={
			@JoinColumn(name="treatment_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="production_system_id")
			}
		)
	protected List<ProductionSystem> productionSystems;
	
	@ManyToOne
	@JoinColumn(name="practice_id")
	protected Practice practice;

	@ManyToOne
	@JoinColumn(name="control_id")
	protected Treatment control;
	
	@ManyToOne
	@JoinColumn(name="experiment_id")
	protected ExperimentArticle experimentArticle;

	@OneToMany(mappedBy="treatment")
	protected List<Outcome> outcomes;

	public Integer getId() {
		return this.id;
	}
	
	public boolean isControlForTreatments() {
		return controlForTreatments;
	}

	public void setControlForTreatments(boolean controlForTreatments) {
		this.controlForTreatments = controlForTreatments;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public Treatment getControl() {
		return control;
	}

	public void setControl(Treatment control) {
		this.control = control;
	}

	public List<ProductionSystem> getAgriculturalResources() {
		return productionSystems;
	}

	public Practice getPractice() {
		return this.practice;
	}

	public void setPractice(Practice practice) {
		this.practice = practice;
	}

	public ExperimentArticle getExperiment() {
		return experimentArticle;
	}

	public void setExperiment(ExperimentArticle experimentArticle) {
		this.experimentArticle = experimentArticle;
	}

	public List<Outcome> getOutcomes() {
		return this.outcomes;
	}

	public Outcome addOutcome(Outcome outcome) {
		getOutcomes().add(outcome);
		outcome.setTreatment(this);

		return outcome;
	}

	public Outcome removeOutcome(Outcome outcome) {
		getOutcomes().remove(outcome);
		outcome.setTreatment(null);

		return outcome;
	}

}