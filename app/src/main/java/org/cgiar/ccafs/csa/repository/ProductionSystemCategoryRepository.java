package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ProductionSystemCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "categories", path = "production_system_categories")
public interface ProductionSystemCategoryRepository extends PagingAndSortingRepository<ProductionSystemCategory, Integer> {

}
