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

    private double cost;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "barrier_id")
    private Barrier barrier;

    public Integer getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
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