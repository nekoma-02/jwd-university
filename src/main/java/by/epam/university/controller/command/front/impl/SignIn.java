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
import by.epam.university.entity.Application;
import by.epam.university.entity.User;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;

public class SignIn implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService service = ServiceFactory.getInstance().getUserService();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		HttpSession session = request.getSession();

		
		
		try {
			
			boolean isSignIn = service.signIn(login, password);
				
			if (isSignIn) {
				
				User user = service.userByLogin(login);
				
				Application app = appService.ApplicationByUserId(user.getId());
				
				if (app != null) {
					session.setAttribute(SessionParameterName.APPLICATION_ID, app.getId());
				}
				
				session.setAttribute(SessionParameterName.USER_ID, user.getId());
				session.setAttribute(SessionParameterName.USER_ROLE, user.getRole());
				session.setAttribute(SessionParameterName.USER_LOGIN, user.getLogin());
				
				response.sendRedirect(JSPPageName.INDEX_PAGE); 
				
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "invalid authorization data");
				forwardTo(request, response, JSPPageName.LOGIN_PAGE);
			}
				
			
		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		}

	}

}
