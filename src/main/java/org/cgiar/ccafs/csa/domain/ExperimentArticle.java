package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The persistent class for the sources database table.
 * 
 */
@Entity
@Table(schema = "public", name="experiment_articles")
public class ExperimentArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ARTICLES_ID_GENERATOR", sequenceName="ARTICLES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLES_ID_GENERATOR")
	private Integer id;
	
	protected String code;
	
	protected String title;
		
	protected String outline;
	
	//protected String link;
	
	protected Integer rating;
	
	private String authors;
		
	@Transient protected List<String> authorsList;
	
	private String contacts;
	
	@Transient protected List<String> contactsList;

	@ManyToOne
	@JoinColumn(name="language")
	protected Language language;
	
	@Column(name="publication")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
	protected LocalDate publicationDate;

	@ManyToOne
	@JoinColumn(name="theme_id")
	protected Theme theme;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	protected ExperimentLocation location;

	@OneToMany(mappedBy="experimentArticle")
	protected List<InitialCondition> initialConditions;
	
	@ManyToMany
	@JoinTable(
		name="experiment_context_values"
		, joinColumns={
			@JoinColumn(name="experiment_article_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="context_value_id")
			}
		)
	protected List<ContextValue> contextValues;
	
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

	public String getOutine() {
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

	public List<String> getAuthorsList() {
		if (this.authorsList == null) {
			this.authorsList = new ArrayList<String>();
		}
		return this.authorsList;
	}
	
	public void addAuthor(String author) {		
		getAuthorsList().add(author);
	}
	
	public List<String> getContactsList() {
		if (this.contactsList == null) {
			this.contactsList = new ArrayList<String>();
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
		return this.publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	public ExperimentLocation getLocation() {
		return this.location;
	}

	public void setLocation(ExperimentLocation location) {
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