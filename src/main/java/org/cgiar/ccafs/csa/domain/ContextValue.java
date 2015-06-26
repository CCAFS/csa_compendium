package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the context_filters database table.
 */
@Entity
@Table(name = "context_values")
public class ContextValue extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "context_variable_id")
    private ContextVariable contextVariable;

    @ManyToMany(mappedBy="contextValues")
    private List<ExperimentArticle> experimentArticles = new ArrayList<>();

    @Override
    public Integer getId() {
        return this.id;
    }

    public ContextVariable getContextVariable() {
        return this.contextVariable;
    }

    public void setContextVariable(ContextVariable contextVariable) {
        this.contextVariable = contextVariable;
    }

    public List<ExperimentArticle> getExperimentArticles() {
        return experimentArticles;
    }
}