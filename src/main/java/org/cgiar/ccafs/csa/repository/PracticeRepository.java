package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Practice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "practices", path = "practices")
public interface PracticeRepository extends PagingAndSortingRepository<Practice, Integer> {

    Practice findByCode(String code);

    Practice findByName(String name);
}