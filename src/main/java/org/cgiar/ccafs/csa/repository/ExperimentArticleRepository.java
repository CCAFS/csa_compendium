package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
public interface ExperimentArticleRepository extends PagingAndSortingRepository<ExperimentArticle, Integer> {
    ExperimentArticle findByCode(String code);

    List<ExperimentArticle> findByThemeId(Integer id);
}