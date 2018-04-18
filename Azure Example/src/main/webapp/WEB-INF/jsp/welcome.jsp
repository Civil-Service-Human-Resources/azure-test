<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">

<body>
	<c:url value="/resources/text.txt" var="url"/>
	<spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl" />
	Spring URL: ${springUrl} at ${time}
	<br>
	JSTL URL: ${url}
	<br>
	Message: ${message}

    <h2>Sign Up</h2>

    <form:form method="POST" modelAttribute="userDetails" action="/createuser">

        <c:out value="UserName:" />
        <form:input path="userName" />

        <c:out value="Password:" />
        <form:password path="password" />

        <br />
        <form:button name="Submit" value="Submit">Submit</form:button>
    </form:form>

        <h2><a href="/secure">Log in</a></h2>
    <c:if test="${not empty userID}">
        <c:out value="New user ID: ${userID}" />
    </c:if>

</body>

</html>
