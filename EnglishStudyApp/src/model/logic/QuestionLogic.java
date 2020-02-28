package model.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.QuestionDAO;
import model.entity.Question;

public class QuestionLogic {
	HttpSession session;
	HttpServletRequest request;

	/**
	 * 問題の設定メソッド
	 */
	public String QuestionController(HttpServletRequest request) {
		this.session = request.getSession(false);
		this.request=request;

		//メイン画面から飛んだ時のみ問題文の初期化
		if (request.getParameter("action") != null) {
			System.out.println("QUestions:doGet:questionLogic.init()");
			init();
		}

		//メインボタンが押された時の処理
		if ("メイン".equals(request.getParameter("actionInQuestion"))) {
			System.out.println("QUestions:doGet:questionLogic.init()");
			return "/WEB-INF/jsp/main.jsp";
		}

		//受験者の回答をチェックする
		setAnswer();

		//前後の移動処理
		nextQuestion();
		previousQuestion();

		List<Question> questionList = (List<Question>) session.getAttribute("questionList");

		//リクエストスコープに、次の画面に表示する問題をセット
		request.setAttribute("question", questionList.get((int) session.getAttribute("questionNum")));

		//"結果表示"の選択がされたならResultViewを飛ばす
		if ("結果表示".equals(request.getParameter("actionInQuestion"))) {
			return "/Result";
		}

		//URLをquestions画面にセット
		return "/WEB-INF/jsp/questions.jsp";
	}

	/**
	 * 問題の初期化メソッド
	 * @param questionList
	 */
	private void init() {

		List<Question> questionList = new ArrayList<Question>();//問題格納用リスト

		System.out.println("QuestionLogic:init");
		// DAOの生成
		QuestionDAO dao = new QuestionDAO();

		// DAOの利用
		//dao.selectAllQuestion(questionList);
		dao.selectRequestedNumberQuestion(questionList, Integer.parseInt(request.getParameter("TotalQuestionNum")));

		session.setAttribute("questionList", questionList);
		//現在の問題番号を０で初期化
		session.setAttribute("questionNum", 0);
		//リクエストスコープからセッションスコープへ最大問題番号をセット
		session.setAttribute("TotalQuestionNum", Integer.parseInt(request.getParameter("TotalQuestionNum")));
		//ステータスの初期化
		//this.session.removeAttribute("status");
		session.setAttribute("status", "inQuestion");
	}

	private void setAnswer() {
		//回答解説を見るモードならreturn
		if ("checkAnswer".equals(session.getAttribute("status"))) {
			return;
		}

		List<Question> questionList = (List<Question>) session.getAttribute("questionList");

		int currentQuestionNum = (int) session.getAttribute("questionNum");

		questionList.get(currentQuestionNum).setChoosenAnswer(request.getParameter("radiobutton"));
		if (questionList.get(currentQuestionNum).getAnswer()
				.equals(questionList.get(currentQuestionNum).getChoosenAnswer())) {
			questionList.get(currentQuestionNum).setJudge("正解");
		} else {
			questionList.get(currentQuestionNum).setJudge("不正解");
		}

		//session scopreに回答リストをセット
		session.setAttribute("questionList", questionList);

	}

	private void nextQuestion() {
		//次の問題が選択されてなければ戻る
		if (!"次の問題".equals(request.getParameter("actionInQuestion"))) {
			return;
		}

		//次の問題が押されたら、セッションスコープに問題番号を加算
		int num = (int) session.getAttribute("questionNum");
		session.setAttribute("questionNum", ++num);
	}

	private void previousQuestion() {
		//前の問題が選択されてなければ戻る
		if (!("前の問題".equals(request.getParameter("actionInQuestion")))) {
			return;
		}

		//前の問題が押されたら、セッションスコープに問題番号を減産
		int num = (int) session.getAttribute("questionNum");
		session.setAttribute("questionNum", --num);
	}
}
