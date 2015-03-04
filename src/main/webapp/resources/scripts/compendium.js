/**
 * This file contains common functions and variables used in the compendium web site
 */

//BLOCK Search page functions
//SEARCH_PREFIX = 'search:';
SEARCH_ID_PREFIX = '#search\\:';

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

function removeSearchParameters() {
    $(SEARCH_ID_PREFIX + 'searchParams option:selected').remove();
}

//END Search page functions

/**
 * Logs JSF ajax errors to console
 *
 * @param errorEvent @see http://myfaces.apache.org/core22/myfaces-api/jsdoc/symbols/jsf.ajax.html#.addOnError
 */
function logError(errorEvent) {
    console.log(errorEvent.serverErrorMessage);
}


