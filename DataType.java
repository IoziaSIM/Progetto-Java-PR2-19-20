import java.util.ArrayList;

public interface DataType {
	/*
	 Overview: Tipo di dato modificabile che rappresenta un post con un contenuto, associato a una categoria quando viene inserito
	 		in una di queste, caratterizzato da un numero di like e dalla lista di amici che hanno messo like
	*/
	
	/*
	 Typical Element:<contenuto,categoria,numeroLike,listaAmici>
							dove contenuto è la stringa che descrive il contenuto del post
							dove categoria è la stringa del nome della categoria associata al post
							dove numeroLike è il numero dei like del post
							dove listaAmici è la lista di amici che hanno messo like al post (i like iniziali sono anonimi)
	*/		
	
	public void Display();	
	/*
	 RETURNS: restituisce le informazioni su this
	 */
	
	public void setCategory(String category);
	/*
	 REQUIRES: Category != null
	 THROWS: NullPointerException se Category == null
	 MODIFIES: this
	 EFFECTS: associa una categoria a this
	 */
	
	public String getContent();
	/*
	 RETURNS: restituisce il contenuto di this
	 */
	
	public String getCategory();
	/*
	 RETURNS: restituisce la categoria di this
	 */
	
	public int getNumLike();
	/*
	 RETURNS: restituisce il numero di likes associati a this
	 */
		
	public ArrayList<String> getFriendLike();
	/*
	 RETURNS: restituisce la lista di amici che hanno messo like a this
	 */
	
	public void newLike(String friend)
			throws NullPointerException, IllegalArgumentException;
	/*
	 REQUIRES: friend != null && l'amico "friend" non è presente nella lista di amici che hanno messo like
	 THROWS: NullPointerException se friend == null
	 		 IllegalArgumentException se l'amico "friend" è presente nella lista di amici che hanno messo like
	 MODIFIES: this
	 EFFECTS: aggiunge un like al numero dei likes e inserisce l'amico "friend" alla lista di amici che hanno messo like
	 */
	
	public void lessLike(String friend)
			throws NullPointerException, IllegalArgumentException;
	/*
	 REQUIRES: friend != null && l'amico "friend" è presente nella lista di amici che hanno messo like
	 THROWS: NullPointerException se friend == null
	 		 IllegalArgumentException se l'amico "friend" non è presente nella lista di amici che hanno messo like
	 MODIFIES: this
	 EFFECTS: sottrae un like dal numero dei likes e rimuove l'amico "friend" dalla lista di amici che hanno messo like
			 */
}