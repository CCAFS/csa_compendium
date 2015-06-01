package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Indicator;
import org.cgiar.ccafs.csa.domain.IndicatorPillar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "indicators_pillars", path = "indicators_pillars")
public interface IndicatorPillarRepository extends PagingAndSortingRepository<IndicatorPillar, Integer> {
}