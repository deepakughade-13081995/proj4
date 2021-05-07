<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj4.Clt.CollegeCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="CollegeCtl" method="POST">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CollegeBean"
            scope="request"></jsp:useBean>

        <center>
      <%   if(bean!=null && bean.getId()>0){
%>
<h1>Update College</h1>
<%}else{%>
        
        
            <h1>Add College</h1>
<%} %>
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>"> <input
                type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy"
                value="<%=bean.getModifiedBy()%>"> <input type="hidden"
                name="createdDatetime"
                value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime"
                value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                <tr>
                    <th align="left">Name<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="name" placeholder="Enter college name"
                        value="<%=DataUtility.getStringData(bean.getName())%>"></td><td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Address<span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="address" placeholder="Enter address"
                        value="<%=DataUtility.getStringData(bean.getAddress())%>"></td><td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">State<span style="color: red">*</span></th>
                    <td><%-- <input type="text" name="state" value="<%=DataUtility.getStringData(bean.getState())%>"> --%>
                    
                     <%
                            HashMap map = new HashMap();
                            map.put("madhya pradesh", "	madhaya predesh");
                            map.put("maharastra", "maharastra");
                            map.put("jammu kashmir", "Jammu kashmir");
                            map.put("gujrat", "gujrat");
                            map.put("rajisthan", "rajisthan");
                            map.put("tamilnadu", "tamilnadu");
                            map.put("keral", "Keral");
                            map.put("jharkhand", "jharkhand");
                            map.put("west bangal", "west bangal");
                            map.put("delhi", "delhi");
                            map.put("goa", "goa");
                            map.put("andhara predesh", "andhra predesh");
                            map.put("karnatka", "karnataka");
                            map.put("utrakhand", "utrakhand");
                            map.put("panjab", "panjab");
                            map.put("himachal predesh", "himachal predesh");
                            
                             String htmlList = HTMLUtility.getList("state", bean.getState(), map);
                        %> <%=htmlList%>
                        
                       
                   </td ><td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">City<span style="color: red">*</span></th>
                    <td><%-- <input type="text" name="city"  value="<%=DataUtility.getStringData(bean.getCity())%>"> --%>
                    
                    <%HashMap map1 = new HashMap();
                    map1.put("BHOPAL","BHOPAL");
                    map1.put("INDORE", "INDORE");
                    map1.put("DELHI", "DELHI");
                    map1.put("CHENNAI", "CHENNAI");
                    map1.put("MUMBAI", "MUMBAI");
                    map1.put("NAGPUR", "NAGPUR");
                    map1.put("KOLKATA", "KOLKATA");
                    map1.put("MOHALI", "MOHALI");
                    map1.put("PUNE", "PUNE");
                    map1.put("HYDRABAD", "HYDRABAD");
                    map1.put("BETUL", "BETUL");
                    map1.put("AMRAVATI", "AMRAVATI");
                    map1.put("VISAKHAPATANUM", "VISAKHAPATANUM");
                    map1.put("GURGAO", "GURGAO");
                    map1.put("SHREENAGAR", "SHREENAGAR");
                    map1.put("PRAYAGRAJ", "PRAYAGRAJ");
                    map1.put("GADHINAGAR", "GANDHINAGAR");
                    map1.put("RACHI", "RACHI");
                    map1.put("ASAM", "ASAM");
                   
                     String htmlList1 = HTMLUtility.getList("city", bean.getCity(), map1);
                %> <%=htmlList1%>
                    
                   </td><td style="position: fixed;"> <font
                        color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">PhoneNo <span style="color: red">*</span></th>
                    <td><input type="text"  size="27px" name="phoneNo" placeholder="Enter phone num"
                        value="<%=DataUtility.getStringData(bean.getPhone())%>">
                        </td><td style="position: fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
                </tr>


                <tr>
                    <th></th>
                     <%
if(bean.getId()>0 && bean!=null){
%> 
  <td> <input type="submit" name ="operation" value="Update">
 &nbsp;<input type="submit" name ="operation" value="<%=CollegeCtl.OP_CANCEL%>"></td>
 

<%}else{ %>
                    
                    
                    
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=CollegeCtl.OP_SAVE%>"> 
                        <input type="submit" name="operation"
                        value="<%=CollegeCtl.OP_RESET%>"></td>
                        
                        <%} %>
                </tr>
            </table>
    </form>
    </center>
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
<br>

<br>
<br>


  
    <%@ include file="Footer.jsp"%>


</body>
</html>