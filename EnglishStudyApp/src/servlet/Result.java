package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.logic.ResultLogic;

/**
 * Servlet implementation class resultView
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//ResultLogic resultLogic = new ResultLogic();;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        System.out.println("Result:Constructor");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//結果表示処理
		ResultLogic resultLogic = new ResultLogic();
		String url = resultLogic.resultController(request);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
