package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
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
import by.epam.university.service.AdminService;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ApplicationExistsException;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.validator.ApplicationValidator;
import by.epam.university.service.validator.UserValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class AddApplication implements Command {

	private static Logger logger = LogManager.getLogger();
	
	private static final String USER_PAGE="/Controller?command=show_userpage";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
		UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
		ApplicationValidator appValidator = ValidatorFactory.getInstance().getApplicationValidator();

		int idUser = (Integer) session.getAttribute(SessionParameterName.USER_ID);
		String idSpecialty = request.getParameter(RequestParameterName.SPECIALTY);
		String idPrivilege = request.getParameter(RequestParameterName.PRIVILEGE);
		String idSchool = request.getParameter(RequestParameterName.SCHOOL);
		String idDocument = request.getParameter(RequestParameterName.ID_DOCUMENT);
		String adres = request.getParameter(RequestParameterName.ADRES);
		String name = request.getParameter(RequestParameterName.NAME);
		String secondName = request.getParameter(RequestParameterName.SECOND_NAME);
		String lastName = request.getParameter(RequestParameterName.LAST_NAME);
		String email = request.getParameter(RequestParameterName.EMAIL);
		String certificate = request.getParameter(RequestParameterName.CERTIFICATE);
		String gender = request.getParameter(RequestParameterName.GENDER);
		String maritalStatus = request.getParameter(RequestParameterName.MARITAL_STATUS);
		String placeOfBirth = request.getParameter(RequestParameterName.PLACE_OF_BIRTH);
		String dateOfBirth = request.getParameter(RequestParameterName.DATE_OF_BIRTH);
		String typeDocument = request.getParameter(RequestParameterName.TYPE_DOCUMENT);
		String seriesPassport = request.getParameter(RequestParameterName.SERIES_PASSPORT);
		String numberPassport = request.getParameter(RequestParameterName.NUMBER_PASSPORT);
		String issuedBy = request.getParameter(RequestParameterName.ISSUED_BY);
		String endStudyDate = request.getParameter(RequestParameterName.END_STUDY_DATE);
		
		
		boolean isCreateApp = false;
		
		try {
			
			
			List<String> validation = userValidator.validate(name, secondName, lastName, email, gender, maritalStatus, placeOfBirth);
			validation.addAll(appValidator.validate(adres, Integer.parseInt(certificate), typeDocument, idDocument, seriesPassport, Integer.parseInt(numberPassport), issuedBy, endStudyDate));
		
			
			if (validation.size() == 0 || validation == null) {
				
				Application application = new Application(adres, Integer.parseInt(certificate), new Privilege(Integer.parseInt(idPrivilege)), new User(idUser), new School(Integer.parseInt(idSchool)), new Specialty(Integer.parseInt(idSpecialty)), false, typeDocument, idDocument, seriesPassport, Integer.parseInt(numberPassport), issuedBy, Date.valueOf(endStudyDate));
				User user = new User(idUser,name,secondName,lastName,email,gender,maritalStatus, placeOfBirth, Date.valueOf(dateOfBirth));
				
				isCreateApp = appService.createApplication(application, user);

				int idApplication = appService.ApplicationByUserId(idUser).getId();
				session.setAttribute(SessionParameterName.APPLICATION_ID, idApplication);
				adminService.createResult(idApplication);
				response.sendRedirect(request.getContextPath() + USER_PAGE); 
				
				if (!isCreateApp) {
					request.setAttribute(RequestParameterName.RESULT_INFO, "create user application is not correct");
					forwardTo(request, response, JSPPageName.ERROR_PAGE);
				}

				
			} else {
				
				List<Faculty> faculties = appService.getAllFaculties();
				List<TypeStudy> typesStudy = appService.getAllTypesStudy();
				List<School> schools = appService.getAllSchools();
				List<Privilege> privileges = appService.getAllPrivileges();
				
				request.setAttribute(RequestParameterName.SCHOOLS, schools);
				
				request.setAttribute(RequestParameterName.PRIVILEGES, privileges);
				request.setAttribute(RequestParameterName.FACULTY, faculties);
				request.setAttribute(RequestParameterName.TYPE_STUDY, typesStudy);
				
				request.setAttribute(RequestParameterName.USER_INFO, new User(idUser,name,secondName,lastName,email,gender,maritalStatus, placeOfBirth, Date.valueOf(dateOfBirth)));
				request.setAttribute(RequestParameterName.APPLICATION, new Application(adres, Integer.parseInt(certificate), new Privilege(Integer.parseInt(idPrivilege)), new User(idUser), new School(Integer.parseInt(idSchool)), new Specialty(Integer.parseInt(idSpecialty)), false, typeDocument, idDocument, seriesPassport, Integer.parseInt(numberPassport), issuedBy, Date.valueOf(endStudyDate)));
				
				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}
				forwardTo(request, response, JSPPageName.ADD_APPLICATION_PAGE);
		 
			}
			
			session.setAttribute(SessionParameterName.QUERY_STRING,request.getQueryString());
			
		} catch (ServiceException | ParseException | ForwardException e ) {
			
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
			
		} catch (ApplicationExistsException e) {
			request.setAttribute(RequestParameterName.RESULT_INFO, "such application already exist! ");
			try {
				forwardTo(request, response, JSPPageName.ADD_APPLICATION_PAGE );
			} catch (ForwardException ex) {
				logger.log(Level.ERROR, ex);
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
		}


	}
	
	}
	


