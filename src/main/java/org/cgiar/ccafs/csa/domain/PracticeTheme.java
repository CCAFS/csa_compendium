package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the themes database table.
 */
@Entity
@Table(name = "practice_themes")
public class PracticeTheme extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "theme")
    private Set<PracticeLevel> practiceLevels = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && o instanceof PracticeTheme && id.equals(((PracticeTheme) o).getId());
    }

    @Override
    public int hashCode() {
        return id * super.hashCode();
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public Set<PracticeLevel> getPracticeLevels() {
        return this.practiceLevels;
    }

    public PracticeLevel addPracticeLevel(PracticeLevel level) {
        getPracticeLevels().add(level);
        level.setTheme(this);

        return level;
    }

    public PracticeLevel removePracticeLevel(PracticeLevel level) {
        getPracticeLevels().remove(level);
        level.setTheme(null);

        return level;
    }
}