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
import by.epam.university.entity.Application;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.TypeStudy;
import by.epam.university.entity.User;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;

public class ShowAddApplicationPage implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = ServiceFactory.getInstance().getUserService();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute(SessionParameterName.USER_ID);
		Object idApplication = session.getAttribute(SessionParameterName.APPLICATION_ID);

		try {
			User user = userService.userById(userId);
			request.setAttribute(RequestParameterName.USER_INFO, user);
			
			Application app = null;
			School school = null;
			
			if (idApplication != null) {
				app = appService.ApplicationByUserId(userId);
				Specialty spec = appService.getSpecialtyById(app.getSpecialties().getId());
				school = appService.getSchoolById(app.getSchool().getId());
				Privilege privilege = appService.getPrivilegeById(app.getPrivilege().getId());
				
				request.setAttribute(RequestParameterName.APPLICATION, app);
				request.setAttribute(RequestParameterName.PRIVILEGE, privilege);
				request.setAttribute(RequestParameterName.SCHOOL, school);
				request.setAttribute(RequestParameterName.SPECIALTY, spec);
				
			}
			
			List<Faculty> faculties = appService.getAllFaculties();
			List<TypeStudy> typesStudy = appService.getAllTypesStudy();
			List<School> schools = appService.getAllSchools();
			List<Privilege> privileges = appService.getAllPrivileges();
			
			request.setAttribute(RequestParameterName.SCHOOLS, schools);
			
			request.setAttribute(RequestParameterName.PRIVILEGES, privileges);
			request.setAttribute(RequestParameterName.FACULTY, faculties);
			request.setAttribute(RequestParameterName.TYPE_STUDY, typesStudy);
			
			session.setAttribute(SessionParameterName.QUERY_STRING,request.getQueryString());
			forwardTo(request, response, JSPPageName.ADD_APPLICATION_PAGE);
			
		} catch (ServiceException | ForwardException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
		
	}

}
