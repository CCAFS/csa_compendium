package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.PracticeTheme;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "practice_themes", path = "practice_themes")
public interface PracticeThemeRepository extends PagingAndSortingRepository<PracticeTheme, Integer> {

    PracticeTheme findByName(String name);

    PracticeTheme findByCode(String code);
}