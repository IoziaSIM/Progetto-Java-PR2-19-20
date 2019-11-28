import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BoardV2<E extends Data> implements DataBoard<E> {

	/*
	 AF:<{categoria_0,...,categoria_k},password> tali che f(categoria_i) =>
    		<nomeCategoria, {amico_1,...,amico_j}, {dato_1,...,dato_h}> 
   				con 0 <= i <= k e la password deve coincidere con la password del proprietario
	 */
	
	/*
	 IR:password != null && bacheca != null && bacheca.size() >= 0 &&
	 		for all key. key appartiene all'insieme delle chiavi => bacheca.get(key) != null
	 		for all key. key_i != key_j (proprietà hashmap)
	 		for all key_i,key_j. key_i,key_j appartengono all'insieme delle chiavi => bacheca.get(key_i).getName() != bacheca.get(key_j).getName
	 		for all key. key appartiene all'insieme delle chiavi => bacheca.get(key).getFriendList() != null
	 		for all key,m,n. key appartiene all'insieme delle chiavi && 0 <= m,n <= bacheca.get(key).getFriendList.size() && m!=n => bacheca.get(key).getFriendList().get(m) != bacheca.get(key).getFriendList.get(n)
	 		for all key. key appartiene all'insieme delle chiavi => bacheca.get(i).getDatabase() != null
	 		for all key,m,n. key appartiene all'insieme delle chiavi && 0 <= m,n <= bacheca.get(key).getDatabase.size() && m!=n => bacheca.get(key).getDatabase().get(m) != bacheca.get(key).getDatabase.get(n)
	 */
	
	
	private HashMap<String,Categories<E>> bacheca; 
	private String password;
	
	public BoardV2(String password) {
		if(password == null)
			throw new NullPointerException();
		
		this.bacheca = new HashMap<String,Categories<E>>();
		this.password = password;
	}
	
	
	//Crea una nuova categoria di dati 
	@Override
	public void createCategory (String passw, String Category) 
			 throws NullPointerException, DuplicateException, WrongPasswordException {
		
		if (Category == null || passw == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		if(bacheca.containsKey(Category) == true)
			throw new DuplicateException("Categoria già presente");
		else {		
			Categories<E> nuovacategoria = new Categories<E>(Category);
			bacheca.put(Category, nuovacategoria);
		}
		
	}

	//Rimuove una categoria di dati 
	@Override
	public void removeCategory(String passw, String Category)
			 throws NullPointerException, ElementNotFoundException, WrongPasswordException{
		
		if (Category == null || passw == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		 
		if (bacheca.containsKey(Category) == true) 
			bacheca.remove(Category);
		else
			throw new ElementNotFoundException("Categoria non presente");
		
	}
	
	
	//Aggiunge un amico ad una categoria di dati 
	@Override
	public void addFriend(String passw, String Category, String friend)
			 throws NullPointerException, ElementNotFoundException, WrongPasswordException, DuplicateException {
		
		if (Category == null || passw == null || friend == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		if (bacheca.containsKey(Category) == true) {
			if(bacheca.get(Category).containsFriend(friend) == false)
				bacheca.get(Category).newFriend(friend);
			else
				throw new DuplicateException("Amico già presente");  
		}
		else
			throw new ElementNotFoundException("Categoria non presente");
		
	}
	
	
	//Rimuove un amico da una categoria di dati 
	@Override
	public void removeFriend(String passw, String Category, String friend)
			 throws NullPointerException, ElementNotFoundException, WrongPasswordException {
		
		if (Category == null || passw == null || friend == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		if (bacheca.containsKey(Category) == true) {
			if(bacheca.get(Category).containsFriend(friend) == true)
				bacheca.get(Category).lessFriend(friend);
			else
				throw new ElementNotFoundException("Amico non presente");
		}
		else
			throw new ElementNotFoundException("Categoria non presente");
	}
	
	//Inserisce un dato in bacheca  
	@Override
	public boolean put(String passw, E dato, String Category) 
			 throws NullPointerException, WrongPasswordException, ElementNotFoundException {
		
		if (Category == null || passw == null || dato == null) throw new NullPointerException();
		if(password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		if(bacheca.containsKey(Category)) {
			if(bacheca.get(Category).containsDato(dato) == false) {
				dato.setCategory(Category);
				bacheca.get(Category).newDato(dato);
				return true;
			} 
		}
		else
			throw new ElementNotFoundException("Categoria non presente");
		
		return false;
	}
	
	
	//Ottiene una copia di un dato in bacheca 
	@Override      
	 public E get(String passw, E dato) 
			 throws NullPointerException, WrongPasswordException {
		
		if (passw == null || dato == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
	
		E tmp;        
		String dataCategory = dato.getCategory();     
		
		if(bacheca.containsKey(dataCategory)) 
			if(bacheca.get(dataCategory).containsDato(dato) == true) {	
				tmp = bacheca.get(dataCategory).getDato(dato);
				return tmp;
			}
		
		return null;	//dato non presente
		
	}
	

	//Rimuove un dato dalla bacheca 
	@Override
	public E remove(String passw, E dato) 
			 throws NullPointerException, WrongPasswordException {
		
		if (passw == null || dato == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		String dataCategory = dato.getCategory();
				
		if(bacheca.containsKey(dataCategory)) 
			return bacheca.get(dataCategory).removeDato(dato);
		
		return null;	//dato non presente
	}

	
	//Ottiene la lista dei dati in bacheca su una determinata categoria 
	@Override
	public List<E> getDataCategory(String passw, String Category)
			 throws NullPointerException, WrongPasswordException {
		
		if (passw == null || Category == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		List<E> tmpList = new ArrayList<E>();     
		
		if(bacheca.containsKey(Category)) { 
				tmpList = bacheca.get(Category).getDatabase();
				return tmpList;
		}
		
		return null;	//categoria non presente

	}
	

	//Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like
	@Override
	public Iterator<E> getIterator(String passw) 
			 throws NullPointerException, WrongPasswordException {
		
		if (passw == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
				
		List<E> allData = new ArrayList<E>();
		
		for (String key : bacheca.keySet()) 
			allData.addAll(bacheca.get(key).getDatabase());
		
		allData.sort(new DecrOrder());
		
		return new MyIterator(allData.iterator());   
	}
	
	
	//Aggiunge un like a un dato 
	@Override
	 public void insertLike(E dato, String friend) 
			 throws NullPointerException, ElementNotFoundException {
		
		if (dato == null || friend == null) throw new NullPointerException();
		
		String dataCategory = dato.getCategory();
		if(dataCategory == null) throw new ElementNotFoundException("Dato non presente");
	
		if(bacheca.containsKey(dataCategory)) {
			if(bacheca.get(dataCategory).containsFriend(friend) == true)
				bacheca.get(dataCategory).getDato(dato).newLike(friend);
			else
				throw new ElementNotFoundException("Amico non presente");
		}
	
	}
	

	//Rstituisce un iteratore (senza remove) che genera tutti i dati in bacheca visibili da un amico
	@Override
	public Iterator<E> getFriendIterator(String friend)
	 		throws NullPointerException, ElementNotFoundException {
		
		if (friend == null) throw new NullPointerException();
		
		List<E> allDataFriend = new ArrayList<E>();
		boolean found = false;
		
		for (String key : bacheca.keySet()) 
			if (bacheca.get(key).containsFriend(friend) == true) {
				found = true;
				allDataFriend.addAll(bacheca.get(key).getDatabase());
			}
		
		if(found == false) throw new ElementNotFoundException("Amico non presente");
		
		return new MyIterator(allDataFriend.iterator());
	}
	
	
	 private class DecrOrder implements Comparator<E> {			
		 
		 @Override
	     public int compare(E dato1, E dato2) {
			if (dato1.getNumLike() > dato2.getNumLike())
	    	 	return -1;
			else if (dato1.getNumLike() < dato2.getNumLike())
	    	 	return 1;
	     	else
	     		return 0;
	     	}
		 
	   }
	 
	 
	 private class MyIterator implements Iterator<E> {        
		private Iterator<E> itr;
		
		public MyIterator(Iterator<E> itr) {
			if (itr == null) 
				throw new NullPointerException();
			else
				this.itr = itr;
		}
		
		@Override
		public boolean hasNext() {
			return itr.hasNext();
		}

		@Override
		public E next() {
			return itr.next();
		}
				
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove non permessa");
		}
		
	}
	 
	 
	@Override
	public void printCategory() {
		 for (String key : bacheca.keySet())
			 System.out.println(bacheca.get(key).getName());
	}
	 
	
	@Override
	public void printFriend(String Category) {
		 
		 if(Category == null)
			 for (String key : bacheca.keySet()) {
				 System.out.println("La categoria " + bacheca.get(key).getName() + " può essere visualizzata da:");
				 bacheca.get(key).printFriendDatabase();
			 }
		 
		 else {
			  if(bacheca.containsKey(Category) == true) {
					 System.out.println("La categoria " + Category + " ora può essere visualizzata da:");
			 		 bacheca.get(Category).printFriendDatabase();
				 }
		 }
		 
	 }
	 
	
	@Override
	public void printData(String Category) {
		 
		 if(Category == null)
			 for (String key : bacheca.keySet()) {
				 System.out.println("La categoria " + bacheca.get(key).getName() + " contiene i seguenti dati:");
				 bacheca.get(key).printDatabase();
			 }
		 
		 else {
			 if(bacheca.containsKey(Category) == true) {
					 System.out.println("La categoria " + Category + " ora contiene i seguenti dati:");
			 		 bacheca.get(Category).printDatabase();
			 }
		 }

	 } 
		
}