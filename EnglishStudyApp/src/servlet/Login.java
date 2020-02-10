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

		//ユーザ名空欄チェック
		if(name.isEmpty()) {
			//入力されたユーザ名が空ならreturn
			request.setAttribute("result", "ユーザ名が入力されておりません");
			dispatcher = request.getRequestDispatcher(str);
			dispatcher.forward(request, response);
			return;
		}

		//何も選択されてないときは空欄を表示
		request.setAttribute("result", "");

		//examineeインスタンスの生成
		Examinees examinee = new Examinees(name, pass);

		//examinee情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("loginExaminee", examinee);

		//ログイン処理クラスをインスタンス化
		LoginLogic loginLogic = new LoginLogic(request,examinee);

		//loginの選択がされたならログイン処理
		 str = loginLogic.loginProcess();

		//新規登録処理
		loginLogic.signupProcess();

		dispatcher = request.getRequestDispatcher(str);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
