<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

input {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  border-radius: 25px;
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

button {
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
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/employer/logout.htm"><button class="btn btn-default"
							data-toggle="modal" data-target="#myModalHorizontal">Logout</button></a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/employer/show/jobs.htm">Show Posting</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/employer/add/jobPage.htm">Add Posting</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/employer/show/applications.htm">View Applications</a></li>
				</ul>
				
				
			</div>
		</div>
	</nav>
	
	<div id="div4">
	<label class="lo"><b>Employer Registration</b></label><br>
	  <form:form action="${contextPath}/employer/add/job.htm" method="post"
		modelAttribute="job">
	     <h3><b>&nbsp;&nbsp;Add new job posting</b></h3>
         <div class="container">
         <table>
         <col width="250">
         <col width="600">
         <tr>
         <td><label for="uname"><b>Job Title</b></label></td>
         <td><form:input type="text" path="title" required="required" /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Organization</b></label></td>
         <td><form:input type="text" path="organization" required="required" /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Openings</b></label></td>
         <td><form:input type="text" path="openings" required="required" /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Term</b></label></td>
         <td><form:input type="text" path="term" required="required" /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Length</b></label></td>
         <td><form:input type="text" path="length" required="required" /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Location</b></label></td>
         <td><form:input type="text" path="location" required="required" /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Minimum Wage</b></label></td>
         <td><form:input type="text" path="wage" required="required" /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Deadline</b></label></td>
         <td><form:input type="date" path="date" required="required" /></td>
         </tr>
         <tr>
         <td><label for="psw"><b>Job Description</b></label></td>
         <td><form:textarea class="tarea" path="description" required="required" /></td>
         </tr>
         </table><br><br>
         <input type="submit" id="b1">
         </div>
         
      </form:form>
	</div>
</body>
</html>
