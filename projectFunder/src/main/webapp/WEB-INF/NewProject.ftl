<!DOCTYPE html>
<html>
  <head>
    <title>Projekt Erstellen</title>
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

      <h1 style = "margin: 25px 50px 75px 800px;" ><strong>Projekt Erstellen</strong></h1>
      
        <form method="POST" action="CreateProjectServlet?action=CreateProject" style = "margin: 25px 50px 75px 800px;">
            <div>
            Title: 
            <input type="text" name="titel" maxlength="30" required style = " margin-left: 90px;" 	oninvalid="this.setCustomValidity('Der Titel muss zwischen 1 und 30 Zeichen lang sein')"
				oninput="this.setCustomValidity('')"><br> <br>
            </div>
            
            <div>
            Finanizierungslimit:
            <input type="number" name="Finanzierungslimit" value="100" min="100"> € <br>
           
            </div>
            
            <div>
            Kategorie <br>
            <input type="radio" name="Kategorie" style = " margin-left: 120px;" value="Health & Wellness" required> Health & Welness<br>
            <input type="radio" name="Kategorie" style = " margin-left: 120px;"  value="Art"> Art & Creative Works<br>
            <input type="radio" name="Kategorie"  style = " margin-left: 120px;" value="Education"> Education <br>
            <input type="radio" name="Kategorie" style = " margin-left: 120px;" value="Tech"> Tech & Innovation <br>
            </div>
            
            Vorgänger <br>
            <#list Vorgaenger as VorgaengerName> 
            
            <div>
            
            <input type="radio" name="vorgeanger" style = " margin-left: 120px;" value="${VorgaengerName.kennung}" required>${VorgaengerName.title} <br>
            
            </div>
            
            </#list>
             <input type="radio" name="vorgeanger" style = " margin-left: 120px;" value="Touch" required>keine Vorgänger <br>
            <div>
            Beschreibung <br>
            <input type="text" name="beschreibung" style = "width: 300px; height: 62px;"> <br>
            </div>
            
            <div>
		    <input type="hidden" value="${email}" name="ersteller">
	        </div>
            <button type="submit" id="submit" class="btn-group">Erstellen</button>
        </form>
  </body>
</html>