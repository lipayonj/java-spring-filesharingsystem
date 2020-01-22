<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Email Composition</title>

<link rel="stylesheet" href="../lib/css/bootstrap.min.css">    
<link rel="stylesheet" href="../css/mailcomposition.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">  
<style> 
</style>
</head>

<body onload="loadPage()"> 

<nav class="navbar navbar-fixed-top" id="navigation">
	<div class="container-fluid"> 
		<div class="navbar-header"> 
			<a href="http://localhost:8080/File_Sharing_System/faces/pages/email.jsp"><img height=55px src="../lib/img/asi.png" alt=logo></a>
		</div>
		
		<form class="navbar-form navbar-right"> 
            Welcome, User@email.com &nbsp; <input type="button" class="form-control btn btn-danger" value="Logout" id="Logout"onclick="logout();">
       </form>
          
	</div> 
</nav>

 <div class="container-fluid">
	<div class="row">
		<div class="col-xs-2 sidebar"> 
	      <ul class="nav nav-sidebar">
	        	<li class="active"><a href="#">New Message<span class="sr-only">(current)</span></a></li>
	          	<li><a href="#">Update List</a></li>  
	   	  </ul> 
		</div>
	
		<div class="col-xs-8 well" id="composemail"> <br/> <br/>
		
		<div class="form-inline">
			 <label for="to" class="col-xs-2"><a data-toggle="modal" data-target="toTextbox" class="to">To</a></label>
			 <input type="text" size=100% name="to" id="toTextbox" class="to" data-list=[] readonly/> 
		</div><br/>
				
		<div id = "emailCC" class="form-inline">
			<label for="cc" class="col-xs-2"><a data-toggle="modal"  data-target="ccTextbox" class="cc">CC</a></label>
			<input type="text" size=100% name="cc" id="ccTextbox" class="cc" data-list=[] readonly/>  
		</div><br/>   
				
		<div  class="form-inline">
			<label for="subject" class="col-xs-2">Subject:</label>
 
			<input type="text" id="emailSubject" size=100%> 
		</div><br/>
				
		<div class="form-inline">
			<label for="body" class="col-xs-2">Body:</label> 
			<textarea id="emailBody" style="width:740px; height:150px;"></textarea> 
		</div><br/>
				
		<div class="form-inline">
			<label for="fileupload" class="col-xs-2">Upload:</label>
			<input id="fileupload" type="file" name="file" data-url="${contextPath}/api/upload" multiple />
		</div><br/>
		
		<div class="form-inline">
			<label  class="col-xs-2"></label>
			<span>Timestamp: <span id="timestamp"></span> <span>  Number of Files Upload: <span id="numFiles"></span></span></span>
		</div><br/>
		<div class="form-inline">
			<div class="col-xs-10 col-xs-offset-2" >
			<div  class="panel panel-default">
			  <div  id="dropzone" class="panel-body">
			  </div>
			</div>
			</div>
		</div><br/>		 
		<div class="form-group" id="buttons">
			<input type="button" class="btn btn-default" value="Send" onclick="sendEmail()">
			<input type="button" class="btn btn-default" value="Discard">
		</div> 
		
	  </div>
	</div>
  <div class="col-xs-2"></div>
</div> 

<!-- Start of Modal -->
			<div id="myModal" class="modal fade" role="dialog" data-src="">
			  <div class="modal-dialog"> 
			
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header bg-primary">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title text-center">ADD RECIPIENTS</h4>
			      </div>
			      <div class="modal-body">
			       	 <input type="text" name="search" id="search" size="50" placeholder="Name or Email Address" style="margin-bottom:15px;"/> <!--  onchange="filter()" onselect="this.onchange()" onpaste="this.onchange();" oninput="this.onchange();"  -->
			       	<div class="tablediv">
				       	<table id="mytable" class="table table-bordred table-striped table-hover table-condensed">
				         	<thead class="bg-info">
								<th><input type="checkbox" id="checkall" /></th>
				                <th>Name</th>
				                <th>Email</th>
				            </thead>
				            
					    	<tbody id ="table-values">
					    	</tbody > 
					    </table>
					   </div>
			        </div>
			      <div class="modal-footer">
			      	<button type="button" class="btn btn-primary" data-dismiss="modal" id="ok">OK</button>
			        <button type="button" class="btn btn-default" data-dismiss="modal">CANCEL</button>
			      </div>
			    </div>
			
			  </div>
			</div> <!--  end -->

<script type="text/javascript" src="../lib/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="../lib/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>  
<script type="text/javascript" src="../js/email.js"></script> 

<script src="../lib/js/vendor/jquery.ui.widget.js"></script>
<script src="../lib/js/jquery.iframe-transport.js"></script>
<script src="../lib/js/jquery.fileupload.js"></script>
<script src="../js/uploadFunctions.js"></script>

</body>
</html>