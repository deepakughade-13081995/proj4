<%@page import="in.co.sunrays.proj4.Clt.SubjectListCtl"%>
<%@page import="in.co.sunrays.proj4.Clt.TimetableListCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.Clt.SubjectCtl"%>
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
<div>
<body>

<form action="<%=ORSView.SUBJECT_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
        
        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.SubjectBean"
       
        scope="request"></jsp:useBean>
        

     
            <%
            List l = (List) request.getAttribute("courceName");
             
        
        %>
      <%--  <%
       List l1 = (List) request.getAttribute("subjectName");
       %>
       --%>  
      <%System.out.println("id========  "+bean.getId()); %>
        
        <center>
           
             <%
if(bean!=null && bean.getId()>0){
%>
<h1>Update Subject</h1>
<%}else{%>
        
  
           
            <h1 >Add Subject</h1>
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
                    <th align="left">Cource name<span style="color: red">*</span></th>
                    <td >
                    
                    <%-- <input type="text" name="cname" value="<%=DataUtility.getStringData(bean.getCourceName())%>"> --%>
                    
                    <%=HTMLUtility.getList("courceId",String.valueOf(bean.getCourceId()),l)%></td>
                   <td style="position: fixed ;" > <font color="red"><%=ServletUtility.getErrorMessage("courceId",request)%></font></td>
                    
                    
                </tr>
                <tr>
                    <th align="left">Subject name<span style="color: red">*</span></th>
                    <td>
                    
                  <input type="text"  size="27px" name="sname" placeholder="Enter subject name"  value="<%=DataUtility.getStringData(bean.getSubjectName())%>"></td>
                    <%-- <%=HTMLUtility.getList("subjectId",String.valueOf(bean.getsubjectId()),l1)%></td> --%>
                  <td style="position: fixed" ;>  <font
                        color="red"> <%=ServletUtility.getErrorMessage("sname", request)%></font></td></td>
                </tr>
                
                <tr>
                    <th align="left">Decription<span style="color: red">*</th>
                    <td><input type="text"  size="27px" name="description" placeholder="Enter description"
                        value="<%=DataUtility.getStringData(bean.getDescription())%>">
                        <td style="position: fixed ";><font
                        color="red"> <%=ServletUtility.getErrorMessage("description1", request)%></font></td></td>
                </tr>
               
                
                <tr>
                    <th></th>
                    
                       
                    <%
if(bean.getId()>0 && bean!=null){
%> 
  <td> <input type="submit" name ="operation" value="<%=SubjectCtl.OP_UPDATE%>">
 &nbsp;<input type="submit" name ="operation" value="<%=SubjectCtl.OP_CANCEL%>"></td>
 

<%}else{ %>
                    
                    
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=SubjectCtl.OP_SAVE%>">
                        <input type="submit"
                        name="operation" value="<%=SubjectCtl.OP_RESET%>">
                       <%--  <input type="submit"
                        name="operation" value="<%=RoleCtl.OP_DELETE%>"> --%></td>
                        <%} %>
                </tr>
            </table>
   

</center>
</form>
<Br>
<Br>
<Br>
<Br>

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

<%@include file="Footer.jsp"%>
</body>
</div>
</html>