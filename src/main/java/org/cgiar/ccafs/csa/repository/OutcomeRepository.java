package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Outcome;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "outcomes", path = "outcomes")
public interface OutcomeRepository extends PagingAndSortingRepository<Outcome, Integer> {

}