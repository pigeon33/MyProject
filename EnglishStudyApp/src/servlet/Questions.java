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
import model.logic.ResultViewLogic;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Questions:doGet:request.getParameter(\"TotalQuestionNum\"):"+request.getParameter("TotalQuestionNum"));

		request.setCharacterEncoding("UTF-8");
		//URLをquestions画面にセット
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/questions.jsp");



		//セッションスコープ取得
		HttpSession session = request.getSession(false);

		//メイン画面から飛んだ時のみ問題文の初期化
		if(request.getParameter("action")!=null) {
			questionLogic.init(questionList);
			//リクエストスコープからセッションスコープへ問題数をセット
			session.setAttribute("TotalQuestionNum", request.getParameter(request.getParameter("TotalQuestionNum")));
		}

		//セッションスコープに、問題番号をセット
		request.setAttribute("question", questionList.get((int) session.getAttribute("questionNum")));



        //Resultの選択がされたならResultViewを飛ばす
        if("Result".equals(request.getParameter("action"))) {
    		ResultViewLogic resultviewlogic = new ResultViewLogic();

        	dispatcher = request.getRequestDispatcher("resultView");
        }

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		System.out.println("Questions:doPost:request.getParameter(\"TotalQuestionNum\"):"+request.getParameter("TotalQuestionNum"));

		//次の問題と前の問題処理
		questionLogic.nextQuestion(request);
		questionLogic.previousQuestion(request);


		doGet(request, response);
	}
}
