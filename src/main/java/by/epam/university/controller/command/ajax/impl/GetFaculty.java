package by.epam.university.controller.command.ajax.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.university.controller.command.ajax.AjaxCommand;
import by.epam.university.controller.command.ajax.FilterParameterName;
import by.epam.university.entity.Faculty;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class GetFaculty implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String filter = request.getParameter(FilterParameterName.FILTER_FACULTY);
		
		String answer = null;
		
		Gson gson = new Gson();
		
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		
		List<Faculty> facultyList = null;
		
		try {
			
			if (filter.equals(FilterParameterName.FILTER_ALL)) {
				facultyList = appService.getAllFaculties();
			}
			
			
			
			answer = gson.toJson(facultyList);
		} catch (ServiceException e) {
			response.setStatus(500);
		}
		
		return answer;
	}

}
