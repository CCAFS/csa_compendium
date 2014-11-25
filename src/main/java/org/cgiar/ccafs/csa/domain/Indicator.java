package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the indicators database table.
 * 
 */
@Entity
@Table(schema = "public", name="indicators")
public class Indicator extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INDICATORS_ID_GENERATOR", sequenceName="INDICATORS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INDICATORS_ID_GENERATOR")
	private Integer id;

	@OneToMany(mappedBy="indicator")
	protected List<IndicatorPillar> pillars;
	
	@Override
	public Integer getId() {
		return this.id;
	}

	public List<IndicatorPillar> getPillars() {
		return this.pillars;
	}

	public IndicatorPillar addPillar(IndicatorPillar pillar) {
		getPillars().add(pillar);
		pillar.setIndicator(this);

		return pillar;
	}

	public IndicatorPillar removePillar(IndicatorPillar pillar) {
		getPillars().remove(pillar);
		pillar.setIndicator(null);

		return pillar;
	}
}