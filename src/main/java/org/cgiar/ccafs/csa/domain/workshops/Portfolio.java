package org.cgiar.ccafs.csa.domain.workshops;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the portfolios database table.
 * 
 */
@Entity
@Table(schema = "workshops", name="portfolios")
public class Portfolio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PORTFOLIOS_ID_GENERATOR", sequenceName="WORKSHOPS.PORTFOLIOS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORTFOLIOS_ID_GENERATOR")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="workshop_id")
	protected Workshop workshop;

	@OneToMany(mappedBy="portfolio")
	protected List<Prioritization> prioritizations;

	public Integer getId() {
		return this.id;
	}

	public Workshop getWorkshop() {
		return this.workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

	public List<Prioritization> getPrioritizations() {
		return this.prioritizations;
	}

	public void setPrioritizations(List<Prioritization> prioritizations) {
		this.prioritizations = prioritizations;
	}

	public Prioritization addPrioritization(Prioritization prioritization) {
		getPrioritizations().add(prioritization);
		prioritization.setPortfolio(this);

		return prioritization;
	}

	public Prioritization removePrioritization(Prioritization prioritization) {
		getPrioritizations().remove(prioritization);
		prioritization.setPortfolio(null);

		return prioritization;
	}

}