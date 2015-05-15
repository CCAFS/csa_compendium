package org.cgiar.ccafs.csa.web;

import org.cgiar.ccafs.csa.domain.Practice;
import org.cgiar.ccafs.csa.repository.ExperimentArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@ManagedBean
@Scope("session")
public class WorkshopController implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExperimentArticleRepository experimentArticleRepository;

    @Autowired
    SearchController searchController;

    private List<Practice> selectedPractices;
    private List<Practice> availablePractices;

    public List<Practice> getAvailablePractices() {
        if (availablePractices == null) {
            availablePractices = new ArrayList<>();
        }
        return availablePractices;
    }

    public List<Practice> getSelectedPractices() {
        if (selectedPractices == null) {
            selectedPractices = new ArrayList<>();
        }
        return selectedPractices;
    }

}
