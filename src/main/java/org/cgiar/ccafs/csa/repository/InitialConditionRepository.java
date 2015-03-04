package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.InitialCondition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "initial_conditions", path = "initial_conditions")
public interface InitialConditionRepository extends PagingAndSortingRepository<InitialCondition, Integer> {

}