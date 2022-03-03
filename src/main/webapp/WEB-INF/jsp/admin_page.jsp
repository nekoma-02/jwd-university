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

<title>Админка</title>
</head>
<body>
	<jsp:include page="part/header_nav.jsp"></jsp:include>
	<div class="container-fluid" id="admin-content">
		<div class="row">
			<jsp:include page="part/admin_nav.jsp"></jsp:include>
			<div class="col-10">
				<div>

					<div class="btn-group" role="group" aria-label="Basic example"
						style="margin-left: 15%; padding-bottom: 10px;">
						<button type="button" class="btn btn-secondary" id="show-app"
							value="all">Заявления</button>
						<button type="button" class="btn btn-secondary" id="show-faculty"
							value="all">Факультеты</button>
						<button type="button" class="btn btn-secondary"
							id="show-specialty" value="all">Специальности</button>
						<button type="button" class="btn btn-secondary"
							id="show-type-study" value="all">Формы обучения</button>
						<button type="button" class="btn btn-secondary"
							id="show-privilege" value="all">Привилегии</button>
						<button type="button" class="btn btn-secondary" id="show-school"
							value="all">Учреждения</button>
						<button type="button" class="btn btn-secondary" id="show-subject"
							value="all">Предметы</button>
					</div>

					<div class="table-filters">
						<label class="radio-inline"><input type="radio"
							name="optradio" checked value="all" class="radio-filter">Показать
							все</label> <label class="radio-inline"><input type="radio"
							name="optradio" value="confirmed" class="radio-filter">
							Показать подтвержденные</label> <label class="radio-inline"><input
							type="radio" name="optradio" value="unconfirmed"
							class="radio-filter">Показать не подтвержденные</label>
					</div>
					<div>
						<h4 id="table-caption">Application</h4>
					</div>
					<table class="table table-hover">
						<thead class="thead-dark" id="table-caption">
							<tr id="Application-caption" style="visibility: visible;">
								<th scope="col">ФИО</th>
								<th scope="col">Адрес</th>
								<th scope="col">Специальность</th>
								<th scope="col">Факультет</th>
								<th scope="col">Форма обучения</th>
								<th scope="col">Подтверждение</th>
								<th scope="col">Кабинет пользователя</th>
							</tr>
							<tr id="Specialty-caption" style="display: none;">
								<th scope="col">Специальность</th>
								<th scope="col">План</th>
								<th scope="col">Год</th>
								<th scope="col">Факультет</th>
								<th scope="col">Форма обучения</th>
								<th scope="col">Изменить</th>
								<th scope="col">Заявления</th>
							</tr>
							<tr id="Subject-caption" style="display: none;">
								<th scope="col">Предмет</th>
								<th scope="col">Изменить</th>
							</tr>
							<tr id="School-caption" style="display: none;">
								<th scope="col">Название</th>
								<th scope="col">Уровень</th>
								<th scope="col">Учреждение</th>
								<th scope="col">Изменить</th>
							</tr>
							<tr id="Faculty-caption" style="display: none;">
								<th scope="col">Факультет</th>
								<th scope="col">Изменить</th>
							</tr>
							<tr id="TypeStudy-caption" style="display: none;">
								<th scope="col">Форма обучения</th>
								<th scope="col">Изменить</th>
							</tr>
							<tr id="Privilege-caption" style="display: none;">
								<th scope="col">Льгота</th>
								<th scope="col">Изменить</th>
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
									<c:if test="${app.confirmation == true }">
									<td>Active</td>
									</c:if>
									<c:if test="${app.confirmation == false }">
									<td>Non active</td>
									</c:if>
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