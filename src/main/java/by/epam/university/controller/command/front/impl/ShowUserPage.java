package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.command.front.ForwardException;
import by.epam.university.controller.parameter.JSPPageName;
import by.epam.university.controller.parameter.RequestParameterName;
import by.epam.university.controller.parameter.SessionParameterName;
import by.epam.university.entity.Application;
import by.epam.university.entity.ExamMark;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.Role;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.Subject;
import by.epam.university.entity.User;
import by.epam.university.service.AdminService;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;

public class ShowUserPage implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = ServiceFactory.getInstance().getUserService();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		HttpSession session = request.getSession();

		int userId = 0;
		int idApplication = 0;
		int totalScore = 0;

		Role role = (Role) session.getAttribute(SessionParameterName.USER_ROLE);

		if (role == Role.USER) {
			userId = (Integer) session.getAttribute(SessionParameterName.USER_ID);
		}

		if (role == Role.ADMIN) {

			userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
			session.setAttribute(SessionParameterName.USER_ID_EDIT, userId);
			idApplication = Integer.parseInt(request.getParameter(RequestParameterName.APPLICATION_ID));
			session.setAttribute(SessionParameterName.APPLICATION_ID, idApplication);
		}

		try {
			User user = userService.userById(userId);
			Application app = appService.ApplicationByUserId(userId);
			Specialty spec = appService.getSpecialtyById(app.getSpecialties().getId());
			School school = appService.getSchoolById(app.getSchool().getId());
			Privilege privilege = appService.getPrivilegeById(app.getPrivilege().getId());

			request.setAttribute(RequestParameterName.APPLICATION, app);
			request.setAttribute(RequestParameterName.PRIVILEGE, privilege);
			request.setAttribute(RequestParameterName.SPECIALTY, spec);
			request.setAttribute(RequestParameterName.SCHOOL, school);
			request.setAttribute(RequestParameterName.USER_INFO, user);
			List<Subject> subjects = adminService.getSubjectBySpecialtyId(app.getSpecialties().getId());
			request.setAttribute(RequestParameterName.SUBJECTS, subjects);

			if (app.isConfirmation() == true) {
				
				List<ExamMark> examMarks = adminService.getAllMarksByApplication(app.getId());
				boolean isAccepted = adminService.getAcceptStudent(app.getId());

				for (ExamMark examMark : examMarks) {
					totalScore += examMark.getMark()*10;
					
				}
				totalScore += app.getCertificate();

				if (isAccepted) {
					request.setAttribute(RequestParameterName.ACCEPT, "Зачислен");
				} else {
					request.setAttribute(RequestParameterName.ACCEPT, "Не зачислен");
				}
				request.setAttribute(RequestParameterName.MARKS, examMarks);
				request.setAttribute(RequestParameterName.TOTAL_SCORE, totalScore);
			}

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());

			forwardTo(request, response, JSPPageName.USER_PAGE);

		} catch (ServiceException | ForwardException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}

	}

}
