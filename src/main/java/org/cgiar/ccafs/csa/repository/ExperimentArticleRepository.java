package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
public interface ExperimentArticleRepository extends PagingAndSortingRepository<ExperimentArticle, Integer> {
	
}