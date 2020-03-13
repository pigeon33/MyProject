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
	HttpServletRequest request;
	Examinees examinee;
	//セッションスコープ取得

	public String resultController(HttpServletRequest request) {

		this.session = request.getSession(false);
		this.request = request;

		this.examinee = (Examinees) session.getAttribute("loginExaminee");


		//まだ回答確認モードになってないなら
		if (!"checkAnswer".equals(session.getAttribute("status"))) {
			//点数を計算
			culcScore();
			//点数をDBに追加
			insertScore();
			//ステータスは回答確認モードに変更
			this.session.setAttribute("status", "checkAnswer");
			//最初の一回のみtoastを表示する
			request.setAttribute("InitialActionInResultView", true);
		}

		//”解説を見る”が押された時の処理
		if (request.getParameter("actionInResultView") != null) {
			//見たい解説の問題番号をセット
			this.session.setAttribute("questionNum", Integer.parseInt(request.getParameter("actionInResultView")) - 1);
			return "/Questions";
		}

		//テキストボックス内のメッセージを初期化。
		request.setAttribute("placeholderFoTweet","４０文字以内のメッセージを点数と一緒に残すことが出来ます。　未入力でもＯＫです。");


		//”tweet”が押された時の処理
		if ("tweet".equals(request.getParameter("actionInResultViewTweet"))) {
			insertTweet();
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

		//点数に応じたメッセージを挿入
		int maxScore = (int) session.getAttribute("TotalQuestionNum") * addingScore;

		if (score == 0) {
			examinee.setMsg("You're poor " + examinee.getName() + ".\nYour score is " + score+".");
		} else if (score == maxScore) {
			examinee.setMsg("Perfect! " + examinee.getName() + ".\nYour score is " + score+".");
		} else {
			examinee.setMsg("Well Done " + examinee.getName() + ".\nYour score is " + score+".");
		}

		//セッションスコープに受験者の点数をセット
		examinee.setScore(score);
		session.setAttribute("loginExaminee", examinee);
	}

	/**
	 * 点数をＤＢに保存するメソッド
	 */
	private void insertScore() {
		// DAOの生成
		ResultDAO dao = new ResultDAO();

		//DBに受験者の点数をセット
		//int i = dao.insertExamineeScore((Examinees) session.getAttribute("loginExaminee"));
		int i = dao.insertExamineeScore(examinee);
		//LastInsertIdを取得して、セッションスコープにセット
		examinee.setLast_insert_id(i);
		session.setAttribute("loginExaminee", examinee);
	}
	/**
	 * TweetをＤＢに保存するメソッド
	 */
	private void insertTweet() {
		String tweet=(String) request.getParameter("comment");

		if(tweet.length() > 40) {
			request.setAttribute("placeholderFoTweet","メッセージは４０文字以内でお願い致します。");
			return;
		}

		ResultDAO dao = new ResultDAO();

		//tweetをDBにセット
		examinee.setTweet(tweet);
		dao.insertExamineeTweet(examinee);

		request.setAttribute("placeholderFoTweet","ありがとうございます。　メッセージを保存しました。　何度でも上書き可能です。");
	}
}
