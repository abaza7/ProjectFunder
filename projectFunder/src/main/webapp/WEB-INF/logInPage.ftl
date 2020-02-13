<html>



<html>
<h1 style = "margin: 25px 50px 5px 840px; color: green;"> Login Demo </h1>
<style>
		body {
		background-image: url('D:\WiSe19-20\datenbanken\lab\block3\projekte\java\projectFunder\src\main\webapp\images\myimage.png');
		}
		table, th, td {
		border: 1px solid green;
		border-collapse: collapse;
		border-spacing: 5px;
		margin: 25px 50px 75px 800px;
		}
		th, td {
		padding: 15px;
		}
		th {
		text-align: center;
		}
		
</style>
<body>

<table class="datatable">
    <tr>
            <th>Email Address</th>  <th>Name</th>   
    </tr>
    <#list users as user>
     <tr>
            <td><a href="HomepageServlet?action=userEmail&amp;email=${user.email}&amp;name=${user.name} "> ${user.email}</a></td> <td>${user.name}</a></td>
     </tr>
    </#list>
</table>


</body>
</html>