package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the indicator_pillars database table.
 * 
 */
@Entity
@Table(schema = "public", name="indicator_pillars")
public class IndicatorPillar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INDICATOR_PILLARS_ID_GENERATOR", 
		sequenceName="INDICATOR_PILLARS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INDICATOR_PILLARS_ID_GENERATOR")
	private Integer id;

	@Enumerated(EnumType.STRING)
	protected Pillar pillar;

	protected float weight;

	@ManyToOne
	@JoinColumn(name="indicator_id")
	protected Indicator indicator;

	public Integer getId() {
		return this.id;
	}

	public Object getPillar() {
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

	public Indicator getIndicator() {
		return this.indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

}