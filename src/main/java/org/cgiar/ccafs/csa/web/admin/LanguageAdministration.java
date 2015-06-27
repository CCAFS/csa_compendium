package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.Language;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

public class LanguageAdministration extends AdministrationConfiguration<Language> {
    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Languages of the data in the Compendium").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("englishName")
                .pluralName("Languages")
                .singularName("Language")
                .build();
    }

}
