package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.entity.Examinees;
import model.entity.Question;

public class ResultDAO {


	public int insertExamineeScore(Examinees examinee) {

		/*String sql = "INSERT INTO m_score (examinee_id,examined_number,score) VALUES (?,(select COUNT(examined_number) AS examined_number from m_score AS temp where examinee_id = ?)+1,?);";*/
		String sql = "INSERT INTO m_score (examinee_id,score) VALUES(?,?);";
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS)){
			pstmt.setInt(1, examinee.getId());
			/*pstmt.setInt(2, examinee.getId());*/
			pstmt.setInt(2, examinee.getScore());
			pstmt.executeUpdate();

			// getGeneratedKeys()により、Auto_IncrementされたIDを取得する
			int autoIncrementKey = 0;
			ResultSet res = pstmt.getGeneratedKeys();
			if(res.next()){
	             autoIncrementKey = res.getInt(1);
	         }

	         return autoIncrementKey;
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void insertExamineeTweet(Examinees examinee) {
		String sql = "UPDATE m_score SET tweet=? where id = ?;";
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, examinee.getTweet());
			pstmt.setInt(2, examinee.getLast_insert_id());
			pstmt.executeUpdate();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insertAccurecy(List<Question> questionList) {
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO m_score (examinee_id,examined_number,score) VALUES (?,(select COUNT(examined_number) AS examined_number from m_score AS temp where examinee_id = ?)+1,?);")){
			for (Question answer : questionList) {
				if ("正解".equals(answer.getJudge())) {

				} else {
				}
			}

			/*			pstmt.setInt(1, examinee.getId());
						pstmt.setInt(2, examinee.getId());
						pstmt.setInt(3, examinee.getScore());*/
			pstmt.executeUpdate();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
