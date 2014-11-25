package org.cgiar.ccafs.csa.domain.workshops;

import java.io.Serializable;

import javax.persistence.*;

import org.cgiar.ccafs.csa.domain.Pillar;


/**
 * The persistent class for the workshop_pillars database table.
 * 
 */
@Entity
@Table(schema = "workshops", name="workshop_pillars")
public class WorkshopPillar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WORKSHOP_PILLARS_ID_GENERATOR", 
		sequenceName="WORKSHOPS.WORKSHOP_PILLARS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WORKSHOP_PILLARS_ID_GENERATOR")
	private Integer id;

	@Enumerated(EnumType.STRING)
	protected Pillar pillar;

	protected float weight;

	@ManyToOne
	@JoinColumn(name="workshop_id")
	protected Workshop workshop;

	public Integer getId() {
		return this.id;
	}

	public Pillar getPillar() {
		return this.pillar;
	}

	public void setPillar(Pillar pillar) {
		this.pillar = pillar;
	}

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Workshop getWorkshop() {
		return this.workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

}