import java.util.List;
import java.util.Iterator;

public interface DataBoard<E extends Data> {
   /*
	Overview: Collezione di dati di tipo E (che estende il tipo Data), divisi in categorie, la quale � gestita dal proprietario,
			che conosce la password per accedervi, e decide quali dati condividere e con quali amici condividerli;
		 		quest'ultimi possono eventualmente porre un like al dato visualizzato 
	*/
	
   /*
	Typical Element:<password,nomeCategoria,listaAmici,listaDati>
						dove password � la stringa richiesta per effettuare una modifica o richiedere dati in bacheca
						dove nomeCategoria � la stringa univoca che identifica ogni categoria in bacheca
						dove listaAmici � la lista di amici che possono visualizzare la categoria associata 
						dove listaDati � la lista dei dati presenti nella categoria associata
	*/
	
	
	public void createCategory(String passw, String Category) 
			 throws NullPointerException, DuplicateException, WrongPasswordException;
	/*
	 REQUIRES: Category != null && passw != null && !this.contains(Category) 
	 THROWS: NullPointerException se Category || passw == null
	 		 DuplicateException se la categoria "Category" � gi� presente
	 		 WrongPasswordException se la password � errata
	 MODIFIES: this
	 EFFECTS: aggiunge una nuova categoria "Category" se sono superati i controlli di identit�
	 	 */
	 
	 
	public void removeCategory(String passw, String Category)
			 throws NullPointerException, ElementNotFoundException, WrongPasswordException;
	/*
	 REQUIRES: Category != null && passw != null && this.contains(Category)
	 THROWS: NullPointerException se Category || passw == null
	 		 ElementNotFoundException se la categoria "Category" non � presente
	 		 WrongPasswordException se la password  � errata
	 MODIFIES: this
	 EFFECTS: rimuove la categoria "Category" se sono superati i controlli di identit�	
	 */
	 
	 
	public void addFriend(String passw, String Category, String friend)
			 throws NullPointerException, ElementNotFoundException, DuplicateException, WrongPasswordException;
	/*
	 REQUIRES: Category != null && passw != null && friend != null && this.contains(Category) && !Category.contains(friend) 
	 THROWS: NullPointerException se Category || passw || friend == null
	 		 ElementNotFoundException se la categoria "Category" non � presente
	 		 DuplicateException se l'amico "friend" � gi� presente
	 		 WrongPasswordException se la password � errata
	 MODIFIES: this
	 EFFECTS: aggiunge un amico "friend" alla lista delle persone che possono visualizzare la categoria "Category" se sono superati i controlli di identit�
	 */
	 
	 
	public void removeFriend(String passw, String Category, String friend)
			 throws NullPointerException, ElementNotFoundException, WrongPasswordException;
	/*
	 REQUIRES: Category != null && passw != null && friend != null && this.contains(Category) && Category.contains(friend)
	 THROWS: NullPointerException se Category || passw  || friend == null
	 		 ElementNotFoundException se la categoria "Category" non � presente o se l'amico "friend" non � presente
	 		 WrongPasswordException se la password � errata
	 MODIFIES: this
	 EFFECTS: rimuove l'amico "friend" dalla lista delle persone che possono visualizzare la categoria "Category" se sono superati i controlli di identit�
	 */
	 
	 
	public boolean put(String passw, E dato, String Category) 
			 throws NullPointerException, WrongPasswordException, ElementNotFoundException;
	/*
	  REQUIRES: Category != null && passw != null && dato != null && this.contains(Category) 
	  THROWS: NullPointerException se Category || passw || dato == null
	  		  ElementNotFoundException se la categoria "Category" non � presente
	  		  WrongPasswordException se la password � errata
	  MODIFIES: this
	  EFFECTS: inserisce un dato alla categoria Category se sono superati i controlli di identit�
	  RETURNS: true se il dato � inserito correttamente, false se il dato era gi� presente nella categoria "Category" (non ci sono duplicati di dati) 
	  */
	 
	 
	public E get(String passw, E dato) 
			 throws NullPointerException, WrongPasswordException; 
	/*
	  REQUIRES: passw != null && dato != null 
	  THROWS: NullPointerException se passw || dato == null
	          WrongPasswordException se la password � errata
	  MODIFIES: none 
	  RETURNS: restituisce una copia del dato "dato" (null se il dato non � presente) se sono superati i controlli di identit�	 
	  */
	 
	 
	public E remove(String passw, E dato) 
			 throws NullPointerException, WrongPasswordException; 
	/*
     REQUIRES: passw != null && dato != null 
	 THROWS: NullPointerException se passw || dato == null
	         WrongPasswordException se la password � errata
	 MODIFIES: this 
	 EFFECTS: rimuove il dato "dato" dalla bacheca se sono superati i controlli di identit�
	 RETURNS: restituisce il dato "dato" che � stato eliminato (null se il dato non � presente)	 
	 */
	 
	 
	public List<E> getDataCategory(String passw, String Category)
			 throws NullPointerException, WrongPasswordException; 
	/*
	 REQUIRES: Category != null && passw != null 
	 THROWS: NullPointerException se Category || passw == null
	 		 WrongPasswordException se la password � errata
	 MODIFIES: none
	 RETURNS: restituisce una lista dei dati facenti parte della categoria "Category" (null se la categoria non � presente) se sono superati i controlli di identit�
	 */
	 
	 
	public Iterator<E> getIterator(String passw) 
			 throws NullPointerException, WrongPasswordException; 
	 /*
	 REQUIRES: passw != null 
     THROWS: NullPointerException se passw == null
    		 WrongPasswordException se la password � errata
     MODIFIES: none
     RETURNS: restituisce un iteratore che genera tutti i dati in bacheca ordinati per il numero dei likes se sono superati i controlli di identit�
	 */
	 
	 	 
	void insertLike(E dato, String friend) 
			 throws NullPointerException, ElementNotFoundException;
	/*
	 REQUIRES: friend != null && dato != null && this.contains(dato) && dato.getCategory.contains(friend)
     THROWS: NullPointerException se friend || dato == null
    		 ElementNotFoundException se il dato "dato" non � presente in bacheca o se l'amico "friend" non pu� visualizzare il dato 
     MODIFIES: this
     EFFECTS: aggiunge un like al dato "dato" visibile dall'amico "friend"  
	 */ 
	 
		 
	public Iterator<E> getFriendIterator(String friend)
	 		throws NullPointerException, ElementNotFoundException; 
	/*
	 REQUIRES: friend != null && this.contains(friend)
     THROWS: NullPointerException se friend == null
    		 ElementNotFoundException se l'amico "friend" non pu� visualizzare nessun dato in bacheca
     MODIFIES: none
     RETURNS: restituisce un iteratore che genera tutti i dati in bacheca condivisi visibili da friend 
	 */
		
	
	public void printCategory();
	/*
	 EFFECTS: stampa i nomi di tutte le categorie presenti in bacheca  
	 */ 
	
	
	 public void printFriend(String Category);
	 /*
	 EFFECTS: per ogni categoria stampa la lista di amici che possono visualizzarla oppure stampa la lista di amici della categoria Category  
	 */ 
	 
	 
	 public void printData(String Category);
	 /*
	 EFFECTS: per ogni categoria stampa la lista di dati che sono presenti in essa oppure stampa la lista di dati della categoria Category  
	 */ 
	 
}