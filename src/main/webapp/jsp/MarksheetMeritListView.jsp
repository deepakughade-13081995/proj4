<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="in.co.sunrays.proj4.Clt.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.text.DecimalFormat"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include file="Header.jsp"%>

<%List list1=ServletUtility.getList(request); %>

 <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>

    <center>
        <h1>Marksheet Merit List</h1>

        <form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
            <br>
            
            <table border="1" width="100%" >
           <!--  cellpadding="10px" cellspacing="1.5" -->
                <tr>

                    <th>S.No</th>
                    <th>Roll No</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
                    <th>Total</th>
                    <th>Percentage</th>
                   <!--  <th>Grade</th>
                    <th>Result</th>
 -->
                </tr>
               
                   
               
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetBean bean = it.next();
                       String grade;
                        int phyMarks=bean.getPhysics();
                        int chemMarks=bean.getChemestry();
                        int mathMarks=bean.getMaths();
                        long total=phyMarks+chemMarks+mathMarks;
                       // double percentage=(((phyMarks+chemMarks+mathMarks)*100)/300);
                       	float percentage = (float) total / 3;
                        percentage= Float.parseFloat(new DecimalFormat("##.##").format(percentage));
                        
                        if(percentage>35 && percentage<50)
                        {
                    	    if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                    	    {
                    	    	grade="Fail";
                    	   }
                    	    else
                    	    {
                    	    	grade="C";
                    	    }
                        }
                        else if(percentage>50 && percentage<60)
                        { 
                        	if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        	{
                        		grade= "Fail";	
                        	}
                        	else {
                        		grade= "B";
                        	}
                        	
                        }
                        else if(percentage>60 && percentage<80)
                        {
                        	if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        	{
                        		grade= "Fail";	
                        	}
                        	else {
                        		grade= "A";
                        	}
                        		
                        }
                        else if(percentage>80 && percentage<90)
                        {
                        	if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        	{
                        		grade= "Fail";	
                        	}
                        	else {
                        		grade= "A+";
                        	}
                        		
                        }
                        else if(percentage>90 && percentage<=100)
                        {
                        	if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        	{
                        		grade= "Fail";	
                        	}
                        	else {
                        		grade= "A++";
                        	}
                        		
                        }
                        else if(percentage<35 || phyMarks<35 || chemMarks<35 || mathMarks<35)
                        {
                        	grade= "Fail";	
                        }

                        
                %>
                <tr>
                       
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getRollNo()%></td>
                    <td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getPhysics()%></td>
                    <td align="center"><%=bean.getChemestry()%></td>
                    <td align="center"><%=bean.getMaths()%></td>
                    <td align="center"><%=total %></td>
                     <td align="center"><%=percentage+"%"%></td>
                     <%--   <td><%=grade%></td> --%>
                        <%--  <%if(grade.equalsIgnoreCase("fail")) {%>
                    <td style="color:red;">FAIL</td>
                    <%} else { %>
                      <td style="color:green;">PASS</td>
                    <%} %>
            --%>
                  </tr>
 <%System.out.println(bean.getName()); %>
                <%
                    }
                %>
               
            </table>
            <table>
                <tr>
                    <td align="right"><input type="submit" name="operation"
                        value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"><input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
      <br>
    
    <br>
    <br>
    <br>
     <br>
    <bt>
     <br>
    <bt>
    <br>
    
    
  
    <%@include file="Footer.jsp"%>


</body>
</html>