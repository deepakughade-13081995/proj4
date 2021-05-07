<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="in.co.sunrays.proj4.Clt.MarksheetListCtl"%>
<%@page import="in.co.sunrays.proj4.Clt.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.MarksheetBean"%>
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
 
 
     <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean"
       
        scope="request"></jsp:useBean>
        
         <jsp:useBean id="model" class="in.co.sunrays.proj4.model.MarksheetModel"
       
        scope="request"></jsp:useBean>
 
 <%List list1=ServletUtility.getList(request); %>
 
    <center>
        <h1>Marksheet List</h1>

 <h2>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
               
               <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
               
                </h2>
        <form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">
<%if(list1.size()!=0){ %>
            <table width="100%">
                <tr>
                    <td align="center"><label> Name :</label> <input type="text"
                        name="name"
                        value="<%=ServletUtility.getParameter("name", request)%>">
                        &emsp; <label>RollNo :</label> <input type="text" name="rollNo"
                        value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
                        <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_SEARCH %>">
                     &nbsp;<input type="submit" name="operation" value="<%=MarksheetListCtl.OP_RESET %>"></td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%" ><!-- cellpadding="10px" cellspacing="1.5" -->
                <tr>
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No</th>
                    <th>RollNo</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
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
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetBean bean1 = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean1.getId()%>" ></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean1.getRollNo()%></td>
                    <td align="center"><%=bean1.getName()%></td>
                    <td align="center"><%=bean1.getPhysics()%></td>
                    <td align="center"><%=bean1.getChemestry()%></td>
                    <td align="center"><%=bean1.getMaths()%></td>
                   <%if(userBean1.getRoleId()==4 ||userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                     <td align="center"><a href="MarksheetCtl?id=<%=bean1.getId()%>">Edit</a></td>
                     <%} %>
           
         
                </tr>

                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                <%if(pageNo==1){ %>     
                <td><input type="submit" name="operation" disabled="disabled"  value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
                        
                        <%} else { %>
                         
                 <td><input type="submit" name="operation" value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
                   
                    </td><%} %>
                    
                    
                    
                   
                   
                <td><input type="submit" name="operation" 
                        value="<%=MarksheetListCtl.OP_NEW%>"></td>
                        
                                     
                        
                   <%if(userBean1.getRoleId()==3 ||userBean1.getRoleId()==2||userBean1.getRoleId()==4){ %>     
                    <td><input type="submit"
                        name="operation"   disabled="disabled" value="<%=MarksheetListCtl.OP_DELETE%>"></td>
                   <%}else{ %>
                   <td><input type="submit"
                        name="operation" value="<%=MarksheetListCtl.OP_DELETE%>"></td>
                 <%} %>
                   
                   
                 <%if((model.nextPK()-1)==bean.getId() || list.size()<pageSize){ %>
                    <td align="right"><input type="submit" name="operation"  disabled="disabled"
                        value="<%=MarksheetListCtl.OP_NEXT%>"></td>
                        
                         <%} else{%>
                        
                        
                        <td align="right"><input type="submit" name="operation"
                        value="<%=MarksheetListCtl.OP_NEXT%>"></td>
            
                        <%} %>
              
           
                        
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
                 <%}
        if(list1.size()==0){%>
        <table>
        <tr>
        <td><input type="submit" name="operation" value="<%=MarksheetListCtl.OP_BACK%>"></td></tr>
        </table>
              <%} %>  
       
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
    
   
    
  
    <%@include file="Footer.jsp" %>


</body>
</html>