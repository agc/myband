<%@ page import="myband.Band" %>



<div class="fieldcontain ${hasErrors(bean: bandInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="band.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${bandInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bandInstance, field: 'gigs', 'error')} ">
	<label for="gigs">
		<g:message code="band.gigs.label" default="Gigs" />
		
	</label>
	<g:select name="gigs" from="${myband.Gig.list()}" multiple="multiple" optionKey="id" size="5" value="${bandInstance?.gigs*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bandInstance, field: 'members', 'error')} ">
	<label for="members">
		<g:message code="band.members.label" default="Members" />
		
	</label>
	<g:select name="members" from="${myband.Member.list()}" multiple="multiple" optionKey="id" size="5" value="${bandInstance?.members*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bandInstance, field: 'songs', 'error')} ">
	<label for="songs">
		<g:message code="band.songs.label" default="Songs" />
		
	</label>
	<g:select name="songs" from="${myband.Song.list()}" multiple="multiple" optionKey="id" size="5" value="${bandInstance?.songs*.id}" class="many-to-many"/>
</div>

