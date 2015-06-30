package org.cgiar.ccafs.csa.domain;

import org.cgiar.ccafs.csa.domain.workshop.WorkshopBarrier;

import javax.persistence.*;

/**
 * The persistent class for the barriers database table.
 */
@Entity
@Table(name = "barriers")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "from_compendium", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Barrier extends AbstractInformationEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public Integer getId() {
        return this.id;
    }

    public boolean belongsToCompendium() {
        return !(this instanceof WorkshopBarrier);
    }
}