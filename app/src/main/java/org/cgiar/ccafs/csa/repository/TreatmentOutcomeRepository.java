package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.TreatmentOutcome;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "treatment_outcomes", path = "treatment_outcomes")
public interface TreatmentOutcomeRepository extends PagingAndSortingRepository<TreatmentOutcome, Integer> {

}