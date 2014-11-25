package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

/**
 * The persistent class for the conditions database table.
 * 
 */
@Entity
@Table(schema = "public", name="conditions")
public class Condition extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONDITIONS_ID_GENERATOR", sequenceName="CONDITIONS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONDITIONS_ID_GENERATOR")
	private Integer id;

	@Override
	public Integer getId() {
		return this.id;
	}
}