package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.FarmingSystem;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import static org.cgiar.ccafs.csa.web.admin.AdministrationTemplates.*;

public class FarmingSystemAdministration extends AdministrationConfiguration<FarmingSystem> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Farming System Types").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("name")
                .pluralName("Farming Systems")
                .singularName("Farming System")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("region").caption("Region");
        return infoListView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("region").caption("Region");
        return infoQuickView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("region").caption("Region");
        return infoShowView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("region").caption("Region");
        return infoFormView(fragmentBuilder).build();
    }

    @Override
    public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
        return filterBuilder.filter("Region", "region").build();
    }

}
