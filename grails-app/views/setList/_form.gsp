<%@ page import="myband.SetList" %>



<div class="fieldcontain ${hasErrors(bean: setListInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="setList.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${setListInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: setListInstance, field: 'songs', 'error')} ">
	<label for="songs">
		<g:message code="setList.songs.label" default="Songs" />
		
	</label>
	<g:select name="songs" from="${myband.Song.list()}" multiple="multiple" optionKey="id" size="5" value="${setListInstance?.songs*.id}" class="many-to-many"/>
</div>

