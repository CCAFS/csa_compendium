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
import org.springframework.web.bind.annotation.RestController;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_HYPHEN;

@RestController
@ManagedBean
@Scope("session")
public class ResultsController implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExperimentArticleRepository experimentArticleRepository;

    private SortedSetMultimap<String, String> searchParametersMap;
    private Map<String, String> searchParametersInfo;
    private Set<ExperimentArticle> articles;

    public String performSearch() {
        articles = new LinkedHashSet<>();
        searchParametersMap = TreeMultimap.create();
        searchParametersInfo = null;

        Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String[] parameterList;
        String[] parameterInfoList;
        String searchKeywords = parameterMap.get("search:keywords");

        if (!searchKeywords.isEmpty()) {
            parameterList = searchKeywords.split(",| ");
            for (String param : parameterList) {
                articles.add(experimentArticleRepository.findByCode(param));
            }
            searchParametersMap.putAll("Keywords", Arrays.asList(parameterList));
        } else {
            String searchParams = parameterMap.get("search:filters");
            parameterList = searchParams.split(",|:");

            String searchParamsInfo = parameterMap.get("search:filtersInfo");
            parameterInfoList = searchParamsInfo.split(":");

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

        return "results";
    }

    public Set<ExperimentArticle> getArticles() {
        return articles;
    }

    public Map<String, String> getSearchParametersInfo() {
        if (searchParametersInfo == null) {
            searchParametersInfo = new TreeMap<>();
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
