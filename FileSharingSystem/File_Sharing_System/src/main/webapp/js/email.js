var employeeList = null; 
var employeeName = [];
 
function logout(){
	alert("logggggg");
	window.location.replace("http://localhost:8080/File_Sharing_System/faces/pages/login.jsp");
}
 
function loadPage() { 
	$.ajax({
		url: "http://localhost:8080/File_Sharing_System/api/getEmployees"
		,type: "POST"
		
	}).done(function(response) {  
		employeeList = response;
		var str = "";	  
		for (var i = 0; i < response.length; i++){
			str += "<tr class='tableRow'>";
			str += "<td>" + "<input type='checkbox' value='"+response[i]['email']+"'> </td><td>" + response[i]['name']+"</td>"
			str += "<td>"+ response[i]['email']+"</td>"
			str += "</tr>";
			}
		$('#mytable').append(str);
	});
}  


$("#search").keyup(function(){  
	var rows = $("#mytable").find(".tableRow").hide();  
	if (this.value.length) {
	    var data = this.value.split(" "); 
	       $.each(data, function (i, v) { 
	           rows.filter(":contains('" + v + "')").show();
	       });
	    } else{
	    	rows.show(); 
	    }
});  

function sendEmail() { 
	var emailTo = $("#toTextbox").data("list").join(",");
	var emailCC = $("#ccTextbox").data("list").join(",");
	var emailSubject = document.getElementById("emailSubject").value;
	var emailBody = document.getElementById("emailBody").value;
	var attachmentId = "jlipayon@csyoutsourcing.com=timestamphere"; 

	if (emailTo == null || emailTo == "") {
		alert("To field is required.")
		return
	} else if (emailSubject == null || emailSubject == "") {
		alert("Subject is required.")
		return
	} else {
		var toRecipientsList = emailTo.split(",");
		var CCRecipientsList = emailCC.split(",");l
		for (var i = 0; i < CCRecipientsList.length; i++) {
			if (CCRecipientsList[i].trim() != "" && toRecipientsList.indexOf(CCRecipientsList[i]) != -1) {
				alert("Duplicate Email Address")
				return
			}
		}
		alert("toController")
		$.ajax({
			url: "http://localhost:8080/File_Sharing_System/api/send-email"
			,type: "POST"
			,data: {
				strToRecipients: emailTo
				,strCCRecipients: emailCC
				,strSubject: emailSubject
				,strBody: emailBody
				,strAttachmentId: attachmentId
			}
		}).done(function(response) {
			alert(response["message"])		
	});
		
	}
	
}

function checked() { 
	$("#mytable input[type=checkbox]").each(function () {
        $(this).prop("checked", false);
    });

    $("#search").val('');
    $("#mytable").find(".tableRow").show();
	
	var checkedBox = $($("#myModal").data("src")).data("list");
	for (var i=0; i < checkedBox.length; i++) { 
		$("#mytable input[type=checkbox]").filter(function() {
			return this.value == checkedBox[i];
		}).prop("checked",true);
	}
}

$(document).ready(function(){

	$(".to").click(function(){	
    	$("#myModal").data("src", "#toTextbox");
    	checked();
        $("#myModal").modal();
    });
    
    $(".cc").click(function(){
    	$("#myModal").data("src", "#ccTextbox");
    	checked();
        $("#myModal").modal();
    });
    
    $("#mytable #checkall").click(function () {
        if ($("#mytable #checkall").is(':checked')) {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", true);
            });

        } else {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", false);
            });
        }
    });
    $("[data-toggle=tooltip]").tooltip();
});

$('#ok').on('click', function() {
	var nameRows = [];
    var emailRows = [];
    $(':checkbox:checked').closest(".tableRow").each(function() {
    	nameRows.push(
          $(this).find('td:eq(1)').map(function() {
              return $(this).html();
          }).get()
        ); 
    	emailRows.push(
                $(this).find('td:eq(2)').map(function() {
                    return $(this).html();
                }).get()
              ); 
     });
    var values = "";
    for (var i = 0; i < nameRows.length; i++){
    	values += (nameRows[i] + "; ");
    }
    $($("#myModal").data("src")).val(values);
    $($("#myModal").data("src")).data("list", emailRows);
});

/*idleTimer = null;
idleState = false;
idleWait = 30000;//5minutes = 30000

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
                idleState = true; 
                }, 
            idleWait);
        });
        
        $("body").trigger("mousemove"); 
    });
}) (jQuery) */
