<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<html>
<head>
<title>Home</title>
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

input {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  border-radius: 25px;
}

#b1 {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 50%;
  border-radius: 25px;
}

button:hover {
  opacity: 0.8;
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
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
			    
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/">Home</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/student/login.htm">Students</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/faculty/loginPage.htm">Faculty/Staff</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/employer/loginPage.htm">Employeers</a></li>
				</ul>

				
			</div>
		</div>
	</nav>
	
	<div id="div4">
	<label class="lo"><b>Employer Registration</b></label><br>
	  <form:form action="${contextPath}/employer/register.htm" method="post"
		modelAttribute="recruiter">
	     <h3><b>&nbsp;&nbsp;Organization Information</b></h3>
         <div class="container">
         <table>
         <col width="250">
         <col width="600">
         <tr>
         <td><label for="uname"><b>Campany Name</b></label></td>
         <td><form:input type="text" path="companyname" /></td>
         <form:errors path="companyname" />
         </tr>
         <tr>
         <td><label for="psw"><b>Website</b></label></td>
         <td><form:input type="text" path="website" /></td>
         <form:errors path="website" />
         </tr>
         <tr>
         <td><label for="psw"><b>Industry</b></label></td>
         <td><form:input type="text" path="industry"  /></td>
         <form:errors path="industry" />
         </tr>
         </table><br><br>
         <h3><b>&nbsp;&nbsp;Recruiter Information</b></h3>
         <table>
         <col width="250">
         <col width="600">
         <tr>
         <td><label for="psw"><b>Name</b></label></td>
         <td><form:input type="text" path="name" /></td>
         <form:errors path="name" />
         </tr>
         <tr>
         <td><label for="psw"><b>Email</b></label></td>
         <td><form:input type="text" path="email"  /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Username</b></label></td>
         <td><form:input type="text" path="username" oninput="myFunction()" id="user" /></td>
         <form:errors path="username" />
         </tr>
         <tr>
         <td><label for="psw"><b>Password</b></label></td>
         <td><form:input type="text" path="password" id="pass"/></td>
         <form:errors path="password" />
         </tr>
         <tr>
         <td><label for="psw"><b>Contact</b></label></td>
         <td><form:input type="text" path="contact" pattern="[123456789][0-9]{9}" /></td>
         <form:errors path="contact" />
         </tr>
         </table>
         <input type="submit" id="b1">
         </div>
         
      </form:form>
	</div>
	
	<script>
	function myFunction() {
		var xhttp = new XMLHttpRequest();
		var name= document.getElementById('user').value;
		xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      var val=xhttp.responseText;
		      if(val=='1')
		    	  alert("Username already exists");
		    }
		  };
		  xhttp.open("GET", "/edu/employer/validate.htm?username="+name, true);
		  xhttp.send();
	}
	  
	</script>
	
</body>
</html>
