<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<input type = "submit" id="sendEmail" value="Send" onClick="sendEmail()">  

	
<script src="http://localhost:8080/J2EE_Training_2015/lib/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript">

function sendEmail() {
	$.ajax({
		url: "http://localhost:8080/File_Sharing_System/api/send-email"
		,type: "POST"
		,data: {
			strToRecipients: "jcruz@csyoutsourcing.com, mbanawan@csyoutsourcing.com"
			,strCCRecipients: "apelayo@csyoutsourcing.com, jjalandoni@csyoutsourcing.com"
			,strSubject: "Subject test 2"
			,strBody: "Shake thy body"
			,strAttachmentId: "jlipayon@csyoutsourcing.com=timestamphere"
		}
	}).done(function(response) {

		alert(response["message"])		
		
			
	});
	
	
	
}

</script>
</body>
</html>