package org.cgiar.ccafs.csa.domain;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static org.cgiar.ccafs.csa.domain.AbstractInformationEntity.joiner;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;


/**
 * The persistent class for the sources database table.
 */
@Entity
@Table(name = "experiment_articles")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "from_compendium", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class ExperimentArticle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    @Column(length = 100000)
    private String title;

    @Column(length = 100000)
    private String outline;

    private String link;

    private Integer rating;

    @Column(length = 100000)
    private String authors;

    @Transient
    private List<String> authorsList = new ArrayList<>();

    @Column(length = 100000)
    private String contacts;

    @Transient
    private List<String> contactsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "language")
    private Language language;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DATE)
    @Column(name = "publication")
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private PracticeTheme theme;

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
    private Set<ContextValue> contextValues = new HashSet<>();

    @OneToMany(mappedBy = "experiment")
    private List<ExperimentContext> contexts = new ArrayList<>();

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<String> getAuthorsList() {
        return this.authorsList;
    }

    public void addAuthor(String author) {
        getAuthorsList().add(author);
    }

    public List<String> getContactsList() {
        return this.contactsList;
    }

    public void addContact(String contactEmail) {
        getContactsList().add(contactEmail);
    }

    @PrePersist
    @PreUpdate
    protected void recordSaved() {
        authors = joiner.join(getAuthorsList());
        contacts = joiner.join(getContactsList());
    }

    @PostLoad
    protected void recordLoaded() {
        if (authors != null && authors.trim().length() != 0) {
            String[] data = authors.split(";");
            authorsList = Arrays.asList(data);
        }

        if (contacts != null && contacts.trim().length() != 0) {
            String[] data = contacts.split(";");
            contactsList = Arrays.asList(data);
        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LocalDate getPublicationDate() {
        return new LocalDate(this.publicationDate);
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate.toDate();
    }

    public PracticeTheme getTheme() {
        return theme;
    }

    public void setTheme(PracticeTheme theme) {
        this.theme = theme;
    }

    public Set<ContextValue> getContextValues() {
        return this.contextValues;
    }

    public ContextValue setContextVariable(ContextValue contextValue) {
        if (contextValue != null) getContextValues().add(contextValue);
        return contextValue;
    }

    public boolean unsetContextVariable(ContextValue contextValue) {
        return getContextValues().remove(contextValue);
    }

    public List<ExperimentContext> getContexts() {
        return contexts;
    }

    public ExperimentContext addContext(ExperimentContext context) {
        getContexts().add(context);
        context.setExperiment(this);
        return context;
    }

    public ExperimentContext removeExperimentContext(ExperimentContext context) {
        getContexts().remove(context);
        context.setExperiment(null);
        return context;
    }
}
