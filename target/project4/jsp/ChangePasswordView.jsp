<%@page import="in.co.sunrays.proj4.Clt.ChangePasswordCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">
<%@include file="Header.jsp"%>
<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"></jsp:useBean>

<center>
<h1>Change Password</h1>

<h2>
<font color="red"><%=ServletUtility.getErrorMessageHtml(request) %></font></h2>
<H3>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
          
            </H3>
 

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

<table>
 <tr>
 <th align="left">Old Password <span style="color: red">*</span></th>
 <td><input type="password" name="oldPassword" placeholder="old password" value="<%=DataUtility.getStringData(request.getParameter("oldPassword"))%>">
 </td><td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage
      ("oldPassword1", request)%></font></td>
      
 </tr>
 
 <tr>
 <th align="left">New Password <span style="color: red">*</span></th>
 <td><input type="password" name="newPassword"  placeholder="new password" value="<%=DataUtility.getStringData(request.getParameter("newPassword"))%>">
 </td><td style="position: fixed;"><font
                        color="red"><%=ServletUtility.getErrorMessage("newPassword1", request)%></font></td>
 
 
 </tr>
 <tr align="left">
 <th>Confirm Password <span style="color: red">*</span></th>
 <td> <input type="password" name="confirmPassword" placeholder="confirm password" value="<%=DataUtility.getStringData(request.getParameter("confirmPassword"))%>">
 </td><td style="position: fixed;"><font
             color="red"> <%=ServletUtility.getErrorMessage("confirmPassword1", request)%></font></td>
 </tr>

<tr align="left">
<th></th>
<td colspan="2"><%-- <input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>"> &nbsp; --%>
<input type="submit" name="operation" value="<%= ChangePasswordCtl.OP_SAVE%>">
            <input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>"> </td>
</tr>
</table>

  

</center>

</form>
  <br>
    <bt>
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
   
    <br>
    <br>
   
    <br>
    <br>
   
   
   
 <%@ include file="Footer.jsp"%>

</body>
</html>