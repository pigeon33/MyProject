package model.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.RankingDAO;
import model.entity.Examinees;

public class RankingLogic {
	private HttpSession session;
	private HttpServletRequest request;


	/**
	 * examineeScoreListに全受験者とスコアを入れる
	 */
	public RankingLogic(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession(false);
	}

	public void rankingController() {

		//全体ランキング確認
		getExamineeScoreList();

		//受験履歴確認
		getExaminedHistory();
	}

	/**
	 * examineeScoreListのゲッター
	 * @return
	 */
	private void getExamineeScoreList() {
		//"ランキングを見る"が選択されてなければ戻る
		if (!"ランキングを見る".equals(request.getParameter("action"))) {
			return;
		}

		// DAOの生成
		RankingDAO dao = new RankingDAO();

		//試験者一覧保存用リストの作成
		List<Examinees> examineeScoreList = dao.selectAllExamineeAndScore();
		// リクエストスコープへ点数リストをセット
		request.setAttribute("examineeScoreList", examineeScoreList);

		//全体ランキング表示モードにセット
		request.setAttribute("actionInRanking", "allExamineesScore");
	}

	private void getExaminedHistory() {
		//"履歴を見る"が選択されてなければ戻る
		if (!"履歴を見る".equals(request.getParameter("action"))) {
			return;
		}

		//セッションスコープから受験者情報を取得
		Examinees examinee = (Examinees) session.getAttribute("loginExaminee");

		// DAOの生成
		RankingDAO dao = new RankingDAO();
		List<Examinees> examineeList = dao.selectExaminedHistory(examinee.getId());
		// リクエストスコープへ点数履歴をセット
		request.setAttribute("examineeScoreList", examineeList);

		//履歴表示モードにセット
		request.setAttribute("actionInRanking", "examineeHistory");
	}
}
