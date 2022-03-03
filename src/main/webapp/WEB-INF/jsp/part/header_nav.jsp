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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/navbar.css" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />
<fmt:message bundle="${loc}" key="local.namepage.main_page"
	var="mainpage" />
<fmt:message bundle="${loc}" key="local.namepage.info_page"
	var="infopage" />
<fmt:message bundle="${loc}" key="local.namepage.contact_page"
	var="contactpage" />
	<fmt:message bundle="${loc}" key="local.namepage.admin_page"
	var="admin_page" />
	<fmt:message bundle="${loc}" key="local.index_page.personal_account"
	var="personal_account" />
	<fmt:message bundle="${loc}" key="local.generic_page.exit"
	var="exit" />

</head>
<body>
	<div>
		<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
			<a class="navbar-brand" href="#">BRAND</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/index.jsp">${mainpage}</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/Controller?command=show_specialties">${infopage}</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">${contactpage}</a>
					</li>

					<c:if test="${not empty sessionScope.application_id}">
						<c:if test="${sessionScope.user_role != 'ADMIN'}">
							<li><a
								href="${pageContext.request.contextPath}/Controller?command=show_userpage"
								class="nav-link">${personal_account}</a></li>
						</c:if>
					</c:if>


					<c:if test="${sessionScope.user_role == 'ADMIN'}">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/Controller?command=admin_page">${admin_page}</a>
						</li>
					</c:if>
					<li>
						<form class="form-inline" id="locale-form"
							action="${pageContext.request.contextPath}/Controller"
							method="post">
							<input type="hidden" name="current_url" id="url" />
							<input type="hidden" name="local" value="ru" /> <input
								type="hidden" name="command" value="change_local" />
							<button class="btn" type="submit" id="rus_btn"></button>
						</form>

					</li>
					<li>
						<form class="form-inline" id="locale-form"
							action="${pageContext.request.contextPath}/Controller"
							method="post">
							<input type="hidden" name="current_url" id="url2" />
							<input type="hidden" name="local" value="en" /> <input
								type="hidden" name="command" value="change_local" />
							<button class="btn" type="submit" id="en_btn"></button>
						</form>
					</li>
				</ul>
				<c:if test="${not empty sessionScope.user_id}">
					<form id="login-form"
						action="${pageContext.request.contextPath}/Controller"
						method="post">
						<input type="hidden" name="command" value="sign_out" /> <a
							href="#">${sessionScope.user_login} </a>
						<button type="submit">${exit}</button>
					</form>
				</c:if>
			</div>
		</nav>
		
		<script type="text/javascript">
		document.getElementById('url').value = window.location.pathname;
		</script>
		<script type="text/javascript">
		document.getElementById('url2').value = window.location.pathname;
		</script>
		
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