package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.PracticeLevel;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.*;

import static org.cgiar.ccafs.csa.web.admin.AdministrationTemplates.*;

public class PracticeLevelAdministration extends AdministrationConfiguration<PracticeLevel> {
	@Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("name")
                .pluralName("Practice Levels")
                .singularName("Practice Level")
                .build();
    }
    
    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
    	fragmentBuilder.field("theme").caption("Theme");
    	return infoListView(fragmentBuilder).build();
    }
    
    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
    	fragmentBuilder.field("theme").caption("Theme");
    	return infoListView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
    	fragmentBuilder.field("theme").caption("Theme");
    	return infoShowView(fragmentBuilder).field("practices").caption("Practices").build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return infoFormView(fragmentBuilder).field("theme").caption("Theme").build();
    }
    
    @Override
	public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
		return filterBuilder.filter("Theme", "theme").build();
	}
    
}