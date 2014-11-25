package org.cgiar.ccafs.csa.domain.workshops;

import java.io.Serializable;

import javax.persistence.*;

import org.cgiar.ccafs.csa.domain.Dimension;


/**
 * The persistent class for the workshop_dimensions database table.
 * 
 */
@Entity
@Table(schema = "workshops", name="workshop_dimensions")
public class WorkshopDimension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WORKSHOP_DIMENSIONS_ID_GENERATOR", 
		sequenceName="WORKSHOPS.WORKSHOP_DIMENSIONS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WORKSHOP_DIMENSIONS_ID_GENERATOR")
	private Integer id;

	@Enumerated(EnumType.STRING)
	protected Dimension dimension;

	protected float weight;

	@ManyToOne
	@JoinColumn(name="workshop_id")
	protected Workshop workshop;

	public Integer getId() {
		return this.id;
	}

	public Dimension getDimension() {
		return this.dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
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