package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

/**
 * The persistent class for the context_filters database table.
 * 
 */
@Entity
@Table(schema = "public", name="context_values")
public class ContextValue extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTEXT_VALUES_ID_GENERATOR", sequenceName="CONTEXT_VALUES_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTEXT_VALUES_ID_GENERATOR")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="context_variable_id")
	private ContextVariable contextVariable;

	@Override
	public Integer getId() {
		return this.id;
	}
	
	public ContextVariable getContext() {
		return this.contextVariable;
	}

	public void setContext(ContextVariable contextVariable) {
		this.contextVariable = contextVariable;
	}
}