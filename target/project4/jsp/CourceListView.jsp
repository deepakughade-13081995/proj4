<%@page import="in.co.sunrays.proj4.bean.CourceBean"%>
<%@page import="in.co.sunrays.proj4.Clt.CourceListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

</head>
<body>
<%UserBean userBean1 = (UserBean) session.getAttribute("user");%>

<%@include file="Header.jsp"%>

 <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CourceBean"
       
        scope="request"></jsp:useBean> 
         <jsp:useBean id="model" class="in.co.sunrays.proj4.model.CourceModel"
       
        scope="request"></jsp:useBean>
        
        
        


<%
List ll=(List)request.getAttribute("courceName");
%>
    <center>
        <h1>Course List</h1>
        
        <h2>
                    <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
                </h2>
                
                <h2><font color="green"><%=ServletUtility.getSuccessMessage(request) %></font></h2>
        

        <form action="<%=ORSView.COURCE_LIST_CTL%>" method="post">
        <%List list1=ServletUtility.getList(request); %>
        <%if(list1.size()!=0) {%>
            <table width="100%">
                <tr>
                    <td align="center"><label> Course Name :</label> <%-- <input type="text"
                        name="name"
                        value="<%=ServletUtility.getParameter("name",request)%>"> --%>
                         <%=HTMLUtility.getList("courceId",String.valueOf(bean.getId()),ll)%>
                        &nbsp; <input type="submit" name="operation" value="<%=CourceListCtl.OP_SEARCH%>">
                    </td>
            </tr>
            </table>
            
             <table border="1" width="100%">
                <tr>
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No</th>
                    <th>Name</th>
                    <!-- <th>CourseId</th> -->
                    <th>Duration(years)</th>
                   
          <%if(userBean1.getRoleId()==3||userBean1.getRoleId()==2 ||userBean1.getRoleId()==4){ %>
                    
                    <%}else{ %>
                   <th>Edit</th>
                   <%} %>
   
                </tr>
               <%--  <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr>
 --%>
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<CourceBean> it = list.iterator();
                    while (it.hasNext()) {
                        CourceBean bean1 = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean1.getId()%>" ></td>
                    
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean1.getName()%></td>
                    <%-- <td align="center"><%=bean1.getCourceId()%></td> --%>
                     <td align="center"><%=bean1.getDuration()%></td>
                    <%if(userBean1.getRoleId()==3 ||userBean1.getRoleId()==2 ||userBean1.getRoleId()==4){ %>
                    
                    <%}else{ %>
                     <td align="center"><a href="CourceCtl?id=<%=bean1.getId()%>">Edit</a></td>
                     <%} %>
          
                </tr>
                <% 
                    }
                %>
            </table>
            
            <table width="100%">
                <tr>
                 <%if(pageNo==1) {%>   <td><input type="submit" name="operation" disabled="disabled"
                        value="<%=CourceListCtl.OP_PREVIOUS%>"></td>
                        
                    <%}else{ %>    <td><input type="submit" name="operation"
                        value="<%=CourceListCtl.OP_PREVIOUS%>"></td>
                    
                       <%} %> 
                       
                        <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %> 
                      <td><input type="submit" name="operation" disabled="disabled"
                        value="<%=CourceListCtl.OP_NEW%>"></td>
                       <%}else{ %>
                           <td><input type="submit" name="operation"
                        value="<%=CourceListCtl.OP_NEW%>"></td>
                  <%} %>
                  
                  
                    <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %>      
                    <td><input type="submit"
                        name="operation" disabled="disabled" value="<%=CourceListCtl.OP_DELETE%>"></td>
                        
                        <%}else{%>
                        <td><input type="submit"
                        name="operation" value="<%=CourceListCtl.OP_DELETE%>"></td>
                    <%} %>
                        
                        
                  
                   <%if((model.nextPK()-1)==bean.getId() || list.size()<pageSize){ %> 
                    <td align="right"><input type="submit" name="operation" disabled="disabled"
                        value="<%=CourceListCtl.OP_NEXT%>"></td>
                   <%}else{ %>     
                         <td align="right"><input type="submit" name="operation"
                        value="<%=CourceListCtl.OP_NEXT%>"></td>
              
              
               <%} %>
               
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
                <% }  if(list1.size()==0){%>
        <table>
        <tr>
        <td><input type="submit" name="operation" value="<%=CourceListCtl.OP_BACK%>"></td></tr>
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