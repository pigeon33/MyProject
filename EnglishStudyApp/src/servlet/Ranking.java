package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.logic.RankingLogic;

/**
 * Servlet implementation class RankingView
 */
//@WebServlet("/RankingView")
@WebServlet("/Ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ranking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ranking:doGet:");

		//RankingLogicの初期化
		RankingLogic rankingLogic = new RankingLogic(request);
		rankingLogic.rankingController();

		//URLをRanking画面にセット
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rankingView.jsp");
		dispatcher.forward(request, response);
	}
}
