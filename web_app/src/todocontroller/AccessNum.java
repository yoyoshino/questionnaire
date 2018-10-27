package todocontroller;

/**
 * アクセス数を定義
 * @author admin
 *
 */
public class AccessNum {
	private int access;
	
	public AccessNum(){
		access = 0;
	}
	
	public int getAccessNum(){
		return access;
	}
	
	public  void clientAccess(){
		access++;
	}
}
