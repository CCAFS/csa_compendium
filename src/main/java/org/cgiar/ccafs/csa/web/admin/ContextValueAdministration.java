package org.cgiar.ccafs.csa.web.admin;

import static org.cgiar.ccafs.csa.web.admin.AdministrationTemplates.infoFormView;
import static org.cgiar.ccafs.csa.web.admin.AdministrationTemplates.infoListView;
import static org.cgiar.ccafs.csa.web.admin.AdministrationTemplates.infoQuickView;
import static org.cgiar.ccafs.csa.web.admin.AdministrationTemplates.infoShowView;

import org.cgiar.ccafs.csa.domain.ContextValue;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FiltersConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

public class ContextValueAdministration extends AdministrationConfiguration<ContextValue> {
	
	@Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Possible Values por Context Variables").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("name")
                .pluralName("Context Variables Values")
                .singularName("Context Value")
                .build();
    }
    
    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
    	//fragmentBuilder.field("contextVariable").caption("Context Variable");
        return infoListView(fragmentBuilder).build();
    }
    
    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
    	fragmentBuilder.field("contextVariable").caption("Context Variable");
        return infoQuickView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        fragmentBuilder.field("contextVariable").caption("Context Variable");
        return infoShowView(fragmentBuilder).build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return infoFormView(fragmentBuilder).field("contextVariable").caption("Context Variable").build();
    }

	@Override
	public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
		return filterBuilder.filter("Context Variable", "contextVariable").build();
	}
}
