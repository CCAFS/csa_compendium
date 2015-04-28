package org.cgiar.ccafs.csa.admin;

import org.cgiar.ccafs.csa.domain.InitialCondition;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

public class InitialConditionAdministration extends AdministrationConfiguration<InitialCondition> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Initial Conditions for Experiments").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("id")
                .pluralName("Initial Conditions")
                .singularName("Experiment Condition")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("id").caption("ID").
                field("experimentArticle").caption("Experiment").
                field("condition").caption("Condition").
                field("value").caption("Value").
                field("measureUnit").caption("Measure Unit").
                build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("experimentArticle").caption("Experiment").
                field("condition").caption("Condition").
                field("value").caption("Value").
                field("measureUnit").caption("Measure Unit").
                build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("experimentArticle").caption("Experiment").
                field("condition").caption("Condition").
                field("value").caption("Value").
                field("measureUnit").caption("Measure Unit").
                field("state").caption("State").
                build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("experimentArticle").caption("Experiment").
                field("condition").caption("Condition").
                field("value").caption("Value").
                field("measureUnit").caption("Measure Unit").
                field("state").caption("State").
                build();
    }

}
