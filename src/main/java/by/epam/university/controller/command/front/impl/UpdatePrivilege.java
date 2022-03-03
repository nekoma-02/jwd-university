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
import by.epam.university.entity.Privilege;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.PrivilegeExistsException;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.validator.PrivilegeValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class UpdatePrivilege implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = ServiceFactory.getInstance().getAdminService();
		HttpSession session = request.getSession();
		
		String id = request.getParameter(RequestParameterName.PRIVILEGE_ID);
		String privilegeName = request.getParameter(RequestParameterName.PRIVILEGE_NAME);

		PrivilegeValidator validator = ValidatorFactory.getInstance().getPrivilegeValidator();
		List<String> validation = validator.validate(privilegeName);
		
		try {
			if (validation.size() != 0) {
				
				Privilege privilege = new Privilege(Integer.parseInt(id), privilegeName);

				boolean isUpdate = service.updatePrivilege(privilege);
				
				if (isUpdate) {
					response.sendRedirect(JSPPageName.UPDATE_PRIVILEGE);
				} else {
					request.setAttribute(RequestParameterName.RESULT_INFO, "wrong update");
					forwardTo(request, response, JSPPageName.UPDATE_PRIVILEGE);
				}
				
			} else {

				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}

				forwardTo(request, response, JSPPageName.UPDATE_PRIVILEGE);
			}

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		} catch (PrivilegeExistsException e) {
			request.setAttribute(RequestParameterName.RESULT_INFO, "such privilege already exist! ");
			try {
				forwardTo(request, response, JSPPageName.UPDATE_PRIVILEGE);
			} catch (ForwardException ex) {
				logger.log(Level.ERROR, ex);
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
		}

	}

}
