package org.cgiar.ccafs.csa.domain.workshops;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the authorities database table.
 * 
 */
@Entity
@Table(schema = "workshops", name="authorities")
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUTHORITIES_ID_GENERATOR", sequenceName="WORKSHOPS.AUTHORITIES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUTHORITIES_ID_GENERATOR")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="user_id")
	protected User user;
	
	protected String authority;

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}