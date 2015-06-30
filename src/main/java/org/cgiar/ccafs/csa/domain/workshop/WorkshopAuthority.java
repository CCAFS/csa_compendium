package org.cgiar.ccafs.csa.domain.workshop;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the authorities database table.
 */
@Entity
@Table(name = "workshop_authorities")
public class WorkshopAuthority implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private WorkshopUser user;

    private String authority;

    public WorkshopUser getUser() {
        return this.user;
    }

    public void setUser(WorkshopUser user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}