package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.Examinees;

public class ResultDAO {
	public void insertExamineeScore(Examinees examinee) {

		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO m_score (examinee_id,examined_number,score) VALUES (?,(select COUNT(examined_number) AS examined_number from m_score AS temp where examinee_id = ?)+1,?);")){
			pstmt.setInt(1, examinee.getId());
			pstmt.setInt(2, examinee.getId());
			pstmt.setInt(3, examinee.getScore());
			pstmt.executeUpdate();
			System.out.println("ResultDAO:insertExamineeScore:examinee.getId()+examinee.getScore()" + examinee.getId()+examinee.getScore());

		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
