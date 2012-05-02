<%@ page import="myband.Instrument" %>



<div class="fieldcontain ${hasErrors(bean: instrumentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="instrument.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${instrumentInstance?.name}"/>
</div>

