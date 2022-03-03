package by.epam.university.controller.command.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.parameter.JSPPageName;
import by.epam.university.controller.parameter.SessionParameterName;

public class SignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        session.removeAttribute(SessionParameterName.USER_ID);
        session.removeAttribute(SessionParameterName.USER_ID_EDIT);
        session.removeAttribute(SessionParameterName.USER_LOGIN);
        session.removeAttribute(SessionParameterName.USER_ROLE);
        session.removeAttribute(SessionParameterName.APPLICATION_ID);
        response.sendRedirect(JSPPageName.INDEX_PAGE);
		
	}

}
