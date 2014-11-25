package org.cgiar.ccafs.csa.domain.workshops;

import java.io.Serializable;

import javax.persistence.*;

import org.cgiar.ccafs.csa.domain.Treatment;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.util.List;

/**
 * The persistent class for the workshops database table.
 * 
 */
@Entity
@Table(schema = "workshops", name="workshops")
public class Workshop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WORKSHOPS_ID_GENERATOR", sequenceName="WORKSHOPS.WORKSHOPS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WORKSHOPS_ID_GENERATOR")
	private Integer id;
	
	@Column(name="start_date")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	protected DateTime startDate;
	
	@Column(name="updated_date")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	protected DateTime updatedDate;

	@OneToMany(mappedBy="workshop")
	protected List<Comment> comments;

	@OneToMany(mappedBy="workshop")
	protected List<Portfolio> portfolios;

	@OneToMany(mappedBy="workshop")
	protected List<Prioritization> prioritizations;

	@OneToMany(mappedBy="workshop")
	protected List<WorkshopDimension> workshopDimensions;

	@OneToMany(mappedBy="workshop")
	protected List<WorkshopPillar> workshopPillars;

	@ManyToMany
	@JoinTable(
		schema="workshops",
		name="workshop_treatments"
		, joinColumns={
			@JoinColumn(name="workshop_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="treatment_id")
			}
		)
	protected List<Treatment> linkedTreatments;

	@ManyToOne
	@JoinColumn(name="location_id")
	protected WorkshopLocation location;
	

	public Integer getId() {
		return this.id;
	}
	
	public DateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(DateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setWorkshop(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setWorkshop(null);

		return comment;
	}

	public List<Portfolio> getPortfolios() {
		return this.portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public Portfolio addPortfolio(Portfolio portfolio) {
		getPortfolios().add(portfolio);
		portfolio.setWorkshop(this);

		return portfolio;
	}

	public Portfolio removePortfolio(Portfolio portfolio) {
		getPortfolios().remove(portfolio);
		portfolio.setWorkshop(null);

		return portfolio;
	}

	public List<Prioritization> getPrioritizations() {
		return this.prioritizations;
	}

	public Prioritization addPrioritization(Prioritization prioritization) {
		getPrioritizations().add(prioritization);
		prioritization.setWorkshop(this);

		return prioritization;
	}

	public Prioritization removePrioritization(Prioritization prioritization) {
		getPrioritizations().remove(prioritization);
		prioritization.setWorkshop(null);

		return prioritization;
	}

	public List<WorkshopDimension> getDimensions() {
		return this.workshopDimensions;
	}

	public List<WorkshopPillar> getPillars() {
		return this.workshopPillars;
	}

	public List<Treatment> getLinkedTreatments() {
		return this.linkedTreatments;
	}

	public WorkshopLocation getLocation() {
		return this.location;
	}

	public void setLocation(WorkshopLocation location) {
		this.location = location;
	}

}