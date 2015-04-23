package org.cgiar.ccafs.csa.web;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.cgiar.ccafs.csa.repository.ExperimentArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@ManagedBean
@Scope("view")
public class ResultsController implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExperimentArticleRepository experimentArticleRepository;

    private Set<ExperimentArticle> articles;

    public Set<ExperimentArticle> getArticles() {
        if (articles == null) {
            articles = new LinkedHashSet<>(experimentArticleRepository.findByLocationCountryName("Kenya"));
        }
        return articles;
    }

}
