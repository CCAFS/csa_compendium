package org.cgiar.ccafs.csa.domain.workshops;

import org.cgiar.ccafs.csa.domain.SubIndicator;

import javax.persistence.*;

/**
 * The persistent class for the barriers database table.
 */
@Entity
@Table(name = "workshop_sub_indicators")
@DiscriminatorValue(value = "0")
public class WorkshopSubIndicator extends SubIndicator {
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