package todocontroller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 * 
 */
@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Documents documents;
	AccessNum access;
	Selects selects;
	int result1, result2, result3, result4;
	int old_result1, old_result2, old_result3, old_result4;
	Timer timer;
	timerTask timerTask;

	/**
	 * サーバ設立時に呼び出されるメソッドinit()
	 * 
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		start();
	}

	/**
	 * サーバ開設と同時に変数を定義
	 */
	public void start() {
		// アクセス数を定義
		access = new AccessNum();

		// アンケート文を定義
		documents = new Documents();

		// 四択選択肢を初期化（値は格納されている）
		selects = new Selects();

		// 投票結果を初期化
		result1 = 0;
		result2 = 0;
		result3 = 0;
		result4 = 0;

		/**
		 * アンケート内容変更へのカウントダウンを開始
		 */
		timer = new Timer();
		timerTask = new timerTask(documents, result1, result2, result3, result4);
		TimerSet();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * アクセス数の更新
		 */
		access.clientAccess();
		request.setAttribute("access", access.getAccessNum());
		
		// Cookieを取得して既に投票済みかどうかを確認し処理を行う
		GetCookies getCookies = new GetCookies(request, documents);
		getCookies.get();
		getCookies.isResult();

		// アンケート内容を.jspへ送る
		request.setAttribute("documents", documents.getDocument(documents.getIndex()));

		// 4択の選択肢を.jspへ送る
		request.setAttribute("select0", selects.getSelect(documents.getIndex())[0]);
		request.setAttribute("select1", selects.getSelect(documents.getIndex())[1]);
		request.setAttribute("select2", selects.getSelect(documents.getIndex())[2]);
		request.setAttribute("select3", selects.getSelect(documents.getIndex())[3]);

		/**
		 * 前回のアンケート文を.jspへ送る しかし、ページに表示するかどうかはGetCookie.javaで判定する
		 */
		GraphData(request, response);

		/**
		 * アンケート文を更新するかどうかの判定
		 */
		ChangeDocument(timerTask.Changeboolean);

		/**
		 * 投票したかどうかの判定
		 */
		if (request.getAttribute("result") != null) {
			GetResult(request, response);
		}

		/**
		 * questionnaire.jspをリクエストで送る
		 */
		RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/questionnaire.jsp");
		dispacher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * アンケート文の更新の時間計測を開始する
	 */
	public void TimerSet() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		try {
			Date date = format.parse("2018/10/5 04:00:00");
			// 午前4時にアンケート内容を更新
			timer.schedule(timerTask, 1000 * 10 * 60 * 24);
		} catch (java.text.ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * 設定時間を経過したときにアンケート文を更新する (timertask.javaのChangebooleanで変更を判定)
	 */
	public void ChangeDocument(boolean Changeboolean) {
		if (Changeboolean) {
			result1 = result2 = result3 = result4 = 0;
			old_result1 = timerTask.getResult1();
			old_result2 = timerTask.getResult2();
			old_result3 = timerTask.getResult3();
			old_result4 = timerTask.getResult4();
			timerTask.Changeboolean = false;
		}
	}

	/**
	 * グラフのデータをjspへ送る setAttribute()で投票数と選択文をそれぞれ配列で送る
	 */
	public void GraphData(HttpServletRequest request, HttpServletResponse response) {
		if (documents.getIndex() != 0) {

			request.setAttribute("old_documents", documents.getDocument(documents.getIndex() - 1));

			String[] old1 = { String.valueOf(old_result1), selects.getSelect(documents.getIndex() - 1)[0] };
			request.setAttribute("old_result0", old1);

			String[] old2 = { String.valueOf(old_result2), selects.getSelect(documents.getIndex() - 1)[1] };
			request.setAttribute("old_result1", old2);

			String[] old3 = { String.valueOf(old_result3), selects.getSelect(documents.getIndex() - 1)[2] };
			request.setAttribute("old_result2", old3);

			String[] old4 = { String.valueOf(old_result4), selects.getSelect(documents.getIndex() - 1)[3] };
			request.setAttribute("old_result3", old4);
		}

	}

	/**
	 * 投票時の処理
	 * 
	 */
	public void GetResult(HttpServletRequest request, HttpServletResponse response) {
		// 投票の値を取得
		int result = Integer.parseInt((String) request.getAttribute("q"));

		// 投票値を保存
		if (result == 1) {
			result1++;
		} else if (result == 2) {
			result2++;
		} else if (result == 3) {
			result3++;
		} else if (result == 4) {
			result4++;
		}
		timerTask = new timerTask(documents, result1, result2, result3, result4);

		/**
		 * Cookieでユーザのブラウザに投票値を付与
		 */
		Cookie cookied = new Cookie("questionnaire" + String.valueOf(documents.getIndex()), String.valueOf(result));

		// Cookieの生存時間を設定
		cookied.setMaxAge(2 * 60 * 60 * 24);
		response.addCookie(cookied);
		request.setAttribute("result", null);
	}
}