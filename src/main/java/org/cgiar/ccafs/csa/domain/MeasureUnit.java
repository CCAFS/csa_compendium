package org.cgiar.ccafs.csa.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The persistent class for the units database table.
 */
@Entity
@Table(name = "units")
public class MeasureUnit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String options;

    @Transient
    private List<String> optionsList;

    private String symbols;

    @Transient
    private List<String> symbolsList;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOptionsList() {
        if (this.optionsList == null) {
            this.optionsList = new ArrayList<>();
        }
        return this.optionsList;
    }

    public void addOption(String option) {
        getOptionsList().add(option);
    }

    public List<String> getSymbolsList() {
        if (this.symbolsList == null) {
            this.symbolsList = new ArrayList<>();
        }
        return this.symbolsList;
    }

    public void addSymbol(String symbol) {
        getSymbolsList().add(symbol);
    }

    @PrePersist
    @PreUpdate
    private void recordSaved() {
        options = StringUtils.join(getOptionsList(), ";");
        symbols = StringUtils.join(getSymbolsList(), ";");
    }

    @PostLoad
    private void recordLoaded() {
        if (options != null && options.trim().length() == 0) {
            String[] data = options.split(";");
            optionsList = Arrays.asList(data);
        }

        if (symbols != null && symbols.trim().length() == 0) {
            String[] data = symbols.split(";");
            symbolsList = Arrays.asList(data);
        }
    }

}
