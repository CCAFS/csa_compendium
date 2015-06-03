package org.cgiar.ccafs.csa.web;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import org.apache.commons.lang3.text.WordUtils;
import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.cgiar.ccafs.csa.repository.ExperimentArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
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
    private SearchController searchController;

    // Search Parameters
    private String searchKeywords;
    private String filters;

    private SortedSetMultimap<String, String> searchParametersMap = TreeMultimap.create();
    private Map<String, String> searchParametersInfo = new TreeMap<>();
    private Set<ExperimentArticle> articles = new LinkedHashSet<>();

    private void reset() {
        searchParametersMap.clear();
        searchParametersInfo.clear();
        articles.clear();
    }

    public void performSearch() {
        if (!nullToEmpty(searchKeywords).equals(searchController.getSearchKeywords())) {
            reset();
            searchKeywords = searchController.getSearchKeywords();
            String[] parameterList = nullToEmpty(searchKeywords).split(",| ");
            for (String param : parameterList) {
                articles.add(experimentArticleRepository.findByCode(param));
            }
            searchParametersMap.putAll("Keywords", Arrays.asList(parameterList));
        } else if (!nullToEmpty(filters).equals(searchController.getFilters())) {
            reset();
            filters = searchController.getFilters();
            String filtersInfo = searchController.getFiltersInfo();
            String[] parameterList = nullToEmpty(filters).split(",|:");
            String[] parameterInfoList = nullToEmpty(filtersInfo).split(":");

            for (int infoPosition = 0, paramPosition = 0; infoPosition < parameterInfoList.length; infoPosition++, paramPosition += 2) {
                if ("regions".equals(parameterList[paramPosition])) {
                    articles.addAll(experimentArticleRepository.findByLocationCountryRegionCode(parameterList[paramPosition + 1]));
                }

                if ("countries".equals(parameterList[paramPosition])) {
                    articles.addAll(experimentArticleRepository.findByLocationCountryCode(parameterList[paramPosition + 1]));
                }

                if ("farmingSystems".equals(parameterList[paramPosition])) {
                    articles.addAll(experimentArticleRepository.findByFarmingSystemId(
                            Integer.valueOf(parameterList[paramPosition + 1])));
                }

                if ("themes".equals(parameterList[paramPosition])) {
                    articles.addAll(experimentArticleRepository.findByPracticeThemeId(
                            Integer.valueOf(parameterList[paramPosition + 1])));
                }

                if ("practiceLevels".equals(parameterList[paramPosition])) {

                }

                if ("productionSystems".equals(parameterList[paramPosition])) {

                }

                searchParametersMap.put(parameterList[paramPosition], parameterInfoList[infoPosition]);
            }
        }
    }

    public Set<ExperimentArticle> getArticles() {
        return articles;
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
