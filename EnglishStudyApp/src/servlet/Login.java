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
import model.entity.LoginLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String str = "/";

		RequestDispatcher dispatcher = null;

		//examineeインスタンスの生成
		Examinees examinee = new Examinees(name, pass);

		//examinee情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("loginExaminees", examinee);

		//ログイン処理クラスをインスタンス化
		LoginLogic loginLogic = new LoginLogic();

		//何も選択されてないときは空欄を表示
		request.setAttribute("result", "");
		System.out.println("test");

		//loginの選択がされたならログイン処理
		if ("ログイン".equals(request.getParameter("action"))) {
			//ログイン処理
			boolean isLogin = loginLogic.execute(examinee);

			//ログイン成功時の処理
			if (isLogin) {
				str = "/WEB-INF/jsp/main.jsp";
			}
			//ログイン失敗したらログイン失敗をリクエストスコープにセット
			else {
				request.setAttribute("result", "ログインに失敗しました");
			}
		}

		//新規登録処理
		if ("新規登録".equals(request.getParameter("action"))) {
			request.setAttribute("result", "新規登録しました");
		}
		dispatcher = request.getRequestDispatcher(str);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
