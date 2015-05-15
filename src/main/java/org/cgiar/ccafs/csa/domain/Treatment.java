package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the implementations database table.
 */
@Entity
@Table(name = "treatments")
public class Treatment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "control_for_treatments")
    private boolean controlForTreatments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "treatment_production_systems"
            , joinColumns = {
            @JoinColumn(name = "treatment_id")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "production_system_id")
    }
    )
    private List<ProductionSystem> productionSystems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "practice_id")
    private Practice practice;

    @ManyToOne
    @JoinColumn(name = "control_id")
    private Treatment control;

    @ManyToOne
    @JoinColumn(name = "experiment_id")
    private ExperimentArticle experimentArticle;

    @OneToMany(mappedBy = "treatment", fetch = FetchType.EAGER)
    private List<TreatmentOutcome> outcomes = new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public boolean isControlForTreatments() {
        return controlForTreatments;
    }

    public void setControlForTreatments(boolean controlForTreatments) {
        this.controlForTreatments = controlForTreatments;
    }

    public Treatment getControl() {
        return control;
    }

    public void setControl(Treatment control) {
        this.control = control;
    }

    public List<ProductionSystem> getProductionSystems() {
        return productionSystems;
    }

    public ProductionSystem addProductionSystem(ProductionSystem productionSystem) {
        getProductionSystems().add(productionSystem);
        return productionSystem;
    }

    public ProductionSystem removeOutcome(ProductionSystem productionSystem) {
        getProductionSystems().remove(productionSystem);
        return productionSystem;
    }

    public Practice getPractice() {
        return this.practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public ExperimentArticle getExperiment() {
        return experimentArticle;
    }

    public void setExperiment(ExperimentArticle experimentArticle) {
        this.experimentArticle = experimentArticle;
    }

    public List<TreatmentOutcome> getOutcomes() {
        return this.outcomes;
    }

    public TreatmentOutcome addOutcome(TreatmentOutcome outcome) {
        getOutcomes().add(outcome);
        outcome.setTreatment(this);

        return outcome;
    }

    public TreatmentOutcome removeOutcome(TreatmentOutcome outcome) {
        getOutcomes().remove(outcome);
        outcome.setTreatment(null);

        return outcome;
    }

}