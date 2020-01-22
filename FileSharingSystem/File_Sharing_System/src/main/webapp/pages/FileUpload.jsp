<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>jQuery File Upload</title>
<script src="../lib/js/jquery-1.9.1.js"></script>

<script src="../lib/js/vendor/jquery.ui.widget.js"></script>
<script src="../lib/js/jquery.iframe-transport.js"></script>
<script src="../lib/js/jquery.fileupload.js"></script>

<!-- bootstrap just to have good looking page -->
<script src="../lib/js/bootstrap.min.js"></script>
<link href="../lib/css/bootstrap.css" type="text/css" rel="stylesheet" />

<!-- we code these -->
<style>
	#dropzone {
	    background: #ccccc;
	    width: 150px;
	    height: 50px;
	    line-height: 50px;
	    text-align: center;
	    font-weight: bold;
	}
	
	#dropzone.in {
	    width: 600px;
	    height: 200px;
	    line-height: 200px;
	    font-size: larger;
	}
	
	#dropzone.hover {
	    background: lawngreen;
	}
	
	#dropzone.fade {
	    -webkit-transition: all 0.3s ease-out;
	    -moz-transition: all 0.3s ease-out;
	    -ms-transition: all 0.3s ease-out;
	    -o-transition: all 0.3s ease-out;
	    transition: all 0.3s ease-out;
	    opacity: 1;
	}
	
	#header{
	position: fixed;
	height: 130px;
	width: 100%;
	z-index: 1;
	background-color: blue;}
	
	#companyLogo{
	padding-top: 23px;
	float:left;
   width:200px;
   height:100px;}
   
   #LogOut{
   margin-top: 75px;
   float:right;
   width:30px;
   height:50px;}
	
</style>
<script src="../js/uploadFunctions.js"></script>

</head>

<body>

<div id="header">
<a href="http://localhost:8080/File_Sharing_System/faces/pages/FileUpload.jsp"><img id="companyLogo" src="C:\Users\Owner\Desktop\FSS\BaseCode\File_Sharing_System\alliance.png" alt="logo" /></a>
<a href="http://localhost:8080/File_Sharing_System/api/logout"><img id="LogOut" src="C:\Users\Owner\Desktop\FSS\BaseCode\File_Sharing_System\logOut.png" alt="log-out" /></a>
</div>

<span id="timestamp"></span>

<div style="width:500px;padding:20px">

	<input id="fileupload" type="file" name="file" data-url="${contextPath}/api/upload" multiple>
	
	<div id="dropzone" class="fade well">Drop files here</div>
	
	<div class="progress">
	    <div class="bar"></div >
	    <div class="percent">0%</div >
	</div>
	
	Number of Files Upload: <span id="numFiles"></span>
	
	<table class="table">
		<thead>
		<tr>
			<th>File Name</th>
			<th>File Size</th>
			<th>File Type</th>
			<th>Download</th>
		</tr>
		</thead>
		<tbody  id="uploaded-files">
		
		</tbody>
	</table>
	
</div>
<script>
	$(document).ready(function(){
		var d = new Date();
	    var n = d.getTime();
	    document.getElementById("timestamp").innerHTML = n;
	    $('#numFiles').html(numFiles);
	});
	
	var numFiles = 0;

	$(function () {
		
		$('#dropzone').on( 'drop', function(e){
			    	
	    	console.log(e.originalEvent.dataTransfer.files[0].name);
	    	
	    	$('#uploaded-files tr').each(function(){
	    		
	    		console.log($(this).find('td').eq(0).text());
	    		
	            if($(this).find('td').eq(0).text() == e.originalEvent.dataTransfer.files[0].name){
	                $(this).css('background','red');
	                e.preventDefault();
	                e.stopPropagation();
	            }
	        });
	    	
	    });
		
	    $('#fileupload').fileupload({
	        dataType: 'json',
	        
	        done: function (e, data) {
	        	
	        	$('#numFiles').html(++numFiles);
	        	$("tr:has(td)").remove();
	            $.each(data.result, function (index, file) {
	            	
	            	
	                $("#uploaded-files").append(
	                		$('<tr/>')
	                		.append($("<td class='fname'/>").text(file.fileName))
	                		.append($('<td/>').text(file.fileSize))
	                		.append($('<td/>').text(file.fileType))
	                		.append($('<td/>').html("<a href='${contextPath}/api/get/"+index+"'>Click</a>"))
	                		)//end $("#uploaded-files").append()
	            }); 
	        },
	        
	        add: function(e, data){
	        	
	        	console.log(data);
	        },
	        
	        progress: function (e, data) {
		        
	        	var progress = parseInt(data.loaded / data.total * 100, 10);
		        
		        $('.progress .bar').css({
		            'width': progress + '%'
		            , 'background-color': 'red'
		        });
		        
		        $('.percent').html(progress + '%');
	   		},
	   		
			dropZone: $('#dropzone')
	    });
	});

	idleTimer = null;
	idleState = false;
	idleWait = 30000;//5minutes

	(function ($) {

	    $(document).ready(function () {
	    
	        $('*').bind('mousemove keydown scroll', function () {
	        
	            clearTimeout(idleTimer);
	                    
	            if (idleState == true) { 
	                    window.location.replace("http://localhost:8080/File_Sharing_System/faces/pages/login.jsp");
						alert("Session has expired.");          
	            }
	            
	            idleState = false;
	            
	            idleTimer = setTimeout(function () { 
	                
	                // Idle Event

	                idleState = true; }, idleWait);
	        });
	        
	        $("body").trigger("mousemove");
	    
	    });
	}) (jQuery)
</script>
</body> 
</html>