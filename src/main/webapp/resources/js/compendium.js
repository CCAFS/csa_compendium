/**
 * This file contains common functions and variables used in the compendium web site
 */

//BLOCK Search page functions
SEARCH_ID_PREFIX = '#search\\:';

/**
 * This function adds a search parameter to the parameters list. It's called by each action button
 * in front of the filters drop menus.
 *
 * @param parentFilter The filter of the first column
 * @param childFilter The filter of the second column
 * @param allowsChildless true if only a parent filter is needed
 */
function addSearchParameter(parentFilter, childFilter, allowsChildless) {
    var paramValue, paramLabel;

    var parentValue = $(SEARCH_ID_PREFIX + parentFilter + 'List').val();
    var childValue = $(SEARCH_ID_PREFIX + childFilter + 'List').val();

    //var parentLabel = $('label[for="' + SEARCH_PREFIX + parentFilter + 'List' + '"]').html();
    var parentLabel = $(SEARCH_ID_PREFIX + parentFilter + 'List option:selected').text();

    //var childLabel = $('label[for="' + SEARCH_PREFIX + childFilter + 'List' + '"]').html();
    var childLabel = $(SEARCH_ID_PREFIX + childFilter + 'List option:selected').text();

    if (childValue != "0") {
        paramValue = childFilter + ':' + childValue;
        paramLabel = parentLabel + ' - ' + childLabel;
    } else {
        if (!allowsChildless) {
            return;
        }
        paramValue = parentFilter + ':' + parentValue;
        paramLabel = parentLabel;
    }

    $(SEARCH_ID_PREFIX + 'searchParams').append($("<option></option>").attr("value", paramValue).text(paramLabel));
}

/**
 * Removes the selected options from the search parameters box.
 */
function removeSearchParameters() {
    $(SEARCH_ID_PREFIX + 'searchParams option:selected').remove();
}

/**
 * This function is used to send the server the final list of search filters
 *
 * @returns {string} Containing a comma separated list with the search parameters
 */
function getSelectedFilters() {
    var result = "";
    $(SEARCH_ID_PREFIX + 'searchParams option').each(function () {
        result += $(this).attr('value') + ",";
    });
    $(SEARCH_ID_PREFIX + 'filters').val(result);
}

//END Search page functions

//BLOCK Maps and geo-coding related functions

MAPPER = new function () {
    var map;
    var coordinates = [];
    var articleTitles = [];
    var markers = [];
    var currentArticle = 0;
    var previousArticle = 0;

    var tileLayerUrl = 'http://{s}.tile.openstreetmap.de/tiles/osmde/{z}/{x}/{y}.png';
    var tileLayerAttribution = '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>';

    //Extend the Default marker class (This is an inner class)
    var RedIcon = L.Icon.Default.extend({
        options: {
            iconUrl: 'resources/images/marker-icon-red.png'
        }
    });

    var redIcon = new RedIcon();
    var blueIcon = new L.Icon.Default();

    this.initMap = function (divIdMap) {
        map = L.map(divIdMap, {
            center: [-1.266667, 36.8], //Center in Africa, just because...
            zoom: 4
        });

        // Set the tile layer
        L.tileLayer(tileLayerUrl, {
            attribution: tileLayerAttribution
        }).addTo(map);
    };

    this.addCoordinate = function (articleTitle, lat, lon) {
        articleTitles[articleTitles.length] = articleTitle;
        var coordinate = L.latLng(lat, lon);
        coordinates[coordinates.length] = coordinate;
        markers[markers.length] = L.marker(coordinate,
            {riseOnHover: true, title: articleTitle}).on('click', markerClick).addTo(map);
    };

    this.updateMap = function () {
        // Center the map to fit all the markers
        if (coordinates.length > 2) {
            map.fitBounds(coordinates);
        }
    };

    this.getCurrentArticle = function () {
        return currentArticle;
    };

    this.setCurrentArticle = function (articleCode) {
        var position = articleTitles.indexOf(articleCode);
        if (position >= 0) {
            previousArticle = currentArticle;
            currentArticle = position;
        }
    };

    this.highlightMarker = function () {
        if (previousArticle != currentArticle) {
            markers[previousArticle].setIcon(blueIcon);
            markers[currentArticle].setIcon(redIcon);
            map.panTo(coordinates[currentArticle]);
        }
    }

};

function loadMap(divIdMap, coordinatesClass, separator) {
    // Init map
    MAPPER.initMap(divIdMap);

    // Add Article markers
    $(coordinatesClass).each(function () {
        var arr = $(this).text().split(separator);
        if (arr.length == 3) {
            MAPPER.addCoordinate(arr[0], arr[1], arr[2]);
        }
    });

    MAPPER.updateMap();
}

function markerClick(event) {
    var articleCode = event.originalEvent.srcElement.title;
    highlightArticle(articleCode);
}

function highlightArticle(articleCode) {
    var panelId = "#article\\:" + MAPPER.getCurrentArticle() + "\\:panel";
    $(panelId).removeClass('panel-primary');
    MAPPER.setCurrentArticle(articleCode);
    panelId = "#article\\:" + MAPPER.getCurrentArticle() + "\\:panel";
    $(panelId).addClass('panel-primary');
    $(panelId).get(0).scrollIntoView();
    MAPPER.highlightMarker();
}

function toggleArticleDetails(articleCode) {
    $("#details_panel_" + articleCode).toggle();
    var link = $("#details_link_" + articleCode);

    if (link.data("visible") == true) {
        link.removeData("visible");
        link.text("Show Details");
    } else {
        link.data("visible", true);
        link.text("Hide Details");
    }
}

//END Maps and geo-coding related functions

/**
 * Logs JSF ajax errors to console
 *
 * @param errorEvent @see http://myfaces.apache.org/core22/myfaces-api/jsdoc/symbols/jsf.ajax.html#.addOnError
 */
function logError(errorEvent) {
    console.log(errorEvent.serverErrorMessage);
}



