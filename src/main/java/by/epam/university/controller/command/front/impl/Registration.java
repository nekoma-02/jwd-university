package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.command.front.ForwardException;
import by.epam.university.controller.parameter.JSPPageName;
import by.epam.university.controller.parameter.RequestParameterName;
import by.epam.university.entity.Role;
import by.epam.university.entity.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.UserExistsException;
import by.epam.university.service.validator.UserValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class Registration implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService service = ServiceFactory.getInstance().getUserService();

		String name = request.getParameter(RequestParameterName.NAME);
		String secondName = request.getParameter(RequestParameterName.SECOND_NAME);
		String lastName = request.getParameter(RequestParameterName.LAST_NAME);
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String email = request.getParameter(RequestParameterName.EMAIL);
		Role role = Role.valueOf(request.getParameter(RequestParameterName.USER_ROLE).toUpperCase());

		try {
			
			UserValidator validator = ValidatorFactory.getInstance().getUserValidator();
			List<String> validation = validator.validate(name, secondName, lastName, email);
			validation.addAll(validator.validate(login, password));

			if (validation.size() == 0 || validation == null) {
				
				User user = new User(name, secondName, lastName, login, password, email, role);
				service.registration(user);
				response.sendRedirect(JSPPageName.INDEX_PAGE);
				
			} else {
				
				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}
				
				forwardTo(request, response, JSPPageName.REGISTRATION_PAGE);
			}

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		} catch (UserExistsException e) {
			request.setAttribute(RequestParameterName.RESULT_INFO, "such user already exist! ");
			try {
				forwardTo(request, response, JSPPageName.REGISTRATION_PAGE);
			} catch (ForwardException ex) {
				logger.log(Level.ERROR, ex);
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
			
		}

	}

}
