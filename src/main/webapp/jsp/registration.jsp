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

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />
<fmt:message bundle="${loc}" key="local.invalid_message.invalid_login"
	var="loc_invalid_login" />
<fmt:message bundle="${loc}"
	key="local.invalid_message.invalid_password" var="loc_invalid_password" />
<fmt:message bundle="${loc}"
	key="local.invalid_message.invalid_first_name"
	var="loc_invalid_first_name" />
<fmt:message bundle="${loc}"
	key="local.invalid_message.invalid_second_name"
	var="loc_invalid_second_name" />
<fmt:message bundle="${loc}"
	key="local.invalid_message.invalid_last_name"
	var="loc_invalid_last_name" />
<fmt:message bundle="${loc}" key="local.invalid_message.invalid_email"
	var="loc_invalid_email" />
	
	<fmt:message bundle="${loc}" key="local.registration_page.registration"
	var="registration" />
	<fmt:message bundle="${loc}" key="local.registration_page.registration_system"
	var="registration_system" />
	<fmt:message bundle="${loc}" key="local.personal_data.name"
	var="name" />
	<fmt:message bundle="${loc}" key="local.personal_data.login"
	var="login" />
	<fmt:message bundle="${loc}" key="local.personal_data.secondname"
	var="secondname" />
	<fmt:message bundle="${loc}" key="local.personal_data.lastname"
	var="lastname" />
	<fmt:message bundle="${loc}" key="local.personal_data.password"
	var="password" />
	<fmt:message bundle="${loc}" key="local.personal_data.email"
	var="email" />
	<fmt:message bundle="${loc}" key="local.personal_data.repeat_password"
	var="repeat_password" />
	
	
	
<title>Регистрация</title>
</head>
<body>
	<jsp:include page="../WEB-INF/jsp/part/header_nav.jsp"></jsp:include>



	<div>

		<h2 style="margin-left: 10%;">${personal_account}</h2>

		<c:if test="${not empty message}">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${message}"></c:out>
			</div>
		</c:if>

		<c:if test="${not empty invalid_login}">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${loc_invalid_login}"></c:out>
			</div>
		</c:if>

		<c:if test="${not empty invalid_password}">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${loc_invalid_password}"></c:out>
			</div>
		</c:if>

		<c:if test="${not empty invalid_first_name}">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${loc_invalid_first_name}"></c:out>
			</div>
		</c:if>

		<c:if test="${not empty invalid_second_name}">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${loc_invalid_second_name}"></c:out>
			</div>
		</c:if>

		<c:if test="${not empty invalid_last_name}">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${loc_invalid_last_name}"></c:out>
			</div>
		</c:if>

		<c:if test="${not empty invalid_email}">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${loc_invalid_email}"></c:out>
			</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/Controller"
			method="post" class="registration-form">

			<legend>${registration_system}</legend>
			<input type="hidden" name="command" value="registration"> <input
				type="hidden" name="user_role" value="user">

			<div class="form-group row">
				<label for="formGroupExampleInput" class="col-sm-2 col-form-label">${login}</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="formGroupExampleInput"
						placeholder="Введите логин" required name="login">
				</div>

			</div>

			<div class="form-group row">
				<label for="formGroupExampleInput" class="col-sm-2 col-form-label">${secondname}</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="formGroupExampleInput"
						placeholder="Введите фамилию" required name="secondname"
						title="Ваша фамилия (на русском или белорусском языке), как она указана в Вашем паспорте (документе, удостоверяющем личность).">
				</div>
			</div>

			<div class="form-group row">

				<label for="formGroupExampleInput" class="col-sm-2 col-form-label">${name}</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="formGroupExampleInput"
						placeholder="Введите имя" required name="name"
						title="Ваше имя (на русском или белорусском языке), как она указана в Вашем паспорте (документе, удостоверяющем личность).">
				</div>
			</div>

			<div class="form-group row">

				<label for="formGroupExampleInput" class="col-sm-2 col-form-label">${lastname}</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="formGroupExampleInput"
						placeholder="Введите отчество" required name="lastname"
						title="Ваше отчество (на русском или белорусском языке), как она указана в Вашем паспорте (документе, удостоверяющем личность).">
				</div>
			</div>

			<div class="form-group row">

				<label for="exampleInputEmail1" class="col-sm-2 col-form-label">${email}</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="exampleInputEmail1"
						placeholder="Введите почтовый ящик" required name="email">
				</div>
			</div>

			<div class="form-group row">
				<label for="exampleInputPassword1" class="col-sm-2 col-form-label">${password}</label>
				<div class="col-sm-10">
					<input type="password" class="form-control"
						id="exampleInputPassword1" placeholder="Придумайте пароль"
						required name="password">
				</div>
			</div>

			<div class="form-group row">
				<label for="exampleInputPassword1" class="col-sm-2 col-form-label">${repeat_password}</label>
				<div class="col-sm-10">
					<input type="password" class="form-control"
						id="exampleInputPassword1" placeholder="Подтвердите пароль"
						required name="repeat_password">
				</div>
			</div>

			<button type="submit" class="btn btn-primary">${registration}</button>

		</form>

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