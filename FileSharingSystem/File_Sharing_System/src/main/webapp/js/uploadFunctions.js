

$(document).ready(function(){
		var d = new Date();
	    var n = d.getTime();
	    document.getElementById("timestamp").innerHTML = n;
	    $('#numFiles').html(numFiles);
	});
	
	var numFiles = 0;
	var filesID = 0;
	
	$(function () {
		
		$(document).on('click','.glyphicon-remove-circle', function(){
			
			var selectedfileName = $(this).parent().find('.progress-bar').attr('title');
			var currFile = $(this);
			
			
			
			$.ajax({
				url: "http://localhost:8080/File_Sharing_System/api/removeFile"
				,type: "POST"
				, data :{
					uuid : $('#timestamp').html()
					, fileName : selectedfileName
				}
			}).done(function(response) {
				$('#numFiles').html(--numFiles);
				$(currFile).parent().remove();
			});
		});
		
		$('#dropzone').on( 'drop', function(e){
			
			$.each(e.originalEvent.dataTransfer.files, function (i){
				e.originalEvent.dataTransfer.files[i].id = "file"+(++filesID) ;   
				
				var fileProgress = $("<div/>",{
					style: 'row'
				});
				
				var icon = $('<span/>', {
 				    'class':'glyphicon glyphicon-remove-circle',
 				    'title':'Insert an appropriate value' ,
 				    'data-file': e.originalEvent.dataTransfer.files[i].id
 				 });
				
				var progress = $('<div/>', {
    			    'title': e.originalEvent.dataTransfer.files[i].name,
    			    rel: 'external',
    			    'class': 'progress fileProgress',
    			    'style': 'float:left;',
    			    width : "90%"
    			}).appendTo(fileProgress);
				
        		 var $bar =  $('<div/>', {
        			 'title': e.originalEvent.dataTransfer.files[i].name,
     			    'id' : e.originalEvent.dataTransfer.files[i].id,
    			    'class': 'progress-bar',
    			    'role': 'progressbar' ,
    			    'aria-valuenow' : '0' ,
    			    'aria-valuemin' : '0' ,
    			    'aria-valuemax' : '100' ,
    			    'style' : 'width: 0%'
    			}).appendTo(progress);
        		 
        		 icon.appendTo(fileProgress);
        		 fileProgress.appendTo('#dropzone'); 
   	        });
	    });
		
	    $('#fileupload').fileupload({
	        dataType: 'json',
	        done: function (e, data) {
				
	        	$('#numFiles').html(++numFiles);
	        	$("tr:has(td)").remove();
	        },
	        progress: function (e, data) {
	        	var progress = parseInt(data.loaded / data.total * 100, 10);
        		 $.each(data.files, function (i) {
 	                $('#'+data.files[i].id)
 	                .attr({
 	                	'style': 'width: '+ progress+'%' ,
 	                	'aria-valuenow' : progress+'%' 
 	                }).text(data.files[i].name+" "+progress+"%");
 	                
 	            }); 
	   		},
	   		
			dropZone: $('#dropzone')
	    }).on('fileuploadsubmit', function (e, data) {
	        data.formData = {
	        	uuid : $('#timestamp').html()
	        }
	        console.log("Add Data");
	        console.log(data);
	        
	    });;
	});