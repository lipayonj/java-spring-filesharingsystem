<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="../lib/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<link rel="stylesheet" href="http://localhost:8080/File_Sharing_System/lib/css/bootstrap.min.css">
<script src="http://localhost:8080/File_Sharing_System/lib/js/jquery-1.10.1.min.js"></script>
<script src="http://localhost:8080/File_Sharing_System/lib/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="../lib/css/bootstrap.min.css">
<script src="../lib/js/jquery-1.10.1.min.js"></script>
<script src="../lib/js/bootstrap.min.js"></script>


<title>Insert title here</title>
</head>
<body onload = "loadPage()">

<div>
<label for="to"><a data-toggle="modal" data-target="toTextbox" class="to">To</a></label>
<input type="text" name="to" id="toTextbox" class ="to" size="200" data-list=[] readonly/>
</br>
</br>
<label for="cc"><a data-toggle="modal"  data-target="ccTextbox" class="cc">CC</a></label>
<input type="text" name="cc" id="ccTextbox" class="cc" size="200" data-list=[] readonly/>
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
       	<input type="text" name="search" id="autocomplete" size="50" placeholder="Name or Email Address" onchange="autoComplete()" onpaste="this.onchange();" oninput="this.onchange();" style="margin-bottom:15px;"/>
       	<div class="tableDiv">
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
</div>

<script type="text/javascript">
//var employeeList = [];
/*
 * @author: Marielle Pacifico Banawan
 */
 
 function autoComplete() {
	 $.ajax({
			url: "http://localhost:8080/File_Sharing_System/api/getEmployees"
			,type: "POST"
			
		}).done(function(response) {
		$("#autocomplete").autocomplete(response)
		});
	}
 
function loadPage() {
	$.ajax({
		url: "http://localhost:8080/File_Sharing_System/api/getEmployees"
		,type: "POST"
		
	}).done(function(response) {
		//employeeList = response;
		var str = "";	
			for (var i = 0; i < response.length; i++){
			str += "<tr>";
			str += "<td >" + "<input type='checkbox' value='"+response[i]["email"]+"'> </td><td>" + response[i]["name"]+"</td>"
			str += "<td>"+ response[i]["email"]+"</td>"
			str += "</tr>";
			}
		$('#mytable').append(str);
	});
}


</script>


</body>
</html>