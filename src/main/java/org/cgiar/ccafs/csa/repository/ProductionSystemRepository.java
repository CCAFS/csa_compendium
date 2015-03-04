package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ProductionSystem;
import org.cgiar.ccafs.csa.domain.ProductionSystemCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "production_systems", path = "production_systems")
public interface ProductionSystemRepository extends PagingAndSortingRepository<ProductionSystem, Integer> {
    List<ProductionSystem> findByCategory(ProductionSystemCategory category);
}