package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.ExperimentContext;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

public class ExperimentContextAdministration extends AdministrationConfiguration<ExperimentContext> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Location and Context of CSA Experiments").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("id")
                .pluralName("Experiment Contexts")
                .singularName("Experiment Context")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("id").caption("ID")
                .field("experiment").caption("Experiment Article")
                .field("farmingSystem").caption("Farming System")
                .field("productionSystems").caption("Production Systems")
                .field("location").caption("Geographical Location")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("id").caption("ID")
                .field("experiment").caption("Experiment Article")
                .field("farmingSystem").caption("Farming System")
                .field("productionSystems").caption("Production Systems")
                .field("location").caption("Geographical Location")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("id").caption("ID")
                .field("experiment").caption("Experiment Article")
                .field("farmingSystem").caption("Farming System")
                .field("productionSystems").caption("Production Systems")
                .field("location").caption("Geographical Location")
                .field("initialConditions").caption("Initial Conditions")
                .field("treatments").caption("Treatments")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("experiment").caption("Experiment Article")
                .field("farmingSystem").caption("Farming System")
                .field("productionSystems").caption("Production Systems")
                .field("location").caption("Geographical Location")
                .field("initialConditions").caption("Initial Conditions")
                .field("treatments").caption("Treatments")
                .build();
    }

    @Override
    public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
        return filterBuilder
                .filter("Experiment Article", "experiment")
                .filter("Farming System", "farmingSystem")
                .filter("Production Systems", "productionSystems")
                .build();
    }
}
