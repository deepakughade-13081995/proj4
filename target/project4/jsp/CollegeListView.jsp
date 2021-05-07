<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj4.Clt.CollegeListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.CollegeBean"%>
<%@page import="in.co.sunrays.proj4.bean.UserBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

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
 
   <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CollegeBean"
       
        scope="request"></jsp:useBean>
        
         <jsp:useBean id="model" class="in.co.sunrays.proj4.model.CollegeModel"
       
        scope="request"></jsp:useBean>
        

 
    <center>
        <h1>College List</h1>
        
        
        <h2>
                    <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
                </h2>
        

        <form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">
<%List list1=ServletUtility.getList(request); %>
<%if(list1.size()!=0){ %>
            <table width="100%">
                <tr>
                    <td align="center"><label> Name :</label> <input type="text"
                        name="name"
                        value="<%=ServletUtility.getParameter("name", request)%>">
                        <label>City :</label> <input type="text" name="city"
                        value="<%=ServletUtility.getParameter("city", request)%>">
                        <input type="submit" name="operation"
                        value="<%=CollegeListCtl.OP_SEARCH%>">
                        <input type="submit" name="operation"
                        value="<%=CollegeListCtl.OP_RESET%>">
                        </td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%">
                <tr>
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No</th>
                    <th>Name.</th>
                    <th>Address.</th>
                    <th>State.</th>
                    <th>City.</th>
                    <th>PhoneNo.</th>
                     <%if(userBean1.getRoleId()==4 || userBean1.getRoleId()==3||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                   <th>Edit</th>
                   <%} %>
                </tr>
               <%--  <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr>
                --%> <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<CollegeBean> it = list.iterator();

                    while (it.hasNext()) {

                        CollegeBean bean1 = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean1.getId()%>" ></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean1.getName()%></td>
                    <td align="center"><%=bean1.getAddress()%></td>
                    <td align="center"><%=bean1.getState()%></td>
                    <td align="center"><%=bean1.getCity()%></td>
                    <td align="center"><%=bean1.getPhone()%></td>
                    <%if(userBean1.getRoleId()==4 ||userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                     <td align="center"><a href="CollegeCtl?id=<%=bean1.getId()%>">Edit</a></td>
                     <%} %>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                  <%if(pageNo==1){ %>  <td><input type="submit" name="operation" disabled="disabled"
                        value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
                    <td>
                    
                   <%}else{ %>  <td><input type="submit" name="operation"
                        value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
                    <td>
                   <%} %>
                    
                    
                     <%if(userBean1.getRoleId()==4||userBean1.getRoleId()==3||userBean1.getRoleId()==2) {%> <td><input type="submit" name="operation" disabled="disabled"
                        value="<%=CollegeListCtl.OP_NEW%>"></td>
                        <%}else{ %>
                        <td><input type="submit" name="operation"
                        value="<%=CollegeListCtl.OP_NEW%>"></td>
                        <%} %>
                        <%if(userBean1.getRoleId()==4||userBean1.getRoleId()==3||userBean1.getRoleId()==2) {%> 
                    <td><input type="submit"
                        name="operation" disabled="disabled" value="<%=CollegeListCtl.OP_DELETE%>"></td>
                  <%}else{ %>
                   <td><input type="submit"
                        name="operation" value="<%=CollegeListCtl.OP_DELETE%>"></td>
                 
                     <%} %>
                    </td>
                    
                    <%if((model.nextPK()-1)==bean.getId() || list.size()<pageSize){ %>
                    <td align="right"><input type="submit" name="operation" disabled="disabled"
                        value="<%=CollegeListCtl.OP_NEXT%>"></td>
                        
                    <%}else{ %>     <td align="right"><input type="submit" name="operation"
                        value="<%=CollegeListCtl.OP_NEXT%>"></td>
             <%} %>
                        
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
                 <%}
        if(list1.size()==0){%>
        <table>
        <tr>
        <td><input type="submit" name="operation" value="<%=CollegeListCtl.OP_BACK%>"></td></tr>
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