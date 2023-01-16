package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToErrorPage implements Command {

	private static final String AUTHER_ERROR = "AuthenticationError";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String error = (String) session.getAttribute(AUTHER_ERROR);

        if (error == null) {
            session.setAttribute(AUTHER_ERROR,"no such command error");
        }
        request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
	}
}