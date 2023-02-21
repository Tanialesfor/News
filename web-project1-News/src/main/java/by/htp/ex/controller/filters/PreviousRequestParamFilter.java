package by.htp.ex.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.HttpMethod;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.Map;

public class PreviousRequestParamFilter implements Filter {

	private static final String PREVIOUS_REQUEST_NAME = "previous request name";
	private static final String PREVIOUS_REQUEST_VALUE = "previous request value";
	private static final String ACTUAL_REQUEST_NAME = "actual request name";
	private static final String ACTUAL_REQUEST_VALUE = "actual request value";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getMethod().equals(HttpMethod.GET)) {
			HttpSession session = req.getSession();
			Map<String, String[]> paramsReqMap = req.getParameterMap();

			if (!paramsReqMap.isEmpty()) {

				session.setAttribute(PREVIOUS_REQUEST_NAME, session.getAttribute(ACTUAL_REQUEST_NAME));
				session.setAttribute(PREVIOUS_REQUEST_VALUE, session.getAttribute(ACTUAL_REQUEST_VALUE));

				session.setAttribute(ACTUAL_REQUEST_NAME, req.getRequestURL().toString());
				session.setAttribute(ACTUAL_REQUEST_VALUE, paramsReqMap);

				if (session.getAttribute(PREVIOUS_REQUEST_NAME) == null) {
					session.setAttribute(PREVIOUS_REQUEST_NAME, session.getAttribute(ACTUAL_REQUEST_NAME));
					session.setAttribute(PREVIOUS_REQUEST_VALUE, session.getAttribute(ACTUAL_REQUEST_VALUE));
				}
			}
		}
		chain.doFilter(request, response);

	}

}
