package org.cgiar.ccafs.csa.domain;

import com.google.common.base.Joiner;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Super class for those entities that have four basic translatable fields
 */
@MappedSuperclass
public abstract class AbstractInformationEntity implements Serializable {

    protected static final Joiner joiner = Joiner.on(";").skipNulls();

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    @Column(length = 100000)
    private String description;

    @Column(length = 100000)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AbstractInformationEntity)) return false;
        AbstractInformationEntity that = (AbstractInformationEntity) o;
        return Objects.equals(this.code, that.code) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}