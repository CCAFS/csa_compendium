package org.cgiar.ccafs.csa.domain.workshop;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the users database table.
 */
@Entity
@Table(name = "workshop_users")
public class WorkshopUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean active;

    private String email;

    private String name;

    private String passwd;

    @OneToMany(mappedBy = "user")
    private List<WorkshopAuthority> authorities;

    public Integer getId() {
        return this.id;
    }

    public Boolean isActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public List<WorkshopAuthority> getAuthorities() {
        return this.authorities;
    }

    public WorkshopAuthority addAuthority(WorkshopAuthority authority) {
        getAuthorities().add(authority);
        authority.setUser(this);

        return authority;
    }

    public WorkshopAuthority removeAuthority(WorkshopAuthority authority) {
        getAuthorities().remove(authority);
        authority.setUser(null);

        return authority;
    }

}