<!DOCTYPE html>
<html>
  <head>
    <title>Profil</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="generator" content="http://chalarangelo.github.io/htmltemplategenerator/">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript">
      $(function(){
        console.log('jQuery: Page loading complete!');
      });
    </script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <style>
		body {
		background-image: url('bk.jpg');
		}
		.btn-group button {
		background-color: #669999; 	
		border: 1px solid green; 
		color: white; 
		padding: 10px 24px; 
		cursor: pointer; 
		float: left;

		}

		.btn-group button:not(:last-child) {
		  border-right: none; 
		}

		
		.btn-group:after {
		  content: "";
		  clear: both;
		  display: table;
		}

		
		.btn-group button:hover {
		  background-color: #3e8e41;
		}
		
		

</style>
  <body >
  
      <h1 style = "margin: 25px 50px 75px 800px;" ><strong>Profil von... ${email}</strong></h1>
	  <hr style = "border-width: 5px;">
	  <h4 style = "margin: 5px 100px 25px 800px;" >
		<b>Benutzername: </b><i>${name}</i><br/>
		<b>Anzahl erstellter Projekte: </b><i>${noOwned}</i><br/>
		<b>Anzahl unterstützter Projekte: </b><i>${noDonated}</i><br/>	 
	  </h4>
	  <hr style = "border-width: 5px;">
	  <h2 style = "margin: 5px 100px 75px 800px;" ><b>Erstellter Projekte</b></h2>
	  <div class="row row-cols-1 row-cols-md-2">
  <#list Eprojects as Eproject>   
      <div class="col mb-4" style="text-align:center;">
        <div class="card">
          <img src="image.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title"><a href="ProjectDetails?action=id&amp;kennung=${Eproject.kennung}&amp;email=${email}&amp;name=${name}">${Eproject.title}</a></h5>
             <p class="card-text">Aktuell: ${Eproject.aktulleSpende}€</p>
             <p class="card-text">Status: ${Eproject.status}</p>
          </div>
        </div>
      </div>
	  
	</#list>
		</div>
		<hr style = "border-width: 5px;">
		<h2 style = "margin: 5px 100px 75px 800px;" ><b>Unterstützte Projekte</b></h2>
	  <div class="row row-cols-1 row-cols-md-2">
  <#list Dprojects as Dproject>   
      <div class="col mb-4" style="text-align:center;">
        <div class="card">
          <img src="image.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title"><a href="ProjectDetails?action=id&amp;kennung=${Dproject.kennung}&amp;email=${email}&amp;name=${name}">${Dproject.title}</a></h5>
             <p class="card-text">Aktuell: ${Dproject.aktulleSpende}€</p>
             <p class="card-text">Status: ${Dproject.status}</p>
          </div>
        </div>
      </div>
	  
	</#list>
		</div>
		





	</body>
</html>
