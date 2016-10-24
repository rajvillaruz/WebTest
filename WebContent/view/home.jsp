<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<!-- <script type="text/javascript" src="js/jquery-3.0.0.min.js"></script> -->
<!-- <script type="text/javascript" src="js/bootstrap.js"></script> -->
<!-- <script type="text/javascript" src="js/bootstrap.min.js"></script> -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}' + '/';
</script>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<title>Automated Testing</title>
</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-12" style="margin-top: 70px; margin-bottom: 50px;">
			<img alt="..." src="imgs/ccc.png" class="img-responsive center-block">
		</div>
		<div id="test" style="width: 500px; margin: auto; margin-top: 50px; ">
			<form class="form-horizontal">
				<div class="form-group">
					<label for="txtProjectName">Project Name:</label>
					<div>
						<input type="text" class="form-control" id="txtProjectName" placeholder="Enter project">
					</div>
				</div>
				<div class="form-group">
					<label for="txtQAName">QA Name:</label>
					<div> 
						<input type="text" class="form-control" id="txtQAName" placeholder="Enter name">
					</div>
				</div>
				<div class="form-group">
					<label for="txtQAName">IP:</label>
					<div> 
						<input type="text" class="form-control" id="txtIP" placeholder="Enter IP Address">
					</div>
				</div>
				<div class="form-group"> 
					<div>
						<div class="file">
							<input type="file" id="fileScript" name="fileScript">
						</div>
					</div>
				</div>
				
			</form>
			
		</div>
		<div style="width: 108px; margin: auto;">
			<button type="submit" class="btn btn-default" id="btnExecute">Execute Test</button>
		</div>
		<br/>
		<div id="results" style="margin-top: 50px; width: 800px; margin: auto;">
		
		</div>
	</div>
</body>
<script type="text/javascript">
$( document ).ready(function() {
	executeScript();
	
});
</script>
</html>