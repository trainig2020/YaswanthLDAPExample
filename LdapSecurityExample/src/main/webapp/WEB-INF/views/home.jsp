<%@page import="java.util.ArrayList"%>
<%@page import="com.yaswanth.dto.Department"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
h1 {
	color: black;
}

#header {
	background-color: #E0FFFF;
	width: 100%;
	height: 50px;
	text-align: center;
}

#sidebar-left {
	float: left;
	width: 15%;
	background-color: #F5F5F5;
}

#main {
	float: left;
	width: 85%;
	background-color: #D8BFD8;
}

#footer {
	clear: both;
	height: 50px;
	width: 100%;
	text-align: center;
	background-color: #E0FFFF;
}

#sidebar-left {
	min-height: 600px
}
#main {
	min-height: 600px
}
table.d, td {
	table-layout: fixed;
	width: 100%;
	height: 20px;
	padding: 8px;
}
table.t, td {
	table-layout: fixed;
	width: 100%;
	height: 20px;
	padding: 8px;
}
</style>
<title><spring:message code="label.getall"></spring:message></title>
</head>
<body>

	<form:errors path="department.*"></form:errors>
	<div id="header">
	</div>

	<div id="sidebar-left">
		<a href="showdepartments">[+]</a>departments
		<a href="home">[Dep]</a>
		<c:if test="${name eq 'names'}">
			<c:forEach var="dept" items="${DeptListemp}">
				<c:if test="${not empty dept}">
					<br>
				     <button type="button" style="width: 100px;">
						<a href="emplist?DeptID=${dept.deptID}"><font color="black">${dept.deptName} </font></a>
					</button>
					<br>
				</c:if>
			</c:forEach>
		</c:if>
	</div>
	<div id=main>
		<div align="center">
			<c:if test="${homepage ne 'emppage'}">
				<c:if test="${Register eq 'newform'}">
					<form action="CreateDepartment" method="post">
				</c:if>
				<c:if test="${Register ne 'newform'}">
					<form action="CreateDepartment" method="post">
				</c:if>

				<table class="d" border="1 px solid black" align="center">
					<thead>
						<tr>
							<th colspan="2" style="text-align: center"><h1>
									<spring:message code="label.details"></spring:message>
								</h1></th>
							<th colspan="2" style="text-align: center"><a
								href="NewDepartment"><h1>
										<spring:message code="label.insertnew"></spring:message>
									</h1></a></th>
						</tr>
						<tr>
							<th style="text-align: center"><spring:message
									code="label.DeptID"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.DeptName"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.Update"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.Delete"></spring:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${DeptList}" var="dept">

							<c:if test="${departmentid eq dept.deptID}">
								<tr>
									<td><input type="text" name="DeptID"
										value="${dept.deptID}" readonly="readonly" /></td>
									<td><input type="text" name="DeptName"
										value="${dept.deptName}" /></td>
									<td><input type="submit"
										value="<spring:message code="label.Update"></spring:message>" /></td>
								</tr>
							</c:if>
							<c:if test="${departmentid ne dept.deptID}">
								<tr>
									<td><c:out value="${dept.deptID}" /></td>
									<td><c:out value="${dept.deptName}" /></td>
									<td><a href="GetDepartment?DeptID=${dept.deptID}"><spring:message
												code="label.Update"></spring:message></a></td>
									<td><a href="DeleteDepartment?DeptID=${dept.deptID}"><h3>
												<spring:message code="label.Delete"></spring:message>
											</h3></a></td>
								</tr>
							</c:if>
						</c:forEach>
						<c:if test="${createdept eq 'newdept'}">
							<tr>
								<td><input type="text" name="DeptID" /></td>
								<td><input type="text" name="DeptName" /></td>
								<td colspan="2"><input type="submit"
									value="<spring:message code="label.S"></spring:message>" /></td>
							</tr>
						</c:if>
					</tbody>
				</table>
				</form>
			</c:if>
		</div>

		<c:if test="${homepage eq 'emppage'}">


			<c:if test="${Register eq 'NewForm'}">
				<form action="saveEmployee" method="post">
			</c:if>
			<c:if test="${Register ne 'NewForm'}">
				<form action="updateEmployee" method="post">
			</c:if>

			<table class="t" border="2" style="width: 100%"background-color:#eee;>
				<thead>
					<tr>
						<th colspan="3" style="text-align: center"><h1><spring:message code="label.details1"></spring:message></h1></th>
						<th colspan="2" style="text-align: center"><a
							href="newEmployee"><h2><spring:message code="label.insertnew1"></spring:message></h2></a></th>
					</tr>
					<tr>
						<th><spring:message code="label.EmpID"></spring:message></th>
						<th><spring:message code="label.DeptID"></spring:message></th>
						<th><spring:message code="label.EmpName"></spring:message></th>
						<th ><spring:message code="label.Update"></spring:message></th>
						<th ><spring:message code="label.Delete"></spring:message></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${EmpList}" var="emp">
						<c:if test="${employeeid eq emp.empID}">
							<c:if test="${Did eq emp.DID}">
								<tr>
									<td><input type="text" name="EmpID" value="${emp.empID}"
										readonly="readonly" /></td>
									<td><input type="text" name="DID" value="${emp.DID}"
										readonly="readonly" /></td>
									<td><input type="text" name="EmpName"
										value="${emp.empName}" /></td>
									<td><input type="submit" value="<spring:message code="label.Update"></spring:message>" /></td>
								</tr>
							</c:if>
						</c:if>
						<c:if test="${employeeid ne emp.empID}">
							<tr>

								<td>${emp.empID}</td>
								<td>${emp.DID}</td>
								<td>${emp.empName}</td>
								<td><a href="getEmployee?id=${emp.empID}&did=${emp.DID}"><spring:message code="label.Update"></spring:message></a></td>
								<td><a href="deleteEmployee?id=${emp.empID}&did=${emp.DID}"><spring:message code="label.Delete"></spring:message></a></td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${insertEmployee eq 'newemployee'}">
						<tr>
							<td><input type="text" name="EmpID" /></td>
							<td><input type="text" name="DID" /></td>
							<td><input type="text" name="EmpName" /></td>
							<td colspan="2"><input type="submit" value="<spring:message code="label.S"></spring:message>" /></td>
						</tr>
					</c:if>
				</tbody>
			</table>
			</form>
		</c:if>
	</div>
	<div id="footer">
	
	</div>
</body>
</html>
