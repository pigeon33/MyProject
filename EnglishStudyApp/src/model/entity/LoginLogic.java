package model.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.AuthorizationDAO;

public class LoginLogic {

	/**
	 * 受験者登録された全受験者と認証する
	 * @param currentExaminee
	 * @return
	 */
	public boolean execute(Examinees currentExaminee) {

		//試験者一覧保存用リストの作成
		List<Examinees> examineeList = new ArrayList<Examinees>();

		// DAOの生成
		AuthorizationDAO dao = new AuthorizationDAO();

		//受験者一覧取得
		try {
			// DAOの利用
			examineeList = dao.selectAllexaminee();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//登録された全受験者と名前＆passが一致したらtrueを返す
		for (Examinees examinees : examineeList) {
			if(currentExaminee.getName().equals(examinees.getName())
					&&currentExaminee.getPass().equals(examinees.getPass())) {
				return true;
			}
		}

		return false;
	}
}
