package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the indicators database table.
 */
@Entity
@Table(name = "indicators")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "from_compendium", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Indicator extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "indicator")
    private List<IndicatorPillar> pillars = new ArrayList<>();

    private String category;

    @Override
    public Integer getId() {
        return this.id;
    }

    public List<IndicatorPillar> getPillars() {
        return this.pillars;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}