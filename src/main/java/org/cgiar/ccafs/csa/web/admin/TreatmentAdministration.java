package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.Treatment;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

public class TreatmentAdministration extends AdministrationConfiguration<Treatment> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Experiment Treatments").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("id")
                .pluralName("Treatments")
                .singularName("Treatment")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("id").caption("ID").
                field("blockNumber").caption("Block Number").
                field("practice").caption("Related Practice").
                field("controlForTreatments").caption("Is a Control?").
                build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("blockNumber").caption("Block Number").
                field("practice").caption("Related Practice").
                field("controlForTreatments").caption("Is a Control?").
                field("experimentArticle").caption("Experiment").
                build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("blockNumber").caption("Block Number").
                field("practice").caption("Related Practice").
                field("controlForTreatments").caption("Is a Control?").
                field("control").caption("Control for this Treatment").
                field("experimentArticle").caption("Experiment").
                field("productionSystems").caption("Production Systems Involved").
                field("outcomes").caption("Outcomes").
                build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("blockNumber").caption("Block Number").
                field("practice").caption("Related Practice").
                field("controlForTreatments").caption("Is a Control?").
                field("control").caption("Control for this Treatment").
                field("experimentArticle").caption("Experiment").
                field("productionSystems").caption("Production Systems Involved").
                field("outcomes").caption("Outcomes").
                build();
    }

    @Override
    public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
        return filterBuilder.
                filter("Related Practice", "practice").
                filter("Experiment", "experimentArticle").
                build();

        //TODO Add Other filters
    }

}
