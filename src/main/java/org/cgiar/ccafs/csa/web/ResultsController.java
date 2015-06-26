package org.cgiar.ccafs.csa.web;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import org.apache.commons.lang3.text.WordUtils;
import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.Strings.nullToEmpty;

@Controller
@ManagedBean
@Scope("session")
public class ResultsController implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExperimentArticleRepository experimentArticleRepository;

    @Autowired
    private ExperimentContextRepository experimentContextRepository;

    @Autowired
    private ContextValueRepository contextValueRepository;

    @Autowired
    private PracticeLevelRepository practiceLevelRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private SubIndicatorRepository subIndicatorRepository;

    @Autowired
    private ProductionSystemRepository productionSystemRepository;

    @Autowired
    private SearchController searchController;

    // Search Parameters
    private String searchKeywords;
    private String filters;

    private SortedSetMultimap<String, String> searchParametersMap = TreeMultimap.create();
    private Map<String, String> searchParametersInfo = new TreeMap<>();
    private Set<ExperimentContext> experimentContexts = new LinkedHashSet<>(32);

    private void reset() {
        searchParametersMap.clear();
        searchParametersInfo.clear();
        experimentContexts.clear();
    }

    @Transactional
    public void performSearch() {
        if (!nullToEmpty(searchKeywords).equals(searchController.getSearchKeywords())) {
            reset();
            searchKeywords = searchController.getSearchKeywords();
            String[] parameterList = nullToEmpty(searchKeywords).split(",| ");
            for (String param : parameterList) {
                ExperimentArticle article = experimentArticleRepository.findByCode(param);
                if (article != null) {
                    experimentContexts.addAll(article.getContexts());
                }
            }
            searchParametersMap.putAll("Keywords", Arrays.asList(parameterList));
        } else if (!nullToEmpty(filters).equals(searchController.getFilters())) {
            reset();
            filters = searchController.getFilters();
            String filtersInfo = searchController.getFiltersInfo();
            String[] parameterList = nullToEmpty(filters).split(",|:");
            String[] parameterInfoList = nullToEmpty(filtersInfo).split(":");

            for (int infoPosition = 0, paramPosition = 0; infoPosition < parameterInfoList.length; infoPosition++, paramPosition += 2) {
                String value = parameterList[paramPosition + 1];
                switch (parameterList[paramPosition]) {
                    case "regions":
                        experimentContexts.addAll(experimentContextRepository.findByLocationCountryRegionCode(value));
                        break;
                    case "countries":
                        experimentContexts.addAll(experimentContextRepository.findByLocationCountryCode(value));
                        break;
                    case "farmingSystems":
                        experimentContexts.addAll(experimentContextRepository.findByFarmingSystemId(Integer.valueOf(value)));
                        break;
                    case "themes":
                        for (ExperimentArticle article : experimentArticleRepository.findByThemeId(Integer.valueOf(value))) {
                            experimentContexts.addAll(article.getContexts());
                        }
                        break;
                    case "practiceLevels":
                        PracticeLevel level = practiceLevelRepository.findOne(Integer.valueOf(value));
                        for (ExperimentArticle article : experimentArticleRepository.findByThemeId(level.getTheme().getId())) {
                            experimentContexts.addAll(article.getContexts());
                        }
                        break;
                    case "productionSystems":
                        ProductionSystem productionSystem = productionSystemRepository.findOne(Integer.valueOf(value));
                        if (productionSystem != null) {
                            experimentContexts.addAll(productionSystem.getExperimentContexts());
                        }
                        break;
                    case "indicators":
                        Indicator indicator = indicatorRepository.findOne(Integer.valueOf(value));
                        for (SubIndicator subIndicator : indicator.getSubIndicators()) {
                            for (TreatmentOutcome outcome : subIndicator.getTreatmentOutcomes()) {
                                experimentContexts.add(outcome.getTreatment().getExperimentContext());
                            }
                        }
                        break;
                    case "subIndicators":
                        SubIndicator subIndicator = subIndicatorRepository.findOne(Integer.valueOf(value));
                        for (TreatmentOutcome outcome : subIndicator.getTreatmentOutcomes()) {
                            experimentContexts.add(outcome.getTreatment().getExperimentContext());
                        }
                        break;
                    case "contextValues":
                        ContextValue contextValue = contextValueRepository.findOne(Integer.valueOf(value));
                        if (contextValue != null) {
                            for (ExperimentArticle article : contextValue.getExperimentArticles()) {
                                experimentContexts.addAll(article.getContexts());
                            }
                        }
                }

                searchParametersMap.put(parameterList[paramPosition], parameterInfoList[infoPosition]);
            }
        }
    }

    public Set<ExperimentContext> getExperimentContexts() {
        return experimentContexts;
    }

    public Map<String, String> getSearchParametersInfo() {
        if (searchParametersInfo.isEmpty()) {
            for (String key : searchParametersMap.keySet()) {
                String newKey = WordUtils.capitalize(LOWER_CAMEL.to(LOWER_HYPHEN, key).replace('-', ' '));
                String value = Joiner.on(", ").join(searchParametersMap.get(key));
                searchParametersInfo.put(newKey, value);
            }
        }
        return searchParametersInfo;
    }

    public List<String> getSearchParametersList() {
        return Lists.newArrayList(getSearchParametersInfo().keySet());
    }
}
