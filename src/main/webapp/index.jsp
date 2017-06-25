<!-- Autorient utvikleroppgave-->
<!-- Espen Kirkesæther Brun -->

<!DOCTYPE html>
<html lang="no">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>REST</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-4 col-sm-offset-4">
				<h1 class="text-center">Simple REST</h1>
				<p class="text-center">Espen Kirkes&aelig;ther Brun</p>
				<h2 class="text-center">Fyll inn skjema</h2>
				<form 
					action="/simple-service-webapp/webapi/myresource"
					method="post" 
					id="form-reg" 
					class="form-horizontal form-registration">
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="name">Name:</label>
				    <div class="col-sm-10">
				      <input type="text" name="name" class="form-control" placeholder="Name">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="adress">Adress:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="adress" placeholder="Adress">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="phone">Phone:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="phone" placeholder="Phone number">
				    </div>
				  </div>
				  <div class="form-group"> 
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" form="form-reg" name="btn-reg" class="btn btn-primary btn-block">Submit</button>
				    </div>
				  </div>
				</form>
				<a href="/simple-service-webapp/webapi/myresource/list">GET list of persons</a>
			</div>
		</div>
		
	</div>
</body>
</html>
