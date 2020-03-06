package model.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ResultDAO;
import model.entity.Examinees;
import model.entity.Question;

public class ResultLogic {
	final int addingScore = 10;
	HttpSession session;
	//セッションスコープ取得

	public String resultController(HttpServletRequest request) {

		session = request.getSession(false);

		//まだ回答確認モードになってないなら
		if (!"checkAnswer".equals(session.getAttribute("status"))) {
			//点数を計算
			culcScore();
			//点数をDBに追加
			insertScore();
			//ステータスは回答確認モードに変更
			session.setAttribute("status", "checkAnswer");
		}



		//”解説を見る”が押された時の処理
		if (request.getParameter("actionInResultView") != null) {
			//見たい解説の問題番号をセット
			session.setAttribute("questionNum", Integer.parseInt(request.getParameter("actionInResultView")) - 1);
			return "/Questions";
		}

		return "/WEB-INF/jsp/resultView.jsp";
	}

	private void culcScore() {
		List<Question> questionList = (List<Question>) session.getAttribute("questionList");

		//点数計算処理
		int score = 0;
		for (Question answer : questionList) {
			if ("〇".equals(answer.getJudge())) {
				score = score + addingScore;
			}
		}
		Examinees examinee = (Examinees) session.getAttribute("loginExaminee");

		//点数に応じたメッセージを挿入

		int maxScore = (int) session.getAttribute("TotalQuestionNum") * addingScore;

		if (score == 0) {
			examinee.setMsg("You're poor " + examinee.getName() + ". Your score is " + score);
		} else if (score == maxScore) {
			examinee.setMsg("Perfect! " + examinee.getName() + ". Your score is " + score);
		} else {
			examinee.setMsg("Well Done " + examinee.getName() + ". Your score is " + score);
		}


		//セッションスコープに受験者の点数をセット
		examinee.setScore(score);
		session.setAttribute("loginExaminee", examinee);
	}

	private void insertScore() {
		// DAOの生成
		ResultDAO dao = new ResultDAO();

		//DBに受験者の点数をセット
		dao.insertExamineeScore((Examinees) session.getAttribute("loginExaminee"));

	}
}
