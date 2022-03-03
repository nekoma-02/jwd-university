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
<script src="resources/js/create-remove-element.js"
	type="text/javascript"></script>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />


<title>Add specialty</title>
</head>
<body>
	<jsp:include page="../../WEB-INF/jsp/part/header_nav.jsp"></jsp:include>
	<div class="container-fluid" id="admin-content">
		<div class="row">
			<jsp:include page="../../WEB-INF/jsp/part/admin_nav.jsp"></jsp:include>
			<div class="col-10">
				<div>
					<c:if test="${not empty message}">
						<div class="alert alert-warning" role="alert" id="alert">
							<c:out value="${message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_plan}">
						<div class="alert alert-warning" role="alert" id="alert">
							<c:out value="${invalid_plan}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_year}">
						<div class="alert alert-warning" role="alert" id="alert">
							<c:out value="${invalid_year}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_specialty_name}">
						<div class="alert alert-warning" role="alert" id="alert">
							<c:out value="${invalid_specialty_name}"></c:out>
						</div>
					</c:if>
					<form action="${pageContext.request.contextPath}/Controller"
						method="post">
						<input type="hidden" name="command" value="add_specialty">
						<div class="container-fluid">
							<div class="row">
								<div class="col">
									<legend>1.Добавление специальности</legend>

									<div class="form-group">
										<label class=" col-form-label" for="faculty_list">Выберете
											факультет:</label> <select class="form-control" id="faculty_list"
											required name="faculty" aria-describedby="inputMutedtext">
											<option value="${requestScope.specialty.faculty.id}" selected>${requestScope.specialty.faculty.name}</option>
											<c:forEach items="${requestScope.faculty}" var="item">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
									</div>

									<div class="form-group">
										<label class=" col-form-label" for="type_study_list">Выберете
											тип обучения:</label> <select class="form-control"
											id="type_study_list" required name="type_study"
											aria-describedby="inputMutedtext">
											<option value="${requestScope.specialty.typeStudy.id}"
												selected>${requestScope.specialty.typeStudy.typeName}</option>
											<c:forEach items="${requestScope.type_study}" var="item">
												<option value="${item.id}">${item.typeName}</option>
											</c:forEach>
										</select>
									</div>

									<div class="form-group">
										<label for="input1" class="col-form-label">Название
											специальности:</label> <input type="text" class="form-control"
											id="input1" required name="specialty">
									</div>
									<div class="form-group">
										<label for="input1" class="col-form-label">Год:</label> <input
											type="text" class="form-control" id="input1" required
											name="specialty_year">
									</div>

									<div class="form-group">
										<label for="input1" class="col-form-label">План:</label> <input
											type="text" class="form-control" id="input1" required
											name="specialty_plan">
									</div>

								</div>
								<div class="col">
									<legend>2.Добавление предмета к специальность</legend>
									<label class=" col-form-label" for="sub_List">Выберете
										предмет:</label>
									<div class="subject_json_plus">
										<select class="form-control subject-list" id="sub_List"
											required name="subject" aria-describedby="inputMutedtext">
											<c:forEach items="${requestScope.subjects}" var="item">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
									</div>
									<span class="btn btn-success plus pull-right">+</span>

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
ml>
