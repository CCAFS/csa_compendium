package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ExperimentContext;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "experiments", path = "experiments")
public interface ExperimentContextRepository extends PagingAndSortingRepository<ExperimentContext, Integer> {

    List<ExperimentContext> findByLocationCountryRegionCode(String code);

    List<ExperimentContext> findByLocationCountryCode(String code);

    List<ExperimentContext> findByFarmingSystemId(Integer id);
}