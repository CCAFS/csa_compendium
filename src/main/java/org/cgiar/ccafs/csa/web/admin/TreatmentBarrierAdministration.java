package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.TreatmentBarrier;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

public class TreatmentBarrierAdministration extends AdministrationConfiguration<TreatmentBarrier> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Treatments Barriers").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("id")
                .pluralName("Treatment Barriers")
                .singularName("Barrier")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        //TODO Create composite fields for display

        return fragmentBuilder.
                field("id").caption("ID").
                field("barrier").caption("Barrier").
                field("treatment").caption("Treatment").
                build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("id").caption("ID").
                field("treatment").caption("Treatment").
                field("barrier").caption("Barrier").
                field("cost").caption("Cost").
                build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("id").caption("ID").
                field("treatment").caption("Treatment").
                field("barrier").caption("Barrier").
                field("cost").caption("Cost").
                build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("treatment").caption("Treatment").
                field("barrier").caption("Barrier").
                field("cost").caption("Cost").
                build();
    }
}
