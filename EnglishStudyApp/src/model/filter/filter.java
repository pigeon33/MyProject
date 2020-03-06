package model.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Examinees;

/**
 * Servlet Filter implementation class filter
 */
@WebFilter("/*")
public class filter implements Filter {
	private static final String[] URL_EXCLUDES = { "/EnglishStudyApp/","/EnglishStudyApp/Login" };

	/**
	 * Default constructor.
	 */
	public filter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpSession session = httpReq.getSession();
		Examinees loginUser = (Examinees) session.getAttribute("loginExaminee");

		if (!isExcludeUrl(httpReq)) {
			if (session == null || loginUser == null) {
				/* まだ認証されていない */
				httpRes.sendRedirect(httpReq.getContextPath());
				return;
			}
		}
		chain.doFilter(request, response);
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * リダイレクト対象外のURLがチェック.
	 * @return 対象外ならtrue
	 */
	private boolean isExcludeUrl(HttpServletRequest request) {
		String target = request.getRequestURI();
		for (String exclude : URL_EXCLUDES) {
			if (target.equals(exclude)) {
				return true;
			}
		}
		return false;
	}
}
