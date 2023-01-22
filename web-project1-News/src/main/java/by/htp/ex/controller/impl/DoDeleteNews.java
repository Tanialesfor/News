package by.htp.ex.controller.impl;

import java.io.IOException;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.impl.NewsServiceImpl;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.UserSecurityValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoDeleteNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();
	private final UserSecurityValidation userSecurityValidation = ValidationProvider.getInstance().getUserSecurityValidation();
	
	private static final String JSP_NEWS_ID = "id";
	private static final String AUTHER_MESSAGE = "autherMessage";
	private static final String ERROR_MESSAGE = "errorMessage";
    	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession();
		
		if (userSecurityValidation.isAdminRole(session)==true) { 
           String[] newsId = request.getParameterValues(JSP_NEWS_ID);
            if (newsId != null) {
            	try {
            		service.delete(newsId); 
              		
             	} catch (ServiceException e) {
             		session.setAttribute(ERROR_MESSAGE,"command delete error");
             		response.sendRedirect("controller?command=go_to_error_page");
             		
            	}
            	request.setAttribute(AUTHER_MESSAGE, "news successfully deleted");   	
            	response.sendRedirect("controller?command=go_to_news_list");
            } else {
                session.setAttribute(ERROR_MESSAGE,"news do not selected for deletion");
                response.sendRedirect("controller?command=go_to_error_page");
            }    
		 } else {
            session.setAttribute(ERROR_MESSAGE,"user does not have permission to delete");
            response.sendRedirect("controller?command=go_to_error_page");
        }   	          
	}
}
