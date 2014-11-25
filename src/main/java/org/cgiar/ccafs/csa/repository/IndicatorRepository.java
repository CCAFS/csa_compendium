package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Indicator;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "indicators", path = "indicators")
public interface IndicatorRepository extends PagingAndSortingRepository<Indicator, Integer> {
	
}