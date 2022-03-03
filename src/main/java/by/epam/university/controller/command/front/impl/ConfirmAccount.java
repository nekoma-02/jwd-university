package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.util.ArrayList;
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
import by.epam.university.entity.ExamMark;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.Subject;
import by.epam.university.entity.User;
import by.epam.university.service.AdminService;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.mail.MailSender;

public class ConfirmAccount implements Command {

	private static Logger logger = LogManager.getLogger();
	private static final String ADMIN_PAGE = "/Controller?command=admin_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		UserService userService = ServiceFactory.getInstance().getUserService();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		HttpSession session = request.getSession();
		MailSender mail = MailSender.getInstance();

		int idApplication = (Integer) session.getAttribute(SessionParameterName.APPLICATION_ID);

		int idSpecialty = Integer.parseInt(request.getParameter(RequestParameterName.SPECIALTY_ID));

		String[] idSubjectArray = request.getParameterValues(RequestParameterName.SUBJECT_ID);
		String[] mark = request.getParameterValues(RequestParameterName.MARK);
		


		try {

			List<ExamMark> examMark = new ArrayList<ExamMark>();

			int index = 0;
			if (idSubjectArray.length == mark.length) {
				for (String item : idSubjectArray) {
					int idSubject = adminService.getIdbySubjectAndSpecialty(Integer.parseInt(item), idSpecialty);
					examMark.add(new ExamMark(idSubject, Integer.parseInt(mark[index])));
					index++;
				}
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "not all marks are filled");
				forwardTo(request, response, JSPPageName.USER_PAGE);
			}

			Application app = appService.applicationById(idApplication);
			User user = userService.userById(app.getUser().getId());
		
			boolean isValidMark = true;
			
			for (ExamMark item : examMark) {
				if (item.getMark() < 0 || item.getMark() > 10 ) {
					isValidMark = false;
				}
			}
			
			
			if (!isValidMark) {

				Specialty spec = appService.getSpecialtyById(app.getSpecialties().getId());
				School school = appService.getSchoolById(app.getSchool().getId());
				Privilege privilege = appService.getPrivilegeById(app.getPrivilege().getId());

				request.setAttribute(RequestParameterName.APPLICATION, app);
				request.setAttribute(RequestParameterName.PRIVILEGE, privilege);
				request.setAttribute(RequestParameterName.SPECIALTY, spec);
				request.setAttribute(RequestParameterName.SCHOOL, school);
				request.setAttribute(RequestParameterName.USER_INFO, user);
				List<Subject> subjects = adminService.getSubjectBySpecialtyId(app.getSpecialties().getId());
				request.setAttribute(RequestParameterName.SUBJECTS, subjects);
				request.setAttribute(RequestParameterName.RESULT_INFO, "failed to add mark");
				forwardTo(request, response, JSPPageName.USER_PAGE);
			} else {
				
				List<ExamMark> isExistMark = adminService.getAllMarksByApplication(idApplication);

				for (ExamMark item : examMark) {

					
					if (isExistMark == null || isExistMark.size() == 0) {
						adminService.addMark(idApplication, item.getMark(), item.getIdSubject());
					} else {
						adminService.updateMark(idApplication, item.getMark(), item.getIdSubject());
					}
					
					
				}
				
				boolean isConfirm = adminService.confirmApplication(idApplication);

				if (isConfirm) {
					mail.sendMail(user, "Здарствуй, " + user.getName() + ". Ваше заявление было подтвержденно");
					response.sendRedirect(request.getContextPath() + ADMIN_PAGE);
				} else {

					request.setAttribute(RequestParameterName.RESULT_INFO, "failed to confirm");
					forwardTo(request, response, JSPPageName.USER_PAGE);
				}
			}

		} catch (ServiceException | ForwardException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}

	}

}
