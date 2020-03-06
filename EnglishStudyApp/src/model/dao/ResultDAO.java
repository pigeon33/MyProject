package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.entity.Examinees;
import model.entity.Question;

public class ResultDAO {
	public void insertExamineeScore(Examinees examinee) {
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO m_score (examinee_id,examined_number,score) VALUES (?,(select COUNT(examined_number) AS examined_number from m_score AS temp where examinee_id = ?)+1,?);")){
			pstmt.setInt(1, examinee.getId());
			pstmt.setInt(2, examinee.getId());
			pstmt.setInt(3, examinee.getScore());
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
