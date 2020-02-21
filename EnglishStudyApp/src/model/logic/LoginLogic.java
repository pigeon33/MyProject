package model.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.LoginDAO;
import model.entity.Examinees;

public class LoginLogic {
	private HttpServletRequest request;
	private Examinees examinee;

	//試験者一覧保存用リストの作成
	List<Examinees> examineeList = new ArrayList<Examinees>();

	// DAOの生成
	LoginDAO dao = new LoginDAO();

	/**
	 * コンストラクラー
	 * @param request
	 * @param examinee
	 */
	public LoginLogic(HttpServletRequest request, Examinees examinee) {
		this.request = request;
		this.examinee = examinee;

		//受験者一覧取得
		dao.selectAllExamineeNameAndPass(examineeList);
	}


	/**
	 * ログイン処理
	 * @return
	 */
	public String loginController() {
		//新規登録処理
		signupProcess();

		//loginの選択がされてないなら戻る
		if (!"ログイン".equals(request.getParameter("action"))) {
			return "/";
		}

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
	private void signupProcess() {

		//新規登録されてないなら戻る
		if (!"新規登録".equals(request.getParameter("action"))) {
			return;
		}


		//既存ユーザ名と一致するかチェック
		if (this.authenticationUser()) {
			//登録された全受験者と名前が一すれば登録しない
			request.setAttribute("result", this.examinee.getName()+"はすでに登録されているユーザ名です");

		} else {
			//登録された全受験者と名前が一致しなければ新規登録する
			dao.insertNewExaminee(this.examinee);
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
				//examineeに自分のＩＤをセット
				examinee.setId(examinees.getId());
				return true;
			}
		}
		return false;
	}

	private boolean authenticationUser() {

		//登録された全受験者と名前が一致したらtrueを返す
		for (Examinees examinees : examineeList) {
			if (examinees.getName().equals(this.examinee.getName())) {
				return true;
			}
		}
		return false;
	}
}
