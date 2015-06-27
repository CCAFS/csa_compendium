package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * The persistent class for the indicators database table.
 */
@Entity
@Table(name = "indicators")
public class Indicator implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "indicator")
    private Set<IndicatorPillar> pillars = new HashSet<>();

    @OneToMany(mappedBy = "indicator")
    private List<SubIndicator> subIndicators = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Indicator)) return false;
        Indicator indicator = (Indicator) o;
        if (!id.equals(indicator.id)) return false;
        return name.equals(indicator.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<IndicatorPillar> getPillars() {
        return this.pillars;
    }

    public List<SubIndicator> getSubIndicators() {
        return subIndicators;
    }

    public IndicatorPillar addPillar(IndicatorPillar pillar) {
        getPillars().add(pillar);
        pillar.setIndicator(this);

        return pillar;
    }

    public IndicatorPillar removePillar(IndicatorPillar pillar) {
        getPillars().remove(pillar);
        pillar.setIndicator(null);

        return pillar;
    }

    public SubIndicator addSubIndicator(SubIndicator subIndicator) {
        getSubIndicators().add(subIndicator);
        subIndicator.setIndicator(this);

        return subIndicator;
    }

    public SubIndicator removeSubIndicator(SubIndicator subIndicator) {
        getSubIndicators().remove(subIndicator);
        subIndicator.setIndicator(null);

        return subIndicator;
    }

    public Pillar getPillar() {
        IndicatorPillar indicatorPillar = Collections.max(pillars);
        return indicatorPillar.getPillar();
    }
}