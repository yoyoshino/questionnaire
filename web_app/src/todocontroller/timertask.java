package todocontroller;

import java.util.TimerTask;

/**
 * アンケート内容変更時に呼び出され、処理を行う
 * 
 * @author admin
 *
 */
 class timerTask extends TimerTask {

	Documents documents;
	int result1;
	int result2;
	int result3;
	int result4;
	int old_result1;
	int old_result2;
	int old_result3;
	int old_result4;
	boolean Changeboolean = false;

	/**
	 * コンストラクタ
	 */
	public timerTask(Documents documents, int result1, int result2, int result3, int result4) {
		this.documents = documents;
		this.result1 = result1;
		this.result2 = result2;
		this.result3 = result3;
		this.result4 = result4;
		// アンケート結果を更新
	}

	public void run() {
		// アンケート文を更新
		documents.PlusIndex();
		old_result1 = result1;
		old_result2 = result2;
		old_result3 = result3;
		old_result4 = result4;
		ChangeQuestion();
	}
	
	public int getResult1(){
		return old_result1;
	}
	public int getResult2(){
		return old_result2;
	}
	
	public int getResult3(){
		return old_result3;
	}
	
	public int getResult4(){
		return old_result4;
	}
	
	public Documents getDocument(){
		return documents;
	}
	
	public boolean ChangeQuestion(){
		Changeboolean = true;
		return Changeboolean;
	}
}
