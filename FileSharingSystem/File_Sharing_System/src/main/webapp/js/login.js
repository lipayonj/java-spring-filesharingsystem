var login = {
	employeeList: null,
	loadEmployeeList: function(){ 
		$.ajax({
			url: "http://localhost:8080/File_Sharing_System/api/loadList"
			,type: "POST"
		}).done(function(response){   
				employeeList = response;   
		});  
	},
 	autoComplete: function(){ 
 	 	$("#username").autocomplete({
 	 		source: employeeList
 	 	})
	},
	authenticate: function(){
		$.ajax({
			url: "http://localhost:8080/File_Sharing_System/api/userAuthenticate"
			,type: "POST"
			,data: {
				username: $('username').val()
				,password: $('pwd').val() 
			}
		}).done(function(response){
			alert(response);
		 });
	}
} 