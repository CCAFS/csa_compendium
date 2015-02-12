package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ContextVariable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "context_variables", path = "context_variables")
public interface ContextVariableRepository extends PagingAndSortingRepository<ContextVariable, Integer> {

}