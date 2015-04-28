package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Treatment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "treatments", path = "treatments")
public interface TreatmentRepository extends PagingAndSortingRepository<Treatment, Integer> {

}