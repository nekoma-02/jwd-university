package by.epam.university.controller.command.ajax.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.university.controller.command.ajax.AjaxCommand;
import by.epam.university.controller.command.ajax.FilterParameterName;
import by.epam.university.entity.Application;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class GetApplication implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String filter = request.getParameter(FilterParameterName.FILTER_APPLICATION);
		
		String answer = null;
		
		Gson gson = new Gson();
		
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		List<Application> listApp = null;
		
		try {
			
			if (filter.equals(FilterParameterName.FILTER_ALL)) {
				listApp = adminService.getAllApplication();
			}
			
			if (filter.equals(FilterParameterName.FILTER_CONFIRMED)) {
				listApp = adminService.getAllConfirmedApplication();
			}
			
			if (filter.equals(FilterParameterName.FILTER_UNCONFIRMED)) {
				listApp = adminService.getAllUnconfirmedApplication();
			}
			
			
			answer = gson.toJson(listApp);
		} catch (ServiceException e) {
			response.setStatus(500);
		}
		
		return answer;
	}

}
