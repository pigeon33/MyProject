package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Question;
import model.logic.QuestionLogic;

/**
 * Servlet implementation class questions
 */
@WebServlet("/Questions")
public class Questions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//試験者一覧保存用リストの作成
	private List<Question> questionList = new ArrayList<Question>();
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

		request.setCharacterEncoding("UTF-8");
		//URLをquestions画面にセット
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/questions.jsp");

		//セッションスコープ取得
		HttpSession session = request.getSession(false);

		//メイン画面から飛んだ時のみ問題文の初期化
		if(request.getParameter("action")!=null) {
			//問題を生成
			questionLogic.init(questionList);
			//現在の問題番号を０で初期化
			session.setAttribute("questionNum", 0);
			//リクエストスコープからセッションスコープへ最大問題番号をセット
			session.setAttribute("TotalQuestionNum", Integer.parseInt(request.getParameter("TotalQuestionNum")));
		}

		//次の問題と前の問題処理
		questionLogic.nextQuestion(request);
		questionLogic.previousQuestion(request);

		//リクエストスコープに、問題をセット
		request.setAttribute("question", questionList.get((int) session.getAttribute("questionNum")));


        //"結果表示"の選択がされたならResultViewを飛ばす
//        if("結果表示".equals(request.getParameter("actionInQuestion"))) {
//    		ResultViewLogic resultViewLogic = new ResultViewLogic();
//    		System.out.println("Qusestions:doGet:\"結果表示\"");
//    		resultViewLogic.init();
//        	//dispatcher = request.getRequestDispatcher("/resultView");
//        }

		dispatcher.forward(request, response);
	}
}
