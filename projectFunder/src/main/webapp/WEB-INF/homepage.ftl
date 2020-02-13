<html>
<head><title>Home Page</title></head>
 
<body>

    <div>
       <a href="/projectFunder/login" class="button">${name}</a>  
	</div>

<br>
<br>
<br> 
 
  <table class="datatable">
    <tr>
        <th>title</th>  <th>ersteller</th>  <th>AktuelleSpende</th> 
    </tr>
    <#list projects as project>
    <tr>
        <td><a href="ProjectDetails?action=id&amp;kennung=${project.kennung}">${project.title}</a></td> <td>${project.ersteller}</td> <td>${project.aktulleSpende}</td> 
    </tr>
    </#list>
  </table>
  
<br>
<br>
<br>  
  
	<div>
       <a href="CreateProjectServlet?action=userEmail&amp;email=${email}" class="button">Projekt Erstelln</a>  
	</div>

  
  
</body>
</html>




