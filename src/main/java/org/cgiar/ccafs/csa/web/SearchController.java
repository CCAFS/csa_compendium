package org.cgiar.ccafs.csa.web;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import java.io.Serializable;
import java.util.*;

@Controller
@ManagedBean
@Scope("session")
@URLMappings(mappings = {
        @URLMapping(id = "home", pattern = "/", viewId = "/search.xhtml"),
        @URLMapping(id = "homeIndex", pattern = "/index.html", viewId = "/search.xhtml")
})
public class SearchController implements Serializable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // Search Parameters
    private String searchKeywords;
    private String filters;
    private String filtersInfo;

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
    private PracticeLevelRepository practiceLevelRepository;

    @Autowired
    private ProductionSystemCategoryRepository productionSystemCategoryRepository;

    @Autowired
    private ProductionSystemRepository productionSystemRepository;

    @Autowired
    private ContextVariableRepository contextVariableRepository;

    @Autowired
    private ContextValueRepository contextValueRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private SubIndicatorRepository subIndicatorRepository;
    // END Repositories

    // Variables BLOCK
    private Region selectedRegion;
    private Country selectedCountry;
    private SetMultimap<Region, Country> regionsCountries;

    private FarmingSystem selectedFarmingSystem;
    private List<FarmingSystem> farmingSystemsList;

    private PracticeTheme selectedPracticeTheme;
    private PracticeLevel selectedPracticeLevel;
    private SetMultimap<PracticeTheme, PracticeLevel> practiceThemesLevels;

    private ProductionSystemCategory selectedProductionSystemCategory;
    private ProductionSystem selectedProductionSystem;
    private SetMultimap<ProductionSystemCategory, ProductionSystem> productionSystemsCategories;

    private Indicator selectedIndicator;
    private SubIndicator selectedSubIndicator;
    private SetMultimap<Indicator, SubIndicator> indicatorSubIndicators;

    private ContextVariable selectedContextVariable;
    private ContextValue selectedContextValue;
    private SetMultimap<ContextVariable, ContextValue> contextVariablesValues;
    // END Variables

    @PostConstruct
    public void init() {
        regionsCountries = HashMultimap.create();
        practiceThemesLevels = HashMultimap.create();
        productionSystemsCategories = HashMultimap.create();
        indicatorSubIndicators = HashMultimap.create();
        contextVariablesValues = HashMultimap.create();

        farmingSystemsList = new ArrayList<>();
        Set<String> farmingSystemsNames = new HashSet<>();

        for (FarmingSystem farmingSystem : farmingSystemRepository.findAll()) {
            if (!farmingSystemsNames.contains(farmingSystem.getName())) {
                farmingSystemsNames.add(farmingSystem.getName());
                farmingSystemsList.add(farmingSystem);
            }
        }

        Collections.sort(farmingSystemsList, new Comparator<FarmingSystem>() {
            @Override
            public int compare(FarmingSystem first, FarmingSystem second) {
                return (first.getName().charAt(0) > second.getName().charAt(0)) ? 1 : -1;
            }
        });

        for (Region region : regionRepository.findAll()) {
            for (Country country : countryRepository.findByRegion(region)) {
                regionsCountries.put(region, country);
            }
        }

        for (PracticeTheme theme : practiceThemeRepository.findAll()) {
            for (PracticeLevel level : practiceLevelRepository.findByTheme(theme)) {
                practiceThemesLevels.put(theme, level);
            }
        }

        for (ProductionSystemCategory category : productionSystemCategoryRepository.findAll()) {
            for (ProductionSystem productionSystem : productionSystemRepository.findByCategory(category)) {
                productionSystemsCategories.put(category, productionSystem);
            }
        }

        for (Indicator indicator : indicatorRepository.findAll()) {
            for (SubIndicator subIndicator : subIndicatorRepository.findByIndicator(indicator)) {
                indicatorSubIndicators.put(indicator, subIndicator);
            }
        }

        for (ContextVariable variable : contextVariableRepository.findAll()) {
            for (ContextValue value : contextValueRepository.findByContextVariable(variable)) {
                contextVariablesValues.put(variable, value);
            }
        }
    }

    // BLOCK Parent Filters
    public Iterable<Region> getRegionsList() {
        return regionsCountries.keySet();
    }

    public Iterable<PracticeTheme> getThemesList() {
        return practiceThemesLevels.keySet();
    }

    public Iterable<ProductionSystemCategory> getProductionSystemCategoriesList() {
        return productionSystemsCategories.keySet();
    }

    public Iterable<Indicator> getIndicatorList() {
        return indicatorSubIndicators.keySet();
    }

    public Iterable<ContextVariable> getContextVariablesList() {
        return contextVariablesValues.keySet();
    }
    // END Parent Filters

    private SelectItemGroup filterItems(Object parent) {
        SelectItemGroup group = new SelectItemGroup();
        List<SelectItem> items = new LinkedList<>();

        if (parent instanceof Region) {
            Region region = (Region) parent;
            group.setLabel(region.getName());
            for (Country country : regionsCountries.get(region)) {
                items.add(new SelectItem(country.getCode(), country.getName()));
            }
        } else if (parent instanceof PracticeTheme) {
            PracticeTheme practiceTheme = (PracticeTheme) parent;
            group.setLabel(practiceTheme.getName());
            for (PracticeLevel level : practiceThemesLevels.get(practiceTheme)) {
                items.add(new SelectItem(level.getId(), level.getName()));
            }
        } else if (parent instanceof ProductionSystemCategory) {
            ProductionSystemCategory productionSystemCategory = (ProductionSystemCategory) parent;
            group.setLabel(productionSystemCategory.getName());
            for (ProductionSystem productionSystem : productionSystemsCategories.get(productionSystemCategory)) {
                items.add(new SelectItem(productionSystem.getId(), productionSystem.getName()));
            }
        } else if (parent instanceof Indicator) {
            Indicator indicator = (Indicator) parent;
            group.setLabel(indicator.getName());
            for (SubIndicator subIndicator : indicatorSubIndicators.get(indicator)) {
                items.add(new SelectItem(subIndicator.getId(), subIndicator.getName()));
            }
        } else if (parent instanceof ContextVariable) {
            ContextVariable contextVariable = (ContextVariable) parent;
            group.setLabel(contextVariable.getName());
            for (ContextValue value : contextVariablesValues.get(contextVariable)) {
                items.add(new SelectItem(value.getId(), value.getName()));
            }
        }

        SelectItem[] itemsArray = new SelectItem[items.size()];
        group.setSelectItems(items.toArray(itemsArray));
        return group;
    }

    private List<SelectItem> formItemsList(Object selectedItemsParent, SetMultimap itemsMap) {
        List<SelectItem> allItems = new LinkedList<>();

        if (selectedItemsParent != null) {
            allItems.add(filterItems(selectedItemsParent));
        } else {
            for (Object itemsParent : itemsMap.keySet()) {
                allItems.add(filterItems(itemsParent));
            }
        }

        return allItems;
    }

    // BLOCK Countries Filter
    public List<SelectItem> getCountriesList() {
        return formItemsList(selectedRegion, regionsCountries);
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

    // BLOCK Farming Systems Filter
    public List<FarmingSystem> getFarmingSystemsList() {
        return farmingSystemsList;
    }

    public Integer getSelectedFarmingSystemId() {
        return selectedFarmingSystem != null ? selectedFarmingSystem.getId() : null;
    }

    public void setSelectedFarmingSystemId(Integer selectedFarmingSystemId) {
        // Not required, as only the filers are used for search
    }
    // END Farming Systems Filter

    // BLOCK Practice Level Filter
    public List<SelectItem> getPracticeLevelsList() {
        return formItemsList(selectedPracticeTheme, practiceThemesLevels);
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

    // BLOCK Production System Filter
    public List<SelectItem> getProductionSystemsList() {
        return formItemsList(selectedProductionSystemCategory, productionSystemsCategories);
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
    // END Production System Filter

    // BLOCK Sub-indicators Filter
    public List<SelectItem> getSubIndicatorsList() {
        return formItemsList(selectedIndicator, indicatorSubIndicators);
    }

    public Integer getSelectedIndicatorId() {
        return selectedIndicator != null ? selectedIndicator.getId() : null;
    }

    public void setSelectedIndicatorId(Integer selectedIndicatorId) {
        this.selectedIndicator = indicatorRepository.findOne(selectedIndicatorId);
    }

    public Integer getSelectedSubIndicatorId() {
        return selectedSubIndicator != null ? selectedSubIndicator.getId() : null;
    }

    public void setSelectedSubIndicatorId(Integer selectedSubIndicatorId) {

    }
    // END Sub-indicators Filter

    // BLOCK Context Values Filter
    public List<SelectItem> getContextValuesList() {
        return formItemsList(selectedContextVariable, contextVariablesValues);
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

    // Search Properties
    public String getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getFiltersInfo() {
        return filtersInfo;
    }

    public void setFiltersInfo(String filtersInfo) {
        this.filtersInfo = filtersInfo;
    }
}
