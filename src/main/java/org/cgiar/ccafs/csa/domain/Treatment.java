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

    @Column(length = 100000)
    private String information;

    @Column(name = "control_for_treatments")
    private boolean controlForTreatments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "treatment_practices"
            , joinColumns = {
            @JoinColumn(name = "treatment_id")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "practice_id")
    }
    )
    private List<Practice> practices = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "control_id")
    private Treatment control;

    @ManyToOne
    @JoinColumn(name = "context_id")
    private ExperimentContext experimentContext;

    @OneToMany(mappedBy = "treatment", fetch = FetchType.EAGER)
    private List<TreatmentOutcome> outcomes = new ArrayList<>();

    @OneToMany(mappedBy = "treatment", fetch = FetchType.EAGER)
    private List<TreatmentBarrier> barriers = new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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

    public List<Practice> getPractices() {
        return practices;
    }

    public Practice addPractice(Practice practice) {
        practices.add(practice);
        return practice;
    }

    public Practice removePractice(Practice practice) {
        practices.remove(practice);
        return practice;
    }

    public ExperimentContext getExperimentContext() {
        return experimentContext;
    }

    public void setExperimentContext(ExperimentContext experimentArticle) {
        this.experimentContext = experimentArticle;
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

    public List<TreatmentBarrier> getBarriers() {
        return barriers;
    }

    public TreatmentBarrier addBarrier(TreatmentBarrier barrier) {
        getBarriers().add(barrier);
        barrier.setTreatment(this);

        return barrier;
    }

    public TreatmentBarrier removeBarrier(TreatmentBarrier barrier) {
        getBarriers().remove(barrier);
        barrier.setTreatment(null);

        return barrier;
    }
}