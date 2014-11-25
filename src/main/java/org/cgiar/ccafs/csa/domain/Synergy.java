package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the synergies database table.
 * 
 */
@Entity
@Table(schema = "public", name="synergies")
public class Synergy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYNERGIES_ID_GENERATOR", sequenceName="SYNERGIES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYNERGIES_ID_GENERATOR")
	private Integer id;

	protected String description;

	protected Boolean exclusive;

	protected Integer score;

	@ManyToOne
	@JoinColumn(name="main_practice_id")
	protected Practice mainPractice;

	@ManyToOne
	@JoinColumn(name="second_practice_id")
	protected Practice secondPractice;

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