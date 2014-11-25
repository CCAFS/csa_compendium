package org.cgiar.ccafs.csa.web.admin;

import static org.lightadmin.api.config.utils.Editors.textArea;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.*;

public class ExperimentArticleAdministration extends AdministrationConfiguration<ExperimentArticle> {
	
	@Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Research Articles about CSA Practices Experiments").build();
    }
	
	@Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("title")
                .pluralName("Experiment Articles")
                .singularName("Article")
                .build();
    }
    
    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
        		.field("id").caption("ID")
        		.field("title").caption("Title")
        		.field("publicationDate").caption("Publication Date")
        		.field("theme").caption("Theme")
        		.build();
    }
    
    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
    	return fragmentBuilder
        		.field("title").caption("Title")
        		.field("code").caption("Code")
        		.field("publicationDate").caption("Publication Date")        		
        		.field("theme").caption("Theme")
        		.field("language").caption("Language")
        		.field("authors").caption("Authors")       		        		
        		.build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
    	return fragmentBuilder
        		.field("title").caption("Title")
        		.field("outline").caption("Outline")
        		.field("code").caption("Code")
        		.field("publicationDate").caption("Publication Date")
        		.field("theme").caption("Theme")
        		.field("language").caption("Language")        		
        		.field("authors").caption("Authors")
        		.field("contacts").caption("Contacts")
        		.field("contextValues").caption("Context Values")
        		.field("initialConditions").caption("Initial Conditions")
        		.build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
    	return fragmentBuilder        		
        		.field("title").caption("Title")
        		.field("outline").caption("Outline").editor(textArea())  
        		.field("code").caption("Code")
        		.field("publicationDate").caption("Publication Date")
        		.field("theme").caption("Theme")
        		.field("language").caption("Language")
        		.field("authors").caption("Authors")
        		.field("contacts").caption("Contacts")      		
        		.field("contextValues").caption("Context Variables")
        		.field("initialConditions").caption("Initial Conditions")
        		.build();
    }
    
    public SidebarsConfigurationUnit sidebars(SidebarsConfigurationUnitBuilder sidebarsBuilder) {
    	return sidebarsBuilder.sidebar("XLS File Upload", "/WEB-INF/admin/upload.jsp").build();
    }
    
}