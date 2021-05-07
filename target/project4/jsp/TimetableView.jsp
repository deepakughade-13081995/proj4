<%@page import="in.co.sunrays.proj4.Clt.TimetableCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
      yearRange: '1980:2020',
      dateFormat:'dd-mm-yy',
       minDate:0 
    });
  });
  </script>

</head>
<body>

<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">
 <%@ include file="Header.jsp"%>

<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.TimetableBean"
       
        scope="request"></jsp:useBean>
        
<%
        List l = (List) request.getAttribute("courceName");
        System.out.println("list value  "+l.size()+" "+bean.getCource_Id());
        
        %>
       <%
       List l1 = (List) request.getAttribute("subjectName");
       System.out.println("list value  "+l1.size()+" "+bean.getSubject_Name());
      
       
       %>
        
        <center>
        <%
if(bean!=null && bean.getId()>0){
%>
<h1>Update Timetable</h1>
<%}else{%>
        
            <h1 >Add Timetable</h1>
            
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
                    <th align="left">Cource Name <span style="color: red">*</span></th>
                   <%--  <td><input type="text" name="cname"  value="<%=DataUtility.getStringData(bean.getCource_Name())%>">
                    <font color="red"><%=ServletUtility.getErrorMessage("cname",request)%></font></td>
                    
                    --%>
                    
                     <td ><%=HTMLUtility.getList("courceId",String.valueOf(bean.getCource_Id()),l)%></td>
                    <td style="position: fixed"> <font color="red" ><%=ServletUtility.getErrorMessage("courceId",request)%></font></td>
                     </tr>
                <tr>
                    <th align="left">Subject Name<span style="color: red">*</span></th>
                    <td>
                    
                    <%-- <input type="text" name="sname"
                        value="<%=DataUtility.getStringData(bean.getSubject_Name())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("sname", request)%></font>
                     --%>  
                     <%=HTMLUtility.getList("subjectId",String.valueOf(bean.getSubject_Id()),l1)%></td>
                       <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("subjectId",request)%></font></td>
                </tr>
                
                 <tr>
                    <th align="left" >Semester<span style="color: red">*</span></th>
                    <td>
                    
                    <%-- <input type="text" name="semester"
                        value="<%=DataUtility.getStringData(bean.getSemester())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("semester", request)%></font> --%>
                        <%
                            HashMap map = new HashMap();
                            map.put("1st", "first");
                            map.put("2nd", "second");
                            map.put("3rd", "third");
                            map.put("4th", "forth");
                            map.put("5th", "fifth");
                            map.put("6th", "sixth");
                            map.put("7th", "seventh");
                            map.put("8th", "eight");
                            map.put("9th", "nineth");
                            map.put("10", "tenth");
                             String htmlList = HTMLUtility.getList("semester", bean.getSemester(), map);
                        %> <%=htmlList%></td>
                        <td style="position: fixed"><font
                        color="red" > <%=ServletUtility.getErrorMessage("semester", request)%></font></td>
                        
                        
                        </td>
                </tr>
                
                <tr>
                    <th align="left">Exam Time<span style="color: red">*</span></th>
                    <td>
                    
                    <%-- <input type="text" name="examtime"
                        value="<%=DataUtility.getStringData(bean.getExam_time())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("examtime", request)%></font>
                         --%> 
                         
                          <%
                          
                            HashMap map1 = new HashMap();
                            map1.put("8:00 am to 11:00 am ", "8:00 am to 11:00 am ");
                            map1.put("9:00 am to 12:00 pm", "9:00 am to 12:00 pm");
                            map1.put("10:00 am to 1:00 pm", "10:00 am to 1:00 pm");
                            map1.put("11:00 am to 2:00 pm", "11:00 am to 2:00 pm");
                            map1.put("12:00 pm to 3:00 pm", "12:00 pm to 3:00 pm");
                            map1.put("12:00 pm to 3:00 pm", "12:00 pm to 3:00 pm");
                            map1.put("2:00 am to 5:00 pm", "2:00 am to 5:00 pm");
                             String htmlList1 = HTMLUtility.getList("examtime", bean.getExam_time(), map1);
                        %> <%=htmlList1%></td>
                       <td style="position: fixed"> <font
                        color="red" > <%=ServletUtility.getErrorMessage("examtime", request)%></font>
                       
                        
                        </td>
              
                </tr>
                
               
                
                <tr>
                    <th align="left">Exam Date<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="examdate" readonly="readonly" placeholder=" Ente exam Date"
                        value="<%=DataUtility.getDateString(bean.getExam_Date())%>" id="datee"></td>
                        <td style="position: fixed"><font 
                        color="red" > <%=ServletUtility.getErrorMessage("examdate", request)%></font></td>
                </tr>
               
                
                <tr>
                    <th></th>
                    
                    <%
if(bean.getId()>0 && bean!=null){
%> 
  <td> <input type="submit" name ="operation" value="<%=TimetableCtl.OP_UPDATE%>">
 &nbsp;<input type="submit" name ="operation" value="<%=TimetableCtl.OP_CANCEL%>"></td>
 

<%}else{ %>
                    
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=TimetableCtl.OP_SAVE%>">
                        <input type="submit"
                        name="operation" value="<%=TimetableCtl.OP_RESET%>">
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

 <%@include file="Footer.jsp"%>


</body>
</html>