package org.cgiar.ccafs.csa.domain.workshops;


import javax.persistence.*;

import org.cgiar.ccafs.csa.domain.Location;


/**
 * The persistent class for the locations database table.
 * 
 */
@Entity
@Table(schema = "workshops", name="locations")
public class WorkshopLocation extends Location {
	private static final long serialVersionUID = 1L;
}