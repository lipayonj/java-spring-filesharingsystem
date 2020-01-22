<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>J2EE Base Code for Newbie Training</title>
  	<meta name="viewport" content="width=device-width" /> 	
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<head>

<body>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
	J2EE Base Code for Newbie Training
</body>
</html>