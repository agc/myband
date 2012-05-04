$(document).ready(function() {
    // Create the validator for our form
    var formular = $("#form").formValidator({
        forms: {
            // Validate the name field
            name: {
                // The validation rules
                rules: {
                    required: true, // Do required check
                    lengthMin: 4,   // Do length check
                    regEx: /^[A-Za-z ]+$/ // Do alpha and space check.
                                          // Not a very good regex pattern but shows concept
                },
                // The messages to show (name of message must match validation rule
                msg: {
                    required: "Name is required",
                    lengthMin: "Name must be at least 4 characters long",
                    regEx: "Only alphabetic and spaces are allowed"
                }
            }
        },
        validateLive: true,
        validateOn: "",     // Hide some content formular puts on the page
        validateOff: "",    // Hide some content formular puts on the page
        submitHowTo: "post",    // Formular will submit the form if everything passes
        submitUrl: validationUrl,   // The URL to submit the form values to
        customError: customError    // We are showing tooltips next to fields, define handler
    })
});


