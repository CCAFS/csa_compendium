package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.TreatmentOutcome;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

public class TreatmentOutcomeAdministration extends AdministrationConfiguration<TreatmentOutcome> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Treatments Outcomes").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("id")
                .pluralName("Treatment Outcomes")
                .singularName("Outcome")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        //TODO Create composite fields for display

        return fragmentBuilder.
                field("id").caption("ID").
                field("treatment").caption("Treatment").
                field("subIndicator").caption("Indicator").
                field("productionSystem").caption("Production System").
                field("meanValue").caption("Mean Value").
                build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("id").caption("ID").
                field("treatment").caption("Treatment").
                field("subIndicator").caption("Indicator").
                field("productionSystem").caption("Production System").
                field("meanValue").caption("Mean Value").
                build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("id").caption("ID").
                field("treatment").caption("Treatment").
                field("subIndicator").caption("Indicator").
                field("productionSystem").caption("Production System").
                field("startDate").caption("Start Date").
                field("endDate").caption("End Date").
                field("soilDepth").caption("Soil Depth").
                field("meanValue").caption("Mean Value").
                field("measureUnit").caption("Measure Unit").
                field("standardError").caption("Standard Error").
                field("standardDeviation").caption("Standard Deviation").
                field("result").caption("Result").
                build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("treatment").caption("Treatment").
                field("subIndicator").caption("Indicator").
                field("productionSystem").caption("Production System").
                field("startDate").caption("Start Date").
                field("endDate").caption("End Date").
                field("soilDepth").caption("Soil Depth").
                field("meanValue").caption("Mean Value").
                field("measureUnit").caption("Measure Unit").
                field("standardError").caption("Standard Error").
                field("standardDeviation").caption("Standard Deviation").
                field("result").caption("Result").
                build();
    }
}
