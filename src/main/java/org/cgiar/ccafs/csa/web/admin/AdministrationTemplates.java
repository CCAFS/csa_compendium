package org.cgiar.ccafs.csa.web.admin;

import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;

import static org.lightadmin.api.config.utils.Editors.textArea;
import static org.lightadmin.api.config.utils.Editors.wysiwyg;

public final class AdministrationTemplates {

    private AdministrationTemplates() {

    }

    public static FieldSetConfigurationUnitBuilder infoListView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("id").caption("ID")
                .field("name").caption("Name")
                .field("description").caption("Description");
    }

    public static FieldSetConfigurationUnitBuilder infoQuickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("code").caption("Code")
                .field("name").caption("Name")
                .field("description").caption("Description");
    }

    public static FieldSetConfigurationUnitBuilder infoShowView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return infoQuickView(fragmentBuilder).field("documentation").caption("Documentation");
    }


    public static PersistentFieldSetConfigurationUnitBuilder infoFormView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return infoFormView(fragmentBuilder, false);

    }

    public static PersistentFieldSetConfigurationUnitBuilder infoFormView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder,
                                                                          boolean withDocumentation) {
        fragmentBuilder.field("code").caption("Code")
                .field("name").caption("Name")
                .field("description").caption("Description").editor(textArea());

        if (withDocumentation) {
            fragmentBuilder.field("documentation").caption("Documentation").editor(wysiwyg());
        }

        return fragmentBuilder;
    }

}