package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

/**
 * The persistent class for the barriers database table.
 * 
 */
@Entity
@Table(schema = "public", name="barriers")
public class Barrier extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BARRIERS_ID_GENERATOR", sequenceName="BARRIERS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BARRIERS_ID_GENERATOR")
	private Integer id;

	@Override
	public Integer getId() {
		return this.id;
	}
}