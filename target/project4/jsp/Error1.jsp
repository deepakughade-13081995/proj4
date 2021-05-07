<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
    <%@ page errorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>

<br><br><br><br><br><br>
<!-- <form action="ErrorCtl" method="post"> -->
<img alt="" src="/project4/img/e.jpg" width="250px" height="130px">
<H1 style="color:red">Requested resource is not available </H1 >
<h2 style="color:blue">please check the url !! </h2>
<a href="<%=ORSView.WELCOME_CTL%>"  style="color:green"><H2>Click here to go back</H2></a>
</center>
</body>
</html>