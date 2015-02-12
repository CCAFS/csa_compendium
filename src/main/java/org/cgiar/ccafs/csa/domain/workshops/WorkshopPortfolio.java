package org.cgiar.ccafs.csa.domain.workshops;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the portfolios database table.
 */
@Entity
@Table(name = "workshop_portfolios")
public class WorkshopPortfolio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    @OneToMany(mappedBy = "portfolio")
    private List<WorkshopPrioritization> prioritizations;

    public Integer getId() {
        return this.id;
    }

    public Workshop getWorkshop() {
        return this.workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public List<WorkshopPrioritization> getPrioritizations() {
        return this.prioritizations;
    }

    public void setPrioritizations(List<WorkshopPrioritization> prioritizations) {
        this.prioritizations = prioritizations;
    }

    public WorkshopPrioritization addPrioritization(WorkshopPrioritization prioritization) {
        getPrioritizations().add(prioritization);
        prioritization.setPortfolio(this);

        return prioritization;
    }

    public WorkshopPrioritization removePrioritization(WorkshopPrioritization prioritization) {
        getPrioritizations().remove(prioritization);
        prioritization.setPortfolio(null);

        return prioritization;
    }

}