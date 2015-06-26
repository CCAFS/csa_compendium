package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.TreatmentBarrier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "treatment_barriers", path = "treatment_barriers")
public interface TreatmentBarrierRepository extends PagingAndSortingRepository<TreatmentBarrier, Integer> {

}