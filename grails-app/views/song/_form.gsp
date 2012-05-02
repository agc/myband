<%@ page import="myband.Song" %>



<div class="fieldcontain ${hasErrors(bean: songInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="song.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${songInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: songInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="song.content.label" default="Content" />
		
	</label>
	<input type="file" id="content" name="content" />
</div>

