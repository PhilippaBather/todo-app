
<%@include file="common/header.jspf"%>

<%@include file="common/navigation.jspf"%>
<div class="container">
	<h1>Enter Todo Details</h1>
	<form:form method="post" modelAttribute="todo">
		<div>
	</form:form>
	<form:form method="post" modelAttribute="todo">

		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>

		<fieldset class="mb-3">
			<form:label path="target">Target Date</form:label>
			<form:input type="date" path="target" required="required" />
			<form:errors path="target" cssClass="text-warning" />
		</fieldset>


		<form:input type="hidden" path="id" />

		<form:input type="hidden" path="done" />

		<button type="submit" class="btn btn-success">Submit</button>

	</form:form>
</div>
<%@include file="common/footer.jspf"%>
</html>