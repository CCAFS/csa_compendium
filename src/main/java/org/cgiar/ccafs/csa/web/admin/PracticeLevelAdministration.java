package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.PracticeLevel;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FiltersConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;

import static org.cgiar.ccafs.csa.web.admin.AdministrationTemplates.*;

public class PracticeLevelAdministration extends AdministrationConfiguration<PracticeLevel> {
    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("name")
                .pluralName("Practice Levels")
                .singularName("Practice Level")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("theme").caption("Theme");
        return infoListView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("theme").caption("Theme");
        return infoListView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("theme").caption("Theme");
        return infoShowView(fragmentBuilder).field("practices").caption("Practices").build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return infoFormView(fragmentBuilder).field("theme").caption("Theme").build();
    }

    @Override
    public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
        return filterBuilder.filter("Theme", "theme").build();
    }

}