package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.command.front.ForwardException;
import by.epam.university.controller.parameter.JSPPageName;
import by.epam.university.controller.parameter.RequestParameterName;
import by.epam.university.controller.parameter.SessionParameterName;
import by.epam.university.entity.Application;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class AdminPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		HttpSession session = request.getSession();
		
		List<Application> appList = null;
		
		try {
			appList = adminService.getAllApplication();
			
			request.setAttribute(RequestParameterName.APPLICATION, appList);
			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());
			
			forwardTo(request, response, JSPPageName.ADMIN_PAGE);
		} catch (ServiceException | ForwardException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
		
		
	}

}
