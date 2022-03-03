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
	<fmt:message bundle="${loc}" key="local.admin_nav.addition"
	var="addition" />
	<fmt:message bundle="${loc}" key="local.admin_nav.faculty"
	var="faculty" />
	<fmt:message bundle="${loc}" key="local.admin_nav.specialty"
	var="specialty" />
	<fmt:message bundle="${loc}" key="local.admin_nav.subject"
	var="subject" />
	<fmt:message bundle="${loc}" key="local.admin_nav.viewing"
	var="viewing" />
	<fmt:message bundle="${loc}" key="local.admin_nav.type_study"
	var="type_study" />
	<fmt:message bundle="${loc}" key="local.admin_nav.privilege"
	var="privilege" />
	<fmt:message bundle="${loc}" key="local.admin_nav.school"
	var="school" />
	<fmt:message bundle="${loc}" key="local.admin_nav.administrator"
	var="administrator" />

	
	








</head>
<body>
	<div class="col-2 bg-dark" id="admin-nav">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/Controller?command=admin_page">${viewing}</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="collapse"
				href="#collapseExample" role="button" aria-expanded="false"
				aria-controls="collapseExample">${addition}</a>
				<div class="collapse" id="collapseExample">
					<div class="card card-body bg-dark">
						<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/jsp/admin/add_moderator.jsp">${administrator}</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/jsp/admin/add_faculty.jsp">${faculty}</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/jsp/admin/add_school.jsp">${school}</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/jsp/admin/add_typeStudy.jsp">${type_study}</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/Controller?command=SHOW_ADD_SPECIALTY_PAGE">${specialty}</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/jsp/admin/add_privilege.jsp">${privilege}</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/jsp/admin/add_subject.jsp">${subject}</a></li>
						</ul>
					</div>
				</div></li>
		</ul>
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