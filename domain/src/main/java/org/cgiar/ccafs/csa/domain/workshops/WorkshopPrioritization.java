package org.cgiar.ccafs.csa.domain.workshops;

import org.cgiar.ccafs.csa.domain.Practice;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the prioritization database table.
 */
@Entity
@Table(name = "workshop_prioritizations")
public class WorkshopPrioritization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float score;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private WorkshopPortfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "practice_id")
    private Practice practice;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    public Integer getId() {
        return this.id;
    }

    public float getScore() {
        return this.score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public WorkshopPortfolio getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(WorkshopPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Practice getPractice() {
        return this.practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public Workshop getWorkshop() {
        return this.workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

}
