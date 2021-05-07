<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.Clt.GetMarksheetCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Get Marksheet</title>
<!-- <style>
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
 --></head>
<body>

<%@ include file="Header.jsp"%>
<form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">
<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean" scope="request"></jsp:useBean>
  <input type="hidden" name="id" value="<%=bean.getId()%>">
  <div align="center">
		
		<div style="height: 15px; margin-bottom: 12px">
			
		</div>
  <input type="hidden" name="id" value="<%=bean.getId()%>">
  <h1 align="center" >Get Marksheet</h1>
  
  <H3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
			</H3>
			<table>
				<tr>
					<th align="left">Roll No :-</th>
					<td><input type="text" name="rollNo" class="put" placeholder="Enter Roll No." maxlength="10"
						value="<%=ServletUtility.getParameter("rollNo", request)%>">&nbsp;</td>
						<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("rollno", request)%></font>
						</td>
						
						</tr>
						<tr>
						<th></th>
					<td align="center"><input type="submit"  name="operation" class="button" style="width: 50px" value="<%=GetMarksheetCtl.OP_GO%>">
					
					<input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_RESET %>" >
					</td>
					
				</tr>
			</table>
			
			<%if((bean.getRollNo()!=null) && bean.getRollNo().trim().length()>0){
				System.out.println(bean.getRollNo());
				%>
			<br>
			<% 
				int physics = DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
				int chemistry = DataUtility.getInt(DataUtility.getStringData(bean.getChemestry()));
				int maths = DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
                int total = physics + chemistry + maths;
				float percentage = (float) total /3;
				percentage = Float.parseFloat(new DecimalFormat("##.##").format(percentage));
				if (bean.getRollNo() != null && bean.getRollNo().trim().length() > 0) {
			%>
			 <div align="center">
    <table border="1" style="border: groove; width: 35%">
				<tr>
					<td align="center"
					><h2>Rays
							Technologies, Indore</h2></td>
				</tr>

			</table>

			<table border="1" style="border: groove; width: 35%">
				<tr>
					<td align="center" style="width: 15%">Name</td>
					<th align="center" style="width: 35%; text-transform: capitalize;"><%=DataUtility.getStringData(bean.getName())%></th>

					<td align="center" style="width: 15%">Roll No</td>
					<th align="center" style="width: 25%; text-transform: uppercase;"><%=DataUtility.getStringData(bean.getRollNo())%></th>

				</tr>
				<tr>
					<td align="center" style="width: 15%">Status</td>
					<th align="center" style="width: 35%">Regular</th>

					<td align="center" style="width: 15%">Course</td>
					<th align="center" style="width: 25%">BE</th>

				</tr>
			</table>

			<table border="1" style="border: groove; width: 35%">
				<tr style="background-color: #e6e6e485;">
					<th align="center" style="width: 25%">Subject</th>
					<th align="center" style="width: 25%">Marks obtained</th>
					<th align="center" style="width: 25%">Maximum marks</th>
					<th align="center" style="width: 25%">Minimum marks</th>
					<th align="center" style="width: 25%">Grade</th>
				</tr>
				<tr>
					<td align="center">Physics</td>
					<td align="center"><%=physics%> <%
 	if (physics < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
					<td align="center">100</td>
					<td align="center">33</td>
					<td align="center">
						<%
							if (physics > 90 && physics <= 100) {
						%>A+ <%
							} else if (physics > 80 && physics <= 90) {
						%>A <%
							} else if (physics > 70 && physics <= 80) {
						%>B+ <%
							} else if (physics > 70 && physics <= 80) {
						%>B <%
							} else if (physics > 60 && physics <= 70) {
						%>C+ <%
							} else if (physics > 50 && physics <= 60) {
						%>C <%
							} else if (physics >= 33 && physics <= 50) {
						%>D <%
							} else if (physics >= 0 && physics < 33) {
						%><span style="color: red;">F</span> <%
 	}
 %>
					</td>

				</tr>
				<tr>
					<td align="center">Chemistry</td>
					<td align="center"><%=chemistry%> <%
 	if (chemistry < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
					<td align="center">100</td>
					<td align="center">33</td>
					<td align="center">
						<%
							if (chemistry > 90 && chemistry <= 100) {
						%>A+ <%
							} else if (chemistry > 80 && chemistry <= 90) {
						%>A <%
							} else if (chemistry > 70 && chemistry <= 80) {
						%>B+ <%
							} else if (chemistry > 70 && chemistry <= 80) {
						%>B <%
							} else if (chemistry > 60 && chemistry <= 70) {
						%>C+ <%
							} else if (chemistry > 50 && chemistry <= 60) {
						%>C <%
							} else if (chemistry >= 33 && chemistry <= 50) {
						%>D <%
							} else if (chemistry >= 0 && chemistry < 33) {
						%><span style="color: red;">F</span> <%
 	}
 %>
					</td>

				</tr>
				<tr>
					<td align="center">Maths</td>
					<td align="center"><%=maths%> <%
 	if (maths < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
					<td align="center">100</td>
					<td align="center">33</td>
					<td align="center">
						<%
							if (maths > 90 && maths <= 100) {
						%>A+ <%
							} else if (maths > 80 && maths <= 90) {
						%>A <%
							} else if (maths > 70 && maths <= 80) {
						%>B+ <%
							} else if (maths > 70 && maths <= 80) {
						%>B <%
							} else if (maths > 60 && maths <= 70) {
						%>C+ <%
							} else if (maths > 50 && maths <= 60) {
						%>C <%
							} else if (maths >= 33 && maths <= 50) {
						%>D <%
							} else if (maths >= 0 && maths < 33) {
						%><span style="color: red;">F</span> <%
 	}
 %>
					</td>

				</tr>
			</table>

			<table border="1" style="border: groove; width: 35%">
				<tr style="background-color: #e6e6e485;">
					<th align="center" style="width: 25%">Total Marks</th>
					<th align="center" style="width: 25%">Percentage (%)</th>
					<th align="center" style="width: 25%">Division</th>
					<th align="center" style="width: 25%">Result</th>

				</tr>
				<tr>
					<th align="center"><%=total%> <%
 	if (total < 99 || physics < 33 || chemistry < 33 || maths < 33) {
 %><span style="color: red;">*</span> <%
 	}
 %></th>
					<th align="center"><%=percentage%> %</th>
					<th align="center">
						<%
							if (percentage >= 60 && percentage <= 100) {
						%>1<sup>st</sup> <%
 	} else if (percentage >= 40 && percentage < 60) {
 %>2<sup>nd</sup> <%
 	} else if (percentage >= 0 && percentage < 40) {
 %>3<sup>rd</sup> <%
 	}
 %>
					</th>

					<th align="center">
						<%
							if (physics >= 33 && chemistry >= 33 && maths >= 33) {
						%><span style="color: forestgreen;">Pass</span> <%
 	} else {
 %><span style="color: red;">Fail</span> <%
 	}
 %>
					</th>
				</tr>

			</table>
			<%
				}
			%>
			
			<%} %>
   </div>
</form>
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
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
 
  
  
   
   
  
<%@ include file="Footer.jsp" %>


</body>
</html>
