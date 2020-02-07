package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class questions
 */
@WebServlet("/Questions")
public class Questions extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		//questions画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/questions.jsp");
		//dispatcher.forward(request, response);

        response.setContentType("text/html; charset=Shift_JIS");

        //String selectedFruit = request.getParameter("radiobutton");
        //System.out.println("あなたが好きな果物は" + selectedFruit + "です。");

        //Resultの選択がされたならResultViewを飛ばす
        if("Result".equals(request.getParameter("action"))) {
        	System.out.println("aaaa");
    		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultView.jsp");

        }else {
        	System.out.println("bbbb");
        }


        try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
