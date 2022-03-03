<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />


<title>Index page</title>
</head>
<body>
	<jsp:include page="../WEB-INF/jsp/part/header_nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col">
				<div class="form-group">
					<label for="input1" class=" col-form-label"><b>Специальность:</b></label>
					<input type="text" readonly class="form-control-plaintext"
						id="input1" name="name" value="${requestScope.specialty.name}"
						style="border: 2px solid black">
				</div>
				<div class="form-group">
					<label for="input1" class=" col-form-label"><b>Факультет:</b></label>
					<input type="text" readonly class="form-control-plaintext"
						id="input1" name="name"
						value="${requestScope.specialty.faculty.name}"
						style="border: 2px solid black">
				</div>
				<div class="form-group">
					<label for="input1" class=" col-form-label"><b>План
							набора:</b></label> <input type="text" readonly
						class="form-control-plaintext" id="input1" name="name"
						value="${requestScope.specialty.plan}"
						style="border: 2px solid black">
				</div>
				<div class="form-group">
					<label for="input1" class=" col-form-label"><b>Год:</b></label> <input
						type="text" readonly class="form-control-plaintext" id="input1"
						name="name" value="${requestScope.specialty.year}"
						style="border: 2px solid black">
				</div>


				<div class="form-group">
					<label for="input1" class=" col-form-label"><b>Форма
							обучения:</b></label> <input type="text" readonly
						class="form-control-plaintext" id="input1" name="name"
						value="${requestScope.specialty.typeStudy.typeName}"
						style="border: 2px solid black">
				</div>
			</div>
			<div class="col">
				<label for="input1" class=" col-form-label"><b>Предметы:</b></label>
				<c:forEach items="${subjects}" var="sub">
					<div class="form-group">
						<input type="text" readonly class="form-control-plaintext"
							id="input1" name="name" value="${sub.name}"
							style="border: 2px solid black">
					</div>
				</c:forEach>
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