package by.epam.university.controller.command.front.impl;

import java.io.IOException;

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
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class RemoveSubject implements Command {

private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		String idSubject = request.getParameter(RequestParameterName.SUBJECT_ID);

		try {

			
				boolean isRemove = adminService.removeSubject(Integer.parseInt(idSubject));
				if (isRemove) {
					response.sendRedirect(JSPPageName.UPDATE_SUBJECT);
				} else {
					request.setAttribute(RequestParameterName.RESULT_INFO, "wrong remove");
					forwardTo(request, response, JSPPageName.UPDATE_SUBJECT);
				}
			
			

		} catch (ServiceException | ForwardException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
		
	}

}
