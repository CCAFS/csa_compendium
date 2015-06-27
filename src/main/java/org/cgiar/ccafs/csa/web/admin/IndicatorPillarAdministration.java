package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.IndicatorPillar;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

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

}
