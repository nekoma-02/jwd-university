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
import by.epam.university.entity.School;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.SchoolExistsException;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.validator.SchoolValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class AddSchool implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		String name = request.getParameter(RequestParameterName.SCHOOL_NAME);
		String level = request.getParameter(RequestParameterName.SCHOOL_LEVEL);
		String institution = request.getParameter(RequestParameterName.SCHOOL_INSTITUTION);

		SchoolValidator validator = ValidatorFactory.getInstance().getSchoolValidator();
		List<String> validation = validator.validate(name, level, institution);

		try {

			if (validation.size() != 0) {
				
				School school = new School(name, level, institution);
				
				boolean isInsert = adminService.insertSchool(school);
				
				if (isInsert) {
					response.sendRedirect(JSPPageName.ADD_SCHOOL);
				} else {
					request.setAttribute(RequestParameterName.RESULT_INFO, "wrong added");
					forwardTo(request, response, JSPPageName.ADD_SCHOOL);
				}
				
			} else {

				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}

				forwardTo(request, response, JSPPageName.ADD_SCHOOL);
			}
		} catch (ServiceException | ForwardException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		} catch (SchoolExistsException e) {
			request.setAttribute(RequestParameterName.RESULT_INFO, "such school already exist! ");
			try {
				forwardTo(request, response, JSPPageName.ADD_SCHOOL);
			} catch (ForwardException ex) {
				logger.log(Level.ERROR, ex);
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
		}

	}

}
