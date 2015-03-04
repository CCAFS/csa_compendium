package org.cgiar.ccafs.csa.web;

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
@Scope("view")
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
    private ThemeRepository themeRepository;

    @Autowired
    private PracticeLevelRepository levelRepository;

    @Autowired
    private ProductionSystemCategoryRepository productionSystemCategoryRepository;

    @Autowired
    private  ProductionSystemRepository productionSystemRepository;

    @Autowired
    private ContextVariableRepository contextVariableRepository;

    @Autowired
    private ContextValueRepository contextValueRepository;
    // END Repositories

    // Variables BLOCK
    private Region selectedRegion;
    private Country selectedCountry;
    private List<Country> countryList;
    private FarmingSystem selectedFarmingSystem;
    private List<FarmingSystem> farmingSystemList;

    private Theme selectedTheme;
    private PracticeLevel selectedPracticeLevel;
    private List<PracticeLevel> practiceLevelList;

    private ProductionSystemCategory selectedProductionSystemCategory;
    private ProductionSystem selectedProductionSystem;
    private List<ProductionSystem> productionSystemList;

    private ContextVariable selectedContextVariable;
    private ContextValue selectedContextValue;
    private List<ContextValue> contextValueList;

    private List<String> searchParams;
    // END Variables

    @PostConstruct
    public void init() {
        countryList = new ArrayList<>();
        farmingSystemList = new ArrayList<>();
        practiceLevelList = new ArrayList<>();
        productionSystemList = new ArrayList<>();
        contextValueList = new ArrayList<>();
        searchParams = new ArrayList<>();
    }

    // BLOCK Parent Filters
    public Iterable<Region> getRegionList() {
        return regionRepository.findAll();
    }

    public Iterable<Theme> getThemeList() {
        return themeRepository.findAll();
    }

    public Iterable<ProductionSystemCategory> getProductionSystemCategoryList() {
        return productionSystemCategoryRepository.findAll();
    }

    public Iterable<ContextVariable> getContextVariableList() {
        return contextVariableRepository.findAll();
    }
    // END Parent Filters

    // BLOCK Countries Filter
    public List<Country> getCountryList() {
        return countryList;
    }

    public void updateCountriesAndFarmingSystems(AjaxBehaviorEvent event) {
        this.countryList = countryRepository.findByRegion(selectedRegion);
        this.farmingSystemList = farmingSystemRepository.findByRegion(selectedRegion);
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
    public List<FarmingSystem> getFarmingSystemList() {
        return farmingSystemList;
    }

    public Integer getSelectedFarmingSystemId() {
        return selectedFarmingSystem != null ? selectedFarmingSystem.getId() : null;
    }

    public void setSelectedFarmingSystemId(Integer selectedFarmingSystemId) {
        // Not required, as only the filers are used for search
    }

    // END Farming Systems

    // BLOCK Practice Level Filter
    public List<PracticeLevel> getPracticeLevelList() {
        return practiceLevelList;
    }

    public void updatePracticeLevels(AjaxBehaviorEvent event) {
        this.practiceLevelList = levelRepository.findByTheme(selectedTheme);
    }

    public Integer getSelectedThemeId() {
        return selectedTheme != null ? selectedTheme.getId() : null;
    }

    public void setSelectedThemeId(Integer selectedThemeId) {
        this.selectedTheme = themeRepository.findOne(selectedThemeId);
    }

    public Integer getSelectedPracticeLevelId() {
        return selectedPracticeLevel != null ? selectedPracticeLevel.getId() : null;
    }

    public void setSelectedPracticeLevelId(Integer selectedPracticeLevelId) {
        // Not required, as only the filers are used for search
    }
    // END Practice Level Filter

    // BLOCK Production System
    public List<ProductionSystem> getProductionSystemList() {
        return productionSystemList;
    }

    public void updateProductionSystems(AjaxBehaviorEvent event) {
        this.productionSystemList = productionSystemRepository.findByCategory(selectedProductionSystemCategory);
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
    public List<ContextValue> getContextValueList() {
        return contextValueList;
    }

    public void updateContextValues(AjaxBehaviorEvent event) {
        this.contextValueList = contextValueRepository.findByContextVariable(selectedContextVariable);
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

    // Search Params
    public List<String> getSearchParams() {
        return searchParams;
    }
}
