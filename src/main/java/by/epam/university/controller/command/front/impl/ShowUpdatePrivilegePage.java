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
import by.epam.university.entity.Privilege;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class ShowUpdatePrivilegePage implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationService service = ServiceFactory.getInstance().getApplicationService();
		HttpSession session = request.getSession();
		String id = request.getParameter(RequestParameterName.PRIVILEGE_ID);

		try {

			Privilege privilege = service.getPrivilegeById(Integer.parseInt(id));

			request.setAttribute(RequestParameterName.PRIVILEGE_ID, privilege.getId());
			request.setAttribute(RequestParameterName.PRIVILEGE_NAME, privilege.getName());

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());
			forwardTo(request, response, JSPPageName.UPDATE_PRIVILEGE);

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		}
		
	}

}
