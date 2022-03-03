package by.epam.university.controller.command.ajax.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.university.controller.command.ajax.AjaxCommand;
import by.epam.university.entity.Subject;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class GetSubject implements AjaxCommand {

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
     //String filter = request.getParameter(FilterParameterName.FILTER_SCHOOL);
		
		String answer = null;
		
		Gson gson = new Gson();
		
		AdminService admintService = ServiceFactory.getInstance().getAdminService();
		
		List<Subject> subjects = null;
		
		try {
			
			subjects = admintService.getAllSubject();
			
			answer = gson.toJson(subjects);
		} catch (ServiceException e) {
			response.setStatus(500);
		}
		
		return answer;
	}

}
