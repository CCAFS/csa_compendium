package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the levels database table.
 */
@Entity
@Table(name = "practice_levels")
public class PracticeLevel extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private PracticeTheme theme;

    @OneToMany(mappedBy = "practiceLevel")
    private List<Practice> practices;

    @Override
    public Integer getId() {
        return this.id;
    }

    public PracticeTheme getTheme() {
        return theme;
    }

    public void setTheme(PracticeTheme theme) {
        this.theme = theme;
    }

    public List<Practice> getPractices() {
        if (this.practices == null) {
            this.practices = new ArrayList<Practice>();
        }
        return this.practices;
    }

    public Practice addPractice(Practice practice) {
        getPractices().add(practice);
        practice.setPracticeLevel(this);

        return practice;
    }

    public Practice removePractice(Practice practice) {
        getPractices().remove(practice);
        practice.setPracticeLevel(null);

        return practice;
    }
}