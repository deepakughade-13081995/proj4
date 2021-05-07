<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
<%@page import="in.co.sunrays.proj4.Clt.UserRegistrationCtl"%> 
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>User Registration</title>
<!--  <style>
.card{
 border-radius:25px;
box-shadow:0 4px 8px 0 rgb(0,0,0,0.2);
transition:0.2s;
text-decoration:none;
padding:15px;
}
.put{
  width: 150%;
  padding: 12px 10px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 2px solid blue;
  border-radius: 15px;
}
.button {
  background-color: white; /* Green */
  border: none;
  color: blue;
  padding: 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  box-shadow:0 4px 8px 0 rgb(0,0,0,0.8);
transition:0.2s;
border-radius: 25px;
padding:15px;
width:160px;
text-decoration:none;
}
.button:hover {background-color:yellow}

.button:active {
  background-color:yellow;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}

</style>
 -->     </head>
<body>
 <%@ include file="Header.jsp"%>

    <form action="<%=ORSView.USER_REGISTRATION_CTL%>"  method="post" >

          
<!-- <br>
<br>
<br>
<br>
<br>
<br>
<hr>
  -->       
        
        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"  scope="request"></jsp:useBean>


<%-- <%

System.out.println("bean id"+bean.getId());
System.out.println("bean name"+bean.getFirstName());

%>
 --%>
       <center>
            <h1>User Registration</h1>

             <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
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
                    <th align="left">First Name <span style="color: red">*</span></th>
                    <td><input type="text" size="27px" name="firstName" class="put" placeholder="First Name"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                        </td>
                        <td style="position: fixed"><font
                        color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Last Name <span style="color: red">*</th>
                    <td><input type="text" size="27px" placeholder="LastName" class="put" name="lastName"  value="<%=DataUtility.getStringData(bean.getLastName())%>">
                    </td>
                    <td style="position: fixed"><font
                        color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">LoginId  <span style="color: red">*</th>
                    <td><input type="text" size="27px" placeholder="loginid" class="put" name="login"
                        
                        value="<%=DataUtility.getStringData(bean.getLogin())%>">
                        </td>
                        <td style="position: fixed"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Password  <span style="color: red">*</th> 
                    <td><input type="password" size="27px" name="password"  class="put" placeholder="Password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>">
                        </td><td style="position: fixed">
                        <font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Confirm Password  <span style="color: red">*</th>
                    <td><input type="password" size="27px" name="confirmPassword"  class="put" placeholder="Confirm Password"
                        value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"> </td>
                                     <td style="position: fixed">
                        <font
                        color="red">  <%=ServletUtility
                    .getErrorMessage("confirmPassword", request)%></font></td>
                </tr>
                <tr >
                    <th align="left">Gender</th>
                    <td>
                          <%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%>
                         <td style="position: fixed"> <font
                        color="red" > <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
                   
                                                  </td>
                </tr>

                 <tr>
                 <th align="left">mobileNo  <span style="color: red">*</th>
                 <td><input type="text " size="27px" name="mobile" class="put" placeholder="mobileNo" 
                 value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
                 </td>
                 <td  style="position: fixed;">
                 <font color="red">
                 <%=ServletUtility.getErrorMessage("mobile",request)%></font></td>
                 </tr>
                
                  <tr>
                    <th align="left">Address<span style="color: red">*</span></th>
                    <td><input type="text" size="27px" name="address" class="put" placeholder="address"
                        value="<%=DataUtility.getStringData(bean.getAddress())%>">
                        </td>
                        <td style="position: fixed"><font
                        color="red"><%=ServletUtility.getErrorMessage("address", request)%></font></td>
                </tr>
              
                 
                <tr>
                    <th align="left">Date Of Birth  <span style="color: red">*</th>
                    <td><input type="text" size="27px" name="dob" readonly="readonly" class="put" placeholder="Date of birth"
                        value="<%=DataUtility.getDateString(bean.getDob())%>" id="date"> 
                     </td><td style="position: fixed">  
                    </a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                 </tr>
                 <br>
                 <tr>
                 <th></th>
                 <td>
                 <input type="submit" name="operation" class="button" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
                 <input type="submit" name="operation" class="button" value="<%=UserRegistrationCtl.OP_RESET%>" style="width: 60px">                       </td>
                 </tr>
            </table>
    </center><br>
    </form>
    
<br>
<br>
<br>
<br>
<br>
<br>

    
   <%@ include file="Footer.jsp"%>
</body>
</html>
