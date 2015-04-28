package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Condition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "conditions", path = "conditions")
public interface ConditionRepository extends PagingAndSortingRepository<Condition, Integer> {

}