package org.cgiar.ccafs.csa.domain;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;


/**
 * The persistent class for the outcomes database table.
 */
@Entity
@Table(name = "treatment_outcomes")
public class TreatmentOutcome implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "initial_value")
    private float initialValue;

    @Column(name = "final_value")
    private float finalValue;

    private String result;

    private int perceivedChange;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "indicator_id")
    private Indicator indicator;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private MeasureUnit measureUnit;

    public LocalDate getStartDate() {
        return new LocalDate(this.startDate);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate.toDate();
    }

    public LocalDate getEndDate() {
        return new LocalDate(this.endDate);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate.toDate();
    }

    public float getInitialValue() {
        return this.initialValue;
    }

    public void setInitialValue(float initialValue) {
        this.initialValue = initialValue;
    }

    public float getFinalValue() {
        return this.finalValue;
    }

    public void setFinalValue(float finalValue) {
        this.finalValue = finalValue;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPerceivedChange() {
        return perceivedChange;
    }

    public void setPerceivedChange(int perceivedChange) {
        this.perceivedChange = perceivedChange;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Indicator getIndicator() {
        return this.indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public MeasureUnit getUnit() {
        return this.measureUnit;
    }

    public void setUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

}
