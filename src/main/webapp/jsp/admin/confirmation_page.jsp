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


<title>Анкета</title>
</head>
<body>
	<jsp:include page="../../WEB-INF/jsp/part/header_nav.jsp"></jsp:include>


	<div>
		<form>
			<input type="hidden" name="command" value="add_application">
			<div class="container">
				<div class="row">
					<div class="col">
						<legend>1.ФИО</legend>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Фамилия:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="secondname" value="${requestScope.user_info.secondName}">
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Имя:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="name" value="${requestScope.user_info.name}">

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Отчество:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="lastname" value="${requestScope.user_info.lastName}">

						</div>
						<div class="form-group">
							<label for="inputDate" class=" col-form-label">Дата
								рождения:</label> <input type="text" readonly
								class="form-control-plaintext" id="inputDate"
								name="date_of_birth"
								value="${requestScope.user_info.dateOfBirth}">

						</div>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">Пол:</label>

							<input type="text" readonly class="form-control-plaintext"
								id="inputDate" name="gender"
								value="${requestScope.user_info.gender}">

						</div>
						<div class="form-group">
							<label class="col-sm-5 col-form-label"
								for="inlineFormCustomSelect">Семейное положение:</label> <input
								type="text" readonly class="form-control-plaintext"
								id="inputDate" name="marital_status"
								value="${requestScope.user_info.maritalStatus}">

						</div>
					</div>
					<div class="col">
						<legend>2.Документ удостоверяющий личность</legend>
						<div class="form-group">
							<label class=" col-form-label text-right"
								for="inlineFormCustomSelect">Тип документа:</label> <input
								type="text" readonly class="form-control-plaintext"
								id="inputDate" name="type_document"
								value="${requestScope.application.typeDocument}">

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Ид. номер:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="id_document"
								value="${requestScope.application.idDocument}">

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Серия:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="series_passport"
								value="${requestScope.application.seriesPassport}">

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Номер:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="number_passport"
								value="${requestScope.application.numberPassport}">

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Кем выдан:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="issued_by" value="${requestScope.application.issuedBy}">

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
								value="${requestScope.school.name}">

						</div>
						<div class="form-group">
							<label for="inputDate" class=" col-form-label">Дата
								окончания:</label> <input type="text" readonly
								class="form-control-plaintext" id="inputDate"
								name="end_study_date"
								value="${requestScope.application.endStudyDate}">

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Ср. балл:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="certificate"
								value="${requestScope.application.certificate}">

						</div>
					</div>
					<div class="col">
						<legend>4.Адрес</legend>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Адрес:</label> <input
								type="text" readonly class="form-control-plaintext" id="input1"
								name="adres" value="${requestScope.application.adress}">

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Место
								рождения:</label> <input type="text" readonly
								class="form-control-plaintext" id="input1" name="place_of_birth"
								value="${requestScope.user_info.placeOfBirth}">

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
								value="${requestScope.specialty.typeStudy.typeName}">

						</div>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">Выбранная
								специальность:</label> <input type="text" readonly
								class="form-control-plaintext" id="input1" name="specialty"
								value="${requestScope.specialty.name}">

						</div>
					</div>
					<div class="col">
						<legend>6.Прочее</legend>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Почтовый
								ящик:</label> <input type="text" readonly class="form-control-plaintext"
								id="input1" name="email" value="${requestScope.user_info.email}">

						</div>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">Привилегия:</label>

							<input type="text" readonly class="form-control-plaintext"
								id="input1" name="privilege"
								value="${requestScope.privilege.name}">

						</div>
					</div>
				</div>
				<div class="row">
					<legend>7.Экзамены</legend>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<a class="btn btn-lg btn-success"
							href="${pageContext.request.contextPath}/Controller?command=show_addapplication_page">Редактировать</a>
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