<%@page import="in.co.sunrays.proj4.Clt.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Forget Password</title>
<!-- <style> 
input[type=text] {
  width: 35%;
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp"%>
  
    <form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

        

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
                            <h1>Forgot your password?</h1>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
 
            <table>
            <tr>
                 <lable >Submit your email address and we'll send you password.</lable><br><br>
                 
                 <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
                
            </H2>
 
                 
               <th align="left">Email Id<span style="color: red">*</span></th>
               <td> <input type="text" name="login" placeholder="Enter ID Here"
                    value="<%=ServletUtility.getParameter("login", request)%>"></td>
                    <td style="position: fixed"> <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                   
                    </tr>
                    <tr>
                    <th></th>
               <td align="center"><input type="submit"  class="button" name="operation" style="width: 60px" value="<%=ForgetPasswordCtl.OP_GO%>">
               
               <input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_RESET%>">
               </td>
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
<br>


    
    
    <%@ include file="Footer.jsp"%>


</body>
</html>