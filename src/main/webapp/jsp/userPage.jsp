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
<link rel="stylesheet" href="resources/css/style.css" />

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />
<fmt:message bundle="${loc}" key="local.personal_data.name" var="name" />
<fmt:message bundle="${loc}" key="local.personal_data.login" var="login" />
<fmt:message bundle="${loc}" key="local.personal_data.secondname"
	var="secondname" />
<fmt:message bundle="${loc}" key="local.personal_data.lastname"
	var="lastname" />
<fmt:message bundle="${loc}" key="local.personal_data.password"
	var="password" />
<fmt:message bundle="${loc}" key="local.personal_data.email" var="email" />
<fmt:message bundle="${loc}" key="local.personal_data.gender"
	var="gender" />
<fmt:message bundle="${loc}" key="local.personal_data.date_birthday"
	var="date_birthday" />
<fmt:message bundle="${loc}" key="local.personal_data.family_status"
	var="family_status" />
<fmt:message bundle="${loc}" key="local.personal_data.type_document"
	var="type_document" />
<fmt:message bundle="${loc}" key="local.personal_data.id_number"
	var="id_number" />
<fmt:message bundle="${loc}" key="local.personal_data.series"
	var="series" />
<fmt:message bundle="${loc}" key="local.personal_data.number"
	var="number" />
<fmt:message bundle="${loc}" key="local.personal_data.issued_by"
	var="issued_by" />
<fmt:message bundle="${loc}" key="local.personal_data.school"
	var="school" />
<fmt:message bundle="${loc}" key="local.personal_data.end_study"
	var="end_study" />
<fmt:message bundle="${loc}" key="local.personal_data.certificate"
	var="certificate" />
<fmt:message bundle="${loc}" key="local.personal_data.adress"
	var="adress" />
<fmt:message bundle="${loc}" key="local.personal_data.place_birth"
	var="place_birth" />
<fmt:message bundle="${loc}" key="local.personal_data.faculty"
	var="faculty_loc" />
<fmt:message bundle="${loc}" key="local.personal_data.type_sudy"
	var="type_sudy_loc" />
<fmt:message bundle="${loc}" key="local.personal_data.specialty"
	var="specialty" />
<fmt:message bundle="${loc}" key="local.personal_data.privilege"
	var="privilege" />

<title>Анкета</title>
</head>
<body>
	<jsp:include page="../WEB-INF/jsp/part/header_nav.jsp"></jsp:include>


	<div>
		<div class="container">
			<c:if test="${not empty message }">
				<div class="alert alert-warning" role="alert" id="alert">
					<c:out value="${message}"></c:out>
				</div>
			</c:if>
			<div class="row">
				<div class="col">
					<legend>1.ФИО</legend>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Фамилия:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="secondname" value="${requestScope.user_info.secondName}"
							style="border: 2px solid black">
					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Имя:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="name" value="${requestScope.user_info.name}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Отчество:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="lastname" value="${requestScope.user_info.lastName}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="inputDate" class=" col-form-label">Дата
							рождения:</label> <input type="text" readonly
							class="form-control-plaintext" id="inputDate"
							name="date_of_birth"
							value="${requestScope.user_info.dateOfBirth}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label class=" col-form-label" for="inlineFormCustomSelect">Пол:</label>

						<input type="text" readonly class="form-control-plaintext"
							id="inputDate" name="gender"
							value="${requestScope.user_info.gender}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label class="col-sm-5 col-form-label"
							for="inlineFormCustomSelect">Семейное положение:</label> <input
							type="text" readonly class="form-control-plaintext"
							id="inputDate" name="marital_status"
							value="${requestScope.user_info.maritalStatus}"
							style="border: 2px solid black">

					</div>
				</div>
				<div class="col">
					<legend>2.Документ удостоверяющий личность</legend>
					<div class="form-group">
						<label class=" col-form-label text-right"
							for="inlineFormCustomSelect">Тип документа:</label> <input
							type="text" readonly class="form-control-plaintext"
							id="inputDate" name="type_document"
							value="${requestScope.application.typeDocument}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Ид. номер:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="id_document" value="${requestScope.application.idDocument}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Серия:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="series_passport"
							value="${requestScope.application.seriesPassport}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Номер:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="number_passport"
							value="${requestScope.application.numberPassport}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Кем выдан:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="issued_by" value="${requestScope.application.issuedBy}"
							style="border: 2px solid black">

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<legend>3.Образование</legend>
					<div class="form-group">
						<label class=" col-form-label" for="inlineFormCustomSelect">Учебное
							учреждение:</label> <input type="text" readonly
							class="form-control-plaintext" id="inputDate" name="school"
							value="${requestScope.school.name}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="inputDate" class=" col-form-label">Дата
							окончания:</label> <input type="text" readonly
							class="form-control-plaintext" id="inputDate"
							name="end_study_date"
							value="${requestScope.application.endStudyDate}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Ср. балл:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="certificate"
							value="${requestScope.application.certificate}"
							style="border: 2px solid black">

					</div>
				</div>
				<div class="col">
					<legend>4.Адрес</legend>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Адрес:</label> <input
							type="text" readonly class="form-control-plaintext" id="input1"
							name="adres" value="${requestScope.application.adress}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Место
							рождения:</label> <input type="text" readonly
							class="form-control-plaintext" id="input1" name="place_of_birth"
							value="${requestScope.user_info.placeOfBirth}"
							style="border: 2px solid black">

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<legend>5.Специальность</legend>
					<div class="form-group">
						<label class=" col-form-label" for="inlineFormCustomSelect">Форма
							обучения:</label> <input type="text" readonly
							class="form-control-plaintext" id="input1" name="specialty"
							value="${requestScope.specialty.typeStudy.typeName}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label class=" col-form-label" for="inlineFormCustomSelect">Выбранная
							специальность:</label> <input type="text" readonly
							class="form-control-plaintext" id="input1" name="specialty"
							value="${requestScope.specialty.name}"
							style="border: 2px solid black">

					</div>
				</div>
				<div class="col">
					<legend>6.Прочее</legend>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Почтовый ящик:</label>

						<input type="text" readonly class="form-control-plaintext"
							id="input1" name="email" value="${requestScope.user_info.email}"
							style="border: 2px solid black">

					</div>
					<div class="form-group">
						<label class=" col-form-label" for="inlineFormCustomSelect">Привилегия:</label>

						<input type="text" readonly class="form-control-plaintext"
							id="input1" name="privilege"
							value="${requestScope.privilege.name}"
							style="border: 2px solid black">

					</div>
				</div>
			</div>
			<c:if
				test="${sessionScope.user_role == 'USER' && requestScope.application.confirmation == false}">
				<div class="row">
					<div class="col-sm-6">
						<a class="btn btn-lg btn-success"
							href="${pageContext.request.contextPath}/Controller?command=show_addapplication_page">Редактировать</a>
					</div>
				</div>
			</c:if>

			<c:if
				test="${sessionScope.user_role == 'USER' && requestScope.application.confirmation == true}">
				<div class="row">
					<div class="col-sm-6">
						<legend>7.Предметы</legend>
						<c:forEach items="${subjects}" var="sub">
							<label for="input1" class=" col-form-label">${sub.name}:</label>
							<c:choose>
								<c:when test="${not empty marks}">
									<c:forEach items="${marks}" var="marks">
										<c:if test="${marks.idSubject == sub.id}">
											<input type="text" required class="form-control-plaintext"
												id="input1" name="mark" value="${marks.mark}"
												style="border: 2px solid black">
										</c:if>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:forEach>

					</div>
					<div class="col-sm-6">
						<legend>8.Результат</legend>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">Общий
								балл:</label> <input type="text" readonly class="form-control-plaintext"
								id="input1" name="total_score"
								value="${requestScope.total_score}"
								style="border: 2px solid black">
						</div>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">Результат:</label>
							<input type="text" readonly class="form-control-plaintext"
								id="input1" name="total_score" value="${requestScope.accept}"
								style="border: 2px solid black">
						</div>

					</div>
				</div>
			</c:if>

			<c:if test="${sessionScope.user_role == 'ADMIN'}">
				<div class="row">
					<div class="col-sm-6">
						<legend>7.Предметы</legend>
						<form action="${pageContext.request.contextPath}/Controller"
							method="post">
							<input type="hidden" name="command" value="confirm_account">
							<input type="hidden" name="specialty_id"
								value="${requestScope.specialty.id}">
							<c:forEach items="${subjects}" var="sub">
								<label for="input1" class=" col-form-label">${sub.name}:</label>
								<input type="hidden" name="subject_id" value="${sub.id}">
								<c:choose>
									<c:when test="${not empty marks}">
										<c:forEach items="${marks}" var="marks">
											<c:if test="${marks.idSubject == sub.id}">
												<input type="text" required class="form-control-plaintext"
													id="input1" name="mark" value="${marks.mark}">
											</c:if>
										</c:forEach>
									</c:when>
									<c:when test="${empty marks || marks == null}">
										<input type="text" required class="form-control-plaintext"
											id="input1" name="mark">
									</c:when>
								</c:choose>
							</c:forEach>
							<button type="submit" class="btn btn-primary">Подтвердить</button>
						</form>
					</div>
					<c:if test="${requestScope.application.confirmation == true}">
						<div class="col-sm-6">
							<legend>7.Зачисление</legend>
							<form action="${pageContext.request.contextPath}/Controller"
								method="post">
								<input type="hidden" name="command" value="accept_student">
								<div class="form-group">
									<label class=" col-form-label" for="inlineFormCustomSelect">Общий
										балл:</label> <input type="text" readonly
										class="form-control-plaintext" id="input1" name="total_score"
										value="${requestScope.total_score}"
										style="border: 2px solid black">
								</div>
								<div class="form-group">
									<label class=" col-form-label" for="inlineFormCustomSelect">Результат:</label>
									<input type="text" readonly class="form-control-plaintext"
										id="input1" name="isAccept" value="${requestScope.accept}"
										style="border: 2px solid black">
								</div>
								<div class="form-group">
									<label class=" col-form-label" for="inlineFormCustomSelect">Выберете
										итог:</label> <select class="custom-select mr-sm-2"
										id="inlineFormCustomSelect" required name="accept"
										aria-describedby="inputMutedtext">
										<option value="true">Зачислен</option>
										<option value="false">Отказ</option>
									</select>
								</div>
								<button type="submit" class="btn btn-primary">Зачислить</button>
							</form>
						</div>
					</c:if>
				</div>
			</c:if>
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