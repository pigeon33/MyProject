package model.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.QuestionDAO;
import model.entity.Answer;
import model.entity.Question;

public class QuestionLogic {
	private HttpServletRequest request;
	private HttpSession session;
	private List<Question> questionList = new ArrayList<Question>();//問題格納用リスト
	private List<Answer> answerList = new ArrayList<Answer>();//回答格納用リスト

	/**
	 * 問題の初期化メソッド
	 * @param questionList
	 */
	public void init() {
		// DAOの生成
		QuestionDAO dao = new QuestionDAO();

		// DAOの利用
		dao.selectAllQuestion(questionList);

		//セッションスコープ取得
		this.session = this.request.getSession(false);

		//現在の問題番号を０で初期化
		this.session.setAttribute("questionNum", 0);
		//リクエストスコープからセッションスコープへ最大問題番号をセット
		this.session.setAttribute("TotalQuestionNum", Integer.parseInt(request.getParameter("TotalQuestionNum")));
	}

	/**
	 * 問題の設定メソッド
	 */
	public void QuestionController() {

		int currentQuestionNum = (int)this.session.getAttribute("questionNum");

		//回答を取得して、Answerにセット
		Answer ans= new Answer();
		ans.setNum(currentQuestionNum+1);
		ans.setAns(request.getParameter("radiobutton"));
		ans.setCollectAns(questionList.get(currentQuestionNum).getAnswer());
		if(questionList.get(currentQuestionNum).getAnswer().equals(ans.getAns())) {
			ans.setJudge("正解");
		} else {
			ans.setJudge("不正解");
		}

		//リストにまだ登録がなければaddしてすでにあればset
		if(answerList.size() <= currentQuestionNum) {
			answerList.add(ans);
		} else {
			answerList.set(currentQuestionNum, ans);
		}

		//session scopreに回答リストをセット
		session.setAttribute("answerList", answerList);

		//前後の移動処理
		nextQuestion();
		previousQuestion();
		//リクエストスコープに、次の画面に表示する問題をセット
		this.request.setAttribute("question", questionList.get((int) this.session.getAttribute("questionNum")));
	}
	private void nextQuestion() {
		//次の問題が選択されてなければ戻る
		if (!"次の問題".equals(request.getParameter("actionInQuestion"))) {
			return;
		}

		//次の問題が押されたら、セッションスコープに問題番号を加算
		int num = (int) this.session.getAttribute("questionNum");
		this.session.setAttribute("questionNum", ++num);
	}
	private void previousQuestion() {
		//前の問題が選択されてなければ戻る
		if (!("前の問題".equals(this.request.getParameter("actionInQuestion")))) {
			return;
		}

		//前の問題が押されたら、セッションスコープに問題番号を減産
		int num = (int) this.session.getAttribute("questionNum");
		this.session.setAttribute("questionNum", --num);
	}


	public void answerAndCommentary() {
		//回答解説を見るが選択されてなければ戻る
		if (!"回答解説を見る".equals(this.request.getParameter("actionInQuestion"))) {
			return;
		}
	}

	/**
	 * requestのセッター
	 * @param request
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
