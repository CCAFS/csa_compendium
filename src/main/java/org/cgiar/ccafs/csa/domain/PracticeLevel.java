package org.cgiar.ccafs.csa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the levels database table.
 * 
 */
@Entity
@Table(schema = "public", name="levels")
public class PracticeLevel extends AbstractInformationEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LEVELS_ID_GENERATOR", sequenceName="LEVELS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LEVELS_ID_GENERATOR")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="theme_id")
	private Theme theme;
	
	@OneToMany(mappedBy="practiceLevel")
	private List<Practice> practices;
	
	@Override
	public Integer getId() {
		return this.id;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
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

	public Practice removeCountry(Practice practice) {
		getPractices().remove(practice);
		practice.setPracticeLevel(null);

		return practice;
	}
}