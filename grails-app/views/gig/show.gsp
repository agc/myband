
<%@ page import="myband.Gig" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gig.label', default: 'Gig')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gig" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gig" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gig">
			
				<g:if test="${gigInstance?.venue}">
				<li class="fieldcontain">
					<span id="venue-label" class="property-label"><g:message code="gig.venue.label" default="Venue" /></span>
					
						<span class="property-value" aria-labelledby="venue-label"><g:fieldValue bean="${gigInstance}" field="venue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gigInstance?.startTime}">
				<li class="fieldcontain">
					<span id="startTime-label" class="property-label"><g:message code="gig.startTime.label" default="Start Time" /></span>
					
						<span class="property-value" aria-labelledby="startTime-label"><g:formatDate date="${gigInstance?.startTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gigInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="gig.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${gigInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gigInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="gig.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${gigInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gigInstance?.setLists}">
				<li class="fieldcontain">
					<span id="setLists-label" class="property-label"><g:message code="gig.setLists.label" default="Set Lists" /></span>
					
						<g:each in="${gigInstance.setLists}" var="s">
						<span class="property-value" aria-labelledby="setLists-label"><g:link controller="setList" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gigInstance?.id}" />
					<g:link class="edit" action="edit" id="${gigInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
