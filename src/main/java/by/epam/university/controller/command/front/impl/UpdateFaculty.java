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
import by.epam.university.entity.Faculty;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.FacultyExistsException;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.validator.FacultyValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class UpdateFaculty implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = ServiceFactory.getInstance().getAdminService();
		HttpSession session = request.getSession();

		String id = request.getParameter(RequestParameterName.FACULTY_ID);
		String facultyName = request.getParameter(RequestParameterName.FACULTY_NAME);

		FacultyValidator validator = ValidatorFactory.getInstance().getFacultyValidator();
		List<String> validation = validator.validate(facultyName);
		try {

			if (validation.size() != 0) {
				Faculty faculty = new Faculty(Integer.parseInt(id), facultyName);

				boolean isUpdate = service.updateFaculty(faculty);
				
				if (isUpdate) {
					response.sendRedirect(JSPPageName.UPDATE_FACULTY);
				} else {
					request.setAttribute(RequestParameterName.RESULT_INFO, "wrong update");
					forwardTo(request, response, JSPPageName.UPDATE_FACULTY);
				}
				
			} else {
				
				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}

				forwardTo(request, response, JSPPageName.UPDATE_FACULTY);
			}

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		} catch (FacultyExistsException e) {
			request.setAttribute(RequestParameterName.RESULT_INFO, "such faculty already exist! ");
			try {
				forwardTo(request, response, JSPPageName.UPDATE_FACULTY);
			} catch (ForwardException ex) {
				logger.log(Level.ERROR, ex);
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
		}

	}

}
