package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

/**
 * The persistent class for the farming_systems database table.
 */
@Entity
@Table(name = "farming_systems")
public class FarmingSystem extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "region_code")
    private Region region;

    @Override
    public Integer getId() {
        return this.id;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
