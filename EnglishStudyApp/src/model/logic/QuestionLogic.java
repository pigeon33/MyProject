package model.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
		//if("inMain".equals(this.session.getAttribute("status"))) {
		if (request.getParameter("action") != null) {
			System.out.println("QUestions:doGet:questionLogic.init()");
			init();
		}

		//受験者の回答をチェックする
		setAnswer();

		List<Question> questionList = (List<Question>) session.getAttribute("questionList");

		//気になるボタンを押したときの処理
		if(request.getParameter("checkbox")!=null) {
			int i = Integer.parseInt(request.getParameter("checkbox"));
				if(i>0) {questionList.get((int) this.session.getAttribute("questionNum")).setConserns(i);}
		} else {
			questionList.get((int) this.session.getAttribute("questionNum")).setConserns(0);
		}

		//前後の移動処理
		nextQuestion();
		previousQuestion();

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
		int totalQuestionNum = Integer.parseInt(request.getParameter("TotalQuestionNum"));

		System.out.println("QuestionLogic:init");
		// DAOの生成
		QuestionDAO dao = new QuestionDAO();

		// DAOの利用
		//dao.selectAllQuestion(questionList);
		dao.selectRequestedNumberQuestion(questionList, totalQuestionNum);

		//選択肢をランダム配置にする
		randomizeChoices(questionList);

		//問題リストをセッションスコープにセット
		session.setAttribute("questionList", questionList);
		//現在の問題番号を０で初期化
		session.setAttribute("questionNum", 0);
		//リクエストスコープからセッションスコープへ最大問題番号をセット
		session.setAttribute("TotalQuestionNum", totalQuestionNum);
		//ステータスの初期化
		//this.session.removeAttribute("status");
		session.setAttribute("status", "inQuestion");

	}

	private void setAnswer() {
		//回答解説を見るモードならreturn
		if ("checkAnswer".equals(session.getAttribute("status"))) {
			return;
		}

		//作業用の問題リストを作成
		List<Question> questionList = (List<Question>) session.getAttribute("questionList");

		//現在の問題番号を取得
		int currentQuestionNum = (int) session.getAttribute("questionNum");

		//選択されたラジオボタンを取得
		questionList.get(currentQuestionNum).setChoosenAnswer(request.getParameter("radiobutton"));
		//正解判定
		if (questionList.get(currentQuestionNum).getAnswer()
				.equals(questionList.get(currentQuestionNum).getChoosenAnswer())) {
			questionList.get(currentQuestionNum).setJudge("〇");
		} else {
			questionList.get(currentQuestionNum).setJudge("×");
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

	private void randomizeChoices(List<Question> questionList) {
		String str[] = {"A","B","C","D"};


		for (Question question : questionList) {
				String ans = question.getAnswer();
				boolean b[] = new boolean[4];
				String choices[] = question.getChoices();

				//正解に一致するshuffledAnswerをtrueにセット
				for (int i = 0; i < 4 ; i++) {
					if( ans.equals(str[i]) ) {
						b[i]=true;
					} else {b[i]=false;	}
				}

				//シャッフル用の数字を作成
				int intArray[]= {0,1,2,3};
				shuffle(intArray);

				//シャッフルした後の選択しをセット
				String[] tmpString = new String[4];
				boolean[] tmpB = new boolean[4];
				for(int i = 0; i < 4 ; i++){
					tmpString[i] = choices[intArray[i]];
					tmpB[i] = b[intArray[i]];

					//シャッフル後の回答をセット
					if( tmpB[i] ) {question.setAnswer(str[i]);}
				}

				//シャッフル後の選択をセット
				question.setChoices(tmpString);
				question.setShuffledAnswer(tmpB);
		}
	}
	private void shuffle(int[] array) {
	    // 配列が空か１要素ならシャッフルしようがないので、そのままreturn
	    if (array.length <= 1) {
	        return;
	    }

	    // Fisher–Yates shuffle
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = array.length - 1; i > 0; i--) {
	        int index = rnd.nextInt(i + 1);
	        // 要素入れ替え(swap)
	        int tmp = array[index];
	        array[index] = array[i];
	        array[i] = tmp;
	    }
	}
}
