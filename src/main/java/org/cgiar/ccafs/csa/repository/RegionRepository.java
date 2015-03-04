package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Region;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "regions", path = "regions")
public interface RegionRepository extends PagingAndSortingRepository<Region, String> {

    Region findByCode(String code);
}