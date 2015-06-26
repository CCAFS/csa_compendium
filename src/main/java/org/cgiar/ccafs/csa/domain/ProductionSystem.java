package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the productive_systems database table. Represents a
 * FAO classified crop, livestock or forest resource.
 */
@Entity
@Table(name = "production_systems")
public class ProductionSystem extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductionSystemCategory category;

    @ManyToMany(mappedBy = "productionSystems")
    private List<ExperimentContext> experimentContexts = new ArrayList<>();

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

    public List<ExperimentContext> getExperimentContexts() {
        return experimentContexts;
    }
}