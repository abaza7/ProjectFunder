<html>
<div>
  <h1 style = "margin: 25px 50px 5px 840px; color: green;">Feedback</h1>
</div>
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
<body>
<h2 style = "margin: 100px 100px 75px 750px; color: red; text-shadow: 1px 1px red; font-size: 400%;" >${message}</h2>

<br>
<br>
<br>  
  
	<div style = "margin: 20px 50px 75px 780px;" class="btn-group">
	   <button onclick="window.location.href = '/projectFunder/home';">Homepage</button>
	   <button onclick="window.location.href = '/projectFunder/create';">Projekt Erstellen</button>
	</div>


<body>
</html>