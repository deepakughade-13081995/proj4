
<%@page import="in.co.sunrays.proj4.Clt.StudentCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include file ="Header.jsp" %>

<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.StudentBean" scope="request"></jsp:useBean>
<center>
<%
if(bean!=null && bean.getId()>0){
%>
<h1>Update Student</h1>
<%}else{%>

<h1>Add Student</h1>
<%} %>
  
<h2>
<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>

<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
</h2>

<form action="<%=ORSView.STUDENT_CTL %>" method="post">
<%
List l1 = (List)request.getAttribute("collegeList");
System.out.println(l1.size());
%>

<input type="hidden" name = "id" value="<%=bean.getId()%>">
<input type="hidden" name = "createdBy" value="<%=bean.getCreatedBy()%>">
<input type="hidden" name = "modifiedBy" value="<%=bean.getModifiedBy()%>">
<input type="hidden" name = "createDateTime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
<input type="hidden" name = "modifiedDateTime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

<table border="1">
<%
System.out.println("id in jsp"+bean.getId());
%>

</table>

<table>
  
   <tr>
      <th align="left">College Name<span style="color: red">*</span></th>
      <td><%=HTMLUtility.getList("collegeId",String.valueOf(bean.getCollegeId()), l1) %></td><td style="position: fixed;">
     <font color="red"><%=ServletUtility.getErrorMessage("college",request) %></font> 
      </td>
 </tr>
      
   <tr >
      <th align="left">First Name<span style="color: red">*</span></th>
      <td><input type="text" name = "fname"  size="27px" placeholder="Enter first name" value="<%=DataUtility.getStringData(bean.getFirstName()) %>"></td>
      <td style="position: fixed">
      <font color="red"><%=ServletUtility.getErrorMessage("firstName1",request) %></font>
      </td>
 </tr>
 
 <tr>
      <th align="left">Last Name<span style="color: red">*</span></th>
      <td><input type="text" name = "lastName"  size="27px" placeholder="Enter last name" value="<%=DataUtility.getStringData(bean.getLastName()) %>"></td>
      <td style="position: fixed">
      <font color="red"><%=ServletUtility.getErrorMessage("lastName",request) %></font>
      </td>
 </tr>
      
 <tr>
      <th align="left">DOB<span style="color: red">*</span></th>
      <td><input type="text"  size="27px" name = "dob" placeholder="Date of Birth"  readonly="readonly"
      value="<%=DataUtility.getDateString(bean.getDob()) %>" id="date">
     </td>
     <td style="position: fixed"> <font color="red"><%=ServletUtility.getErrorMessage("dob",request) %></font>
      </td>
 </tr>
 
 <tr>
      <th align="left">Mobile<span style="color: red">*</span></th>
      <td><input type="text"  size="27px" name = "mobileNo"  placeholder="Enter mobile number" value="<%=DataUtility.getStringData(bean.getMobileNo()) %>">
      </td>
      <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("mobileNo",request) %></font>
      </td>
 </tr>
 
 <tr>
      <th align="left">Email<span style="color: red">*</span></th>
      <td><input type="text"  size="27px" name ="email" placeholder="Enter email Id" value="<%=DataUtility.getStringData(bean.getEmail()) %>">
      </td>
      <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("email",request) %></font>
      </td>
 </tr>
  <tr >
      <th align="left">Adderss<span style="color: red">*</span></th>
      <td><input type="text"  size="27px" name = "address" placeholder="Enter address" value="<%=DataUtility.getStringData(bean.getAddress()) %>"></td>
      <td style="position: fixed">
      <font color="red"><%=ServletUtility.getErrorMessage("address",request) %></font>
      </td>
 </tr>
 
      
</table>

<table>
<tr>
   <th></th>
   <td>
<%
if(bean.getId()>0 && bean!=null){
%> 
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name ="operation" value="<%=StudentCtl.OP_UPDATE%>">

 &nbsp;<input type="submit" name ="operation" value="<%=StudentCtl.OP_CANCEL%>"> </td> 

<%}else{ %>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name ="operation" value="<%=StudentCtl.OP_SAVE %>">
  
 &nbsp; <input type="submit" name ="operation" value="<%=StudentCtl.OP_RESET%>">
   
<%} %>
</table>
</form>
</center>
  <br>
    <br>
    <br>
    <br>
   
  <br>
    <br>
    <br>
    <br>
   
  <br>
    <br>
    <br>
    <br>
    <br>
    


   
  
   
  
   
  
   
  
   
    
    <%@ include file="Footer.jsp"%>

</body>
</html>