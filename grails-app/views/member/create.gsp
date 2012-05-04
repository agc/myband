<%@ page import="myband.Member" %>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
    <link rel="stylesheet" href="${resource(dir:'css/form',file:'tooltip.css')}" />
    <link rel="stylesheet" href="${resource(dir: 'css/form', file: 'style.css')}" />
   <!-- <link rel="stylesheet" href="${resource(dir: 'css/multiselect', file: 'common.css')}" />
    <link rel="stylesheet" href="${resource(dir: 'css/multiselect', file: 'ui.multiselect.css')}" /> -->
    <link rel="stylesheet" href="${resource(dir: 'css/multiselect_nuevo', file: 'jquery.multiselect.css')}" />

    <!--[if gte IE 6]>
          <style type="text/css">@import url(${resource(dir: 'css/form', file: 'style_ie.css')});</style>
        <![endif]-->

    <r:script>
        // URL used for the form validations
        var validationUrl = "${createLink(action: 'update')}";




        $(document).ready(function() {
            $("#instruments").multiselect({
            selectedText: "# de # seleccionados" ,
            noneSelectedText: 'Seleccionar instrumentos'

        });



        }) ;

</r:script>

    <g:javascript src="form/ui.formValidator.js"/>
    <g:javascript src="app/validation.js"/>
    <g:javascript src="app/member/createEdit.js"/>
    <g:javascript src="app/member/validation.js"/>
     <r:require module="multiselect_nuevo" />
   <!-- <g:javascript src="multiselect/ui.multiselect.js"/>   -->
   <!-- <g:javascript src="multiselect_nuevo/jquery.multiselect.min.js" />  -->
</head>
<body>
<div class="body">
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${memberInstance}">
        <div class="errors ui-widget-header ui-corner-all">
            <g:renderErrors bean="${memberInstance}" as="list" />
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
                <g:message code="default.edit.label" args="[entityName]" />
            </legend>
            <table>
                <tr>
                    <td><label for="firstName">First Name <em title="Required">*</em></label></td>
                    <td><g:textField name="firstName" value="${memberInstance?.firstName}" type="text" tabIndex="1" class="text ui-widget-content ui-corner-all"/></td>
                </tr>
                <tr>
                    <td><label for="lastName">Last Name <em title="Required">*</em></label></td>
                    <td><g:textField name="lastName" value="${memberInstance?.lastName}" type="text" tabIndex="2" class="text ui-widget-content ui-corner-all"/></td>
                </tr>
                <tr>
                    <td><label for="phoneNumber">Phone Number <em title="Required">*</em></label></td>
                    <td><g:textField name="phoneNumber" value="${memberInstance?.phoneNumber}" type="text" tabIndex="3" class="text ui-widget-content ui-corner-all"/></td>
                </tr>
                <tr>
                    <td><label for="image">Picture</label></td>
                    <td>
                        <input type="file" name="image" tabIndex="4" class="text ui-widget-content ui-corner-all"/>
                    </td>
                </tr>
                <tr>
                    <td style="vertical-align: top;"><label for="instruments">Instruments <em title="Required">*</em></label></td>
                    <td>
                        <g:select class="multiselect" name="instruments" from="${instruments}" multiple="yes" optionKey="id" size="5" value="${memberInstance?.instruments}" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <g:actionSubmit tabindex="5"  class="submit ui-state-default ui-corner-all" action="save" value="${message(code: 'default.button.save.label', default: 'Save')}" />
                        <g:actionSubmit tabindex="6"  class="submit ui-state-default ui-corner-all" action="list" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" onclick="window.location.replace('${createLink(action: 'list')}'); return false;" />
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <p>${instruments}</p>
</div>
</body>
</html>