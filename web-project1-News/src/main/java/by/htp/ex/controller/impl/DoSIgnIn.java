package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSIgnIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String USER = "user";
	private static final String ACTIVE = "active";
	private static final String NOT_ACTIVE = "not active";
	private static final String ROLE = "role";
	private static final String PERMISSION_ROLE = "permissionRole";
	
	private static final String WRONG_LOGIN_OR_PASSWORD = "local.signIn.auther.error.text";
	private static final String AUTHER_ERROR = "AuthenticationError";
	private static final String ERROR_MESSAGE = "errorMessage";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;

		login = request.getParameter(JSP_LOGIN_PARAM);
		password = request.getParameter(JSP_PASSWORD_PARAM);
		
		try {

			String role = service.signIn(login, password);

			if (!role.equals("guest")) {
				request.getSession(true).setAttribute(USER, ACTIVE);
				request.getSession().setAttribute(ROLE, role);
				request.getSession().setAttribute(PERMISSION_ROLE, service.isPermission(login, password));
				response.sendRedirect("controller?command=go_to_news_list");
			} else {
				request.getSession(true).setAttribute(USER, NOT_ACTIVE);
				request.setAttribute(AUTHER_ERROR, WRONG_LOGIN_OR_PASSWORD);
				request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			}
			
		} catch (ServiceException e) {
			request.getSession().setAttribute(ERROR_MESSAGE, e.getMessage());
			response.sendRedirect("controller?command=go_to_error_page");
		}			
	}
}
