package org.cgiar.ccafs.csa.web;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@ManagedBean
@Scope("session")
@URLMappings(mappings = {
        @URLMapping(id = "home", pattern = "/", viewId = "/search.xhtml"),
        @URLMapping(id = "homeIndex", pattern = "/index.html", viewId = "/search.xhtml")
})
public class SearchController implements Serializable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // BLOCK Repositories
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private FarmingSystemRepository farmingSystemRepository;

    @Autowired
    private PracticeThemeRepository practiceThemeRepository;

    @Autowired
    private PracticeLevelRepository levelRepository;

    @Autowired
    private ProductionSystemCategoryRepository productionSystemCategoryRepository;

    @Autowired
    private ProductionSystemRepository productionSystemRepository;

    @Autowired
    private ContextVariableRepository contextVariableRepository;

    @Autowired
    private ContextValueRepository contextValueRepository;
    // END Repositories

    // Variables BLOCK
    private Region selectedRegion;
    private Country selectedCountry;
    private List<Country> countriesList;
    private FarmingSystem selectedFarmingSystem;
    private List<FarmingSystem> farmingSystemsList;

    private PracticeTheme selectedPracticeTheme;
    private PracticeLevel selectedPracticeLevel;
    private List<PracticeLevel> practiceLevelsList;

    private ProductionSystemCategory selectedProductionSystemCategory;
    private ProductionSystem selectedProductionSystem;
    private List<ProductionSystem> productionSystemsList;

    private ContextVariable selectedContextVariable;
    private ContextValue selectedContextValue;
    private List<ContextValue> contextValuesList;

    // END Variables

    @PostConstruct
    public void init() {
        countriesList = new ArrayList<>();
        farmingSystemsList = new ArrayList<>();
        practiceLevelsList = new ArrayList<>();
        productionSystemsList = new ArrayList<>();
        contextValuesList = new ArrayList<>();
    }

    // BLOCK Parent Filters
    public Iterable<Region> getRegionsList() {
        return regionRepository.findAll();
    }

    public Iterable<PracticeTheme> getThemesList() {
        return practiceThemeRepository.findAll();
    }

    public Iterable<ProductionSystemCategory> getProductionSystemCategoriesList() {
        return productionSystemCategoryRepository.findAll();
    }

    public Iterable<ContextVariable> getContextVariablesList() {
        return contextVariableRepository.findAll();
    }
    // END Parent Filters

    // BLOCK Countries Filter
    public List<Country> getCountriesList() {
        return countriesList;
    }

    public void updateCountriesAndFarmingSystems(AjaxBehaviorEvent event) {
        this.countriesList = countryRepository.findByRegion(selectedRegion);
        this.farmingSystemsList = farmingSystemRepository.findByRegion(selectedRegion);
    }

    public String getSelectedRegionCode() {
        return selectedRegion != null ? selectedRegion.getCode() : null;
    }

    public void setSelectedRegionCode(String selectedRegionCode) {
        this.selectedRegion = regionRepository.findByCode(selectedRegionCode);
    }

    public String getSelectedCountryCode() {
        return selectedCountry != null ? selectedCountry.getCode() : null;
    }

    public void setSelectedCountryCode(String selectedCountryCode) {
        // Not required, as only the filers are used for search
    }
    // END Countries Filter

    // BLOCK Farming Systems
    public List<FarmingSystem> getFarmingSystemsList() {
        return farmingSystemsList;
    }

    public Integer getSelectedFarmingSystemId() {
        return selectedFarmingSystem != null ? selectedFarmingSystem.getId() : null;
    }

    public void setSelectedFarmingSystemId(Integer selectedFarmingSystemId) {
        // Not required, as only the filers are used for search
    }

    // END Farming Systems

    // BLOCK Practice Level Filter
    public List<PracticeLevel> getPracticeLevelsList() {
        return practiceLevelsList;
    }

    public void updatePracticeLevels(AjaxBehaviorEvent event) {
        this.practiceLevelsList = levelRepository.findByTheme(selectedPracticeTheme);
    }

    public Integer getSelectedThemeId() {
        return selectedPracticeTheme != null ? selectedPracticeTheme.getId() : null;
    }

    public void setSelectedThemeId(Integer selectedThemeId) {
        this.selectedPracticeTheme = practiceThemeRepository.findOne(selectedThemeId);
    }

    public Integer getSelectedPracticeLevelId() {
        return selectedPracticeLevel != null ? selectedPracticeLevel.getId() : null;
    }

    public void setSelectedPracticeLevelId(Integer selectedPracticeLevelId) {
        // Not required, as only the filers are used for search
    }
    // END Practice Level Filter

    // BLOCK Production System
    public List<ProductionSystem> getProductionSystemsList() {
        return productionSystemsList;
    }

    public void updateProductionSystems(AjaxBehaviorEvent event) {
        this.productionSystemsList = productionSystemRepository.findByCategory(selectedProductionSystemCategory);
    }

    public Integer getSelectedProductionSystemCategoryId() {
        return selectedProductionSystemCategory != null ? selectedProductionSystemCategory.getId() : null;
    }

    public void setSelectedProductionSystemCategoryId(Integer selectedProductionSystemCategoryId) {
        this.selectedProductionSystemCategory = productionSystemCategoryRepository.findOne(selectedProductionSystemCategoryId);
    }

    public Integer getSelectedProductionSystemId() {
        return selectedProductionSystem != null ? selectedProductionSystem.getId() : null;
    }

    public void setSelectedProductionSystemId(Integer selectedProductionSystemId) {
        // Not required, as only the filers are used for search
    }
    // END Context Values Filter

    // BLOCK Context Values Filter
    public List<ContextValue> getContextValuesList() {
        return contextValuesList;
    }

    public void updateContextValues(AjaxBehaviorEvent event) {
        this.contextValuesList = contextValueRepository.findByContextVariable(selectedContextVariable);
    }

    public Integer getSelectedContextVariableId() {
        return selectedContextVariable != null ? selectedContextVariable.getId() : null;
    }

    public void setSelectedContextVariableId(Integer selectedContextVariableId) {
        this.selectedContextVariable = contextVariableRepository.findOne(selectedContextVariableId);
    }

    public Integer getSelectedContextValueId() {
        return selectedContextValue != null ? selectedContextValue.getId() : null;
    }

    public void setSelectedContextValueId(Integer selectedContextVariableId) {
        // Not required, as only the filers are used for search
    }
    // END Context Values Filter

}
