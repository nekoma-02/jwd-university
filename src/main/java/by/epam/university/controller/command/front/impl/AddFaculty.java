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
import by.epam.university.entity.Faculty;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.FacultyExistsException;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.validator.FacultyValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class AddFaculty implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		String name = request.getParameter(RequestParameterName.FACULTY_NAME);

		FacultyValidator validator = ValidatorFactory.getInstance().getFacultyValidator();
		List<String> validation = validator.validate(name);

		try {

			if (validation.size() != 0) {
				Faculty faculty = new Faculty(name);
				boolean isInsert = adminService.insertFaculty(faculty);
				if (isInsert) {
					response.sendRedirect(JSPPageName.ADD_FACULTY);
				} else {
					request.setAttribute(RequestParameterName.RESULT_INFO, "wrong added");
					forwardTo(request, response, JSPPageName.ADD_FACULTY);
				}
			} else {

				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}

				forwardTo(request, response, JSPPageName.ADD_FACULTY);
			}
			

		} catch (ServiceException | ForwardException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		} catch (FacultyExistsException e) {
			request.setAttribute(RequestParameterName.RESULT_INFO, "such faculty already exist! ");
			try {
				forwardTo(request, response, JSPPageName.ADD_FACULTY);
			} catch (ForwardException ex) {
				logger.log(Level.ERROR, ex);
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
		}

	}

}
