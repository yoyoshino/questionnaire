package todocontroller;

import java.util.ArrayList;

/**
 * Selectクラスで四択の選択肢の配列をさらにArrayListに格納する
 * 
 * @author admin
 *
 */
public class Selects {
	//四択選択肢をArrayListに格納
	ArrayList<String[]> selects;

	/**
	 * 四択の選択肢をここで定義
	 */
	public Selects() {
		selects = new ArrayList<String[]>();
		Select select1 = new Select(" SF映画やアクション映画", "ホラー映画", "恋愛映画や青春映画", "コメディ映画");
		Select select2 = new Select("公務員", "システムエンジニア", "プロゲーマー", "事務");
		this.selects.add(select1.select);
		this.selects.add(select2.select);
	}

	public String[] getSelect(int index){
		return selects.get(index);
	}
}
