package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.ContextValue;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import static org.cgiar.ccafs.csa.web.admin.AdministrationTemplates.*;

public class ContextValueAdministration extends AdministrationConfiguration<ContextValue> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Possible Values por Context Variables").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("name")
                .pluralName("Context Variables Values")
                .singularName("Context Value")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("contextVariable").caption("Context Variable");
        return infoListView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("contextVariable").caption("Context Variable");
        return infoQuickView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("contextVariable").caption("Context Variable");
        return infoShowView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return infoFormView(fragmentBuilder).field("contextVariable").caption("Context Variable").build();
    }

    @Override
    public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
        return filterBuilder.filter("Context Variable", "contextVariable").build();
    }
}
