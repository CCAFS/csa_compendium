package org.cgiar.ccafs.csa.domain.workshops;

import java.io.Serializable;

import javax.persistence.*;

import org.cgiar.ccafs.csa.domain.Practice;


/**
 * The persistent class for the prioritizations database table.
 * 
 */
@Entity
@Table(schema = "workshops", name="prioritizations")
public class Prioritization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRIORITIZATIONS_ID_GENERATOR", sequenceName="WORKSHOPS.PRIORITIZATIONS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRIORITIZATIONS_ID_GENERATOR")
	private Integer id;

	protected float score;

	@ManyToOne
	@JoinColumn(name="portfolio_id")
	protected Portfolio portfolio;

	@ManyToOne
	@JoinColumn(name="practice_id")
	protected Practice practice;

	@ManyToOne
	@JoinColumn(name="workshop_id")
	protected Workshop workshop;

	public Integer getId() {
		return this.id;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Practice getPractice() {
		return this.practice;
	}

	public void setPractice(Practice practice) {
		this.practice = practice;
	}

	public Workshop getWorkshop() {
		return this.workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

}