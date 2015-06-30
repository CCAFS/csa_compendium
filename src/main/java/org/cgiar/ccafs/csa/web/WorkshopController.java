package org.cgiar.ccafs.csa.web;

import com.google.common.collect.Lists;
import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.CountryRepository;
import org.cgiar.ccafs.csa.repository.IndicatorRepository;
import org.cgiar.ccafs.csa.repository.LocationRepository;
import org.cgiar.ccafs.csa.repository.PracticeRepository;
import org.primefaces.component.slider.Slider;
import org.primefaces.event.SlideEndEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.chart.PieChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.*;

@Controller
@ManagedBean
@Scope("session")
public class WorkshopController implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResultsController resultsController;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private LocationRepository locationRepository;

    private Iterable<Country> countriesList;

    private Iterable<Location> countryPlaces;

    private DualListModel<Practice> practices;

    private List<Practice> selectedPractices;
    private List<Practice> availablePractices;

    private PieChartModel pieModel;
    private int mitigationValue = 50;
    private int productionValue = 50;
    private int adaptationValue = 50;

    private List<Indicator> mitigationIndicators;
    private List<Indicator> productionIndicators;
    private List<Indicator> adaptationIndicators;

    private List<Integer> selectedMitigationIndicators;
    private List<Integer> selectedProductionIndicators;
    private List<Integer> selectedAdaptationIndicators;

    private String workshopCountryCode;
    private String workshopPlace;
    private String workshopPartners;

    private List<Practice> getAvailablePractices() {
        if (availablePractices == null) {
            Set<Practice> practices = new HashSet<>();

            for (ExperimentContext context : resultsController.getExperimentContexts()) {
                practices.addAll(context.getTreatmentsPractices());
            }
            /*
            availablePractices.add(practiceRepository.findByCode("a11"));
            availablePractices.add(practiceRepository.findByCode("c1"));
            availablePractices.add(practiceRepository.findByCode("b35"));
            availablePractices.add(practiceRepository.findByCode("a11"));
            availablePractices.add(practiceRepository.findByCode("b20"));
            availablePractices.add(practiceRepository.findByCode("a4"));
            availablePractices.add(practiceRepository.findByCode("d6"));
            */
            availablePractices = Lists.newArrayList(practices);
        }
        return availablePractices;
    }

    public void updateCountry() {
        countryPlaces = locationRepository.findByCountryCodeOrderByPlaceAsc(workshopCountryCode);
    }

    public List<String> completePlaces(String query) {
        Set<String> results = new LinkedHashSet<>();
        for(Location location : countryPlaces) {
            if (location.getPlace().toLowerCase().startsWith(query)) {
                results.add(location.getPlace());
            }
        }
        return Lists.newArrayList(results);
    }


    private List<Practice> getSelectedPractices() {
        if (selectedPractices == null) {
            selectedPractices = new ArrayList<>();
        }
        return selectedPractices;
    }

    @PostConstruct
    public void init() {
        countriesList = countryRepository.findAll();

        practices = new DualListModel<>(getAvailablePractices(), getSelectedPractices());

        selectedAdaptationIndicators = new ArrayList<>();
        selectedMitigationIndicators = new ArrayList<>();
        selectedProductionIndicators = new ArrayList<>();

        adaptationIndicators = indicatorRepository.findByPillar(Pillar.ADAPTATION);
        mitigationIndicators = indicatorRepository.findByPillar(Pillar.MITIGATION);
        productionIndicators = indicatorRepository.findByPillar(Pillar.PRODUCTION);

        pieModel = new PieChartModel();

        pieModel.setTitle("CSA Pillars");
        pieModel.setLegendPosition("e");
        pieModel.setShowDataLabels(true);
    }

    public DualListModel<Practice> getPractices() {
        return practices;
    }

    public void setPractices(DualListModel<Practice> practices) {
        this.practices = practices;
    }

    public PieChartModel getPieModel() {
        pieModel.set("Mitigation", mitigationValue);
        pieModel.set("Production", productionValue);
        pieModel.set("Adaptation", adaptationValue);
        return pieModel;
    }

    public void checkSlider(SlideEndEvent event) {
        String sliderId = ((Slider) event.getSource()).getId();
        int newValue = event.getValue();

        switch (sliderId) {
            case "mitigation":
                mitigationValue = newValue;
                break;
            case "production":
                productionValue = newValue;
                break;
            case "adaptation":
                adaptationValue = newValue;
                break;
        }
    }

    public Iterable<Country> getCountriesList() {
        return countriesList;
    }

    public int getMitigationValue() {
        return mitigationValue;
    }

    public void setMitigationValue(int mitigationValue) {
        this.mitigationValue = mitigationValue;
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

    public String getWorkshopPlace() {
        return workshopPlace;
    }

    public void setWorkshopPlace(String workshopPlace) {
        this.workshopPlace = workshopPlace;
    }

    public String getWorkshopPartners() {
        return workshopPartners;
    }

    public void setWorkshopPartners(String workshopPartners) {
        this.workshopPartners = workshopPartners;
    }
}
