package org.cgiar.ccafs.csa.domain;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Super class for those entities that have four basic translatable fields
 */
@MappedSuperclass
public abstract class AbstractInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    @Lob
    private String description;

    @Lob
    private String documentation;

    public abstract Integer getId();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentation() {
        return this.documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

}