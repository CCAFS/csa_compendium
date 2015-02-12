package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Barrier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "barriers", path = "barriers")
public interface BarrierRepository extends PagingAndSortingRepository<Barrier, Integer> {
	
}