package org.cgiar.ccafs.csa.domain.workshops;

import org.cgiar.ccafs.csa.domain.Practice;

import javax.persistence.*;

/**
 * The persistent class for the practices database table.
 */
@Entity
@Table(name = "workshop_practices")
@DiscriminatorValue(value = "0")
public class WorkshopPractice extends Practice {
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