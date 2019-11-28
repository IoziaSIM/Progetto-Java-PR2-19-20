import java.util.ArrayList;
import java.util.List;

public class Categories<E extends Data> {
	
	private String nome = null;
	private ArrayList<String> friendlist;
	private List<E> datalist; 
	
	public Categories(String nome) {
		if(nome == null) 
			throw new NullPointerException();
		
		this.nome = nome;
		this.friendlist = new ArrayList<String>();
		this.datalist = new ArrayList<E>();
	}
		
	
	public String getName() {
		return nome;
	}
		
	
	public void newFriend(String friend) {
		friendlist.add(friend);
	}
	
	
	public void lessFriend(String friend) {
		friendlist.remove(friend);
	}
	
	
	public boolean containsFriend (String friend) {
		if(friendlist.contains(friend) == true) 
			return true;
		else
			return false;
	}
	
	public List<String> getFriendList() {
		return friendlist;
	}
	
	public void printFriendDatabase() {
		for(int i = 0;i < friendlist.size(); i++)
			 System.out.println(friendlist.get(i));
	}
	
	
	public void newDato(E dato) {
		datalist.add(dato);
	}
	
	
	public E getDato(E dato) {
		int i = datalist.indexOf(dato); 
		return datalist.get(i);
	}
	
		
	public E removeDato (E dato) {
		int i = datalist.indexOf(dato);
		return datalist.remove(i);
	}
	
	
	public boolean containsDato(E dato) {
		if(datalist.contains(dato) == true) 
			return true;
		else
			return false;
	}
	
	
	public List<E> getDatabase() {
		return datalist;
	}
	
	
	public void printDatabase() {
		for(int i=0; i < datalist.size(); i++)
			 datalist.get(i).Display();
	}
	
}