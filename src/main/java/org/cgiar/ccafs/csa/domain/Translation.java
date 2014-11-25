package org.cgiar.ccafs.csa.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the translations database table.
 * 
 */
@Entity
@Table(schema = "public", name="translations")
public class Translation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRANSLATIONS_ID_GENERATOR", sequenceName="TRANSLATIONS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSLATIONS_ID_GENERATOR")
	private Integer id;

	protected String translation;

	@ManyToOne
	@JoinColumn(name="language_code")
	protected Language language;
	
	@Column(name="row_id")
	protected Integer rowId;
	
	@Column(name="column_name")
	protected String columnName;

	public String getTranslation() {
		return this.translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}