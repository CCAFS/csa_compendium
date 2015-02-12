package org.cgiar.ccafs.csa.domain.workshops;

import org.cgiar.ccafs.csa.domain.Dimension;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the workshop_dimensions database table.
 */
@Entity
@Table(name = "workshop_dimensions")
public class WorkshopDimension implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Dimension dimension;

    private float weight;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    public Integer getId() {
        return this.id;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Workshop getWorkshop() {
        return this.workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

}