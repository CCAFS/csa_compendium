package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Barrier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "barriers", path = "barriers")
public interface RegionRepository extends PagingAndSortingRepository<Barrier, Integer> {

    List<Barrier> findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM barriers WHERE compendium = 1", nativeQuery = true)
    List<Barrier> findCompendiumBarriers();
}