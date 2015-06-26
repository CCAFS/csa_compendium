package org.cgiar.ccafs.csa.web.admin;

import org.cgiar.ccafs.csa.domain.Location;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

public class LocationAdministration extends AdministrationConfiguration<Location> {

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder.screenName("Georeferenced Geographical Locations").build();
    }

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("place")
                .pluralName("Geographical Locations")
                .singularName("Location")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        //TODO Create composite fields for display

        return fragmentBuilder.
                field("place").caption("Village / Town / City").
                field("country").caption("Country").
                field("latitude").caption("Latitud (Northing)").
                field("longitude").caption("Longitude (Easting)").
                field("maxAltitude").caption("Max Altitude (from Sea Lever)").
                field("minAltitude").caption("Min Altitude").
                build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("place").caption("Village / Town / City").
                field("country").caption("Country").
                field("latitude").caption("Latitud (Northing)").
                field("longitude").caption("Longitude (Easting)").
                field("maxAltitude").caption("Max Altitude (from Sea Lever)").
                field("minAltitude").caption("Min Altitude").
                build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("place").caption("Village / Town / City").
                field("country").caption("Country").
                field("latitude").caption("Latitud (Northing)").
                field("longitude").caption("Longitude (Easting)").
                field("maxAltitude").caption("Max Altitude (from Sea Lever)").
                field("minAltitude").caption("Min Altitude").
                build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder.
                field("place").caption("Village / Town / City").
                field("country").caption("Country").
                field("latitude").caption("Latitud (Northing)").
                field("longitude").caption("Longitude (Easting)").
                field("maxAltitude").caption("Max Altitude (from Sea Lever)").
                field("minAltitude").caption("Min Altitude").
                build();
    }

}
