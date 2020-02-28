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
	//private QuestionLogic questionLogic = new QuestionLogic();

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
		QuestionLogic questionLogic = new QuestionLogic();

		//問題処理
		String url = questionLogic.QuestionController(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
