package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.FarmingSystem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "farming_systems", path = "farming_systems")
public interface FarmingSystemRepository extends PagingAndSortingRepository<FarmingSystem, Integer> {
    FarmingSystem findByCode(@Param("code") String code);

    @Query(value = "SELECT FS.* FROM farming_systems AS FS WHERE id IN (SELECT MIN(id) FROM farming_systems GROUP BY name) " +
            "ORDER BY name", nativeQuery = true)
    List<FarmingSystem> findAllByDistinctName();
}