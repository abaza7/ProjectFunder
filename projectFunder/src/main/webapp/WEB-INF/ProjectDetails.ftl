<!DOCTYPE html>
<html>
  <head>
    <title>Projektdetails</title>
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
  
      <h1 style = "margin: 25px 50px 75px 800px;" ><strong>Informationen</strong></h1>
      <h2 style = "margin: 5px 100px 75px 800px;" >${title}</h2>
	   <a  style = "margin: 5px 100px 75px 800px;" href="ProfileServlet?action=profile&amp;erstellerEmail=${erstellerEmail}&amp;ersteller=${ersteller}">von: ${ersteller}</a>
	  <p style = "margin: 5px 100px 25px 800px;" align="justify">${beschreibung}</p>
	  <h4 style = "margin: 5px 100px 25px 800px;" >
		<b>Finanzierungslimit: </b><i>${finanzierungslimit}</i> € <br/>
		<b>Aktuelle Spendensumme: </b><i>${aktulleSpende}</i> € <br/>
		<b>Status: </b><i>${status}</i><br/>	 
		<b>Vorgänger-Projekt: </b><a href="ProjectDetails?action=id&amp;kennung=${vorgaenger}&amp;email=${LoggedNutzerEmail}&amp;name=${LoggedNutzerName}"><i>${vorgaenger}</i></a>
	  </h4>
	  <hr style = "border-width: 5px;">
	  <h1 style = "margin: 25px 50px 75px 800px;" ><strong>Aktionsleiste</strong></h1>
	  <div style = "margin: 20px 50px 75px 720px;" class="btn-group">
	  
	    
		
		<#if LoggedNutzerEmail == "same">
		<button onclick="window.location.href = 'ProjectDetails?action=delete&amp;email=${email}&amp;kennung=${kennung}&amp;ersteller=${ersteller}';">Projekt Löschen</button>
		<button onclick="window.location.href = 'ProjectDetails?action=editProject&amp;email=${email}&amp;kennung=${kennung}&amp;ersteller=${ersteller}';">Projekt Editieren</button>
        <#elseif LoggedNutzerEmail == "NotSame">
        <button onclick="window.location.href = 'ProjectDetails?action=makeDonation&amp;email=${email}&amp;kennung=${kennung}&amp;ersteller=${ersteller}';">Spenden</button>
        </#if>
  	    	    
	  </div>
	  <hr style = "border-width: 5px;">
	  <h1 style = "margin: 25px 50px 75px 800px;" ><strong>Liste der Spender</strong></h1>
	  <div style = "margin: 20px 50px 75px 720px;">
	  
	  
	  <table class="datatable">
    <tr>
        <th>Benutzer</th>  <th>SpendBetrag</th>   
    </tr>
    <#list donations as donation>
    <tr>
    <#if donation.privacy == "privat" >
            <td>anynom</td> <td>${donation.amount} 
        <#else>
        <td><a href="ProfileServlet?action=profile&amp;erstellerEmail=${donation.email}&amp;ersteller=${donation.owner}">${donation.owner}</a></td> <td>${donation.amount} </td>
    
    </#if>
    </tr>
    </#list>
  </table>
		</div>
	<hr style = "border-width: 5px;">
	  <h1 style = "margin: 25px 50px 75px 800px;" ><strong>Kommentare</strong></h1>	
	  
	  <div style = "margin: 20px 50px 75px 720px;"> 
	  <table class="datatable">
    <tr>
        <th>Benutzer</th>  <th>Text</th>  <th>Zeit</th> 
    </tr>
           
    <#list comments as comment>

    <tr>
    
    <#if comment.privacy == "privat" >
    <td>anonym</td> <td>${comment.text}  </td> <td>${comment.date}</td>
    <#else>
        <td><a href="ProfileServlet?action=profile&amp;erstellerEmail=${comment.email}&amp;ersteller=${comment.owner}">${comment.owner}</a></td> <td>${comment.text}  </td> <td>${comment.date}</td> 
    </#if>
    </tr>
    </#list>
  </table>
	  
		</div>
		
		<div style = "margin: 20px 50px 75px 840px;" class = "btn-group">
		<button onclick="window.location.href = 'ProjectDetails?action=comment&amp;email=${email}&amp;kennung=${kennung}&amp;ersteller=${ersteller}';">Kommentieren</button>
		</div>


	</body>
</html>