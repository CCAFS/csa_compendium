package org.cgiar.ccafs.csa.repository;

import org.cgiar.ccafs.csa.domain.Country;
import org.cgiar.ccafs.csa.domain.Region;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {

    Country findByName(String name);

    List<Country> findByRegion(Region region);

}