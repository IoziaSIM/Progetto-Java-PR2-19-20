import java.util.ArrayList;

public class Data implements DataType {

	/*
	 AF:<content,category,numLike,{friend_0...friend_k}> 
	 		con 0 <= k <= numLike
	 */
	
	/*
	 IR: content != null && numLike >= 0 && friendLike != null && friendLike.size() >= 0
	 		for all i. 0 <= i <= friendLike.size() => friendLike.get(i) != null
	 		for all i,j. 0 <= i,j <= friendLike.size() && i!=j => friendLike.get(i) != friendLike.get(j)
	 */
	
	
	private String content;
	private String category;
	private int numLike;
	private ArrayList<String> friendLike;
	
	public Data(String content, int numLike) {
		if(content == null) 
			throw new NullPointerException();
		if(numLike < 0) 
			throw new IllegalArgumentException();
		
		this.content = content;
		this.category = null;  
		this.numLike = numLike;
		this.friendLike = new ArrayList<String>(); 
	}
	
	@Override
	public void Display() {
		System.out.println("Contenuto: " + this.getContent() + "  Categoria: " + this.getCategory() + "  Likes: " + this.getNumLike() );
	}	
	
	@Override
	public void setCategory(String Category)
		throws NullPointerException {
		if(Category == null) throw new NullPointerException();
		category = Category;
	}
	
	@Override
	public String getContent() {
		return content;
	}
	
	@Override
	public String getCategory() {
		return category;
	}
	
	@Override
	public int getNumLike() {
		return numLike;
	}
	
	@Override
	public ArrayList<String> getFriendLike() {     
		return friendLike;
	}
	
	@Override
	public void newLike(String friend)
		throws NullPointerException, IllegalArgumentException {
		if(friend == null) throw new NullPointerException();
		
		if(friendLike.contains(friend) == false) {
			numLike+=1;
			friendLike.add(friend);
		}
		else
			throw new IllegalArgumentException("Inserito amico che ha già messo like");
	}
	
	@Override
	public void lessLike(String friend)
		throws NullPointerException, IllegalArgumentException {
		if(friend == null) throw new NullPointerException();
		
		if(friendLike.contains(friend) == true) {
			numLike-=1;
			friendLike.remove(friend);
		}
		else
			throw new IllegalArgumentException("Inserito amico che non ha messo like");
	}
	
	public void printFriendLike(String friend) {
		if(friendLike.contains(friend) == true) {
			int num = this.getNumLike()-1;
			System.out.println("Ora questo dato piace a " + friend + " e ad altre " + num + " persone");
		}
	}
	
}