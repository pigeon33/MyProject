package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.entity.Question;

public class QuestionDAO {
	public void selectAllQuestion(List<Question> questionList)  {

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("select * from m_question")) {

			// 結果の操作
			int questionNumber = 1;
			String str[]= {"a","b","c","d"};
			while (res.next()) {
				Question question = new Question();
				question.setQuestionNumber(questionNumber);
				question.setQuestion(res.getString("question"));
				question.setChoices(0,res.getString("a"));
				question.setChoices(1,res.getString("b"));
				question.setChoices(2,res.getString("c"));
				question.setChoices(3,res.getString("d"));
				question.setAnswer(res.getString("answer"));
				question.setCommentary(res.getString("commentary"));
				questionList.add(question);
				questionNumber++;
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void selectRequestedNumberQuestion(List<Question> questionList,int num)  {

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement("select * from m_question order by rand() limit ?;")) {

			pstmt.setInt(1, num);
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			int questionNumber = 1;
			String str[]= {"a","b","c","d"};
			while (res.next()) {
				Question question = new Question();
				question.setQuestionNumber(questionNumber);
				question.setQuestion(res.getString("question"));
				question.setChoices(0,res.getString("a"));
				question.setChoices(1,res.getString("b"));
				question.setChoices(2,res.getString("c"));
				question.setChoices(3,res.getString("d"));
				question.setAnswer(res.getString("answer"));
				question.setCommentary(res.getString("commentary"));
				questionList.add(question);
				questionNumber++;
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
