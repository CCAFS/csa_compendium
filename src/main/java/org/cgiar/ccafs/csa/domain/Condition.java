package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

/**
 * The persistent class for the conditions database table.
 */
@Entity
@Table(name = "conditions")
public class Condition extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private MeasureUnit measureUnit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public Integer getId() {
        return this.id;
    }

    public MeasureUnit getMeasureUnit() {
        return this.measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }
}