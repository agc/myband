
<%@ page import="myband.Gig" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gig.label', default: 'Gig')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gig" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gig" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="venue" title="${message(code: 'gig.venue.label', default: 'Venue')}" />
					
						<g:sortableColumn property="startTime" title="${message(code: 'gig.startTime.label', default: 'Start Time')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'gig.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'gig.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gigInstanceList}" status="i" var="gigInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gigInstance.id}">${fieldValue(bean: gigInstance, field: "venue")}</g:link></td>
					
						<td><g:formatDate date="${gigInstance.startTime}" /></td>
					
						<td><g:formatDate date="${gigInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${gigInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gigInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
