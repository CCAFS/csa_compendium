package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;


/**
 * The persistent class for the locations database table.
 * 
 */
@Entity
@Table(schema = "public", name="locations")
public class ExperimentLocation extends Location {
	private static final long serialVersionUID = 1L;
}