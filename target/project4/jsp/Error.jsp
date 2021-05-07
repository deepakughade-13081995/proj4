<%@page import="in.co.sunrays.proj4.Clt.ErrorCtl"%>
<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>505 error</title>
</head>
<body>
<center><form action="ErrorCtl" method="post"><br><br><br>
<img alt="" src="/project4/img/e.jpg" width="250px" height="130px">
<h2>OOPS!! No internet</h2>
<h4>Try:</h4>

<div style="color:green" >
 <h3><span style="color:blue">1)</span>Checking the network cables, modem, and router</h3>
 <h3><span  style="color:blue">2)</span>Running Windows Network Diagnostics</h3>
 <h3><span  style="color:blue">3)</span>Reconnecting to Wi-Fi</h3>
 <h3><span  style="color:blue">4)</span>Problem in WebApplication!! Try after some time</h3>
  </div>
 
<p style="color:red">ERR_INTERNET_DISCONNECTED</p>

<a href="<%=ORSView.WELCOME_CTL%>"  style="color:blue"><H2>Click here to go back</H2></a></center>
</body>
</html>