package todocontroller;

import java.util.ArrayList;

/**
 * アンケート文を定義
 * 
 * @author admin
 *
 */
public class Documents {
	private ArrayList<String> documents;
	private int CurrentIndex;

	public Documents() {
		documents = new ArrayList<String>();
		String document1 = "この中でどうしてもこれだけは見たくないな" + "<br />と思う映画のジャンルは何ですか？";
		String document2 = "この中でかっこいいなと思う職業はどれですか？";
		documents.add(document1);
		documents.add(document2);
		CurrentIndex = 0;
	}

	public String getDocument(int index) {
		return this.documents.get(index);
	}

	public int getIndex() {
		return CurrentIndex;
	}

	public void PlusIndex() {
		CurrentIndex++;
	}
}
