package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.IndicatorPillar;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;
import org.lightadmin.api.config.utils.EnumElement;

import static org.lightadmin.api.config.utils.EnumElement.*;

public class IndicatorPillarAdministration extends AdministrationConfiguration<IndicatorPillar> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Weight of CSA Pillars on the Indicators").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("pillar")
                .pluralName("Weights of Pillars")
                .singularName("Weight of Pillar")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("indicator").caption("Indicator")
                .field("pillar").caption("Pillar").enumeration(
                        element("ADAPTATION", "ADAPTATION"),
                        element("MITIGATION", "MITIGATION"),
                        element("PRODUCTION", "PRODUCTION")
                )
                .field("weight").caption("Weight")
                .build();
    }

}
