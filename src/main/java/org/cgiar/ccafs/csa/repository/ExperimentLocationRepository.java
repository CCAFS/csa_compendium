package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ExperimentLocation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "locations", path = "locations")
public interface ExperimentLocationRepository extends PagingAndSortingRepository<ExperimentLocation, Integer> {
	
}