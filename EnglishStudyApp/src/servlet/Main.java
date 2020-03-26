package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		//Themeボタンが押された時
		if ("Theme1".equals(request.getParameter("action"))) {
			if(session.getAttribute("Theme") == null)
			{session.setAttribute("Theme", 3);}else
			{
				int i = (int) session.getAttribute("Theme");
				i =  i < 3 ? ++i:  1;
				session.setAttribute("Theme", i);
			}
		}

		//ステータスをメインモードに変更
		session.setAttribute("status", "inMain");

		//ログインしているならメイン画面へ
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}
