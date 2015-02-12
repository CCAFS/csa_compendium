package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the barriers database table.
 */
@Entity
@Table(name = "treatment_barriers")
public class TreatmentBarrier implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float cost;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "barrier_id")
    private Barrier barrier;

    public Integer getId() {
        return id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Barrier getBarrier() {
        return barrier;
    }

    public void setBarrier(Barrier barrier) {
        this.barrier = barrier;
    }

}