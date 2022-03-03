package by.epam.university.controller.command.front.impl;

import java.io.IOException;

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
import by.epam.university.service.AdminService;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class AcceptStudent implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		HttpSession session = request.getSession();

		int idApplication = (Integer) session.getAttribute(SessionParameterName.APPLICATION_ID);
		int totalScore = Integer.parseInt(request.getParameter(RequestParameterName.TOTAL_SCORE));
		String accept = request.getParameter(RequestParameterName.ACCEPT);

		boolean isAccept = false;

		if (accept.equals("true")) {
			isAccept = true;
		} else {
			isAccept = false;
		}

		try {
			boolean isAccepted = adminService.acceptStudent(totalScore, isAccept, idApplication);
			
			if (isAccepted) {
				int userId = appService.applicationById(idApplication).getUser().getId();
				response.sendRedirect(request.getContextPath() + "/Controller?command=show_userpage&user_id=" + userId + "&application_id=" + idApplication);
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "ошибка подтверждения");
				forwardTo(request, response, JSPPageName.USER_PAGE);
			}

		} catch (ServiceException | ForwardException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}

	}

}
