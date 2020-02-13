<!DOCTYPE html>
<html>
  <head>
    <title>Projet Kommentar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  </head>
  <style>
		body {
		background-image: url('bk.jpg');
		}
		button {
		background-color: #669999; 	
		border: 1px solid green; 
		color: white; 
		padding: 10px 24px; 
		cursor: pointer; 
		float: left;
		margin: 25px 50px 75px 100px;

		}

		button:not(:last-child) {
		  border-right: none; 
		}

		
		button:after {
		  content: "";
		  clear: both;
		  display: table;
		}

		
		button:hover {
		  background-color: #3e8e41;
		}
		
</style>
  <body >

      <h1 style = "text-align: center;margin: 25px 50px 75px 100px;" ><strong>${LoggedNutzerName}</strong></h1>
       <h1 style = "text-align: center;margin: 25px 50px 75px 100px;" ><strong>${LoggedNutzerEmail}</strong></h1>
      <h1 style = "text-align: center;margin: 25px 50px 75px 100px;" ><strong>${title}</strong></h1>
      <h1 style = "text-align: center;margin: 25px 50px 75px 100px;" ><strong>${ErstellerName}</strong></h1>
      <h1 style = "text-align: center;margin: 25px 50px 75px 100px;" ><strong>${ErstllerEmail}</strong></h1>
      <h1 style = "text-align: center;margin: 25px 50px 75px 100px;" ><strong>${kennung}</strong></h1>
      
        <form method ="POST" action="ProjectDetails?action=MakingComment" style = "margin: 25px 50px 75px 800px;">
              <input type="text" style = "width: 500px; height: 100px;" name="kommentar" placeholder = "Schreibe Kommentar..." required oninvalid="this.setCustomValidity('Ein Kommentar darf nicht leer sein')"
				oninput="this.setCustomValidity('')"><br>
            <input type="checkbox"  name="Anonym" value="Anonym"> <strong>Anonym kommentieren?</strong><br>
            
                <button type="submit" id="submit">Kommentar hinzufügen</button>
        </form>
  </body>
</html>