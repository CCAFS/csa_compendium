package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.FarmingSystem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "farming_systems", path = "farming_systems")
public interface FarmingSystemRepository extends PagingAndSortingRepository<FarmingSystem, Integer> {
    public FarmingSystem findByCode(String code);
}
