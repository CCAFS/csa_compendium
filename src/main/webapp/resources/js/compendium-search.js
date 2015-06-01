/**
 * This file contains functions and variables used in the compendium web site Search page
 */

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
        paramLabel = allowsChildless || parentLabel == "--" ? childLabel : (parentLabel + ': ' + childLabel);
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
function setSelectedFilters() {
    var result = "", resultInfo = "";
    $(SEARCH_ID_PREFIX + 'searchParams option').each(function () {
        result += $(this).attr('value') + ",";
        var paramLine = $(this).text();
        var colonPlace = paramLine.indexOf(':');
        resultInfo += colonPlace < 0 ? paramLine : paramLine.substring(colonPlace + 2);
        resultInfo += ":"
    });
    $(SEARCH_ID_PREFIX + 'filters').val(result);
    $(SEARCH_ID_PREFIX + 'filtersInfo').val(resultInfo);
}

/**
 * Logs JSF ajax errors to console
 *
 * @param errorEvent @see http://myfaces.apache.org/core22/myfaces-api/jsdoc/symbols/jsf.ajax.html#.addOnError
 */
function logError(errorEvent) {
    console.log(errorEvent.serverErrorMessage);
}



