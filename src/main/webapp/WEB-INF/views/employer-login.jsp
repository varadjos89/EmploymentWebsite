<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
.login {
    margin: auto;
    width: 65%;
    height: 350px;
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

.b1 {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 50%;
  border-radius: 25px;
}

.b1:hover {
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
					<li class="active"><a href="${contextPath}/student/loginPage.htm">Students</a></li>
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
	
	<div class="login" id="div3">
	<label class="lo">Employer Login</label>
         
         <form:form action="${contextPath}/employer/dashboard.htm" method="POST" modelAttribute="recruiter">
         <div class="container">
         <label for="uname"><b>Username</b></label><br>
         <form:input type="text" path="username"  />
         <form:errors path="username" /><br>
         
         <label for="psw"><b>Password</b></label><br>
         <form:input type="text" path="password"  />
         <form:errors path="password" />
         
         <input type="submit" value="login" class="b1">
         </div>
         </form:form>
         <a href="${contextPath}/employer/registerPage.htm"><button type="submit" class="b1" style='margin-left: 22px;margin-top: -18px;width: 563px'>Sign Up</button></a> 
	</div>
</body>
</html>
