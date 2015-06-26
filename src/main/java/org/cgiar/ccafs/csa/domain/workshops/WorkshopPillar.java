package org.cgiar.ccafs.csa.domain.workshops;

import org.cgiar.ccafs.csa.domain.Pillar;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the workshop_pillars database table.
 */
@Entity
@Table(name = "workshop_pillars")
public class WorkshopPillar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Pillar pillar;

    private double weight;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    public Integer getId() {
        return this.id;
    }

    public Pillar getPillar() {
        return this.pillar;
    }

    public void setPillar(Pillar pillar) {
        this.pillar = pillar;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Workshop getWorkshop() {
        return this.workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

}