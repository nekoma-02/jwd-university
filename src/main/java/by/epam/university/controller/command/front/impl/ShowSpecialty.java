package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import by.epam.university.entity.Specialty;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class ShowSpecialty implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ApplicationService service = ServiceFactory.getInstance().getApplicationService();
		HttpSession session = request.getSession();
		try {

			List<Specialty> specialties = service.getAllSpecialties();
			List<String> faculties = deleteDuplicates(specialties);

			request.setAttribute(RequestParameterName.SPECIALTIES, specialties);
			request.setAttribute(RequestParameterName.FACULTIES, faculties);

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());
			forwardTo(request, response, JSPPageName.INFO_PAGE);

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		}

	}
	
	private static List<String> deleteDuplicates(List<Specialty> list) {
		
		List<String> faculties = new ArrayList<String>();
		
		for (Specialty item : list) {
			faculties.add(item.getFaculty().getName());
		}
		
		Set<String> temp = new HashSet<String>(faculties);
		faculties.clear();
		faculties = new ArrayList<String>(temp);
		
		return faculties;
	}

}
