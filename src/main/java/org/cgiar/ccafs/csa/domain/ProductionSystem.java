package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

/**
 * The persistent class for the productive_systems database table. Represents a
 * FAO classified crop, livestock or forestal resource.
 * 
 */
@Entity
@Table(schema = "public", name="production_systems")
public class ProductionSystem extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTION_SYSTEMS_ID_GENERATOR", sequenceName="PRODUCTION_SYSTEMS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTION_SYSTEMS_ID_GENERATOR")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="category_id")
	private ProductionSystemCategory category;
	
	@Override
	public Integer getId() {
		return this.id;
	}

	public ProductionSystemCategory getCategory() {
		return category;
	}

	public void setCategory(ProductionSystemCategory category) {
		this.category = category;
	}
}