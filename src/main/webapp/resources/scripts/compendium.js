/**
 * Created by CARDILA on 3/3/2015.
 */

/**
 * Logs JSF ajax errors to console
 *
 * @param errorEvent @see http://myfaces.apache.org/core22/myfaces-api/jsdoc/symbols/jsf.ajax.html#.addOnError
 */
function logError(errorEvent) {
    console.log(errorEvent.serverErrorMessage);
}
