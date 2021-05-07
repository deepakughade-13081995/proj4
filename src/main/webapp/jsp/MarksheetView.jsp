<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj4.Clt.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head><title>Marksheet View</title></head>
<body>
<%@ include file="Header.jsp"%>  

<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
        

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean"
            scope="request"></jsp:useBean>

        <%
            List l = (List) request.getAttribute("studentList");
        
        System.out.println("list value  "+l.size());
        System.out.println("stu id  "+bean.getStudentId());
        %>
        
      
        <center>
            
      <%  if(bean!=null && bean.getId()>0){
%>
<h1>Update Marksheet</h1>
<%}else{%>
        
  
            <h1>Add Marksheet</h1>
            <%} %>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>


            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table> 
                <tr>
                    <th align="left">Rollno<span style="color: red">*</span></th>
                    <td><input type="text" size="27px" name="rollNo" placeholder="roll number"
                        value="<%=DataUtility.getStringData(bean.getRollNo())%>"
                        <%=(bean.getId() > 0) ? "readonly" : ""%>></td>
                        <td style="position: fixed;"> <font
                        color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td> 
                </tr>
                
                  <tr>
                    <th align="left">Name<span style="color: red">*</span></th>
                    <td><%=HTMLUtility.getList("studentId",String.valueOf(bean.getStudentId()),l)%></td>
                    <td style="position: fixed"> <font
                        color="red" > <%=ServletUtility.getErrorMessage("studentId", request)%></font></td>
         
                </tr>
                 
                  <tr>
                    <th align="left">Physics<span style="color: red">*</span></th>
                    <td><input type="text" size="27px" name="physics" placeholder="physics marks"
                        value="<%=(DataUtility.getStringData(bean.getPhysics()).equals("0")?"":DataUtility.getStringData(bean.getPhysics()))%>">
                        </td>
                        <td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Chemistry<span style="color: red">*</span></th>
                    <td><input type="text" size="27px" name="chemistry" placeholder="chemistry marks"
                        value="<%=(DataUtility.getStringData(bean.getChemestry()).equals("0")?"":DataUtility.getStringData(bean.getChemestry()))%>">
                        </td>
                        <td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
                </tr>
                <tr align="left">
                    <th>Maths<span style="color: red">*</span></th>
                    <td><input type="text" size="27px" name="maths" placeholder="maths marks"
                        value="<%=(DataUtility.getStringData(bean.getMaths()).equals("0")?"":DataUtility.getStringData(bean.getMaths()))%>">
                        </td>
                        <td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

                </tr>
               <tr>
                 <th></th>
                        <%
if(bean.getId()>0 && bean!=null){
%> 
  <td> <input type="submit" name ="operation" value="<%=MarksheetCtl.OP_UPDATE%>">
 &nbsp;<input type="submit" name ="operation" value="<%=MarksheetCtl.OP_CANCEL%>"></td>
 

<%}else{ %>
                    
                    <td colspan="2"><input type="submit" size="25px" name="operation"
                        value="<%=MarksheetCtl.OP_SAVE%>"> 
                        <input type="submit" name="operation"
                        value="<%=MarksheetCtl.OP_RESET%>"></td>
                        <%} %>
                        </tr>
                        </table>
        </form>
        </center>
         <br>
    
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
   
    
    
  
       <%@include file="Footer.jsp"%>
    </body>
</html>
