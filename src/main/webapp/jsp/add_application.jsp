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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"
	type="text/javascript"></script>
<script src="resources/js/ajax-request.js" type="text/javascript"></script>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />


<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_login"
	var="inv_login" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_password"
	var="inv_password" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_first_name"
	var="inv_first_name" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_second_name"
	var="inv_second_name" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_last_name"
	var="inv_last_name" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_email"
	var="inv_email" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_series"
	var="inv_series" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_id_document"
	var="inv_id_document" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_gender"
	var="inv_gender" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_date_birth"
	var="inv_date_birth" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_family_status"
	var="inv_family_status" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_type_document"
	var="inv_type_document" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_number_passport"
	var="inv_number_passport" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_issued_by"
	var="inv_issued_by" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_school"
	var="inv_school" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_end_study"
	var="inv_end_study" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_certificate"
	var="inv_certificate" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_place_birth"
	var="inv_place_birth" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_faculty_name"
	var="inv_faculty_name" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_type_name"
	var="inv_type_name" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_specialty"
	var="inv_specialty" />
	<fmt:message bundle="${loc}" key="local.Invalid_message.Invalid_privilege"
	var="inv_privilege" />

	



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

	<c:if test="${not empty message}">
		<div class="alert alert-warning" role="alert" id="alert">
			<c:out value="${message}"></c:out>
		</div>
	</c:if>

	<div>
		<form action="${pageContext.request.contextPath}/Controller"
			method="post">
			<c:if test="${empty sessionScope.application_id}">
				<input type="hidden" name="command" value="add_application">
			</c:if>
			<c:if test="${not empty sessionScope.application_id}">
				<input type="hidden" name="command" value="update_application">
			</c:if>
			<div class="container">
				<div class="row">
					<div class="col">
						<legend>1.ФИО</legend>
						<div class="form-group">
							<label for="input1" class="col-form-label">${secondname}:</label> <input
								type="text" class="form-control" id="input1" name="secondname"
								aria-describedby="inputMutedtext"
								value="${requestScope.user_info.secondName}">
							<c:if test="${not empty invalid_second_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_second_name}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${name}:</label> <input
								type="text" class="form-control" id="input1" required
								name="name" aria-describedby="inputMutedtext"
								value="${requestScope.user_info.name}">
							<c:if test="${not empty invalid_first_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_first_name}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${lastname}:</label> <input
								type="text" class="form-control" id="input1" required
								name="lastname" aria-describedby="inputMutedtext"
								value="${requestScope.user_info.lastName}">
							<c:if test="${not empty invalid_last_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_last_name}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="inputDate" class=" col-form-label">${date_birthday}:</label> <input type="date" class="form-control" id="inputDate"
								required name="date_of_birth" aria-describedby="inputMutedtext"
								value="${requestScope.user_info.dateOfBirth}">
							<c:if test="${not empty invalid_date_of_birth}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_date_birth}"></c:out>
								</small>
							</c:if>

						</div>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">${gender}:</label>

							<select class="custom-select mr-sm-2" id="inlineFormCustomSelect"
								required name="gender" aria-describedby="inputMutedtext">
								<c:choose>
									<c:when test="${empty requestScope.user_info.gender}">
										<option selected value="Мужской">Мужской</option>
										<option value="Женский">Женский</option>
									</c:when>
									<c:when test="${requestScope.user_info.gender == 'Мужской'}">
										<option selected value="${requestScope.user_info.gender}">${requestScope.user_info.gender}</option>
										<option value="Женский">Женский</option>
									</c:when>
									<c:otherwise>
										<option selected value="${requestScope.user_info.gender}">${requestScope.user_info.gender}</option>
										<option value="Мужской">Мужской</option>
									</c:otherwise>
								</c:choose>
							</select>
							<c:if test="${not empty invalid_gender}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_gender}"></c:out>
								</small>
							</c:if>

						</div>
						<div class="form-group">
							<label class="col-sm-5 col-form-label"
								for="inlineFormCustomSelect">${family_status}:</label> <select
								class="form-control" id="inlineFormCustomSelect" required
								name="marital_status" aria-describedby="inputMutedtext">
								<c:choose>
									<c:when test="${empty requestScope.user_info.maritalStatus}">
										<option value="Холост/Не замужем">Холост/Не замужем</option>
										<option value="Женат/Замужем">Женат/Замужем</option>
									</c:when>
									<c:when
										test="${requestScope.user_info.maritalStatus == 'Холост/Не
									замужем'}">
										<option selected
											value="${requestScope.user_info.maritalStatus}">${requestScope.user_info.maritalStatus}</option>
										<option value="Женат/Замужем">Женат/Замужем</option>
									</c:when>
									<c:otherwise>
										<option selected
											value="${requestScope.user_info.maritalStatus}">${requestScope.user_info.maritalStatus}</option>
										<option value="Холост/Не замужем">Холост/Не замужем</option>
									</c:otherwise>
								</c:choose>
							</select>
							<c:if test="${not empty invalid_marital_status}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_family_status}"></c:out>
								</small>
							</c:if>

						</div>
					</div>
					<div class="col">
						<legend>2.Документ удостоверяющий личность</legend>
						<div class="form-group">
							<label class=" col-form-label text-right"
								for="inlineFormCustomSelect">${type_document}:</label> <select
								class="custom-select mr-sm-2" id="inlineFormCustomSelect"
								required name="type_document" aria-describedby="inputMutedtext">
								<option selected value="Паспорт гражданина РБ">Паспорт
									гражданина РБ</option>
							</select>
							<c:if test="${not empty invalid_type_document}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_type_document}"></c:out>
								</small>
							</c:if>

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${id_number}:</label> <input
								type="text" class="form-control" id="input1" required
								name="id_document" aria-describedby="inputMutedtext"
								value="${requestScope.application.idDocument}">
							<c:if test="${not empty invalid_id_document}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_id_document}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${series}:</label> <input
								type="text" class="form-control" id="input1" required
								name="series_passport" aria-describedby="inputMutedtext"
								value="${requestScope.application.seriesPassport}">
							<c:if test="${not empty invalid_series_passport}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_series}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${number}:</label> <input
								type="text" class="form-control" id="input1" required
								name="number_passport" aria-describedby="inputMutedtext"
								value="${requestScope.application.numberPassport}">
							<c:if test="${not empty invalid_number_passport}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_number_passport}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${issued_by}:</label> <input
								type="text" class="form-control" id="input1" required
								name="issued_by" aria-describedby="inputMutedtext"
								value="${requestScope.application.issuedBy}">
							<c:if test="${not empty invalid_issued_by}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_issued_by}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<legend>3.Образование</legend>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">${school}:</label> <select class="form-control"
								id="inlineFormCustomSelect" required name="school">
								<option value="${requestScope.school.id}" selected>${requestScope.school.name}</option>
								<c:forEach items="${requestScope.schools}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty invalid_school}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_school}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="inputDate" class=" col-form-label">${end_study}:</label> <input type="date" class="form-control"
								id="inputDate" required name="end_study_date"
								aria-describedby="inputMutedtext"
								value="${requestScope.application.endStudyDate}">
							<c:if test="${not empty invalid_end_study_date}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_end_study}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${certificate}:</label> <input
								type="text" class="form-control" id="input1" required
								name="certificate" aria-describedby="inputMutedtext"
								value="${requestScope.application.certificate}">
							<c:if test="${not empty invalid_certificate}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_certificate}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
					<div class="col">
						<legend>4.Адрес</legend>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${adress}:</label> <input
								type="text" class="form-control" id="input1" required
								name="adres" aria-describedby="inputMutedtext"
								value="${requestScope.application.adress}">
							<c:if test="${not empty invalid_adress}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_adress}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${place_birth}:</label> <input type="text" class="form-control" id="input1"
								required name="place_of_birth" aria-describedby="inputMutedtext"
								value="${requestScope.user_info.placeOfBirth}">
							<c:if test="${not empty invalid_place_of_birth}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_place_birth}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<legend>5.Специальность</legend>
						<div class="form-group">
							<label class=" col-form-label" for="faculty_list">${faculty_loc}:</label> <select class="form-control" id="faculty_list"
								required name="faculty" aria-describedby="inputMutedtext">
								<option value="${requestScope.specialty.faculty.id}" selected>${requestScope.specialty.faculty.name}</option>
								<c:forEach items="${requestScope.faculty}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty invalid_faculty_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_faculty_name}"></c:out>
								</small>
							</c:if>
						</div>

						<div class="form-group">
							<label class=" col-form-label" for="type_study_list">${type_sudy_loc}:</label> <select class="form-control" id="type_study_list"
								required name="type_study" aria-describedby="inputMutedtext">
								<option value="${requestScope.specialty.typeStudy.id}" selected>${requestScope.specialty.typeStudy.typeName}</option>
								<c:forEach items="${requestScope.type_study}" var="item">
									<option value="${item.id}">${item.typeName}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty invalid_type_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_type_name}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label class=" col-form-label" for="specialty_list">${specialty}:</label> <select class="form-control specialties"
								id="specialty_list" required name="specialty"
								aria-describedby="inputMutedtext">
								<option value="${requestScope.specialty.id}">${requestScope.specialty.name}</option>
							</select>
							<c:if test="${not empty invalid_specialty}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_specialty}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
					<div class="col">
						<legend>6.Прочее</legend>
						<div class="form-group">
							<label for="input1" class=" col-form-label">${email}:</label> <input type="text" class="form-control" id="input1"
								required name="email" aria-describedby="inputMutedtext"
								value="${requestScope.user_info.email}">
							<c:if test="${not empty invalid_email}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_email}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">${privilege}:</label>

							<select class="form-control" id="inlineFormCustomSelect" required
								name="privilege" aria-describedby="inputMutedtext">
								<option value="${requestScope.privilege.id}" selected>${requestScope.privilege.name}</option>
								<c:forEach items="${requestScope.privileges}" var="priv">
									<option value="${priv.id}">${priv.name}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty invalid_privilege}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${inv_privilege}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<button type="submit" class="btn btn-primary btn-block">Сохранить</button>
					</div>
				</div>
			</div>
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