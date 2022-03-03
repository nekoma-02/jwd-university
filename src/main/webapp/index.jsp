<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="resources/css/style.css">

<meta http-equiv="Content-type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />
<fmt:message bundle="${loc}" key="local.button.name.sign_in"
	var="sign_in" />
<fmt:message bundle="${loc}" key="local.button.name.sign_up"
	var="sign_up" />
	
<fmt:message bundle="${loc}" key="local.index_page.login_account"
	var="login_account" />
	<fmt:message bundle="${loc}" key="local.index_page.personal_account"
	var="personal_account" />
	<fmt:message bundle="${loc}" key="local.index_page.filling_account"
	var="filling_account" />
	<fmt:message bundle="${loc}" key="local.index_page.havent_account"
	var="havent_account" />
	<fmt:message bundle="${loc}" key="local.index_page.have_account"
	var="have_account" />
	<fmt:message bundle="${loc}" key="local.index_page.info1"
	var="info1" />
	<fmt:message bundle="${loc}" key="local.index_page.bsua"
	var="bsua" />
	<fmt:message bundle="${loc}" key="local.index_page.info2"
	var="info2" />
	<fmt:message bundle="${loc}" key="local.index_page.info3"
	var="info3" />
	<fmt:message bundle="${loc}" key="local.index_page.info4"
	var="info4" />
	<fmt:message bundle="${loc}" key="local.namepage.admin_page"
	var="admin_page" />

<title>Главная страница</title>
</head>
<body>
	<jsp:include page="WEB-INF/jsp/part/header_nav.jsp"/>


	<div class="content">
		<div class="row" id="accaunt-row">
			<div class="col">
				<legend>
					<h2 style="text-align: center;">${personal_account}</h2>
				</legend>

				<c:if test="${not empty sessionScope.user_id}">

					<c:if
						test="${not empty sessionScope.application_id && sessionScope.user_role != 'ADMIN'}">
						<div class="row">
							<div class="col">
								<a class="btn btn-lg btn-success"
									href="${pageContext.request.contextPath}/Controller?command=show_userpage"
									style="width: 100%;">${login_account}</a>
							</div>
						</div>
					</c:if>
					<c:if test="${sessionScope.user_role == 'ADMIN'}">
						<div class="row">
							<div class="col">
								<a class="btn btn-lg btn-success"
									href="${pageContext.request.contextPath}/Controller?command=admin_page"
									style="width: 100%;">${admin_page}</a>
							</div>
						</div>
					</c:if>
					<c:if test="${empty sessionScope.application_id}">

						<c:if test="${sessionScope.user_role != 'ADMIN'}">
							<div class="row">
								<div class="col">
									<a class="btn btn-lg btn-success"
										href="${pageContext.request.contextPath}/Controller?command=show_addapplication_page"
										style="width: 100%;">${filling_account}</a>
								</div>
							</div>
						</c:if>
					</c:if>

				</c:if>

				<c:if test="${empty sessionScope.user_id}">
					<div class="row">
						<div class="col">
							<p>${havent_account}</p>
						</div>
						<div class="col" style="border-left: 1px dotted lightgray;">
							<p>${have_account}</p>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div>
								<a class="btn btn-lg btn-primary" href="jsp/registration.jsp"
									style="width: 100%;">${sign_up}</a>
							</div>
						</div>
						<div class="col">
							<div>
								<a class="btn btn-lg btn-success" href="jsp/login.jsp"
									style="width: 100%;">${sign_in}</a>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col">

				<h3 style="margin-top: 10px;">${bsua}</h3>
				<p style="font-size: medium; text-align: justify;">
					${info1}
				</p>
				<br />
				<div class="row" id="img-content">
					<p style="font-size: smaller; text-align: justify;"
						class="col-sm-4">
						<img src="resources/images/1.jpg" /> <br /> ${info2}
					</p>
					<p style="font-size: smaller; text-align: justify;"
						class="col-sm-4">
						<img src="resources/images/3.jpg" /><br /> ${info3}
					</p>
					<p style="font-size: smaller; text-align: justify;"
						class="col-sm-4">
						<img src="resources/images/2.jpg" /><br /> ${info4}
					</p>
				</div>
			</div>
		</div>
	</div>




	<mytag:footer-tag/>



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
