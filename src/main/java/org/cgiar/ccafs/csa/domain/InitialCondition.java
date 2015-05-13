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

    private float value;

    @ManyToOne
    @JoinColumn(name = "condition_id")
    private Condition condition;

    @ManyToOne
    @JoinColumn(name = "experiment_id")
    private ExperimentArticle experimentArticle;



    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public ExperimentArticle getExperiment() {
        return this.experimentArticle;
    }

    public void setExperiment(ExperimentArticle experimentArticle) {
        this.experimentArticle = experimentArticle;
    }

}