package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the context_variables database table.
 */
@Entity
@Table(name = "context_variables")
public class ContextVariable extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ContextVariableScope scope;

    @OneToMany(mappedBy = "contextVariable")
    private List<ContextValue> contextValues = new ArrayList<>();

    @Override
    public Integer getId() {
        return this.id;
    }

    public ContextVariableScope getScope() {
        return scope;
    }

    public void setScope(ContextVariableScope scope) {
        this.scope = scope;
    }

    public List<ContextValue> getContextValues() {
        return this.contextValues;
    }

    public ContextValue addContextValue(ContextValue contextValue) {
        getContextValues().add(contextValue);
        contextValue.setContextVariable(this);

        return contextValue;
    }

    public ContextValue removeContextValue(ContextValue contextValue) {
        getContextValues().remove(contextValue);
        contextValue.setContextVariable(null);

        return contextValue;
    }
}