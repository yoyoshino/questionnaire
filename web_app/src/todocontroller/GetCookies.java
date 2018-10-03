package todocontroller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * ユーザーがブラウザにアクセスしたときにクッキーを取得
 * 投票済みかを確認
 * @author admin
 *
 */
public class GetCookies extends HttpServlet {
	HttpServletRequest request;
	Documents documents;

	public GetCookies(HttpServletRequest request, Documents documents) {
		this.request = request;
		this.documents = documents;
	}

	public void get() {
		/**
		 * リクエストユーザからのCookie取得(投票済みかどうかの確認のため)
		 */
		Cookie[] cookies = request.getCookies();

		/**
		 * 投票済みかどうかの確認
		 */
		if (cookies == null) {
			request.setAttribute("cookie", null);
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("questionnaire" + String.valueOf(documents.getIndex()))) {
					request.setAttribute("cookie", Integer.parseInt(cookie.getValue()));
				}
			}System.out.println("cookie:"+request.getAttribute("cookie"));
			request.setAttribute("cookie", null);
		}
	}

}
