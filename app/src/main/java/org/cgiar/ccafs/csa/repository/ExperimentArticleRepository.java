package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
public interface ExperimentArticleRepository extends PagingAndSortingRepository<ExperimentArticle, Integer> {

    List<ExperimentArticle> findByLocationCountryRegionCode(String code);

    List<ExperimentArticle> findByLocationCountryCode(String code);

    List<ExperimentArticle> findByFarmingSystemId(Integer id);

    List<ExperimentArticle> findByPracticeThemeId(Integer id);

    List<ExperimentArticle> findByLocationCountryName(String code);
}