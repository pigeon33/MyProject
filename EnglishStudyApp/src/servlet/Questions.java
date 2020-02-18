package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.logic.QuestionLogic;

/**
 * Servlet implementation class questions
 */
@WebServlet("/Questions")
public class Questions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//試験者一覧保存用リストの作成
	private QuestionLogic questionLogic = new QuestionLogic();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Questions() {
        super();
        System.out.println("Question:Constructor");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//URLをquestions画面にセット
		String url="/WEB-INF/jsp/questions.jsp";

		request.setCharacterEncoding("UTF-8");

		//questionLogicクラスにrequest情報をセット
		questionLogic.setRequest(request);

		//メイン画面から飛んだ時のみ問題文の初期化
		if(request.getParameter("action")!=null) {questionLogic.init();}

		System.out.println("Questions:doGet:request.getParameter(\"radiobutton\"):"+request.getParameter("radiobutton"));
		//次の問題と前の問題処理
		questionLogic.QuestionController();

        //"結果表示"の選択がされたならResultViewを飛ばす
        if("結果表示".equals(request.getParameter("actionInQuestion"))) {
        	url="/resultView";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
