package model.dao;

import java.sql.Connection;
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
			while (res.next()) {
				Question question = new Question();
				question.setQuestionNumber(questionNumber);
				question.setQuestion(res.getString("question"));
				question.setChoiceA(res.getString("a"));
				question.setChoiceB(res.getString("b"));
				question.setChoiceC(res.getString("c"));
				question.setChoiceD(res.getString("d"));
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
