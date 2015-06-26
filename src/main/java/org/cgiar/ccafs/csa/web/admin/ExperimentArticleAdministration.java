package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.*;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;
import org.lightadmin.api.config.unit.SidebarsConfigurationUnit;

import static org.lightadmin.api.config.utils.Editors.textArea;

public class ExperimentArticleAdministration extends AdministrationConfiguration<ExperimentArticle> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Research Articles about CSA Practices Experiments").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("code")
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
                .field("theme").caption("CSA Theme")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("title").caption("Title")
                .field("code").caption("Code")
                .field("publicationDate").caption("Publication Date")
                .field("theme").caption("CSA Theme")
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
                .field("theme").caption("CSA Theme")
                .field("language").caption("Language")
                .field("authors").caption("Authors")
                .field("contacts").caption("Contacts")
                .field("contextValues").caption("Context Values")
                .field("contexts").caption("Experiment Locations")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("title").caption("Title")
                .field("outline").caption("Outline").editor(textArea())
                .field("code").caption("Code")
                .field("publicationDate").caption("Publication Date")
                .field("theme").caption("CSA Theme")
                .field("language").caption("Language")
                .field("authors").caption("Authors")
                .field("contacts").caption("Contacts")
                .field("contextValues").caption("Context Variables")
                .field("contexts").caption("Experiment Locations")
                .build();
    }

    public SidebarsConfigurationUnit sidebars(SidebarsConfigurationUnitBuilder sidebarsBuilder) {
        return sidebarsBuilder.sidebar("XLS File Upload", "/WEB-INF/lightadmin/upload.jsp").build();
    }

}
