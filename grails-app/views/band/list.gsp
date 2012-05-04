



<html>
<head>
    <meta name="layout" content="main" />

    <g:set var="entityName" value="${message(code: 'band.label', default: 'Band')}" />

    <script type="text/javascript">
        // URL values used for list interaction
        // Defined here so we don't inline all of the javascript
        var listSourceUrl = "${createLink(action: 'listJSON')}";
        var listEditUrl = "${createLink(action: 'editJSON')}";
        var showUrl = "${createLink(action: 'show')}";
        var editUrl = "${createLink(action: 'edit')}";
        var createUrl = "${createLink(action: 'create')}";

    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#message").hide();
        });


    </script>

    <r:require modules="jqgridband"/>


</head>
<body>
<div class="body">
    <g:if test="${flash.message}">
        <div id="message" class="message ui-widget-header ui-corner-all">${flash.message}</div>
    </g:if>

    <div id="gridWrapper" class="ui-widget-header ui-corner-all" style="overflow: auto;">
        <h3 class="ui-widget-header ui-corner-all" style="text-align: center;">${entityName}s</h3>
        <!-- table tag will hold our grid -->
        <table id="bandGrid" class="scroll jqTable" style="width: 100%;" cellpadding="0" cellspacing="0"></table>
        <!-- pager will hold our paginator -->
        <div id="bandGridPager" class="scroll" style="text-align:center;"></div>
    </div>
</div>

<!-- the dialog div we will populate if nothing is selected for edit or delete -->
<div id="noSelection" style="display: none;" />
</body>
</html>






