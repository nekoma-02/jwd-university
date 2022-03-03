package by.epam.university.controller.command.ajax.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.university.controller.command.ajax.AjaxCommand;
import by.epam.university.controller.command.ajax.FilterParameterName;
import by.epam.university.entity.School;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class GetSchool implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String filter = request.getParameter(FilterParameterName.FILTER_SCHOOL);
		
		String answer = null;
		
		Gson gson = new Gson();
		
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		
		List<School> schoolList = null;
		
		try {
			
			if (filter.equals(FilterParameterName.FILTER_ALL)) {
				schoolList = appService.getAllSchools();
			}
			
			answer = gson.toJson(schoolList);
		} catch (ServiceException e) {
			response.setStatus(500);
		}
		
		return answer;
	}

}
