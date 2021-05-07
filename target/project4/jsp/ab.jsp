<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="in.co.sunrays.proj4.Clt.ErrorCtl"%>
<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
#error
{
	 padding: 60px;
	 margin-left: 200px;
}

</style>
</head>

</head>
<body>
<form action="<%= ORSView.ERROR_CTL %>" method="GET">
<div id="error">

<h1 align="Left">
                <img src="<%=ORSView.APP_CONTEXT%>/img/error.png" width="170"
                    height="150">
            </h1>


<h2>OOPS!! No internet</h2>
<h4>Try:</h4>

<ul>
  <li>Checking the network cables, modem, and router</li>
  <li>Running Windows Network Diagnostics</li>
  <li>Reconnecting to Wi-Fi</li>
  <li>Problem in WebApplication!! Try after some time</li>
</ul>  
<p>ERR_INTERNET_DISCONNECTED</p>
</div>

</body>
</html>