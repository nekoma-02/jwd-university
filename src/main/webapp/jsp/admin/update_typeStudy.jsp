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


<title>Update type study</title>
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
					<c:if test="${not empty invalid_type_name}">
						<div class="alert alert-warning" role="alert" id="alert">
							<c:out value="${invalid_type_name}"></c:out>
						</div>
					</c:if>
					<form action="${pageContext.request.contextPath}/Controller"
						method="post">
						<input type="hidden" name="command" value="update_type_study">
						<input type="hidden" name="type_study_id"
							value="${requestScope.type_study_id}">
						<div class="container-fluid">
							<div class="row">
								<div class="col">
									<legend>1.Изменение формы обучения</legend>
									<div class="form-group">
										<label for="input1" class="col-form-label">Название
											формы обучения:</label> <input type="text" class="form-control"
											id="input1" name="type_study_name"
											value="${requestScope.type_study_name}">
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
					<form action="${pageContext.request.contextPath}/Controller"
						method="post">
						<input type="hidden" name="command" value="remove_type_study">
						<input type="hidden" name="type_study_id"
							value="${requestScope.type_study_id}">
							<div class="container-fluid">
							<div class="row">
								<div class="col-sm-6">
									<button type="submit" class="btn btn-primary btn-block">Удалить</button>
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
