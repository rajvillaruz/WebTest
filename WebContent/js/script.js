function executeScript() {
	$('#btnExecute').click( function() {
		var projectName = $('#txtProjectName').val();
		var qaName = $('#txtQAName').val();
		var formData = new FormData();
		formData.append('file', $('#fileScript')[0].files[0]); 
		
		$.ajax({
			url: contextPath + 'MainController?action=execute',
			method: 'POST',
			data :	formData,
			async: true,
			contentType: false,
		    processData: false,
		    success: function(result){
				$('#results').html(result);
			}
		});
		
	});
};