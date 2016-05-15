package org.cgiar.ccafs.csa.repository.workshop;

import org.cgiar.ccafs.csa.domain.workshop.Workshop;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "workshops", path = "workshops")
public interface WorkshopRepository extends PagingAndSortingRepository<Workshop, Integer> {

}