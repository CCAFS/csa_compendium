package org.cgiar.ccafs.csa.domain.workshop;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the comments database table.
 */
@Entity
@Table(name = "workshop_comments")
public class WorkshopComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String body;

    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    public Integer getId() {
        return this.id;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Workshop getWorkshop() {
        return this.workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

}