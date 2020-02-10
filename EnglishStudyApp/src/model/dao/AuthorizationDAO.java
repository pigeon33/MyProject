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
public class AuthorizationDAO {
	/**
	 * すべての従業員のリストを返します。
	 * @return 従業員のリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	//public List<Examinees> selectAllexaminee(List<Examinees> examineeList) throws SQLException, ClassNotFoundException {
	public void selectAllexaminee(List<Examinees> examineeList)  {
		//List<Examinees> examineeList = new ArrayList<Examinees>();

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("select * from m_examinee")) {

			// 結果の操作
			while (res.next()) {
				Examinees examinee = new Examinees();
				examinee.setName(res.getString("examinee_name"));
				examinee.setPass(res.getString("examinee_pass"));
				examinee.setTimestamp(res.getTimestamp("createdtime"));
				examineeList.add(examinee);
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//return examineeList;
	}

	public void insertExaminee(Examinees examinee){
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