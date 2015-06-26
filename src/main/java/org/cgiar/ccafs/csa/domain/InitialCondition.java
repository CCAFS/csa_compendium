package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the initial_conditions database table.
 */
@Entity
@Table(name = "initial_conditions")
public class InitialCondition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String state;

    private double value;

    @ManyToOne
    @JoinColumn(name = "condition_id")
    private Condition condition;

    @ManyToOne
    @JoinColumn(name = "experiment_context_id")
    private ExperimentContext experimentContext;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private MeasureUnit measureUnit;

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public ExperimentContext getExperimentContext() {
        return this.experimentContext;
    }

    public void setExperimentContext(ExperimentContext experimentContext) {
        this.experimentContext = experimentContext;
    }

    public MeasureUnit getMeasureUnit() {
        return this.measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

}