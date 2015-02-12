package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.MeasureUnit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "units", path = "units")
public interface UnitRepository extends PagingAndSortingRepository<MeasureUnit, Integer> {

}