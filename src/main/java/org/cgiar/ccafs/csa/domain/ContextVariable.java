package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the context_variables database table.
 * 
 */
@Entity
@Table(schema = "public", name="context_variables")
public class ContextVariable extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTEXT_VARIABLES_ID_GENERATOR", sequenceName="CONTEXT_VARIABLES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTEXT_VARIABLES_ID_GENERATOR")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	protected ContextVariableScope scope;

	@OneToMany(mappedBy="contextVariable")
	private List<ContextValue> contextValues;
	
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
		contextValue.setContext(this);

		return contextValue;
	}

	public ContextValue removeContextValue(ContextValue contextValue) {
		getContextValues().remove(contextValue);
		contextValue.setContext(null);

		return contextValue;
	}
}