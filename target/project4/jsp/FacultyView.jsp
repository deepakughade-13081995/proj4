<%@page import="in.co.sunrays.proj4.Clt.FacultyCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    $( "#date1" ).datepicker({
   /*  beforeShowDay:DisableSunday, */
      changeMonth: true,
      changeYear: true,
      yearRange: '1980:2019',
      dateFormat:'dd-mm-yy',
      /*  minDate:0 */
    });
  } );
  </script>

<title>Insert title here</title>
</head>
<body>

<%@include file ="Header.jsp" %>

<form action="<%=ORSView.FACULTY_CTL %>" method="post">
<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.FacultyBean" scope="request"></jsp:useBean>

  <%
        List l = (List) request.getAttribute("courceName");
        System.out.println("list value  "+l.size()+" "+bean.getCource_Id());
        
        %>
       <%
       List l1 = (List) request.getAttribute("subjectName");
       System.out.println("list value  "+l1.size()+" "+bean.getSubject_Name());
      
       
       %>
       
        <%
       List l2 = (List) request.getAttribute("collegeName");
       System.out.println("list value  "+l1.size()+" "+bean.getCollege_Name());
      
       
       %>
      
      

<%-- <%
List l = (List)request.getAttribute("FacultyList");
System.out.println("value of  "+l);
%>
 --%>
<center>
<%
System.out.println("id ye h "+bean.getId());

if(bean!=null && bean.getId()>0){
%>
<h1>Update Faculty</h1>
<%}else{%>


  <h1>Add Faculty</h1>
<%} %>

<h2>
<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
</h2>

<H2>
    <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
  </H2>

<input type="hidden" name = "id" value="<%=bean.getId()%>">
<input type="hidden" name = "createdBy" value="<%=bean.getCreatedBy()%>">
<input type="hidden" name = "modifiedBy" value="<%=bean.getModifiedBy()%>">
<input type="hidden" name = "createDateTime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
<input type="hidden" name = "modifiedDateTime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

<table>
                <tr>
                    <th align="left">First Name<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="fname" placeholder="Enter first name" value="<%=DataUtility.getStringData(bean.getFirst_Name())%>"></td>
                  <td style="position: fixed">  <font color="red" ><%=ServletUtility.getErrorMessage("fname",request)%></font></td>
                </tr>
                <tr>
                
                    <th align="left">Last Name<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="lname" placeholder="Enter last name"
                        value="<%=DataUtility.getStringData(bean.getLast_Name())%>">
                        </td>
                       <td style="position: fixed"> <font
                        color="red"> <%=ServletUtility.getErrorMessage("lname", request)%></font></td>
                </tr>
                
                <tr>
            
                    <th align="left">Gender<span style="color: red">*</span></th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGrnder(),
                                    map);
                        %> <%=htmlList%>
                       <td style="position: fixed"> <font
                        color="red" > <%=ServletUtility.getErrorMessage("gender1", request)%></font></td>
                    </td>
                   </tr>
                      
                 <tr>
                    <th align="left">Address<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="address" placeholder="Enter address" value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
                  <td style="position: fixed">  <font color="red" ><%=ServletUtility.getErrorMessage("address",request)%></font></td>
                </tr>
              
                
                <tr>
                    <th align="left">Login Id<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="loginid" placeholder="Enter login Id"
                        value="<%=DataUtility.getStringData(bean.getLogin_Id())%>"></td>
                        
                        <td style="position: fixed">
                        <font
                        color="red" > <%=ServletUtility.getErrorMessage("loginid", request)%></font></td>
                </tr>
               
                <tr>
                    <th align="left">Date Of Joining<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="doj" readonly="readonly" placeholder="date of joining"
                        value="<%=DataUtility.getDateString(bean.getDate_Of_joining())%>" id="date1"></td>
                   
                  <td style="position: fixed">  <font
                        color="red" > <%=ServletUtility.getErrorMessage("doj", request)%></font></td>
                </tr>
                   
                <tr>
                    <th align="left">Qualification<span style="color: red">*</span></th>
                    <td>
                    
                    <%-- <input type="text" name="qualification"
                        value="<%=DataUtility.getStringData(bean.getQualification())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("qualification", request)%></font> --%>
                         <%
                            HashMap map1 = new HashMap();
                            map1.put("BCA", "BCA");
                            map1.put("BTECH", "BTECH");
                            map1.put("BE", "BE");
                            map1.put("BSC", "BSC");
                            map1.put("MSC", "MSC");
                            map1.put("MTECH", "MTECH");
                            map1.put("MBA", "MBA");
                            map1.put("MCA", "MCA");
                            map1.put("POLYTECHNIQUE", "POLYTECHNIQUE");
                            map1.put("BA", "BA");
                            map1.put("MA", "MA");
                            map1.put("MBBS", "MBBS");
                            map1.put("PHD", "PHD");
                            String htmlList1 = HTMLUtility.getList("qualification", bean.getQualification(), map1);
                        %> <%=htmlList1%>
                       </td>
                       
                       <td style="position: fixed"> <font
                        color="red" > <%=ServletUtility.getErrorMessage("qualification1", request)%></font>
                        
                       
                        
                        
                        </td>
                </tr>
               
                <tr>
                    <th align="left">Mobile No<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="mobileno" placeholder="mobile number"
                        value="<%=DataUtility.getStringData(bean.getMobile_No())%>"></td>
                        
                        <td style="position: fixed"><font
                        color="red" > <%=ServletUtility.getErrorMessage("mobileno", request)%></font></td>
                </tr>
               
               <tr>
                    <th align="left">College Name<span style="color: red">*</span></th>
                    <td>
              <%--  <input type="text" name="collegename" value="<%=DataUtility.getStringData(bean.getCollege_Name())%>"> --%>
             
              <%=HTMLUtility.getList("collegeId",String.valueOf(bean.getCollege_Id()),l2)%></td>
                    
                   <td style="position: fixed"> <font
                        color="red" > <%=ServletUtility.getErrorMessage("collegeId1", request)%></font></td>
                </tr>
               
               <tr>
                    <th align="left">Cource Name<span style="color: red">*</span></th>
                    <td><%-- <input type="text" name="courcename" value="<%=DataUtility.getStringData(bean.getCource_Name())%>"> --%>
                    
                    <%=HTMLUtility.getList("courceId",String.valueOf(bean.getCource_Id()),l)%></td>
                  <td style="position: fixed">  <font
                        color="red" > <%=ServletUtility.getErrorMessage("courceId", request)%></font>
                        
                        </td>
                </tr>
               
               <tr>
                    <th align="left">Subject Name<span style="color: red">*</span></th>
             <td>
             
            <%--  <input type="text" name="subjectname" value="<%=DataUtility.getStringData(bean.getSubject_Name())%>"> --%>
             
             <%=HTMLUtility.getList("subjectId",String.valueOf(bean.getSubject_Id()),l1)%></td>
           <td style="position: fixed">  <font color="red" > <%=ServletUtility.getErrorMessage("subjectId1", request)%></font></td>
                
                </tr>
               
                
                <tr>
                    <th></th>
                    
                    <%
if(bean.getId()>0 && bean!=null){
%> 
  <td> <input type="submit" name ="operation" value="<%=FacultyCtl.OP_UPDATE%>">

 &nbsp;<input type="submit" name ="operation" value="<%=FacultyCtl.OP_CANCEL%>"></td>  

<%}else{ %>
                   
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=FacultyCtl.OP_SAVE%>">
                        <input type="submit"
                        name="operation" value="<%=FacultyCtl.OP_RESET%>">
                       <%--  <input type="submit"
                        name="operation" value="<%=RoleCtl.OP_DELETE%>"> --%></td>
                        
                        <%}%>
                </tr>
            </table>
</center>
</form>
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