package org.cgiar.ccafs.csa.repository;

import ch.qos.logback.classic.Level;
import org.cgiar.ccafs.csa.domain.PracticeLevel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "levels", path = "levels")
public interface PracticeLevelRepository extends PagingAndSortingRepository<PracticeLevel, Integer> {

    List<Level> findByName(@Param("name") String name);
}