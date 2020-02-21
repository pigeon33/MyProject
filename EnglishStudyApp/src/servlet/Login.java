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
import model.logic.LoginLogic;

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

		//何も選択されてないときは説明を表示
		request.setAttribute("result", "初めての場合は、ユーザ名とパスワードを入力して新規登録ボタンを押してください。");

		//examineeインスタンスの生成
		Examinees examinee = new Examinees(name, pass);


		//ログイン処理クラスをインスタンス化
		LoginLogic loginLogic = new LoginLogic(request, examinee);

		//ログイン処理
		 str = loginLogic.loginController();

		//examinee情報をセッションスコープに保存
		HttpSession session = request.getSession(true);
		session.setAttribute("loginExaminee", examinee);

		dispatcher = request.getRequestDispatcher(str);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
