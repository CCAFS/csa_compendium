package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.Indicator;
import org.cgiar.ccafs.csa.domain.Practice;
import org.cgiar.ccafs.csa.domain.workshop.WorkshopPrioritization;
import org.cgiar.ccafs.csa.repository.IndicatorRepository;
import org.cgiar.ccafs.csa.web.WorkshopController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Controller
@ManagedBean
@Scope("session")
public class PracticesController implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WorkshopController workshopController;

    @Autowired
    private IndicatorRepository indicatorRepository;

    private List<Indicator> selectedIndicators;

    private Set<PracticePriority> practicesPriorities;

    @PostConstruct
    private void init() {
        selectedIndicators = new ArrayList<>();
        List selectedIndicatorsIds = new ArrayList();

        selectedIndicatorsIds.addAll(workshopController.getSelectedProductionIndicators());
        selectedIndicatorsIds.addAll(workshopController.getSelectedAdaptationIndicators());
        selectedIndicatorsIds.addAll(workshopController.getSelectedMitigationIndicators());


        for (int i = 0, n = selectedIndicatorsIds.size(); i < n; i++) {
            selectedIndicators.add(indicatorRepository.findOne(Integer.valueOf((String) selectedIndicatorsIds.get(i))));
        }

        practicesPriorities = new HashSet<>();

        for (WorkshopPrioritization prioritization : workshopController.getWorkshop().getPrioritizations()) {
            PracticePriority priority = new PracticePriority();
            priority.practice = prioritization.getPractice();
            for (Indicator selectedIndicator : selectedIndicators) {
                priority.indicatorValues.add(prioritization.getScore() * selectedIndicator.getName().hashCode() / 1000);
            }
        }
    }

    public List<Indicator> getSelectedIndicators() {
        return selectedIndicators;
    }

    public Set<PracticePriority> getPracticesPriorities() {
        return practicesPriorities;
    }

    private class PracticePriority {
        Practice practice;
        List<Double> indicatorValues = new ArrayList<>();

        public String getPracticeName() {
            return practice.getName();
        }

        public List<Double> getIndicatorValues() {
            return indicatorValues;
        }
    }
}
