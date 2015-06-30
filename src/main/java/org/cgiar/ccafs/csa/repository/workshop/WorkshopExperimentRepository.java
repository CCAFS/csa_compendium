package org.cgiar.ccafs.csa.repository.workshop;

import org.cgiar.ccafs.csa.domain.workshop.WorkshopExperiment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
public interface WorkshopExperimentRepository extends PagingAndSortingRepository<WorkshopExperiment, Integer> {

}