package org.cgiar.ccafs.csa.repository.workshop;

import org.cgiar.ccafs.csa.domain.workshop.WorkshopPillar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "workshop_pillars", path = "workshop_pillars")
public interface WorkshopPillarRepository extends PagingAndSortingRepository<WorkshopPillar, Integer> {

}