package org.cgiar.ccafs.csa.repository;

import java.util.List;

import org.cgiar.ccafs.csa.domain.PracticeLevel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ch.qos.logback.classic.Level;

@RepositoryRestResource(collectionResourceRel = "levels", path = "levels")
public interface PracticeLevelRepository extends PagingAndSortingRepository<PracticeLevel, Integer> {

	List<Level> findByName(@Param("name") String name);
}