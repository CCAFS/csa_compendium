package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the indicators database table.
 */
@Entity
@Table(name = "sub_indicators")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "from_compendium", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class SubIndicator extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "indicator_id")
    private Indicator indicator;

    @OneToMany(mappedBy = "subIndicator")
    private List<TreatmentOutcome> treatmentOutcomes = new ArrayList<>();

    @Override
    public Integer getId() {
        return this.id;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public List<TreatmentOutcome> getTreatmentOutcomes() {
        return treatmentOutcomes;
    }
}