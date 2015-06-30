package org.cgiar.ccafs.csa.domain.workshop;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;

import javax.persistence.*;

/**
 * The persistent class for the barriers database table.
 */
@Entity
@Table(name = "workshop_experiments")
@DiscriminatorValue(value = "0")
public class WorkshopExperiment extends ExperimentArticle {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop originalWorkshop;

    public Workshop getOriginalWorkshop() {
        return this.originalWorkshop;
    }

    public void setOriginalWorkshop(Workshop originalWorkshop) {
        this.originalWorkshop = originalWorkshop;
    }
}