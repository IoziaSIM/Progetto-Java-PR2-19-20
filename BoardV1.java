import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

public class BoardV1<E extends Data> implements DataBoard<E> {
	
	/*AF:<{categorie_0,...,categorie_k}, password> tali che this.get(i) =>
     		<nomeCategoria, {amico_1,...,amico_j}, {dato_1,...,dato_h}>
     			con 0 <= i <= k e la password deve coincidere con la password del proprietario
     */
	
	/*
	 IR: password != null && bacheca != null && bacheca.size() >= 0 &&
	 		for all i. 0 <= i <= bacheca.size() => bacheca.get(i) != null
	 		for all i,j. 0 <= i,j <= bacheca.size() && i!=j => bacheca.get(i).getName() != bacheca.get(j).getName
	 		for all i. 0 <= i <= bacheca.size() => bacheca.get(i).getFriendList() != null
	 		for all i,j,m,n. 0 <= i <= bacheca.size() && 0 <= m,n <= bacheca.get(i).getFriendList.size() && m!=n => bacheca.get(i).getFriendList().get(m) != bacheca.get(i).getFriendList.get(n)
	 		for all i. 0 <= i <= bacheca.size() => bacheca.get(i).getDatabase() != null
	 		for all i,j,m,n. 0 <= i <= bacheca.size() && 0 <= m,n <= bacheca.get(i).getDatabase.size() && m!=n => bacheca.get(i).getDatabase().get(m) != bacheca.get(i).getDatabase.get(n)
	 */
	
	
	private List<Categories<E>> bacheca; 
	private String password;
	
	public BoardV1(String password) {
		if(password == null)
			throw new NullPointerException();
		
		this.bacheca = new ArrayList<Categories<E>>();
		this.password = password;
	}
	
	
	//Crea una nuova categoria di dati 
	@Override
	public void createCategory (String passw, String Category) 
			 throws NullPointerException, DuplicateException, WrongPasswordException {
		
		if (Category == null || passw == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		for (int i = 0; i < bacheca.size(); i++) 
			if(bacheca.get(i).getName().equals(Category) == true) throw new DuplicateException("Categoria già presente");
			
		Categories<E> nuovacategoria = new Categories<E>(Category);
		bacheca.add(nuovacategoria);
	}

	
	//Rimuove una categoria di dati 
	@Override
	public void removeCategory(String passw, String Category)
			 throws NullPointerException, ElementNotFoundException, WrongPasswordException{
		
		if (Category == null || passw == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
			
		boolean found = false;
		for (int i = 0; i < bacheca.size() || found == false; i++) 
			if (bacheca.get(i).getName().equals(Category) == true) {
				found = true;
				bacheca.remove(i);
			}
		
		if (found == false) throw new ElementNotFoundException("Categoria non presente");
		
	}
	
	
	//Aggiunge un amico ad una categoria di dati 
	@Override
	public void addFriend(String passw, String Category, String friend)
			 throws NullPointerException, DuplicateException, WrongPasswordException, ElementNotFoundException {
		
		if (Category == null || passw == null || friend == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		boolean found = false;
		for (int i = 0; i < bacheca.size() || found == false; i++) 
			if (bacheca.get(i).getName().equals(Category) == true) {
				found = true;
				if(bacheca.get(i).containsFriend(friend) == false) 
					bacheca.get(i).newFriend(friend);
				else
					throw new DuplicateException("Amico già presente");
		}
		
		if (found == false) throw new ElementNotFoundException("Categoria non presente");
		
	}
	
	
	//Rimuove un amico da una categoria di dati 
	@Override
	public void removeFriend(String passw, String Category, String friend)
			 throws NullPointerException, ElementNotFoundException, WrongPasswordException {
		
		if (Category == null || passw == null || friend == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		boolean found = false;
		for (int i = 0; i < bacheca.size() || found == false; i++) 
			if (bacheca.get(i).getName().equals(Category) == true) {
				found = true;
				if(bacheca.get(i).containsFriend(friend) == true)
					bacheca.get(i).lessFriend(friend);
				else 
					throw new ElementNotFoundException("Amico non presente");
			}
		
		if (found == false) throw new ElementNotFoundException("Categoria non presente");
	}
	
	
	//Inserisce un dato in bacheca 
	@Override
	public boolean put(String passw, E dato, String Category) 
			 throws NullPointerException, WrongPasswordException, ElementNotFoundException {
		
		if (Category == null || passw == null || dato == null) throw new NullPointerException();
		if(password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		boolean found = false;	
		for (int i = 0; i < bacheca.size() || found == false; i++) 
			if (bacheca.get(i).getName().equals(Category) == true) {
				found = true;
				if(bacheca.get(i).containsDato(dato) == false) {
					dato.setCategory(Category);
					bacheca.get(i).newDato(dato);
					return true;
				}  
			}
		
		if(found == false) throw new ElementNotFoundException("Categoria non presente");
		return false;	//Non possono esistere dati duplicati
	}
	
	
	// Ottiene una copia di un dato in bacheca 
	@Override
	 public E get(String passw, E dato) 
			 throws NullPointerException, WrongPasswordException {     
		
		if (passw == null || dato == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
	
		E tmp;       
		String dataCategory = dato.getCategory();
				
		for (int i = 0; i < bacheca.size(); i++) 
			if (bacheca.get(i).getName().equals(dataCategory) == true) 
				if(bacheca.get(i).containsDato(dato) == true) {
					tmp = bacheca.get(i).getDato(dato);
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
				
		for (int i = 0; i < bacheca.size(); i++) 
			if (bacheca.get(i).getName().equals(dataCategory) == true) 
				return bacheca.get(i).removeDato(dato);
		
		return null;	//dato non presente
	}

	
	//Ottiene la lista dei dati in bacheca su una determinata categoria 
	@Override
	public List<E> getDataCategory(String passw, String Category)
			 throws NullPointerException, WrongPasswordException {      
		
		if (passw == null || Category == null) throw new NullPointerException();
		if (password.equals(passw) == false) throw new WrongPasswordException("Password errata");
		
		List<E> tmpList = new ArrayList<E>();      
		boolean found = false;
		for (int i = 0; i < bacheca.size() || found == false; i++) 
			if (bacheca.get(i).getName().equals(Category) == true) {
				found = true;
				tmpList = bacheca.get(i).getDatabase();
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
		
		for (int i = 0; i < bacheca.size(); i++) 
			allData.addAll(bacheca.get(i).getDatabase());
		
		allData.sort(new DecrOrder());
		
		return new MyIterator(allData.iterator());   
	}
	
	
	//Aggiunge un like a un dato 
	@Override
	 public void insertLike(E dato, String friend) 
			 throws NullPointerException, ElementNotFoundException {
		
		if (dato == null || friend == null) throw new NullPointerException();
		
		String dataCategory = dato.getCategory();
		if (dataCategory == null) throw new ElementNotFoundException("Dato non presente");
		
		for (int i = 0; i < bacheca.size(); i++) 
			if (bacheca.get(i).getName().equals(dataCategory) == true) {
				if(bacheca.get(i).containsFriend(friend) == true) 
					bacheca.get(i).getDato(dato).newLike(friend);
				else
					throw new ElementNotFoundException("Amico non presente");
			}
	}
	
	
	//Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca visibili da un amico
	@Override
	public Iterator<E> getFriendIterator(String friend)
	 		throws NullPointerException, ElementNotFoundException {
		
		if (friend == null) throw new NullPointerException();
		
		List<E> allDataFriend = new ArrayList<E>();
		boolean found = false;
		
		for (int i = 0; i < bacheca.size(); i++) 
			if (bacheca.get(i).containsFriend(friend) == true) {
				found = true;
				allDataFriend.addAll(bacheca.get(i).getDatabase());
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
		
		 for(int i = 0;i < bacheca.size(); i++)
			 System.out.println(bacheca.get(i).getName());
	
	 }
	 
	 
	 @Override
	 public void printFriend(String Category) {
		
		 if(Category == null)
			 for(int i=0;i < bacheca.size(); i++) {
				 System.out.println("La categoria " + bacheca.get(i).getName() + " può essere visualizzata da:");
				 bacheca.get(i).printFriendDatabase();
			 }
		 
		 else {
			 for(int i=0; i<bacheca.size(); i++)
				 if(bacheca.get(i).getName().equals(Category) == true) {
					 System.out.println("La categoria " + bacheca.get(i).getName() + " ora può essere visualizzata da:");
			 		 bacheca.get(i).printFriendDatabase();
				 }
		 }
		 
	 }
	 
	 
	 @Override
	 public void printData(String Category) {
		 
		 if(Category == null)
			 for(int i=0;i < bacheca.size(); i++) {
				 System.out.println("La categoria " + bacheca.get(i).getName() + " contiene i seguenti dati:");
				 bacheca.get(i).printDatabase();
			 }
		 
		 else {
			 for(int i=0; i<bacheca.size(); i++)
				 if(bacheca.get(i).getName().equals(Category) == true) {
					 System.out.println("La categoria " + bacheca.get(i).getName() + " ora contiene i seguenti dati:");
			 		 bacheca.get(i).printDatabase();
				 }
		 }

	 }
	 
}