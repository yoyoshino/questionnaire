package todocontroller;

/**
 * 四択選択肢の四つの文を一つの配列に収める
 * @author admin
 *
 */
public class Select {
	String[] select;
	
	/**
	 * 四択を一つの配列に保存
	 * @param select1
	 * @param select2
	 * @param select3
	 * @param select4
	 */
	public Select(String select1,String select2,String select3,String select4){
		this.select = new String[4];
		this.select[0] = select1;
		this.select[1] = select2;
		this.select[2] = select3;
		this.select[3] = select4;
	}
	
	public String getSelect(int index){
		return this.select[index];
	}
}
