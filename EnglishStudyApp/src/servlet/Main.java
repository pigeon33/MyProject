package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Examinees;

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

		System.out.println("Main:doGet");
		//ろぐいんしているか確認するためセッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		Examinees loginUser = (Examinees) session.getAttribute("loginExaminee");

		if (loginUser == null) {
			//ログインしてないならログイン画面へ
			response.sendRedirect("/EnglishStudyApp/");
		} else {
			//ログインしているならメイン画面へ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}

		//問題番号を０で初期化
		session.setAttribute("questionNum", 0);
	}

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		//リクエストパラメータの取得
//		request.setCharacterEncoding("UTF-8");
//		String text = request.getParameter("text");
//
//		//メイン画面にフォワード
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
//		dispatcher.forward(request, response);
//	}
}
