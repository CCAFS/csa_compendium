package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the indicator_pillars database table.
 */
@Entity
@Table(name = "indicator_pillars")
public class IndicatorPillar implements Comparable<IndicatorPillar>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Pillar pillar;

    private float weight = 1.0f;

    @ManyToOne
    @JoinColumn(name = "indicator_id")
    private Indicator indicator;

    public IndicatorPillar() {
    }

    public IndicatorPillar(Pillar pillar, float weight) {
        this.pillar = pillar;
        this.weight = weight;
    }

    public Integer getId() {
        return this.id;
    }

    public Pillar getPillar() {
        return this.pillar;
    }

    public void setPillar(Pillar pillar) {
        this.pillar = pillar;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Indicator getIndicator() {
        return this.indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    @Override
    public int compareTo(IndicatorPillar indicatorPillar) {
        return this.getWeight() > indicatorPillar.getWeight() ? 1 : -1;
    }
}