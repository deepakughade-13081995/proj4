<%@page import="in.co.sunrays.proj4.bean.TimetableBean"%>
<%@page import="in.co.sunrays.proj4.Clt.TimetableListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
<script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script> function DisableSunday(date){
	  var day=date.getDay();
	  
	  if(day==0){
		  return [false];
		  
	  }else{
		  return [true];
	  }
	  
}
var d=new Date(90,0,1);
$( function() {
  $( "#datee" ).datepicker({
  beforeShowDay:DisableSunday,
    changeMonth: true,
    changeYear: true,
    yearRange: '1980:2025',
    dateFormat:'dd-mm-yy',
    // minDate:0 
  });
});


</script>
</head>
<body>

<%@include file="Header.jsp"%>
<%UserBean userBean1 = (UserBean) session.getAttribute("user");%>

     <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.TimetableBean"
       
        scope="request"></jsp:useBean>
        
         <jsp:useBean id="model" class="in.co.sunrays.proj4.model.TimetableModel"
       
        scope="request"></jsp:useBean>
        

   <%
            List l1 = (List) request.getAttribute("courceName");
   //     System.out.println("list value  "+l.size()+" "+bean.getCource_Id());
        
        %>
       <%
       List l2 = (List) request.getAttribute("subjectName");
    //   System.out.println("list value  "+l1.size()+" "+bean.getSubject_Name());
      
       
       %>
      


    <center>
        <h1>Timetable List</h1>

<h2>
                    <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
                </h2>
                
                
                <h2><font color="green"><%=ServletUtility.getSuccessMessage(request) %></font></h2>
                
        <form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post">
        <%List list1=ServletUtility.getList(request);%>
        <%if(list1.size()!=0){ %>
            <table width="100%">
                <tr>
                    <td align="center"><label>Cource Name :</label> <%-- <input type="text"
                        name="courceId"
                        value="<%=ServletUtility.getParameter("courceId", request)%>">
                       --%>  
                      <%=HTMLUtility.getList("courceId", String.valueOf(bean.getCource_Id()),l1)%>                       
                   <label>Subject Name :</label> <%-- <input type="text"
                        name="subjectId"
                        value="<%=ServletUtility.getParameter("subjectId", request)%>">
                         --%>
                    <%=HTMLUtility.getList("subjectId",String.valueOf(bean.getSubject_Id()),l2)%>
                            
                         
                   <label>Exam Date :</label> <input type="text"
                        name="examdate" readonly="readonly"
                        value="<%=ServletUtility.getParameter("examdate", request)%>"  id="datee">
                        &nbsp; <input type="submit" name="operation" value="<%=TimetableListCtl.OP_SEARCH%>">
                        &nbsp; <input type="submit" name="operation" value="<%=TimetableListCtl.OP_RESET%>">
                   
                    </td>
            </tr>
            
            
            </table>
            
             <table border="1" width="100%">
                <tr>
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No</th>
                    <th>Cource Name</th>
                    <th>subject Name</th>
                    <th>Semester</th>
                    <th>Exam Time</th>
                    <th>Exam Date</th>
                     <%if(userBean1.getRoleId()==3||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                   <th>Edit</th>
                   <%} %>
          
                </tr>
                

                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<TimetableBean> it = list.iterator();
                    while (it.hasNext()) {
                        TimetableBean bean1= it.next();
                        
                %>
                <tr>
                     <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean1.getId()%>" ></td>
                    <td align="center"><%=index++ %></td>
                    <td align="center"><%=bean1.getCource_Name()%></td>
                    <td align="center"><%=bean1.getSubject_Name()%></td>
                     <td align="center"><%=bean1.getSemester()%></td>
                      <td align="center"><%=bean1.getExam_time()%></td>
                        <td align="center"><%=bean1.getExam_Date()%></td>
                   <%if(userBean1.getRoleId()==3 ||userBean1.getRoleId()==2){ %>
                    
                    <%}else{ %>
                     <td align="center"><a href="TimetableCtl?id=<%=bean1.getId()%>">Edit</a></td>
                     <%} %>
           
         
                </tr>
                <% 
                    }
                %>
            </table>
            
            <table width="100%">
                <tr>
                <%if(pageNo==1){ %>    <td><input type="submit" name="operation"
                       disabled="disabled" value="<%=TimetableListCtl.OP_PREVIOUS%>"></td>
                    </td>
                  
                   
                   <%} else { %> <td><input type="submit" name="operation"
                        value="<%=TimetableListCtl.OP_PREVIOUS%>"></td>
                    </td><%} %>
                    
                    
                    <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4 ||userBean1.getRoleId()==3){ %>
                    <td><input type="submit" name="operation" disabled="disabled"
                        value="<%=TimetableListCtl.OP_NEW%>"></td>
                        <%}else{ %>
                         <td><input type="submit" name="operation"
                        value="<%=TimetableListCtl.OP_NEW%>"></td>
                
                     <%} %>   
                        
                        
                        <%if(userBean1.getRoleId()==2||userBean1.getRoleId()==4||userBean1.getRoleId()==3){ %> 
                        
                    <td><input type="submit"
                        name="operation" disabled="disabled" value="<%=TimetableListCtl.OP_DELETE%>"></td>
                        <%}else{ %>
                        <td><input type="submit"
                        name="operation" value="<%=TimetableListCtl.OP_DELETE%>"></td>
                   <%} %>
                        
                   
                <%if((model.nextPK()-1)==bean.getId() || list.size()<pageSize){ %>    
                <td align="right"><input type="submit" name="operation"
                       disabled="disabled" value="<%=TimetableListCtl.OP_NEXT%>"></td>
                        
                        <%} else{ %>
                        
                        
                        <td align="right"><input type="submit" name="operation"
                        value="<%=TimetableListCtl.OP_NEXT%>"></td>
            
                        <%} %>
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
               <% }   if(list1.size()==0){%>
        <table>
        <tr>
        <td><input type="submit" name="operation" value="<%=TimetableListCtl.OP_BACK%>"></td></tr>
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