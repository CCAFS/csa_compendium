package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the practice_dimensions database table.
 */
@Entity
@Table(name = "practice_dimensions")
public class PracticeDimension implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Dimension dimension;

    private float weight;

    @ManyToOne
    @JoinColumn(name = "practice_id")
    private Practice practice;

    public Integer getId() {
        return this.id;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Practice getPractice() {
        return this.practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

}