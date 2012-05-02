/*
 * Provides all the javascript / jquery code for our top flyout menu
 */
$(document).ready(function() {
    // Original fg menu script calls component Menu. This causes issues with other
    // jQuery UI components. I renamed to fgMenu. Search the script for "fgMenu"
    // to see what I have changed.

    // Band Menu

    $('#bandMenu').fgMenu({
        content: $("#bandMenuContent").html(),
        flyOut: true,
        width: 200,
        maxHeight: 200
    });
    /* If you need to dynamically create the menu you can call a controller
     * that will return the data for you (in html or build a json parser for the
     * menu. Example code for calling ajax service is below

     // bandMenuUrl would be the URL of the correct action on a Controller
     // If you want to use createLink() then you can create a global javascript
     // variable in your gsp view that is assigned the output of createLink()
     $.get(bandMenuUrl,
     function(data){
     // grab content from another page
     $('#bandMenu').fgMenu({
     content: data,
     flyOut: true,
     width: 200,
     maxHeight: 200
     });
     });
     */

    // Gig Menu
    $('#gigMenu').fgMenu({
        content: $("#gigMenuContent").html(),
        flyOut: true,
        width: 200,
        maxHeight: 200
    });

    // Admin Menu
    $('#adminMenu').fgMenu({
        content: $("#adminMenuContent").html(),
        flyOut: true,
        width: 200,
        maxHeight: 200
    });

    $("#leftnav").accordion({
        fillSpace: true
    });

});

