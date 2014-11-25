package org.cgiar.ccafs.csa.repository;

import java.util.List;

import org.cgiar.ccafs.csa.domain.Theme;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "themes", path = "themes")
public interface ThemeRepository extends PagingAndSortingRepository<Theme, Integer> {

	List<Theme> findByName(@Param("name") String name);
}