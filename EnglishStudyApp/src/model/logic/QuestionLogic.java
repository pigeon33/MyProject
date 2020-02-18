package model.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.QuestionDAO;
import model.entity.Question;

public class QuestionLogic {

	/**
	 * 問題の初期化メソッド
	 * @param questionList
	 */
	public void init(List<Question> questionList) {
		// DAOの生成
		QuestionDAO dao = new QuestionDAO();

		// DAOの利用
		dao.selectAllQuestion(questionList);
	}

	public void nextQuestion(HttpServletRequest request) {
		//次の問題が選択されてなければ戻る
		if (!"次の問題".equals(request.getParameter("actionInQuestion"))) {
			return;
		}

		//次の問題が押されたら、セッションスコープに問題番号を加算
		HttpSession session = request.getSession(false);
		int num = (int) session.getAttribute("questionNum");
		session.setAttribute("questionNum", ++num);

	}
	public void previousQuestion(HttpServletRequest request) {
		//前の問題が選択されてなければ戻る
		if (!("前の問題".equals(request.getParameter("actionInQuestion")))) {
			return;
		}

		//前の問題が押されたら、セッションスコープに問題番号を減産
		HttpSession session = request.getSession(false);
		int num = (int) session.getAttribute("questionNum");
		session.setAttribute("questionNum", --num);

	}
	public void answerAndCommentary(HttpServletRequest request) {
		//回答解説を見るが選択されてなければ戻る
		if (!"回答解説を見る".equals(request.getParameter("actionInQuestion"))) {
			return;
		}
	}
}
