<%@page import="in.co.sunrays.proj4.bean.SubjectBean"%>
<%@page import="in.co.sunrays.proj4.Clt.SubjectListCtl"%>
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

<%@include file="Header.jsp"%>
<%UserBean userBean1 = (UserBean) session.getAttribute("user");%>
<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.SubjectBean"
       
        scope="request"></jsp:useBean>
        
            <jsp:useBean id="model" class="in.co.sunrays.proj4.model.SubjectModel"
       
        scope="request"></jsp:useBean>
     
<%
List l=(List)request.getAttribute("subjectname");
%>

<% 
List l1=(List)request.getAttribute("courcename");
%> 


    <center>
        <h1>Subject List</h1>
        
        <h2>
                    <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
                </h2>
        
        <h2>
        <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
        
        </h2>

         <form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">
         <%List list1=ServletUtility.getList(request); %>
         <%if(list1.size()!=0){ %>
            <table width="100%">
                <tr>
                    <td align="center"><label>Subject Name :</label><%--  <input type="text"
                        name="name" value="<%=ServletUtility.getParameter("name", request)%>"> --%>
                        
                         <%=HTMLUtility.getList("subjectId",String.valueOf(bean.getId()),l)%>
                           
                         
                         <label>Cource Name :</label>
                         <%=HTMLUtility.getList("courceId",String.valueOf(bean.getCourceId()),l1)%>
                        
                         &nbsp; <input type="submit" name="operation" value="<%=SubjectListCtl.OP_SEARCH%>">
                          &nbsp; <input type="submit" name="operation" value="<%=SubjectListCtl.OP_RESET%>">
                    </td>
            </tr>
            </table>
            
             <table border="1" width="100%">
                <tr>
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No</th>
                    
                    <th>Course Name</th>
                    <th>Subject Name</th>
                    <th>Description</th>
                     <%if(userBean1.getRoleId()==4 || userBean1.getRoleId()==3||userBean1.getRoleId()==2){ %>
                    
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
                    Iterator<SubjectBean> it = list.iterator();
                    while (it.hasNext()) {
                        SubjectBean bean1 = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean1.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                  
                    <td align="center"><%=bean1.getCourceName()%></td>
                     <td align="center"><%=bean1.getSubjectName()%></td>
                      <td align="center"><%=bean1.getDescription()%></td>
                    <%if(userBean1.getRoleId()==4 ||userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                     <td align="center"><a href="SubjectCtl?id=<%=bean1.getId()%>">Edit</a></td>
                     <%} %>
           
         
                </tr>
                <% 
                    }
                %>
            </table>
            
            
            <table width="100%">
                <tr>
                   <%if(pageNo==1){ %> <td><input type="submit" name="operation" disabled="disabled"
                        value="<%=SubjectListCtl.OP_PREVIOUS%>"></td>
                        
                      <%}else{ %>  <td><input type="submit" name="operation" 
                        value="<%=SubjectListCtl.OP_PREVIOUS%>"></td>
                        <%} %>
                        
                        
                   <td></td>
                    <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %>
                      <td><input type="submit" name="operation" disabled="disabled"
                        value="<%=SubjectListCtl.OP_NEW%>"></td>
                        <%}else{ %>
                         <td><input type="submit" name="operation" 
                        value="<%=SubjectListCtl.OP_NEW%>"></td>
                    <%} %>
                        
                      <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %>
                    <td><input type="submit"
                        name="operation" disabled="disabled" value="<%=SubjectListCtl.OP_DELETE%>"></td>
                      <%}else{ %>
                         <td><input type="submit"
                        name="operation" value="<%=SubjectListCtl.OP_DELETE%>"></td>
                <%} %>
                        
                   <%if((model.nextPk()-1)==bean.getId() || list.size()<pageSize){ %>
                    <td align="right"><input type="submit" name="operation" disabled="disabled"
                        value="<%=SubjectListCtl.OP_NEXT%>"></td>
                        
                    <%}else{ %>    <td align="right"><input type="submit" name="operation"
                        value="<%=SubjectListCtl.OP_NEXT%>"></td>
             
             <%} %>
             
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
                 <% } if(list1.size()==0){%>
        <table>
        <tr>
        <td><input type="submit" name="operation" value="<%=SubjectListCtl.OP_BACK%>"></td></tr>
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

    
   
    
  
    
    <%@include file="Footer.jsp"%>
            

</body>
</html>