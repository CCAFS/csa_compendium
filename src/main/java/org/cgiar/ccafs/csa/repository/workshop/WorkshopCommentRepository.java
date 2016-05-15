package org.cgiar.ccafs.csa.repository.workshop;

import org.cgiar.ccafs.csa.domain.workshop.WorkshopComment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "workshop_comments", path = "workshop_comments")
public interface WorkshopCommentRepository extends PagingAndSortingRepository<WorkshopComment, Integer> {

}