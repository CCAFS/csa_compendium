package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the practice_dimensions database table.
 * 
 */
@Entity
@Table(schema = "public", name="practice_dimensions")
public class PracticeDimension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRACTICE_DIMENSIONS_ID_GENERATOR", 
		sequenceName="PRACTICE_DIMENSIONS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRACTICE_DIMENSIONS_ID_GENERATOR")
	private Integer id;

	@Enumerated(EnumType.STRING)
	protected Dimension dimension;

	protected float weight;

	@ManyToOne
	@JoinColumn(name="practice_id")
	protected Practice practice;

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

	public Practice getPractice() {
		return this.practice;
	}

	public void setPractice(Practice practice) {
		this.practice = practice;
	}

}