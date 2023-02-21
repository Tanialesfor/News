package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

public class DoLocalization implements Command {

	private static final String PREVIOUS_REQUEST_NAME = "previous request name";
	private static final String PREVIOUS_REQUEST_VALUE = "previous request value";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String LOCAL = "local";

//	@Override
//	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getSession(true).setAttribute("local", request.getParameter("local"));
//		response.sendRedirect("controller?command=go_to_news_list");
//	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(LOCAL, request.getParameter(LOCAL));

		try {
			String previousRequestName = (String) session.getAttribute(PREVIOUS_REQUEST_NAME);
			Map <String, String[]> paramsReqMap = (Map <String, String[]>) session.getAttribute(PREVIOUS_REQUEST_VALUE);
			String previousRequest = getRequest(previousRequestName, paramsReqMap);
			if (previousRequest == null) {
				session.setAttribute(ERROR_MESSAGE, "error when trying to get previous request");
				response.sendRedirect("controller?command=go_to_error_page");
			} else {
				response.sendRedirect(previousRequest);
			}
		} catch (Exception e) {
			session.setAttribute(ERROR_MESSAGE, "error when trying to get previous request");
			response.sendRedirect("controller?command=go_to_error_page");
		}
	}

	private static String getRequest(String requestName, Map<String, String[]> requestParams) {
		StringBuilder previousRequest = new StringBuilder(requestName);
		try {
			Map<String, String[]> paramsReqMap = requestParams;
			previousRequest.append("?");

			for (var arrayParam : paramsReqMap.entrySet()) {
				for (var param : arrayParam.getValue()) {
					previousRequest.append(arrayParam.getKey()).append("=").append(param).append("&");
				}
			}
			previousRequest.deleteCharAt(previousRequest.length() - 1);
			return previousRequest.toString();
		} catch (Exception e) {
			return null;
		}
	}
}