<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"
	type="text/javascript"></script>
<script src="resources/js/ajax-request.js" type="text/javascript"></script>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />

<title>Заявления по специальности</title>
</head>
<body>
	<jsp:include page="../../WEB-INF/jsp/part/header_nav.jsp"></jsp:include>
	<div class="container-fluid" id="admin-content">
		<div class="row">
			<jsp:include page="../../WEB-INF/jsp/part/admin_nav.jsp"></jsp:include>
			<div class="col-10">
				<div>
					<div>
						<h4>Заявления по специальности</h4>
					</div>
					<table class="table table-hover">
					
						<thead class="thead-dark" id="table-caption">
							<tr id="Application-caption">
								<th scope="col">ФИО</th>
								<th scope="col">Адрес</th>
								<th scope="col">Специальность</th>
								<th scope="col">Факультет</th>
								<th scope="col">Форма обучения</th>
								<th scope="col">Подтверждение</th>
								<th scope="col">Кабинет пользователя</th>
							</tr>
						</thead>
						<tbody id="table-list">
							<c:forEach items="${application}" var="app">
								<tr>
									<td>${app.user.secondName} ${app.user.name} ${app.user.lastName}</td>
									<td>${app.adress}</td>
									<td>${app.specialties.name}</td>
									<td>${app.specialties.faculty.name}</td>
									<td>${app.specialties.typeStudy.typeName}</td>
									<td>${app.confirmation}</td>
									<td><a
										href="${pageContext.request.contextPath}/Controller?command=show_userpage&user_id=${app.user.id}&application_id=${app.id}">CLICK</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>

</body>
</html>