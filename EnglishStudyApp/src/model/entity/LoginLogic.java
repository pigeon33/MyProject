package model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.AuthorizationDAO;

public class LoginLogic {
	private HttpServletRequest request;
	private Examinees examinee;

	//試験者一覧保存用リストの作成
	List<Examinees> examineeList = new ArrayList<Examinees>();

	// DAOの生成
	AuthorizationDAO dao = new AuthorizationDAO();

	/**
	 * コンストラクラー
	 * @param request
	 * @param examinee
	 */
	public LoginLogic(HttpServletRequest request, Examinees examinee) {
		this.request = request;
		this.examinee = examinee;



		//受験者一覧取得
		dao.selectAllexaminee(examineeList);
	}

	/**
	 * ログイン処理
	 * @return
	 */
	public String loginProcess() {
		//何も選択されてないときは空欄を表示
		request.setAttribute("result", "");

		//ログイン成功時したらメインへ移動
		if (this.authenticationUserAndPass()) {
			return "/WEB-INF/jsp/main.jsp";
		}
		//ログイン失敗したらログイン失敗をリクエストスコープにセット
		else {
			request.setAttribute("result", "ログインに失敗しました");
		}
		return "/";
	}
	/**
	 * 新規登録処理
	 */
	public void signupProcess() {
		System.out.println("signupProcess");

		//既存ユーザ名と一致するかチェック
		if (this.authenticationUser()) {
			//登録された全受験者と名前が一致しなければ登録しない
			request.setAttribute("result", this.examinee.getName()+"はすでに登録されているユーザ名です");

		} else {
			//登録された全受験者と名前が一致しなければ新規登録する
			dao.insertExaminee(this.examinee);
			request.setAttribute("result", this.examinee.getName()+"を新規登録しました");
		}
	}
	/**
	 * 受験者登録された全受験者と認証する
	 * @param currentExaminee
	 * @return
	 */
	private boolean authenticationUserAndPass() {

		//登録された全受験者と名前＆passが一致したらtrueを返す
		for (Examinees examinees : examineeList) {
			if (examinees.getName().equals(this.examinee.getName())
					&& examinees.getPass().equals(this.examinee.getPass())) {
				return true;
			}
		}
		return false;
	}

	private boolean authenticationUser() {

		//登録された全受験者と名前＆passが一致したらtrueを返す
		for (Examinees examinees : examineeList) {
			if (examinees.getName().equals(this.examinee.getName())) {
				System.out.println("authenticationUser:true");
				return true;
			}
		}
		return false;
	}


}
