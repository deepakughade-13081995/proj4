<%@page import="in.co.sunrays.proj4.Clt.RoleListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="in.co.sunrays.proj4.Clt.RoleCtl"%>
<%@page import="in.co.sunrays.proj4.Clt.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<head>
<body>

<form action="<%=ORSView.ROLE_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

     <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.RoleBean"
       
        scope="request"></jsp:useBean>
        
    <%
    System.out.println("bean me se name liya    "+bean.getName());%>

        <center>
        
           <%
if(bean!=null && bean.getId()>0){
%>
<h1>Update Role</h1>
<%}else{%>
        
  
            <h1>Add Role</h1>
           <%} %>
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table>
                <tr>
                    <th align="left">Name<span style="color: red">*</span></th>
                    <td><input type="text" name="name" placeholder="Enter role name" value="<%=DataUtility.getStringData(bean.getName())%>"></td>
                    <td style="position: fixed;">
                    <font color="red"><%=ServletUtility.getErrorMessage("name",request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Description<span style="color: red">*</th>
                    <td><input type="text" name="description" placeholder="Enter description"
                        value="<%=DataUtility.getStringData(bean.getDescription())%>"></td>
                        <td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    
                     <%
if(bean.getId()>0 && bean!=null){
%> 
  <td> <input type="submit" name ="operation" value="<%=RoleCtl.OP_UPDATE%>">
 &nbsp;<input type="submit" name ="operation" value="<%=RoleCtl.OP_CANCEL%>"></td>
 

<%}else{ %>
                    
                    
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=RoleCtl.OP_SAVE%>">
                        <input type="submit"
                        name="operation" value="<%=RoleCtl.OP_RESET%>">
                       <%--  <input type="submit"
                        name="operation" value="<%=RoleCtl.OP_DELETE%>"> --%></td>
                        <%} %>
                </tr>
            </table>
    </form>
    </center>
    <br>
    <bt>
    <br>
    <br>
    <br>
     <br>
    <bt>
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
  
   
    <%@ include file="Footer.jsp"%>


</body>
</html>