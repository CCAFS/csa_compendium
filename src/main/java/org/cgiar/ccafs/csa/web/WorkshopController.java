package org.cgiar.ccafs.csa.web;

import org.cgiar.ccafs.csa.domain.Practice;
import org.cgiar.ccafs.csa.repository.ExperimentArticleRepository;
import org.cgiar.ccafs.csa.repository.PracticeRepository;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
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
    private SearchController searchController;

    @Autowired
    private ExperimentArticleRepository experimentArticleRepository;

    @Autowired
    private PracticeRepository practiceRepository;

    private DualListModel<Practice> practices;

    private List<Practice> selectedPractices;
    private List<Practice> availablePractices;

    private List<Practice> getAvailablePractices() {
        if (availablePractices == null) {
            availablePractices = new ArrayList<>();
            availablePractices.add(practiceRepository.findByCode("a11"));
            availablePractices.add(practiceRepository.findByCode("c1"));
            availablePractices.add(practiceRepository.findByCode("b35"));
            availablePractices.add(practiceRepository.findByCode("a11"));
            availablePractices.add(practiceRepository.findByCode("b20"));
            availablePractices.add(practiceRepository.findByCode("a4"));
            availablePractices.add(practiceRepository.findByCode("d6"));
        }
        return availablePractices;
    }

    private List<Practice> getSelectedPractices() {
        if (selectedPractices == null) {
            selectedPractices = new ArrayList<>();
        }
        return selectedPractices;
    }

    @PostConstruct
    public void init() {
        practices = new DualListModel<>(getAvailablePractices(), getSelectedPractices());
    }

    public DualListModel<Practice> getPractices() {
        return practices;
    }

    public void setPractices(DualListModel<Practice> practices) {
        this.practices = practices;
    }
}
