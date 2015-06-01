package org.cgiar.ccafs.csa.web;

import org.cgiar.ccafs.csa.domain.Indicator;
import org.cgiar.ccafs.csa.domain.Pillar;
import org.cgiar.ccafs.csa.domain.Practice;
import org.cgiar.ccafs.csa.repository.IndicatorRepository;
import org.cgiar.ccafs.csa.repository.PracticeRepository;
import org.primefaces.model.DualListModel;
import org.primefaces.model.chart.PieChartModel;
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

    /*@Autowired
    private SearchController searchController;

    @Autowired
    private ExperimentArticleRepository experimentArticleRepository;*/

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    private DualListModel<Practice> practices;

    private List<Practice> selectedPractices;
    private List<Practice> availablePractices;

    private PieChartModel pieModel;
    private int mitigationValue = 33;
    private int productionValue = 33;
    private int adaptationValue = 33;

    private List<Indicator> mitigationIndicators;
    private List<Indicator> productionIndicators;
    private List<Indicator> adaptationIndicators;

    private List<Integer> selectedMitigationIndicators;
    private List<Integer> selectedProductionIndicators;
    private List<Integer> selectedAdaptationIndicators;
    private String workshopCountryCode;

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

        selectedAdaptationIndicators = new ArrayList<>();
        selectedMitigationIndicators = new ArrayList<>();
        selectedProductionIndicators = new ArrayList<>();

        adaptationIndicators = indicatorRepository.findByPillar(Pillar.ADAPTATION) ;
        mitigationIndicators = indicatorRepository.findByPillar(Pillar.MITIGATION) ;
        productionIndicators = indicatorRepository.findByPillar(Pillar.PRODUCTION) ;

        createPieModel();
    }

    public DualListModel<Practice> getPractices() {
        return practices;
    }

    public void setPractices(DualListModel<Practice> practices) {
        this.practices = practices;
    }

    private void createPieModel() {
        pieModel = new PieChartModel();

        pieModel.setTitle("CSA Pillars");
        pieModel.setLegendPosition("e");
        pieModel.setShowDataLabels(true);
        //pieModel.setLegendPlacement(LegendPlacement.INSIDE);

        getPieModel();
    }

    public PieChartModel getPieModel() {
        pieModel.set("Mitigation", mitigationValue);
        pieModel.set("Production", productionValue);
        pieModel.set("Adaptation", adaptationValue);
        return pieModel;
    }

    public void checkSlider() {
        int value = getMitigationValue();
    }

    public void setMitigationValue(int mitigationValue) {
        this.mitigationValue = mitigationValue;
    }

    public int getMitigationValue() {
        return mitigationValue;
    }

    public int getProductionValue() {
        return productionValue;
    }

    public void setProductionValue(int productionValue) {
        this.productionValue = productionValue;
    }

    public int getAdaptationValue() {
        return adaptationValue;
    }

    public void setAdaptationValue(int adaptationValue) {
        this.adaptationValue = adaptationValue;
    }

    public List<Indicator> getMitigationIndicators() {
        return mitigationIndicators;
    }

    public List<Indicator> getProductionIndicators() {
        return productionIndicators;
    }

    public List<Indicator> getAdaptationIndicators() {
        return adaptationIndicators;
    }

    public List<Integer> getSelectedMitigationIndicators() {
        return selectedMitigationIndicators;
    }

    public List<Integer> getSelectedProductionIndicators() {
        return selectedProductionIndicators;
    }

    public List<Integer> getSelectedAdaptationIndicators() {
        return selectedAdaptationIndicators;
    }

    public String getWorkshopCountryCode() {
        return workshopCountryCode;
    }

    public void setWorkshopCountryCode(String workshopCountryCode) {
        this.workshopCountryCode = workshopCountryCode;
    }
}
