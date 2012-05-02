<%@ page import="myband.Member" %>



<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="member.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${memberInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="member.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${memberInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'phoneNumber', 'error')} required">
	<label for="phoneNumber">
		<g:message code="member.phoneNumber.label" default="Phone Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phoneNumber" pattern="${memberInstance.constraints.phoneNumber.matches}" required="" value="${memberInstance?.phoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'instruments', 'error')} ">
	<label for="instruments">
		<g:message code="member.instruments.label" default="Instruments" />
		
	</label>
	<g:select name="instruments" from="${myband.Instrument.list()}" multiple="multiple" optionKey="id" size="5" value="${memberInstance?.instruments*.id}" class="many-to-many"/>
</div>

