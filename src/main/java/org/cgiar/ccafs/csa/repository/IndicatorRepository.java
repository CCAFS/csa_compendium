package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Indicator;
import org.cgiar.ccafs.csa.domain.Pillar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "indicators", path = "indicators")
public interface IndicatorRepository extends PagingAndSortingRepository<Indicator, Integer> {

    Indicator findByName(String name);

    @Query("SELECT i FROM Indicator i, IN (i.pillars) p WHERE p.pillar = :pillar")
    List<Indicator> findByPillar(@Param("pillar") Pillar pillar);
}