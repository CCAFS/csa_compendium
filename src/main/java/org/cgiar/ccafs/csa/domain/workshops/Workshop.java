package org.cgiar.ccafs.csa.domain.workshops;

import org.cgiar.ccafs.csa.domain.Location;
import org.cgiar.ccafs.csa.domain.Treatment;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

/**
 * The persistent class for the workshops database table.
 */
@Entity
@Table(name = "workshops")
public class Workshop implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DATE)
    @Column(name = "updated_date")
    private Date updatedDate;

    @OneToMany(mappedBy = "workshop")
    private List<WorkshopComment> comments;

    @OneToMany(mappedBy = "workshop")
    private List<WorkshopPortfolio> portfolios;

    @OneToMany(mappedBy = "workshop")
    private List<WorkshopPrioritization> prioritizations;

    @OneToMany(mappedBy = "workshop")
    private List<WorkshopPillar> workshopPillars;

    @OneToMany(mappedBy = "originalWorkshop")
    private List<WorkshopBarrier> newBarriers;

    @OneToMany(mappedBy = "originalWorkshop")
    private List<WorkshopExperiment> newExperiments;

    @OneToMany(mappedBy = "originalWorkshop")
    private List<WorkshopSubIndicator> newIndicators;

    @OneToMany(mappedBy = "originalWorkshop")
    private List<WorkshopPractice> newPractices;

    @OneToMany(mappedBy = "originalWorkshop")
    private List<WorkshopSynergy> newSynergies;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany
    @JoinTable(
            name = "workshop_used_treatments"
            , joinColumns = {
            @JoinColumn(name = "workshop_id")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "treatment_id")
    }
    )
    private List<Treatment> linkedTreatments;

    public Integer getId() {
        return this.id;
    }

    public LocalDate getStartDate() {
        return new LocalDate(this.startDate);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate.toDate();
    }

    public LocalDate getUpdatedDate() {
        return new LocalDate(this.updatedDate);
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate.toDate();
    }

    public List<WorkshopComment> getComments() {
        return this.comments;
    }

    public void setComments(List<WorkshopComment> comments) {
        this.comments = comments;
    }

    public WorkshopComment addComment(WorkshopComment comment) {
        getComments().add(comment);
        comment.setWorkshop(this);

        return comment;
    }

    public WorkshopComment removeComment(WorkshopComment comment) {
        getComments().remove(comment);
        comment.setWorkshop(null);

        return comment;
    }

    public List<WorkshopPortfolio> getPortfolios() {
        return this.portfolios;
    }

    public void setPortfolios(List<WorkshopPortfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public WorkshopPortfolio addPortfolio(WorkshopPortfolio portfolio) {
        getPortfolios().add(portfolio);
        portfolio.setWorkshop(this);

        return portfolio;
    }

    public WorkshopPortfolio removePortfolio(WorkshopPortfolio portfolio) {
        getPortfolios().remove(portfolio);
        portfolio.setWorkshop(null);

        return portfolio;
    }

    public List<WorkshopPrioritization> getPrioritizations() {
        return this.prioritizations;
    }

    public void setPrioritizations(List<WorkshopPrioritization> prioritizations) {
        this.prioritizations = prioritizations;
    }

    public WorkshopPrioritization addPrioritization(WorkshopPrioritization prioritization) {
        getPrioritizations().add(prioritization);
        prioritization.setWorkshop(this);

        return prioritization;
    }

    public WorkshopPrioritization removePrioritization(WorkshopPrioritization prioritization) {
        getPrioritizations().remove(prioritization);
        prioritization.setWorkshop(null);

        return prioritization;
    }

    public List<WorkshopPillar> getPillars() {
        return this.workshopPillars;
    }

    public void setWorkshopPillars(List<WorkshopPillar> workshopPillars) {
        this.workshopPillars = workshopPillars;
    }

    public WorkshopPillar addWorkshopPillar(WorkshopPillar pillar) {
        getPillars().add(pillar);
        pillar.setWorkshop(this);

        return pillar;
    }

    public WorkshopPillar removeWorkshopPillar(WorkshopPillar pillar) {
        getPillars().remove(pillar);
        pillar.setWorkshop(null);

        return pillar;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
