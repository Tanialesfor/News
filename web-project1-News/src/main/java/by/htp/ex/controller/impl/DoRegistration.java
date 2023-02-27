package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	private static final String JSP_NAME_PARAM = "name";
	private static final String JSP_SURNAME_PARAM = "surname";
	private static final String JSP_BIRTHDAY_PARAM = "birthday";
	private static final String JSP_EMAIL_PARAM = "email";
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String AUTHER_MESSAGE_REG = "autherMessageReg";
	private static final String REGISTRATION_COMPLETED_SUCCESSFULLY = "local.doRegistration.auther.message.text";
	private static final String AUTHER_INF_REG = "autherInfReg";
	private static final String USER_ALREADY_EXIST = "local.doRegistration.auther.inf.text";
	private static final String PRESENTATION = "presentation";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name;
		String surname;
		String birthday;
		String email;
		String login;
		String password;

		name = request.getParameter(JSP_NAME_PARAM);
		surname = request.getParameter(JSP_SURNAME_PARAM);
		birthday = request.getParameter(JSP_BIRTHDAY_PARAM);
		email = request.getParameter(JSP_EMAIL_PARAM);
		login = request.getParameter(JSP_LOGIN_PARAM);
		password = request.getParameter(JSP_PASSWORD_PARAM);

		try {
			NewUserInfo user = new NewUserInfo(name, surname, birthday, email, login, password);
			if (service.registration(user)) {
				request.getSession().setAttribute(AUTHER_MESSAGE_REG, REGISTRATION_COMPLETED_SUCCESSFULLY);
				response.sendRedirect("controller?command=go_to_news_list");
			} else {
				request.getSession().setAttribute(AUTHER_INF_REG, USER_ALREADY_EXIST);
				request.setAttribute(PRESENTATION, "registration");
				request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			request.getSession().setAttribute(ERROR_MESSAGE, e.getMessage());
			response.sendRedirect("controller?command=go_to_error_page");
		}

	}
}
