package by.epam.university.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.university.controller.command.CommandProvided;
import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.parameter.RequestParameterName;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doCommand(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doCommand(request, response);
	}

	private void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(RequestParameterName.COMMAND);
		Command command = CommandProvided.getInstance().getFrontCommand(commandName);
		command.execute(request, response);

	}

}
