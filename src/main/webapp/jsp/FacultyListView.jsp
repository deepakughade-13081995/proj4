<%@page import="in.co.sunrays.proj4.bean.FacultyBean"%>
<%@page import="in.co.sunrays.proj4.Clt.FacultyListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

</head>
<body>

<%@include file="Header.jsp"%>
<%UserBean userBean1 = (UserBean) session.getAttribute("user");%>
  <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.FacultyBean" scope="request"></jsp:useBean>
 
 
  <jsp:useBean id="model" class="in.co.sunrays.proj4.model.FacultyModel"
       
        scope="request"></jsp:useBean>
        
 
    <center>
        <h1>Faculty List</h1>
        
        <h2>
                    <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
                </h2>
        

<h2> <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font></h2>

        <form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">
        <%List list1=ServletUtility.getList(request); %>
        <%if(list1.size()!=0){ %>

            <table width="100%">
                <tr>
                    <td align="center"><label>FirstName :</label> 
                    <input type="text" name="firstName" value="<%=ServletUtility.getParameter("firstName", request)%>">
                        &emsp;
                        
                         <label>LoginId:</label> <input type="text" name="login"
                        value="<%=ServletUtility.getParameter("login", request)%>">
                        &emsp; <input type="submit" name="operation" value="<%=FacultyListCtl.OP_SEARCH %>">
                        &nbsp; <input type="submit" name="operation" value="<%=FacultyListCtl.OP_RESET%>">
                    </td>
                </tr>
            </table>
            <br>

            <table border="1" width="100%">
                <tr>
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No</th>
                    <th>First Name</th>
                    <th>LastName</th>
                    <th>LoginId</th>
                    <th>Course Name</th>
                    <th>College Name</th>
                    <th>Address</th>
                    <th>Mobile No</th>
                     
                    <%if(userBean1.getRoleId()==4 || userBean1.getRoleId()==3||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                   <th>Edit</th>
                   <%} %>
            
                </tr>

                              <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<FacultyBean> it = list.iterator();
                    while (it.hasNext()) {
                        FacultyBean bean1 = it.next();
                %>
                <tr>
                    <td><input type="checkbox" class="checkbox" name="ids" value="<%=bean1.getId()%>"  ></td>
                     <td align="center"><%=index++%></td>
                    <td align="center"><%=bean1.getFirst_Name()%></td>
                    <td align="center"><%=bean1.getLast_Name()%></td>
                    <td align="center"><%=bean1.getLogin_Id()%></td>
                    <td align="center"><%=bean1.getCource_Name()%></td>
                    <td align="center"><%=bean1.getCollege_Name()%></td>
                      <td align="center"><%=bean1.getAddress()%></td>
                     <td align="center"><%=bean1.getMobile_No()%></td>
                     <%if(userBean1.getRoleId()==4 ||userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
                    
                     <%}else{ %>
                     <td align="center"><a href="FacultyCtl?id=<%=bean1.getId()%>">Edit</a></td>
                     <%} %>
           
         
      
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                  <%if(pageNo==1){ %>  <td ><input type="submit" name="operation" disabled="disabled"
                        value="<%=FacultyListCtl.OP_PREVIOUS%>"></td>
                        
                     <%}else{ %>     <td ><input type="submit" name="operation"
                        value="<%=FacultyListCtl.OP_PREVIOUS%>"></td>
                  <%} %>
                     
                     <%if(userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>   
                        <td><input type="submit" name="operation"
                        disabled="disabled" value="<%=FacultyListCtl.OP_NEW%>"></td>
                        
                        <%}else{ %>
                         <td><input type="submit" name="operation"
                        value="<%=FacultyListCtl.OP_NEW%>"></td>
                     <%} %>
                    
                   
                    <%if(userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
            <td ><input type="submit" name="operation"  disabled="disabled" value="<%=FacultyListCtl.OP_DELETE%>"></td>
                      <%}else{ %>
                      <td ><input type="submit" name="operation" value="<%=FacultyListCtl.OP_DELETE%>"></td>
                      <%} %>
                      
                      
                        
                      <%if((model.nextPK()-1)==bean.getId() || list.size()<pageSize){ %>  
                     <td align="right"><input type="submit" name="operation" disabled="disabled"
                        value="<%=FacultyListCtl.OP_NEXT%>"></td>
                        
                    <%}else{ %>    
                        
                         <td align="right"><input type="submit" name="operation"
                        value="<%=FacultyListCtl.OP_NEXT%>"></td>
             <%} %>
                        
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
                <%}  if(list1.size()==0){%>
        <table>
        <tr>
        <td><input type="submit" name="operation" value="<%=FacultyListCtl.OP_BACK%>"></td></tr>
        </table>
              <%} %>  
  
        </form>
    </center>
     <br>
    
<br>
<br>
<br>
<br>
   
    
  
    <%@include file="Footer.jsp"%>
</body>
</html>