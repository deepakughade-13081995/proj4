<%@page import="in.co.sunrays.proj4.model.RoleModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.proj4.Clt.UserListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

</head>
   <body>
<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

<%@include file="Header.jsp"%>


		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<jsp:useBean id="bean2" class="in.co.sunrays.proj4.bean.RoleBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="model" class="in.co.sunrays.proj4.model.RoleModel"
			scope="request"></jsp:useBean>


		<%
			List list1 = ServletUtility.getList(request);
		%>

		<center>
			<h1>User List</h1>

			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>

			</h2>
			<%
				if (list1.size() != 0) {
			%>


			<table width="100%">
				<tr>
					<td align="center"><label>FirstName :</label> <input
						type="text" name="firstName"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
						&emsp; <label>LoginId:</label> <input type="text" name="login"
						value="<%=ServletUtility.getParameter("login", request)%>">
						&emsp; <input type="submit" name="operation"
						value="<%=UserListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=UserListCtl.OP_RESET%>">
					</td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%">
				<tr>
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Role</th>
					<th>Address</th>
					<th>LoginId</th>
					<th>Gender</th>
					<th>DOB</th>
					<th>Edit</th>
				</tr>
	
				<%--  <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr>

 --%>
				<%
					int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;
						UserBean bean1 = null;
						List list = ServletUtility.getList(request);
						Iterator<UserBean> it = list.iterator();

						while (it.hasNext()) {
							bean1 = (UserBean) it.next();

							 bean2 = model.findByPK(bean1.getRoleId());
							//  System.out.println("role name------------------>"+bean2.getName());
							bean1.setRoleName(bean2.getName());
				%>
				<tr>
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean1.getId()%>" <%if (bean1.getRoleId() == 1) {%>
						<%="disabled"%> <%}%>></td>
					<td align="center"><%=bean1.getFirstName()%></td>
					<td align="center"><%=bean1.getLastName()%></td>
					<td align="center"><%=bean1.getRoleName()%></td>
					<td align="center"><%=bean1.getAddress()%></td>
					<td align="center"><%=bean1.getLogin()%></td>
					<td align="center"><%=bean1.getGender()%></td>
					<td align="center"><%=bean1.getDob()%></td>

					<td align="center"><a href="UserCtl?id=<%=bean1.getId()%>"
						<%if (userBean.getId() == bean1.getId() || bean1.getRoleId() == RoleBean.ADMIN) {%>
						onclick="return false;" <%}%>>Edit</a></td>

				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>

					<%
						} else {
					%><td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>

					<%
						}
					%>


					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_DELETE%>"></td>



					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEW%>"></td>


					<%
						if (list.size() < pageSize) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=UserListCtl.OP_NEXT%>"></td>

					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEXT%>"></td>

					<%
						}
					%>

				</tr>

			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
			<%
				}
				if (list1.size() == 0) {
			%>
			<table>
				<tr>
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
				}
			%>
		
	</form>
	</center>
	<br>

	<br>
	<br>
	<br>
	<br>




	<%@include file="Footer.jsp"%>

</body>
</html>