package org.cgiar.ccafs.csa.web;

import org.cgiar.ccafs.csa.service.TestService;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("view")
public class DataController {
    @Autowired
    TestService testService;

    int counter = 0;

    private String jsfTestString = "This string came from server side";

    /**
     * This method demonstrates grabbing a value from the view scope and the
     * session scope.
     *
     * Something I wanted to see to verify the same Spring context is managing
     * each scope.
     */
    public void doSomething() {
        setJsfTestString(testService.getMessage() + " " + counter++);
    }

    public String getJsfTestString() {
        return jsfTestString;
    }

    public void setJsfTestString(String jsfTestString) {
        this.jsfTestString = jsfTestString;
    }

}