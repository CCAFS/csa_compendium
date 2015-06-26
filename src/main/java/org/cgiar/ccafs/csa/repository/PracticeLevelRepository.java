package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.PracticeLevel;
import org.cgiar.ccafs.csa.domain.PracticeTheme;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "practice_levels", path = "practice_levels")
public interface PracticeLevelRepository extends PagingAndSortingRepository<PracticeLevel, Integer> {

    PracticeLevel findByName(String name);

    List<PracticeLevel> findByTheme(PracticeTheme practiceTheme);

    PracticeLevel findByNameAndTheme(String name, PracticeTheme theme);
}