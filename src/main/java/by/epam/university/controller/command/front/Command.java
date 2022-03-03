package by.epam.university.controller.command.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	default void forwardTo(HttpServletRequest request, HttpServletResponse response, String page) throws IOException, ServletException, ForwardException{

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);

        
            if (requestDispatcher != null) {
                requestDispatcher.forward(request, response);
            } else {
            	throw new ForwardException("forward to page error! requestDispatcher = null.");
            }
       

    }

}
