<%@page import="in.co.sunrays.proj4.Clt.MyProfileCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>My Profile</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

<%@include file="Header.jsp"%>

<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean" scope="request"></jsp:useBean>

<center>
<h1>My Profile</h1>

<h2>
<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>

</h2>
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
          <th align="left">LoginId<span style="color: red">*</th>
          <td><input type="text" size="27px" name="login" value="<%=DataUtility.getStringData(bean.getLogin())%>"  readonly="readonly">
          </td><td style="position: fixed;"> <font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
          </td>
            </tr>
            <tr>
            <th align="left">First Name<span style="color: red">*</th>
            <td><input type="text" size="27px" name="firstName" value="<%=DataUtility.getStringData(bean.getFirstName())%>">
           </td><td style="position: fixed;"> <font color="red"><%=ServletUtility.getErrorMessage("firstName",request)%></font></td>
             </tr>
             
              <tr>
            <th align="left">Last Name<span style="color: red">*</th>
            <td><input type="text" size="27px" name="lastName" value="<%=DataUtility.getStringData(bean.getLastName())%>">
            </td><td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("lastName",request)%></font></td>
             </tr>
             
             <tr>
             <th align="left">Gender<span style="color: red">*</th>
             <td size="30px">
             <%
             HashMap map=new HashMap();
             map.put("Male", "Male");
             map.put("Female","Feamle");
             String htmlList=HTMLUtility.getList("gender",bean.getGender(), map);
             
             %>
             <%=htmlList%>
            </td><td style="position: fixed"> <font color="red"><%=ServletUtility.getErrorMessage("gender",request)%></font>
             </td>
             
             </tr>
             
               <tr>
                    <th align="left">Mobile No<span style="color: red">*</th>
                    <td><input type="text" size="27x" name="mobileNo"
                        value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td><td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
                </tr>

                <tr>
                    <th align="left">Date Of Birth<span style="color: red">*</th>
                    <td><input type="text" size="27px" name="dob" readonly="readonly" 
                        value="<%=DataUtility.getDateString(bean.getDob())%>" id="date">
                    <a href="javascript:getCalendar(document.forms[0].dob);">
                            
                    </a></td><td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                
                <tr><th></th>
                <td><input type="submit"
                        name="operation" value="<%=MyProfileCtl.OP_SAVE%>">
                    
                    <input type="submit" name="operation" 
                        value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>" style="width: 150px">  </td>
               
            </td>
                
                
                
                </tr>
                  </table> 
            
                
           
             </form> 
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





    
   <%@ include file="Footer.jsp"%>
          
</center>
</body>
</html>