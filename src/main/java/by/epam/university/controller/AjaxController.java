package by.epam.university.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.university.controller.command.CommandProvided;
import by.epam.university.controller.command.ajax.AjaxCommand;
import by.epam.university.controller.parameter.RequestParameterName;

/**
 * Servlet implementation class AjaxController
 */
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doCommand(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doCommand(request, response);
	}
	
	private void doCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ajaxCommandName = request.getParameter(RequestParameterName.COMMAND);
        CommandProvided ajaxCommandProvider = CommandProvided.getInstance();
        AjaxCommand ajaxCommand = ajaxCommandProvider.getAjaxCommand(ajaxCommandName.toUpperCase());

        String jsonAnswer = ajaxCommand.execute(request, response);
        
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonAnswer);

    }

}
