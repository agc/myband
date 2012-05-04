// Use fluidGrid to handle browser resizing so the grid isn't a static width
function resize_the_grid() {
    $('#bandGrid').fluidGrid({
        base:'#gridWrapper',
        offset: -2
    });
}

/* when the page has finished loading.. execute the follow */
$(document).ready(function () {
    /* when the page has finished loading.. execute the follow */
    // Fade any message that may be shown
    $('#message').show().fadeOut(5000);

    // Setup our grid

    var instrumentGrid = jQuery("#bandGrid").jqGrid({

        url: listSourceUrl,  // The URL that provides our row data
        editurl: listEditUrl,  // The URL to call when we submit an edit/add/delete operation
        datatype: "json",
        colNames:['Name', 'Members','id'],  // We are only showing the Name field. Id must also be defined
        colModel:[
            {name:'name',editable:true}, // The field to pull from our JSON data for our Name column
            {name:'members',editable:false,sortable:false} ,
            {name:'id', hidden: true} // The field to pull from our JSON data for our ID column (hidden)
        ],
        caption:'Band List',
        filterToolbar:true,
        searchOnEnter:false,
        autowidth: true,
        sortname: 'name',  // Need to have a default sort field
        sortorder: 'asc',  // Need to have a default sort order
        scrollOffset: 0,   // Remove the screen space used for the scroll bar
        height: 300,       // Height of the table
        rowNum: 25,        // Number of rows to show
        rowList: [25,50,75,100],    // Values to show in dropdown for number of rows to display
        pager: jQuery('#bandGridPager'), // The element that will hold our pagination info
        viewrecords: true,
        gridview: true,
        ondblClickRow: function(id) {
            // Double Click the row to navigate to the "show" page
            if (id) {
                var url = showUrl;
                $(location).attr('href', url + "/" + id);
            }
        }
    });

    // jqGrid will give us a toolbar that we can add buttons to. There are default buttons
    // but we need to tell the library which ones to display
    $('#bandGrid').navGrid('#bandGridPager',
    {
        add: false,  // Don't show the default 'add' button (form based)
        edit: false, // Don't show the default 'edit' button (form based)
        del: false,  // Don't show the default 'delete' button
        search: false, // Don't show the default 'search' button (form based)
        refresh: true // Show the 'refresh' button so we can reload data
    });

    // Add a custom 'delete' button.
    $('#bandGrid').navButtonAdd('#bandGridPager', {
        caption:"",         // The caption to show (none for us)
        position: "first",  // The position in the toolbar for our custom button
        buttonicon:"ui-icon-trash", // The jquery-ui theme icon to use
        onClickButton:function() {
            // Custom javascript handler that is executed when our button is clicked
            // Get the row that is selected
            var rowid = $("#bandGrid").getGridParam("selrow");
            // User must select a row for delete to work
            if (rowid != null) {
                // Post to our controller for the delete operation
                $.post(listEditUrl, {id: rowid, oper: "del"}, function(data){
                    // Server returned success so show message and set it to fade
                    if (data.state == "OK") {
                        // Tell the Grid to remove the selected row from the view
                        $("#bandGrid").delRowData(rowid);
                        $('#message').html(data.message);
                        $('#message').show().fadeOut(5000);
                    } else {
                        // Server reported failure. Show message and set to fade
                        $('#message').html(data.message);
                        $('#message').show().fadeOut(10000);
                    }
                });
            } else {
                // User did not select a row. Show a dialog telling them what is wrong
                $("#noSelection").html("You must select a row to delete.");
                $("#noSelection").dialog({
                    modal: true,
                    title: "Invalid Selection"
                });
            }
        }
    });

    // Create a custom 'edit' button in our toolbar
    $('#bandGrid').navButtonAdd('#bandGridPager', {
        caption:"",  // No caption for this
        position: "first", // Make first in list
        buttonicon:"ui-icon-pencil", // Use the pencil icon from the theme
        onClickButton:function() {
            // Get selected row
            var rowid = $("#bandGrid").getGridParam("selrow");
            // Must select a row to edit
            if (rowid != null) {
                // direct browser to our 'edit' page
                $(location).attr('href', editUrl + "/" + rowid);
            } else {
                // No row selected, can't edit. Show a dialog
                $("#noSelection").html("You must select a row to edit.");
                $("#noSelection").dialog({
                    modal: true,
                    title: "Invalid Selection"
                });
            }
        }
    });

    // Create a custom 'add' button for the toolbar
    $('#bandGrid').navButtonAdd('#bandGridPager', {
        caption:"", // No caption
        position: "first", // First in list
        buttonicon:"ui-icon-plus", // use the 'plus' icon from the theme
        onClickButton:function() {
            // direct browser to our 'create' page
            $(location).attr('href', createUrl);
        }
    });

    // Create a custom 'search' button for the toolbar
    $('#bandGrid').navButtonAdd('#banddPager', {
        caption:"", // No caption
        buttonicon:"ui-icon-search", // use the 'search' icon from the theme
        onClickButton:function() {
            // We are doing inline search/filtering so when the user clicks
            // search either display or hide the filter/search row
            bandGrid[0].toggleToolbar();
        }
    });

    // jqGrid allows for inline searching/filtering. Configure filter row to work
    // like autocomplete
    $('#bandGrid').filterToolbar({
        autosearch: true,
        searchOnEnter: false
    });
});

// Resize the grid when the browser is resized
$(window).resize(resize_the_grid);


