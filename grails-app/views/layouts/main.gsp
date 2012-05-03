<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
        <link rel="stylesheet" href="${resource(dir:'css/grid', file:'ui.jqgrid.css')}" />
       <link rel="stylesheet" href="${resource(dir:'css/menu', file:'fg.menu.css')}" />

        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'site.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">

    <g:layoutHead/>
        <r:require modules="application,core,menu"/>
        <r:layoutResources/>


	</head>

<body class="ui-widget-content">
<div>
    <div id="header" class="ui-widget-header">
        <!-- Generally would include the header section -->
    </div>


    <g:render template="/layouts/includes/menu"/>


    <div id="switcher" style="margin-top: 5px;"></div>

    <div id="bodyContent">
        <div id="sidebar">
            <!-- Incluir la barra de navegacion accordion  -->
            <g:render template="/layouts/includes/leftnav" />
        </div>

        <div id="main">
            <g:layoutBody/>
        </div>
    </div>


    <div id="footer" class="ui-widget-header">
        <div id="footerContent">
            MyBand <g:meta name="app.version"/> on Grails <g:meta name="app.grails.version" />.
        </div>
    </div>
</div>
<r:layoutResources/>
</body>
</html>
