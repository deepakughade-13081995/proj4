<%@page import="in.co.sunrays.proj4.Clt.LoginCtl"%>
<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj4.bean.RoleBean"%>
<%@page import="in.co.sunrays.proj4.Clt.LoginCtl"%>
<%@page import="in.co.sunrays.proj4.bean.UserBean"%>
<%@page import="in.co.sunrays.proj4.Clt.ORSView"%>
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	function DisableSunday(date){
		var day = date.getDay();

		if (day == 0) {
			return [ false ];

		} else {
			return [ true ];
		}

	}
	var d = new Date(90, 0, 1);
	$(function() {
		$("#date").datepicker({
			/*  beforeShowDay:DisableSunday, */
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2001',
			dateFormat : 'dd-mm-yy',
		/*minDate:0 */
		});
	});
</script>
<style>
.card {
	border-radius: 25px;
	box-shadow: 0 4px 8px 0 rgb(0, 0, 0, 0.2);
	transition: 0.2s;
	text-decoration: none;
	padding: 15px;
}
</style>
</head>
<body>
	<%
		UserBean userBean = (UserBean) session.getAttribute("user");

		boolean userLoggedIn = userBean != null;

		String welcomeMsg = "Hi, ";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userBean.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "Guest";

		}
	%>

<table width="100%" border="0">
		<tr>
<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>"><b>|Welcome|</b></a> <%
 	if (userLoggedIn) {

 		//request.setAttribute("message","loged out successfully");
 %> <a
				href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><b>Logout|</b></a>

				<%
					} else {
				%> <a href="<%=ORSView.LOGIN_CTL%>"><b>Login|</b></a> <%
 	}
 %></td>
			<td rowspan="2">
				<h1 align="Right">

					<img align="right" alt="" src="/project4/img/sun.jpg" width="230px"
						height="60px">
				</h1>
			</td>

		</tr>

		<tr>
			<td>
				<h3>
					<%=welcomeMsg%></h3>
			</td>
		</tr>


		<%
			if (userLoggedIn) {
		%>

		<tr>
			<td colspan="2"><br> <a
				href="<%=ORSView.GET_MARKSHEET_CTL%>">|Get Marksheet</b></a>&nbsp; &nbsp;<a
				href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">| Marksheet Merit List</b>
			</a>&nbsp; &nbsp;<a href="<%=ORSView.MY_PROFILE_CTL%>">| MyProfile</b></a>&nbsp; &nbsp; <a
				href="<%=ORSView.CHANGE_PASSWORD_CTL%>">| Change Password</b></a>
				<%
					if (userBean.getRoleId() == RoleBean.ADMIN) {
				%> &nbsp;&nbsp;<a href="<%=ORSView.MARKSHEET_CTL%>">| Add Marksheet</b></a>&nbsp; &nbsp;<a
				href="<%=ORSView.MARKSHEET_LIST_CTL%>"> |Marksheet List</b></a> &nbsp;&nbsp;<a
				href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank">| Java Doc|</b></a>&nbsp;&nbsp; <a
				href="<%=ORSView.USER_CTL%>">| Add User</b></a>&nbsp;&nbsp;<a
				href="<%=ORSView.USER_LIST_CTL%>">| User List</b></a>&nbsp;&nbsp;<a
				href="<%=ORSView.COLLEGE_CTL%>">| Add College</b></a>&nbsp;&nbsp;<a
				href="<%=ORSView.COLLEGE_LIST_CTL%>">| College List</b></a>&nbsp;&nbsp; <a
				href="<%=ORSView.STUDENT_CTL%>">| Add Student</b></a>&nbsp; &nbsp;<a
				href="<%=ORSView.STUDENT_LIST_CTL%>">| Student List</b></a>&nbsp;&nbsp;<a
				href="<%=ORSView.ROLE_CTL%>">| Add Role</b></a>&nbsp;&nbsp;<a
				href="<%=ORSView.ROLE_LIST_CTL%>">| Role List</b></a>&nbsp;&nbsp; <a
				href="<%=ORSView.COURCE_CTL%>">| Add course |</b></a>&nbsp;&nbsp;
			<a href="<%=ORSView.COURCE_LIST_CTL%>">| Course List </b></a>&nbsp;&nbsp; <a
				href="<%=ORSView.SUBJECT_CTL%>">| Add Subject</b></a> &nbsp;&nbsp;<a
				href="<%=ORSView.SUBJECT_LIST_CTL%>">| Subject List</b></a> &nbsp;&nbsp;<a
				href="<%=ORSView.FACULTY_CTL%>">| Add Faculty</b></a>&nbsp;&nbsp; <a
				href="<%=ORSView.FACULTY_LIST_CTL%>">| Faculty List</b></a>&nbsp;&nbsp; <a
				href="<%=ORSView.TIMETABLE_CTL%>">| Add timetable</b></a> &nbsp;&nbsp;<a
				href="<%=ORSView.TIMETABLE_LIST_CTL%>">| Timetable List|</b>&nbsp;&nbsp;</a> <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.FACULTY) {
 %> <a
				href="<%=ORSView.FACULTY_LIST_CTL%>">| Faculty List</b></a> 
				<a
href="<%=ORSView.TIMETABLE_LIST_CTL%>">| Timetable List|</b></a> <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>">| Subject List</b></a> <a
				href="<%=ORSView.STUDENT_LIST_CTL%>">| Student List</b></a> <a
				href="<%=ORSView.COURCE_LIST_CTL%>">| Cource List </b></a> <a
				href="<%=ORSView.COLLEGE_LIST_CTL%>">| College List</b></a> <a
				href="<%=ORSView.MARKSHEET_CTL%>">| Add Marksheet</b></a><a
				href="<%=ORSView.MARKSHEET_LIST_CTL%>"> |Marksheet List</b></a> <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.COLLEGE) {
 %>
				<a href="<%=ORSView.STUDENT_CTL%>">| Add Student</b></a> <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>">| Subject List</b></a>
				<a
				href="<%=ORSView.FACULTY_LIST_CTL%>">| Faculty List</b></a>
				<a
				href="<%=ORSView.STUDENT_LIST_CTL%>">| Student List</b></a>
				 <a
				href="<%=ORSView.GET_MARKSHEET_CTL%>">|Get Marksheet</b></a><a
				href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">| Marksheet Merit
					List</b> <a href="<%=ORSView.MARKSHEET_LIST_CTL%>"> |Marksheet
						List</b>
				</a>
			</a> <a href="<%=ORSView.TIMETABLE_LIST_CTL%>">| Timetable List|</b></a> <a
				href="<%=ORSView.COURCE_LIST_CTL%>">| Cource List </b></a> <a
				href="<%=ORSView.COLLEGE_LIST_CTL%>">| College List</b></a> <%
 	}
 %></td>

		</tr>
		<%
			}
		%>
	</table>
	<hr>

</body>
</html>
