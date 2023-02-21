package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.impl.security.SecurityController;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoEditNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	private static final String JSP_NEWS_ID = "id";
	private static final String JSP_TITLE = "title";
	private static final String JSP_BRIEF = "brief";
	private static final String JSP_CONTENT = "content";
	private static final String JSP_DATE = "date";
	private static final String AUTHER_MESSAGE = "autherMessage";
	private static final String ERROR_MESSAGE = "errorMessage";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession();

		if (SecurityController.isAdminRole(session) == true) {
			int newsId = Integer.parseInt(request.getParameter(JSP_NEWS_ID));
			String title = request.getParameter(JSP_TITLE);
			String brief = request.getParameter(JSP_BRIEF);
			String content = request.getParameter(JSP_CONTENT);
			String date = request.getParameter(JSP_DATE);

			News news = new News(newsId, title, brief, content, date);

			try {
				service.update(news);
				session.setAttribute(AUTHER_MESSAGE, "news edited successfully");
				response.sendRedirect("controller?command=go_to_news_list");
			} catch (ServiceException e) {
				session.setAttribute(ERROR_MESSAGE, "error when trying to edit news");
				response.sendRedirect("controller?command=go_to_error_page");
			}
		} else {
			session.setAttribute(ERROR_MESSAGE, "user does not have permission to edit");
			response.sendRedirect("controller?command=go_to_error_page");
		}
	}
}
