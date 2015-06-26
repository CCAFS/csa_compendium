package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ContextValue;
import org.cgiar.ccafs.csa.domain.ContextVariable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "context_values", path = "context_values")
public interface ContextValueRepository extends PagingAndSortingRepository<ContextValue, Integer> {
    List<ContextValue> findByContextVariable(ContextVariable contextVariable);

    ContextValue findByContextVariableAndCode(ContextVariable contextVariable, String code);
}