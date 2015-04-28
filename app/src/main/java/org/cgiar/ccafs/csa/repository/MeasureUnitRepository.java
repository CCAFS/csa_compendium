package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.MeasureUnit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "measure_units", path = "measure_units")
public interface MeasureUnitRepository extends PagingAndSortingRepository<MeasureUnit, Integer> {

}