package org.cgiar.ccafs.csa.web;

import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.ContextVariableRepository;
import org.cgiar.ccafs.csa.repository.ProductionSystemCategoryRepository;
import org.cgiar.ccafs.csa.repository.RegionRepository;
import org.cgiar.ccafs.csa.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope("view")
public class SearchController {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private ProductionSystemCategoryRepository productionSystemCategoryRepository;

    @Autowired
    private ContextVariableRepository contextVariableRepository;

    private ContextVariable selectedContextVariable;

    public Iterable<Region> getRegions() {
        return regionRepository.findAll();
    }

    public Iterable<Theme> getThemes() {
        return themeRepository.findAll();
    }

    public Iterable<ProductionSystemCategory> getProductionSystemCategories() {
        return productionSystemCategoryRepository.findAll();
    }

    public Iterable<ContextVariable> getContextVariables() {
        return contextVariableRepository.findAll();
    }

    public ContextVariable getSelectedContextVariable() {
        return selectedContextVariable;
    }

    public void setSelectedContextVariable(ContextVariable selectedContextVariable) {
        this.selectedContextVariable = selectedContextVariable;
    }

    private List<ContextValue> contextValues;

    public List<ContextValue> getContextValues() {
        return contextValues;
    }

    public void updateContextValues() {
        this.contextValues = selectedContextVariable.getContextValues();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/index.xhtml";
    }
}
