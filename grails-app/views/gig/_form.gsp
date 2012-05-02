<%@ page import="myband.Gig" %>



<div class="fieldcontain ${hasErrors(bean: gigInstance, field: 'venue', 'error')} required">
	<label for="venue">
		<g:message code="gig.venue.label" default="Venue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="venue" required="" value="${gigInstance?.venue}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gigInstance, field: 'startTime', 'error')} required">
	<label for="startTime">
		<g:message code="gig.startTime.label" default="Start Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startTime" precision="minutes"  value="${gigInstance?.startTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: gigInstance, field: 'setLists', 'error')} ">
	<label for="setLists">
		<g:message code="gig.setLists.label" default="Set Lists" />
		
	</label>
	<g:select name="setLists" from="${myband.SetList.list()}" multiple="multiple" optionKey="id" size="5" value="${gigInstance?.setLists*.id}" class="many-to-many"/>
</div>

