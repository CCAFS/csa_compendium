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

    @ManyToOne
    @JoinColumn(name = "production_system_id")
    private ProductionSystem productionSystem;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DATE)
    @Column(name = "start_date")
    private Date nativeStartDate;
    private transient LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DATE)
    @Column(name = "end_date")
    private Date nativeEndDate;
    private transient LocalDate endDate;

    @Column(name = "mean_value")
    private double meanValue;

    @Column(name = "standard_error")
    private double standardError;

    @Column(name = "standard_deviation")
    private double standardDeviation;

    private String result;

    @Column(name = "soil_depth")
    private String soilDepth;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "sub_indicator_id")
    private SubIndicator subIndicator;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private MeasureUnit measureUnit;

    public Integer getId() {
        return id;
    }

    public ProductionSystem getProductionSystem() {
        return productionSystem;
    }

    public void setProductionSystem(ProductionSystem productionSystem) {
        this.productionSystem = productionSystem;
    }

    public LocalDate getStartDate() {
        if (startDate == null) startDate = new LocalDate(nativeStartDate);
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        if (startDate != null) nativeStartDate = startDate.toDate();
    }

    public LocalDate getEndDate() {
        if (endDate == null) endDate = new LocalDate(nativeEndDate);
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        if (endDate != null) nativeEndDate = endDate.toDate();
    }

    public double getMeanValue() {
        return meanValue;
    }

    public void setMeanValue(double meanValue) {
        this.meanValue = meanValue;
    }

    public double getStandardError() {
        return standardError;
    }

    public void setStandardError(double standardError) {
        this.standardError = standardError;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSoilDepth() {
        return soilDepth;
    }

    public void setSoilDepth(String soilDepth) {
        this.soilDepth = soilDepth;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public SubIndicator getSubIndicator() {
        return this.subIndicator;
    }

    public void setSubIndicator(SubIndicator subIndicator) {
        this.subIndicator = subIndicator;
    }

    public MeasureUnit getMeasureUnit() {
        return this.measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

}
