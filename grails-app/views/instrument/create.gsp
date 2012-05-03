
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'instrument.label', default: 'Instrument')}" />
    <link rel="stylesheet" href="${resource(dir:'css/form',file:'tooltip.css')}" />
    <link rel="stylesheet" href="${resource(dir: 'css/form', file: 'style.css')}" />

    <!--[if gte IE 6]>
          <style type="text/css">@import url(${resource(dir: 'css/form', file: 'style_ie.css')});</style>
        <![endif]-->

    <script type="text/javascript">
        // URL used for the form validations
        var validationUrl = "${createLink(action: 'save')}";
    </script>


    <g:javascript src="form/ui.formValidator.js"/>     <!-- si se define library requiere un resource -->
    <g:javascript src="app/validation.js"/>
    <g:javascript src="app/instrument/createEdit.js"/>
    <g:javascript src="app/instrument/validation.js"/>

</head>
<body>
<div class="body">


    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${instrumentInstance}">
        <div class="errors">
            <g:renderErrors bean="${instrumentInstance}" as="list" />
        </div>
    </g:hasErrors>



    <form id="form" method="post" enctype="multipart/form-data">
        <div class="ui-formular-info info ui-state-highlight ui-corner-all">
            <p>
                <span class="ui-icon ui-icon-info" style="float: left; margin-right: 0.3em;"></span>
                Fields with an asterix (<em>*</em>) are required.
            </p>
        </div>
        <fieldset class="ui-widget-content ui-corner-all" style="align: left;">
            <legend class="ui-widget-header ui-corner-all">
                <g:message code="default.create.label" args="[entityName]" />
            </legend>
            <table>
                <tr>
                    <td><label for="name">Name <em title="Required">*</em></label></td>
                    <td><g:textField tabindex="1" name="name" value="${instrumentInstance?.name}" type="text" tabIndex="1" class="text ui-widget-content ui-corner-all"/></td>
                </tr>
                <tr>
                    <td><label for="image">Picture</label></td>
                    <td>
                        <input type="file" name="image" tabIndex="2" class="text ui-widget-content ui-corner-all"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <g:actionSubmit tabindex="2"  class="submit ui-state-default ui-corner-all" action="save" value="${message(code: 'default.button.save.label', default: 'Save')}" />
                        <g:actionSubmit tabindex="3"  class="submit ui-state-default ui-corner-all" action="list" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" onclick="window.location.replace('${createLink(action: 'list')}'); return false;" />
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
</body>
</html>
