package org.cgiar.ccafs.csa.repository.workshop;

import org.cgiar.ccafs.csa.domain.workshop.WorkshopPrioritization;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "workshop_prioritizations", path = "workshop_prioritizations")
public interface WorkshopPrioritizationRepository extends PagingAndSortingRepository<WorkshopPrioritization, Integer> {

}