<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Dashboard</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.navbar {
	margin-bottom: 50px;
	border-radius: 0;
}

.jumbotron {
	margin-bottom: 0;
}
.login {
    margin: auto;
    width: 65%;
    height: 350px;
    display:none;
    padding: 10px;
    border: red 1px solid;
    position: relative;
}
#div4 {
	margin: auto;
    width: 65%;
    padding: 10px;
    border: red 1px solid;
    position: relative;   

}
.lo {
  color: white;
  line-height: 20px;
  padding: 0px 5px;
  position: absolute;
  background-color: black;
  /* Adjust these values to posiytion the title or label */
  top: -10px;
  left: 10px;
}



.tarea {
  width: 50%;
  height:500px;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  border-radius: 25px;
}

.button {
  background-color: blue;
  border: none;
  color: white;
  padding: 8px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}

.button:hover {
  opacity: 0.8;
}

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

.container {
  padding: 18px;
}


#frame {
	border: 1px solid grey;
	background-size: 100%;
	/*background-image:
		url(/images/background.jpg);*/
	background-repeat: no-repeat;
	background-position: center;
	background-color: red;
}
#textcenter{
	padding-left: 5%;
	color:  white;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<div class="jumbotron" id="frame">
		<div id="textcenter">
			<h1>Neu Careers</h1>
			
		</div>
	</div>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="${contextPath}/employer/logout.htm">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/faculty/logout.htm"><button class="btn btn-default"
							data-toggle="modal" data-target="#myModalHorizontal">Logout</button></a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/faculty/dashboard.htm">Show Employer Requests</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/faculty/add/studentPage.htm">Add Students</a></li>
				</ul>
				
			</div>
		</div>
	</nav>
	
	<div id="div4">
	<label class="lo"><b>Employer Requests</b></label><br>
	  
         <div class="container">
             <table>  
             <tr>
                <td><b>Name</b></td>
                <td><b>Email</b></td>
                <td><b>Contact</b></td>
                <td><b>Company Name</b></td>
                <td><b>Website</b></td>
                <td><b>Industry</b></td>
                <td></td>
                <td></td>
             </tr>
             <c:forEach items="${rlist}" var="recruiter">
                <tr>
                    <td><c:out value="${recruiter.name}"/></td>
                    <td><c:out value="${recruiter.email}"/></td>
                    <td><c:out value="${recruiter.contact}"/></td>
                    <td><c:out value="${recruiter.companyname}"/></td>
                    <td><c:out value="${recruiter.website}"/></td>
                    <td><c:out value="${recruiter.industry}"/></td>
                    <td>
                    <form action="${contextPath}/faculty/approve/request.htm?id=${recruiter.id}" method="post">
                    <input type="submit" value="Approve" class="button"/>
                    </form>
                    </td>
                    <td>
                    <form action="${contextPath}/faculty/deny/request.htm?id=${recruiter.id}" method="post">
                    <input type="submit" value="Deny" class="button"/>
                    </form>
                    </td>
                </tr>                
            </c:forEach>
            </table>
         </div>
         
	</div>
</body>
</html>
