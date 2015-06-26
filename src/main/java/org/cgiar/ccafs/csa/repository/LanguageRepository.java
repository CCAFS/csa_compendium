package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Language;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "languages", path = "languages")
public interface LanguageRepository extends PagingAndSortingRepository<Language, String> {
    Language findByCode(String code);
}