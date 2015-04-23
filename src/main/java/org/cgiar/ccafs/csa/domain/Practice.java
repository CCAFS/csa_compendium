package org.cgiar.ccafs.csa.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The persistent class for the practices database table.
 */
@Entity
@Table(name = "practices")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "from_compendium", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Practice extends AbstractInformationEntity implements ContextualizedEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tags;

    @Transient
    private List<String> tagsList;

    @OneToMany(mappedBy = "practice")
    private List<Treatment> treatments;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private PracticeLevel practiceLevel;

    @OneToMany(mappedBy = "mainPractice")
    private List<Synergy> synergies = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "practice_context_values"
            , joinColumns = {
            @JoinColumn(name = "practice_id")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "context_value_id")
    }
    )
    private List<ContextValue> contextValues = new ArrayList<>();

    @Override
    public Integer getId() {
        return this.id;
    }

    public List<String> getTagsList() {
        if (this.tagsList == null) {
            this.tagsList = new ArrayList<>();
        }
        return this.tagsList;
    }

    public void addTag(String tag) {
        getTagsList().add(tag);
    }

    @PrePersist
    @PreUpdate
    private void recordSaved() {
        tags = StringUtils.join(getTagsList(), ";");
    }

    @PostLoad
    private void recordLoaded() {
        if (tags != null && tags.trim().length() == 0) {
            String[] data = tags.split(";");
            tagsList = Arrays.asList(data);
        }
    }

    public PracticeLevel getPracticeLevel() {
        return practiceLevel;
    }

    public void setPracticeLevel(PracticeLevel practiceLevel) {
        this.practiceLevel = practiceLevel;
    }

    public List<Synergy> getSynergies() {
        return this.synergies;
    }

    public List<ContextValue> getContextValues() {
        return this.contextValues;
    }

}
