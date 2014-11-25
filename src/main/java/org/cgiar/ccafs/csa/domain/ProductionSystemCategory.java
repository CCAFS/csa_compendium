package org.cgiar.ccafs.csa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(schema = "public", name="categories")
public class ProductionSystemCategory extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORIES_ID_GENERATOR", sequenceName="CATEGORIES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIES_ID_GENERATOR")
	private Integer id;

	@OneToMany(mappedBy="category")
	private List<ProductionSystem> productionSystems;
	
	public List<ProductionSystem> getProductionSystems() {
		if (this.productionSystems == null) {
			this.productionSystems = new ArrayList<ProductionSystem>();
		}
		return this.productionSystems;
	}
	
	@Override
	public Integer getId() {
		return this.id;
	}

	public ProductionSystem addPractice(ProductionSystem productionSystem) {
		getProductionSystems().add(productionSystem);
		productionSystem.setCategory(this);

		return productionSystem;
	}

	public ProductionSystem removePractice(ProductionSystem productionSystem) {
		getProductionSystems().remove(productionSystem);
		productionSystem.setCategory(null);

		return productionSystem;
	}
}