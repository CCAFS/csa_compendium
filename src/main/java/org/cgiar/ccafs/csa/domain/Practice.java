package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The persistent class for the practices database table.
 * 
 */
@Entity
@Table(schema = "public", name="practices")
public class Practice extends AbstractInformationEntity implements ContextualizedEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRACTICES_ID_GENERATOR", sequenceName="PRACTICES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRACTICES_ID_GENERATOR")
	private Integer id;
	
	private String tags;

	@Transient protected List<String> tagsList;

	@OneToMany(mappedBy="practice")
	protected List<Treatment> treatments;
	
	@ManyToOne
	@JoinColumn(name="level")
	protected PracticeLevel practiceLevel;

	@OneToMany(mappedBy="mainPractice")
	protected List<Synergy> synergies;
	
	@OneToMany(mappedBy="practice")
	protected List<PracticeDimension> dimensions;
	
	@ManyToMany
	@JoinTable(
		name="practice_context_values"
		, joinColumns={
			@JoinColumn(name="practice_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="context_value_id")
			}
		)
	protected List<ContextValue> contextValues;
	
	@Override
	public Integer getId() {
		return this.id;
	}

	public List<String> getTagsList() {
		if (this.tagsList == null) {
			this.tagsList = new ArrayList<String>();
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
	
	public List<PracticeDimension> getDimensions() {
		return this.dimensions;
	}

	public void setDimensions(List<PracticeDimension> dimensions) {
		this.dimensions = dimensions;
	}

	public PracticeDimension addDimension(PracticeDimension dimension) {
		getDimensions().add(dimension);
		dimension.setPractice(this);

		return dimension;
	}

	public PracticeDimension removeDimension(PracticeDimension dimension) {
		getDimensions().remove(dimension);
		dimension.setPractice(null);

		return dimension;
	}
	
	public List<ContextValue> getContextValues() {
		return this.contextValues;
	}

}