package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the synergies database table.
 */
@Entity
@Table(name = "synergies")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "compendium", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Synergy implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Boolean exclusive;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "main_practice_id")
    private Practice mainPractice;

    @ManyToOne
    @JoinColumn(name = "second_practice_id")
    private Practice secondPractice;

    public Integer getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isExclusive() {
        return this.exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Practice getMainPractice() {
        return this.mainPractice;
    }

    public void setMainPractice(Practice mainPractice) {
        this.mainPractice = mainPractice;
    }

    public Practice getSecondPractice() {
        return this.secondPractice;
    }

    public void setSecondPractice(Practice secondPractice) {
        this.secondPractice = secondPractice;
    }

}