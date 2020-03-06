package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.Examinees;

public class RankingDAO {

	public List<Examinees> selectAllExamineeAndScore() {

		//一時処理ようのリスト作成
		List<Examinees> examineeList = new ArrayList<Examinees>();

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(
						" SELECT t1.examinee_name, t2.score, t2.recordedtime FROM m_examinee t1 INNER JOIN m_score t2 ON t1.examinee_id = t2.examinee_id ORDER BY t2.score DESC;")) {

			int i = 1, ranking = 1, score = 0;
			// 結果の操作
			while (res.next()) {
				Examinees examinee = new Examinees();
				if (score != res.getInt("score")) {
					ranking = i;
				}
				examinee.setRankingNumber(ranking);
				examinee.setName(res.getString("examinee_name"));
				examinee.setScore(res.getInt("score"));
				//examinee.setTimestamp(res.getTimestamp("recordedtime"));
				examinee.setTimestampStr(res.getTimestamp("recordedtime"));
				examineeList.add(examinee);
				score = res.getInt("score");
				i++;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return examineeList;
	}

	public List<Examinees> selectExaminedHistory(int examineeID) {

		//一時処理ようのリスト作成
		List<Examinees> examinendHistory = new ArrayList<Examinees>();

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"select * from m_score where examinee_id = ? ORDER BY recordedtime DESC;")) {

			pstmt.setInt(1, examineeID);
			ResultSet res= pstmt.executeQuery();
			// 結果の操作
			while (res.next()) {
				Examinees examinee = new Examinees();
				examinee.setScore(res.getInt("score"));
				//examinee.setTimestamp(res.getTimestamp("recordedtime"));
				examinee.setTimestampStr(res.getTimestamp("recordedtime"));
				examinendHistory.add(examinee);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return examinendHistory;
	}
}
