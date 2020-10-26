<%@page import="java.util.Iterator"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.neu.edu.pojo.Job"%>
<%@ page import="com.neu.edu.pojo.Application"%>
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
			    <a href="${contextPath}/student/logout.htm">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/student/logout.htm"><button class="btn btn-default"
							data-toggle="modal" data-target="#myModalHorizontal">Logout</button></a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/student/dashboard.htm">Co-op Postings</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/student/myapplications.htm">My Applications</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/student/shortlisted/jobs.htm">Shortlisted Jobs</a></li>
				</ul>
				

				
			</div>
		</div>
	</nav>
	
	
	<div id="div4">
	<label class="lo"><b>Postings</b></label><br>
	  
         <div class="container">
             <table>  
             <tr>
                <td></td>
                <td><b>Status</b></td>
                <td><b>Term</b></td>
                <td><b>Job Title</b></td>
                <td><b>Organization</b></td>
                <td><b>Openings</b></td>
                <td><b>Location</b></td>
                <td><b>Deadline</b></td>
             </tr>
                    <%
                         String status="";
			             List<Job> jobs= (List<Job>) request.getAttribute("jlist");
			             List<Application> appliedJobs= (List<Application>) request.getAttribute("alist");
			             for(Job j:jobs)
			             {
			            	for(Application a:appliedJobs)
			            	{
			            		if(j.getId()==a.getJob().getId())
			            		{
			            		    status="Applied";
			            		}
			            	}
			          %>
			          <tr>
			          <td><form method="post" action="${contextPath}/student/view/job.htm?id=<%=j.getId()%>">
			          <input type="submit" value="View" class="button">
			          <input type="hidden" name="status" value="<%=status %>"/>
			          </form>
			          </td>
			          <td><%=status %></td>
			          <td><%=j.getTerm() %></td>
			          <td><%=j.getTitle() %></td>
			          <td><%=j.getOrganization() %></td>
			          <td><%=j.getOpenings() %></td>
			          <td><%=j.getLocation() %></td>
			          <td><%=j.getDate().toString() %></td>
			          </tr>
			            	<%
			             status="";
			             }
			             
			        %>               
            </table>
         </div>
         
	</div>
	
</body>
</html>
