<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj4.Clt.LoginCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<title>Logine</title>
<!--  <style>
.card{
 border-radius:25px;
box-shadow:0 4px 8px 0 rgb(0,0,0,0.8);
transition:0.2s;
text-decoration:none;
padding:15px;
}
.put{
  width: 100%;
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
 -->  
</head>
<body>
    <form action="<%=ORSView.LOGIN_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
            scope="request"></jsp:useBean>
             <br>
    <bt>
    <br>
    <br>
    <br>
    

        <center>                &nbsp; &nbsp; &nbsp;  <h1>Login</h1>
                                
                                <%String s=(String)request.getAttribute("message");
                                
                                if(s!=null){%>
                                <h2 style="color: red"><%=request.getAttribute("message")%></h2>	
                                	
                                	
                            <%} %>    
               <H2>
                      
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
                <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
            </H2>
              
              <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                <tr>
                    <th align="left">LoginId<span style="color: red" >*</span></th>
                    <td><input type="text" class="put" name="login" size=30 placeholder="Enter your login id"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
                      <td style="position: fixed">  <font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Password<span style="color: red">*</span></th>
                    <td><input type="password"  class="put" name="password" size=30  placeholder="Enter your login password"  
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
                      <td style="position: fixed">  <font
                        color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="5"><input class="button" type="submit" name="operation"
                        value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; <input type="submit"
                       class="button" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>"> &nbsp;
 
                     <br>  <a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget my password</b></a>&nbsp;</td>
                </tr>
                
                
                <tr><th></th>
                <td></td>
            </tr>
            </table>
    </form>
    </center>
    <br>
    <bt>
    <br>
    <br>
    <br>
     <br>
    <bt>
     <br>
    <bt>
    <br>
    <br>
   
    <br>
    <br>
   
    
    <%@ include file="Footer.jsp"%>
</body>
</html>
