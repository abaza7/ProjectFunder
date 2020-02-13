<!DOCTYPE html>
<html>
  <head>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
      <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Projet Funder</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  </head>
  
 <style>
  
  body {
		background-image: image.jpg" ;
		
  
 </style> 
  <body >
         <h1 style = "text-align: center;margin: 25px 50px 75px 100px;" ><strong>Projet Funder</strong></h1>
        <div style = "text-align: left; margin-left:5%;">
            <div style = "float:right; width: 300px;">
           <a href="ProfileServlet?action=MyProfile&amp;email=${email}&amp;name=${name}">${name}</a>
             </div>
            <br>
            <h1 ><strong>Offne Projekte</strong></h1>
            
        </div>
		
		
		
<div class="row row-cols-1 row-cols-md-2">
  <#list Oprojects as oproject>   
      <div class="col mb-4" style="text-align:center;">
        <div class="card">
             <div class="card-body">
             <img src="file:///D:/WiSe19-20/datenbanken/lab/block3/projekte/java/projectFunder/src/main/webapp/icons/art.png" class="card-img-top" alt="...">
            <h5 class="card-title"><a href="ProjectDetails?action=id&amp;kennung=${oproject.kennung}&amp;email=${email}&amp;name=${name}">${oproject.title}</a></h5>
            <p class="card-text">von: ${oproject.ersteller}</p>
             <p class="card-text">Aktuell: ${oproject.aktulleSpende} €</p>
          </div>
        </div>
      </div>
	   </#list>
</div>
<h1 style = "text-align: left; margin-left:5%;"><strong>Abgeschlossene Projekte</strong></h1>
</div>
		
		
<div class="row row-cols-1 row-cols-md-2">
  <#list Cprojects as Cproject>   
      <div class="col mb-4" style="text-align:center;">
        <div class="card">
          <img src="file:///D:/WiSe19-20/datenbanken/lab/block3/projekte/java/projectFunder/src/main/webapp/icons/art.png" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title"><a href="ProjectDetails?action=id&amp;kennung=${Cproject.kennung}&amp;email=${email}&amp;name=${name}">${Cproject.title}</a></h5>
            <p class="card-text">von: ${Cproject.ersteller}</p>
             <p class="card-text">Aktuell: ${Cproject.aktulleSpende} €</p>
          </div>
        </div>
      </div>
	  
	      </#list>
</div>s
      <div style = "float:right; width: 300px;">
           <a href="CreateProjectServlet?action=userEmail&amp;email=${email}&amp;name=${name}">Projekte Erstellen</a>
      </div>

           
  </body>
</html>