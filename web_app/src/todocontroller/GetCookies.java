package todocontroller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * ユーザーがブラウザにアクセスしたときにクッキーを取得 投票済みかを確認
 * 
 * @author admin
 *
 */
public class GetCookies extends HttpServlet {
	HttpServletRequest request;
	Documents documents;
	Cookie[] cookies;

	public GetCookies(HttpServletRequest request, Documents documents) {
		this.request = request;
		this.documents = documents;

		/**
		 * リクエストユーザからのCookie取得(投票済みかどうかの確認のため)
		 */
		cookies = this.request.getCookies();
	}

	public void get() {

		/**
		 * 投票済みかどうかの確認
		 */
		request.setAttribute("cookie", null);
		if (cookies == null) {
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("questionnaire" + String.valueOf(documents.getIndex()))) {
					request.setAttribute("cookie", Integer.parseInt(cookie.getValue()));
					System.out.println(Integer.parseInt(cookie.getValue()));
				}
			}
		}
	}

	/*
	 * 
	 * グラフを表示するためのデータを送るメソッド (アンケートに投票した人しか結果をみることはできないようにする。 )
	 * 
	 */
	public void isResult() {
		request.setAttribute("graph", null);
		if (cookies == null) {
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("questionnaire" + String.valueOf(documents.getIndex() - 1))) {
					request.setAttribute("graph", "ok");
				}
			}
		}
	}

}
