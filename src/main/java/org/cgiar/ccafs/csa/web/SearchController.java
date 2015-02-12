package org.cgiar.ccafs.csa.web;

import org.cgiar.ccafs.csa.domain.Region;
import org.cgiar.ccafs.csa.service.SearchService;
import org.cgiar.ccafs.csa.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("view")
public class SearchController {

    @Autowired
    private SearchService search;

    private Iterable<Region> regions;

    public Iterable<Region> getRegions() {
        if (regions == null) {
            regions = search.getRegions();
        }
        return regions;
    }

    private Region selectedRegion;

    public Region getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(Region selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/index.xhtml";
    }
}
