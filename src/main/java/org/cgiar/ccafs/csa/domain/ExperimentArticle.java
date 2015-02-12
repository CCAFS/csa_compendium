package org.cgiar.ccafs.csa.domain;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;


/**
 * The persistent class for the sources database table.
 */
@Entity
@Table(name = "experiment_articles")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "compendium", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class ExperimentArticle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String title;

    private String outline;

    private String link;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "farming_system_id")
    private FarmingSystem farmingSystem;

    private String authors;

    @Transient
    private List<String> authorsList;

    private String contacts;

    @Transient
    private List<String> contactsList;

    @ManyToOne
    @JoinColumn(name = "language")
    private Language language;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DATE)
    @Column(name = "publication")
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "experimentArticle")
    private List<InitialCondition> initialConditions;

    @ManyToMany
    @JoinTable(
            name = "experiment_context_values"
            , joinColumns = {
            @JoinColumn(name = "experiment_article_id")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "context_value_id")
    }
    )
    private List<ContextValue> contextValues;

    // Methods //

    public Integer getId() {
        return this.id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOutline() {
        return this.outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public FarmingSystem getFarmingSystem() {
        return farmingSystem;
    }

    public void setFarmingSystem(FarmingSystem farmingSystem) {
        this.farmingSystem = farmingSystem;
    }

    public List<String> getAuthorsList() {
        if (this.authorsList == null) {
            this.authorsList = new ArrayList<>();
        }
        return this.authorsList;
    }

    public void addAuthor(String author) {
        getAuthorsList().add(author);
    }

    public List<String> getContactsList() {
        if (this.contactsList == null) {
            this.contactsList = new ArrayList<>();
        }
        return this.contactsList;
    }

    public void addContact(String author) {
        getContactsList().add(author);
    }

    @PrePersist
    @PreUpdate
    protected void recordSaved() {
        authors = StringUtils.join(getAuthorsList(), ";");
        contacts = StringUtils.join(getAuthorsList(), ";");
    }

    @PostLoad
    protected void recordLoaded() {
        if (authors != null && authors.trim().length() == 0) {
            String[] data = authors.split(";");
            authorsList = Arrays.asList(data);
        }

        if (contacts != null && contacts.trim().length() == 0) {
            String[] data = contacts.split(";");
            contactsList = Arrays.asList(data);
        }
    }

    public LocalDate getPublicationDate() {
        return new LocalDate(this.publicationDate);
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate.toDate();
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<ContextValue> getContextValues() {
        return this.contextValues;
    }

    public List<InitialCondition> getInitialConditions() {
        return this.initialConditions;
    }

    public InitialCondition addInitialCondition(InitialCondition initialCondition) {
        getInitialConditions().add(initialCondition);
        initialCondition.setExperiment(this);

        return initialCondition;
    }

    public InitialCondition removeInitialCondition(InitialCondition initialCondition) {
        getInitialConditions().remove(initialCondition);
        initialCondition.setExperiment(null);

        return initialCondition;
    }

}
