package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;

/**
 * The persistent class for the conditions database table.
 */
@Entity
@Table(name = "conditions")
public class Condition extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public Integer getId() {
        return this.id;
    }
}