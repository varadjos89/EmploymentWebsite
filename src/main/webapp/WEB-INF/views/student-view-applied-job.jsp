<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.neu.edu.pojo.Job"%>
<%@ page import="com.neu.edu.pojo.Application"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
.container{
    margin: auto;
    width: 65%;
    padding: 10px;
    border: red 1px solid;
   }
.container h2 {
    background:#0094ff;
    color:white;
    padding:10px;
    }
#div4 {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#div4 td, #div4 th {
  border: 1px solid #ddd;
  padding: 8px;
}

#div4 tr:nth-child(even){background-color: #f2f2f2;}

#div4 tr:hover {background-color: #ddd;}

#div4 th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
html, body {
  text-align: center;
  padding-top: 40px;
  
  -webkit-perspective: 700px;
     -moz-perspective: 700px;
      -ms-perspective: 700px;
          perspective: 700px;
}

/* Reset */
.button {
  background: transparent;
  border: 0;
  padding: 0;
  cursor: pointer;
  outline: 0;
  -webkit-appearance: none;
}

/* Custom */
.button {
  display: inline-block;
  position: relative;
  padding: 10px 38px;
  top: 0;
  font-size: 20px;
  font-family: "Open Sans", Helvetica;
  border-radius: 6px;
  border-bottom: 1px solid rgba( 28, 227, 125, 0.5 );
  background: rgba( 22, 230, 137, 1 );
  color: #fff;
  box-shadow: 0px 0px 0px rgba( 15, 165, 60, 0.1 );
  
  -webkit-transform: translateZ(0);
     -moz-transform: translateZ(0);
      -ms-transform: translateZ(0);
          transform: translateZ(0);
  
  -webkit-transition: all 0.2s ease;
     -moz-transition: all 0.2s ease;
      -ms-transition: all 0.2s ease;
          transition: all 0.2s ease;
}

.button:hover {
  top: -10px;
  box-shadow: 0px 10px 10px rgba( 15, 165, 60, 0.2 );
  
  -webkit-transform: rotateX(20deg);
     -moz-transform: rotateX(20deg);
      -ms-transform: rotateX(20deg);
          transform: rotateX(20deg);
}

.button:active {
  top: 0px;
  box-shadow: 0px 0px 0px rgba( 15, 165, 60, 0.0 );
  background: rgba( 20, 224, 133, 1 );
}
</style>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <div class="container">
  <h2>Job Posting Information</h2>
  <c:set var ="jobs" value = "${job}" />
  <form action="${contextPath}/student/apply.htm">
  <input type="hidden" name="id" value="${jobs.id}"/>
  <%
    Application appliedjob= (Application)request.getAttribute("job");
    Job job= appliedjob.getJob();             
  %>
  <table id="div4">
  <col width="250">
  <col width="600">
  <tr>
     <td><b>Co-op Job Title</b></td>
     <td><%=job.getTitle() %></td>
  <tr>
  <tr>
     <td><b>Openings</b></td>
     <td><%=job.getOpenings() %></td>
  <tr>
  <tr>
     <td><b>Term</b></td>
     <td><%=job.getTerm() %></td>
  <tr>
  <tr>
     <td><b>Length</b></td>
     <td><%=job.getLength() %></td>
  <tr>
  <tr>
     <td><b>Location</b></td>
     <td><%=job.getLocation() %></td>
  <tr>
  <tr>
     <td><b>Job Description</b></td>
     <td><%=job.getDescription() %></td>
  <tr>
  <tr>
     <td><b>Minimum Wage</b></td>
     <td><%=job.getWage() %></td>
  <tr>
  <tr>
     <td><b>Resume</b></td>
     <td><%=appliedjob.getResume() %></td>
  <tr>
  </table><br>
  </form>
  </div>
</body>

</html>