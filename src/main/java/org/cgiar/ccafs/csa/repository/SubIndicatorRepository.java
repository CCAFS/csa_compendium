package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Indicator;
import org.cgiar.ccafs.csa.domain.SubIndicator;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "sub_indicators", path = "sub_indicators")
public interface SubIndicatorRepository extends PagingAndSortingRepository<SubIndicator, Integer> {

    List<SubIndicator> findByIndicator(Indicator indicator);
}