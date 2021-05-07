<%@page import="in.co.sunrays.proj4.Clt.UserCtl"%>
<%@page import="in.co.sunrays.proj4.Clt.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp"%>
 <form action="<%=ORSView.USER_CTL%>" method="post">
        
      
        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
            scope="request"></jsp:useBean>
                         
        <%
        List l = (List) request.getAttribute("roleList");
        System.out.println("list value  "+l.size());
        %>
        

        <center>
        
      <% if(bean!=null && bean.getId()>0){
%>
<h1>Update User</h1>
<%}else{%>
        
        
            <h1>Add User</h1>
<%} %>
           

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
                <font
                        color="red"> <%=ServletUtility.getErrorMessage(
                    request)%></font> 
            </H2>



            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


            <table>
                <tr>
                    <th align="left">First Name<span style="color: red">*</span></th>
                    <td><input type="text" size="27px" name="firstName"  placeholder="Enter first name"
                         value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                        </td><td style="position: fixed"><font
                        color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                  </tr>
                <tr>
                    <th align="left">Last Name<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="lastName" placeholder="Enter last name"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>">
                        </td><td style="position: fixed"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">LoginId<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="login" placeholder="Enter login id"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"
                        <%=(bean.getId() > 0) ? "readonly" : ""%>>
                        </td><td style="position: fixed"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                
                <%if(bean.getId()>0 && bean!=null){
                	
                  %>
                  
                  <input type="hidden" name="password" placeholder="Enter password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>">
                    <input type="hidden" name="confirmPassword" placeholder="Enter confirm password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>">
                  
                  <% }else{%>
                <tr>
                    <th align="left">Password<span style="color: red">*</span></th>
                    <td><input type="password"  size="27px" name="password" placeholder="Enter password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>">
                        </td><td style="position: fixed"><font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr> 
                    <th align="left">Confirm Password<span style="color: red">*</span></th>
                    <td><input type="password"   size="27px" name="confirmPassword" placeholder="Enter confirm password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>">
                        </td><td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword",request)%>
                </font>
                    </td>
                </tr>
                <%} %>
                
                <tr>
                <th align="left">Mobile No<span style="color: red">*</span></th>
                <td><input type="text"  size="27px" name="mobileNo" placeholder="mobile number" value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
              </td><td style="position: fixed">  <font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
                </td>
                </tr>
                
                <tr>
            
                    <th align="left">Gender<span style="color: red">*</span></th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");

                     String htmlList = HTMLUtility.getList("gender", bean.getGender(),map);
                        %> <%=htmlList%>
                         <td style="position: fixed"> <font
                        color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
               
                    </td>
                   </tr>
                      
                       <tr>
                    <th align="left">Address<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="address"  placeholder="Enter address"
                        value="<%=DataUtility.getStringData(bean.getAddress())%>">
                        </td><td style="position: fixed"><font
                        color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
                  </tr>
              
                    <tr>  
                    <th align="left">Role<span style="color: red">*</span></th>
                    <td><%=HTMLUtility.getList("roleId",String.valueOf(bean.getRoleId()),l)%></td>
                    <td style="position: fixed"> <font
                        color="red"> <%=ServletUtility.getErrorMessage("roleId", request)%></font></td>
               
                </tr> 
                <tr>
                    <th align="left">Date Of Birth<span style="color: red">*</th>
                    <td><input type="text"  size="27px" name="dob" readonly="readonly" placeholder="DOB"
                        value="<%=DataUtility.getDateString(bean.getDob())%>" id="date">
                   
                      
                    <!--  <a href="javascript:getCalendar(document.forms[0].dob);">
                            <img src="../img/cal.jpg" width="16" height="15" border="0" alt="Calender">
                    </a>
                     --></td><td style="position: fixed">
                    <font
                        color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    
                     <%
if(bean.getId()>0 && bean!=null){
%> 
  <td> <input type="submit" name ="operation" value="<%=UserCtl.OP_UPDATE%>">
 &nbsp;<input type="submit" name ="operation" value="<%=UserCtl.OP_CANCEL%>"></td>
 

<%}else{ %>
                    
                    
                    
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=UserCtl.OP_SAVE%>" style="width: 60px">&emsp; <input type="submit"
                        name="operation" value="<%=UserCtl.OP_RESET%>"></td>
                        
                        <%} %>
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
<br>
   
<%@ include file="Footer.jsp"%>
</body>
</html>