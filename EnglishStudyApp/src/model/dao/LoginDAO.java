/*
 * WebApp_05_sp01_MVC
 * model.dao.examineeDAO.java
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.entity.Examinees;

/**
 * m_examineeテーブルのDAOです。
 */
public class LoginDAO {

	/**
	 * 全ユーザの名前とパスワードを取得
	 * @param examineeList
	 */
	public void selectAllExamineeNameAndPass(List<Examinees> examineeList)  {

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("select * from m_examinee")) {

			// 結果の操作
			while (res.next()) {
				Examinees examinee = new Examinees();
				examinee.setId(Integer.parseInt(res.getString("examinee_id")));
				examinee.setName(res.getString("examinee_name"));
				examinee.setPass(res.getString("examinee_pass"));
				examinee.setTimestamp(res.getTimestamp("createdtime"));
				examineeList.add(examinee);
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void selectAllExamineeScore(List<Examinees> examineeList)  {

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT t1.examinee_id, t2.examinee_name, t1.score, t1.recordedtime  FROM m_score t1 INNER JOIN m_examinee t2 ON t1.examinee_id = t2.examinee_id ORDER BY score DESC;")) {

			int ranking = 1;
			// 結果の操作
			while (res.next()) {
				Examinees examinee = new Examinees();
				examinee.setRankingNumber(ranking);
				examinee.setName(res.getString("examinee_name"));
				examinee.setScore(res.getInt("score"));
				examinee.setTimestamp(res.getTimestamp("recordedtime"));
				examineeList.add(examinee);
				ranking++;
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void insertNewExaminee(Examinees examinee){
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO m_examinee(examinee_name, examinee_pass) VALUES(?,?);")){

			pstmt.setString(1, examinee.getName());
			pstmt.setString(2, examinee.getPass());

			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}