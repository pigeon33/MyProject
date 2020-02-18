package model.logic;

import java.util.ArrayList;
import java.util.List;

import model.dao.RankingDAO;
import model.entity.Examinees;

public class RankingLogic {
	//試験者一覧保存用リストの作成
	private List<Examinees> examineeScoreList = new ArrayList<Examinees>();

	/**
	 * examineeScoreListに全受験者とスコアを入れる
	 */
	public RankingLogic() {
		System.out.println("ResultViewLogic:Constructor");

		// DAOの生成
		RankingDAO dao = new RankingDAO();

		// DAOの利用
		dao.selectAllExamineeAndScore(examineeScoreList);
	}

	/**
	 * examineeScoreListのゲッター
	 * @return
	 */
	public List<Examinees> getExamineeScoreList() {
		return examineeScoreList;
	}

}
