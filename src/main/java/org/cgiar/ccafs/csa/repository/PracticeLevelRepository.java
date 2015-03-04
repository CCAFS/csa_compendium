package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.PracticeLevel;
import org.cgiar.ccafs.csa.domain.Theme;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "practice_levels", path = "practice_levels")
public interface PracticeLevelRepository extends PagingAndSortingRepository<PracticeLevel, Integer> {

    List<PracticeLevel> findByName(@Param("name") String name);

    List<PracticeLevel> findByTheme(Theme theme);
}