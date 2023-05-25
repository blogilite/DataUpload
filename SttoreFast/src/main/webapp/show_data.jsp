<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.second {
	height: 500px;
	overflow: auto;
}

::-webkit-scrollbar {
	width: 20px;
}

tr, td, table, thead {
	border: black 1px solid;
	padding: 1px;
	text-align: center;
	color: #343e0d;
}
</style>
<body style="background-color: burlywood; color: blue;">
	<div align="center">
		<c:out value="${time}"></c:out>
		<br>
		<c:out value="${data}"></c:out>
	</div>
	<div align="center" class="second">
		<table>
			<thead>
				<tr>
					<td>Id</td>
					<td>name</td>
					<td>Address</td>
					<td>Email ID</td>
					<td>Phone Number</td>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${listData}" var="row">
					<tr>
						<c:forEach items="${row}" var="cell">
							<td>${cell}</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>