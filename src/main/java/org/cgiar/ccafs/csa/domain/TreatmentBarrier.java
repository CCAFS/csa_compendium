package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the barriers database table.
 * 
 */
@Entity
@Table(schema = "public", name="treatment_barriers")
public class TreatmentBarrier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TREATMENT_BARRIERS_ID_GENERATOR", sequenceName="TREATMENT_BARRIERS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TREATMENT_BARRIERS_ID_GENERATOR")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="treatment_id")
	protected Treatment treatment;

	@ManyToOne
	@JoinColumn(name="barrier_id")
	protected Barrier barrier;

	protected float cost;

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public Barrier getBarrier() {
		return barrier;
	}

	public void setBarrier(Barrier barrier) {
		this.barrier = barrier;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	

}