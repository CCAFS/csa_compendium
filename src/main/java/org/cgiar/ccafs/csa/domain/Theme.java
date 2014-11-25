package org.cgiar.ccafs.csa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the themes database table.
 * 
 */
@Entity
@Table(schema = "public", name="themes")
public class Theme extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="THEMES_ID_GENERATOR", sequenceName="THEMES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="THEMES_ID_GENERATOR")
	private Integer id;
	
	@OneToMany(mappedBy="theme")
	private List<PracticeLevel> practiceLevels;
	
	@Override
	public Integer getId() {
		return this.id;
	}
	
	public List<PracticeLevel> getPracticeLevels() {
		if (this.practiceLevels == null) {
			this.practiceLevels = new ArrayList<PracticeLevel>();
		}
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