var customError = function(event, data){
    // Iterate over any errors we may have and show if needed
    for (var error in data["errors"]){
        // New error, show tooltip with message
        if (data.errors[error] == "new") {
            // did we already set a tooltip?
            if (!data.tooltip) {
                // If the field we are validating is hidden (maybe library like multiselect)
                // then we need to make it visible so we can calculate tooltip location
                var vis = data.element.is(":visible");
                if (!vis)
                    data.element.show();

                // we need to check for a context which is not a checkbox so we use to the parent element (which is div.pair)
                var selector = (data.type == "group") ? data.element.first().parent() : data.element; // jquery 1.4.x

                data.tooltip = selector.tooltip({
                    content: function() {
                        // use the error message
                        return data.msg[error];
                    },
                    // ui.position settings
                    position: {
                        my: "left top",
                        at: "right top",
                        offset: "35 0",
                        of: selector
                    },
                    tooltipClass: "ui-widget-content",
                    noHover: true
                });
                // Show tooltip next to the element
                data.tooltip.tooltip("open", selector);
                // We may have shown a hidden element so we could get position info
                // Hide it now if it was hidden before
                if (!vis)
                    data.element.hide();
                
            }
        } else if (data.errors[error] == "corrected") {
            // Close the tooltip, validation error was corrected
            data.tooltip.tooltip("close");
            data.tooltip.tooltip("destroy");
            data.tooltip	= false;
        }
    }
}