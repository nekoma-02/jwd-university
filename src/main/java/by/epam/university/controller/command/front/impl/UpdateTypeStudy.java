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
import by.epam.university.entity.TypeStudy;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.TypeStudyExistsException;
import by.epam.university.service.validator.TypeStudyValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class UpdateTypeStudy implements Command {

	private static Logger logger = LogManager.getLogger();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = ServiceFactory.getInstance().getAdminService();
		HttpSession session = request.getSession();
		
		String id =  request.getParameter(RequestParameterName.TYPE_STUDY_ID);
		String typeName = request.getParameter(RequestParameterName.TYPE_STUDY_NAME);
		
		TypeStudyValidator validator = ValidatorFactory.getInstance().getTypeStudyValidator();
		List<String> validation = validator.validate(typeName);
		
		try {
			
			if (validation.size() != 0) {
			
			TypeStudy typeStudy = new TypeStudy(Integer.parseInt(id), typeName);

			boolean isUpdate = service.updateTypeStudy(typeStudy);
			if (isUpdate) {
				response.sendRedirect(JSPPageName.UPDATE_TYPE_STUDY);
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "wrong update");
				forwardTo(request, response, JSPPageName.UPDATE_TYPE_STUDY);
			}
			
			} else {

				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}

				forwardTo(request, response, JSPPageName.UPDATE_SCHOOL);
			}
			
			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		} catch (TypeStudyExistsException e) {
			request.setAttribute(RequestParameterName.RESULT_INFO, "such type study already exist! ");
			try {
				forwardTo(request, response, JSPPageName.ADD_TYPE_STUDY);
			} catch (ForwardException ex) {
				logger.log(Level.ERROR, ex);
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
		}
		
	}

}
