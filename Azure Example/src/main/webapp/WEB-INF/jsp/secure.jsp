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

    <h1>You have logged in</h1>

        <c:if test="${not empty userID}">
            <c:out value="New user ID: ${userID}" />
        </c:if>

    <a href="https://login.microsoftonline.com/gmackayme.onmicrosoft.com/oauth2/logout?post_logout_redirect_uri=http%3A%2F%2Flocalhost%3A8080%2F">SignOut</a>

</body>

</html>
