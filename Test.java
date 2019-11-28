import java.util.List;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		
		//creazione di password
		String P1 = "ProgettoPassword";
		String P2 = "Hello";
		String P3 = null;
				
		//creazione dei dati
		Data D1 = new Data("Roma", 45); 
		Data D2 = new Data("Milano", 67);
		Data D3 = new Data("Compleanno", 16);
		Data D4 = null;
		Data D5 = new Data("Jumanji", 56);
		Data D6 = new Data("Avengers", 109);	//non inserito
		Data D7 = new Data("Joker", 43);		//rimosso
		Data D8 = new Data("Pisa", 12);
		Data D9 = new Data("Catania", 28);
		Data D10 = new Data("Laurea", 97);
		Data D11 = new Data("Interstellar", 127);
		Data D12 = new Data("Battesimo", 32);
		
		//creazione di categorie
		String C1 = "Città";
		String C2 = "Foto";
		String C3 = "Film";
		String C4 = "Cibo";			//rimossa
		String C5 = null;
		String C6 = "Paesaggi";		//non inserita
		
		//creazione di amici
		String A1 = "Gabriele";
		String A2 = "Stefano";
		String A3 = "Luciano";
		String A4 = "Salvo";
		String A5 = "Davide";
		String A6 = "Raffaele";		 	//rimosso
		String A7 = "Alessandro";		//non inserito
		String A8 = "Nico";
		String A9 = null;
		String A10 = "Rocco";
		String A11 = "Andrea";
		String A12 = "Federico";
		
		//creazione della bacheca
		DataBoard<Data> collezione = null;
		try{
			collezione = new BoardV2<Data>(P1);		//per testare l'implementazione alternativa, basta cambiare V1 in V2, o viceversa
			//collezione = new BoardV1<Data>(P3);	//NullPointerException(passw)	
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		
		//inserimento di categorie
		try {
			collezione.createCategory(P1, C1);
			collezione.createCategory(P1, C2);
			collezione.createCategory(P1, C3);
			collezione.createCategory(P1, C4);
			
			System.out.println("Sono state inserite in bacheca le categorie: ");
			collezione.printCategory();
			System.out.println("");
					
			//collezione.createCategory(P2, C6);	//WrongPasswordException
			//collezione.createCategory(P3, C6);	//NullPointerException(passw)
			//collezione.createCategory(P1, C5);	//NullPointerException(category)
			//collezione.createCategory(P1, C2);	//DuplicateException(category)
		}
		catch(NullPointerException | DuplicateException | WrongPasswordException e) {
			e.printStackTrace();
		}
		
		//rimozione di categorie
		try {
			collezione.removeCategory(P1, C4);
			
			System.out.println("E'stata rimossa la categoria: " + C4);
			System.out.println("Le categorie rimanenti in bacheca sono: ");
			collezione.printCategory();
			System.out.println("");
			
			//collezione.removeCategory(P2, C4);	//WrongPasswordException
			//collezione.createCategory(P3, C4);	//NullPointerException(passw)
			//collezione.removeCategory(P1, C5);	//NullPointerException(category)
			//collezione.removeCategory(P1, C6);	//ElementNotFoundException(category)
		}
		catch(NullPointerException | ElementNotFoundException | WrongPasswordException e) {
			e.printStackTrace();
		}
		
		
		//aggiunta di amici
		try {
			collezione.addFriend(P1, C1, A1);
			collezione.addFriend(P1, C1, A2);
			collezione.addFriend(P1, C1, A3);
			collezione.addFriend(P1, C1, A4);
			collezione.addFriend(P1, C1, A10);
			collezione.addFriend(P1, C2, A3);
			collezione.addFriend(P1, C2, A4);
			collezione.addFriend(P1, C2, A8);
			collezione.addFriend(P1, C2, A12);
			collezione.addFriend(P1, C3, A5);
			collezione.addFriend(P1, C3, A6);
			collezione.addFriend(P1, C3, A11);
			
			collezione.printFriend(null);
			System.out.println("");
			
			//collezione.addFriend(P2, C1, A7);		//WrongPasswordException
			//collezione.addFriend(P1, C1, A9);		//NullPointerException(friend)
			//collezione.addFriend(P3, C1, A7);		//NullPointerException(passw)
			//collezione.addFriend(P1, C5, A7);		//NullPointerException(category)
			//collezione.addFriend(P1, C2, A4);		//DuplicateException(friend)
			//collezione.addFriend(P1, C6, A7);		//ElementNotFoundException(category)
		}
		catch(NullPointerException | DuplicateException | WrongPasswordException | ElementNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//rimozione di amici
		try {
			collezione.removeFriend(P1, C3, A6);
			
			System.out.println("L'amico " + A6 + " non può più visualizzare la categoria " + C3);
			collezione.printFriend(C3);
			System.out.println("");
			
			//collezione.removeFriend(P2, C2, A4);		//WrongPasswordException	
			//collezione.removeFriend(P1, C6, A4);		//ElementNotFoundException(category)
			//collezione.removeFriend(P1, C1, A5);		//ElementNotFoundException(friend)
			//collezione.removeFriend(P3, C2, A3);		//NullPointerException(passw)
			//collezione.removeFriend(P1, C5, A3);		//NullPointerException(category)
			//collezione.removeFriend(P1, C3, A9);		//NullPointerException(friend)
		}
		catch(NullPointerException | WrongPasswordException | ElementNotFoundException e) {
			e.printStackTrace();
		}
		
		//aggiunta di dati
		try {
			collezione.put(P1, D1, C1);
			collezione.put(P1, D2, C1);
			collezione.put(P1, D3, C2);
			collezione.put(P1, D5, C3);
			collezione.put(P1, D7, C3);
			collezione.put(P1, D8, C1);
			collezione.put(P1, D9, C1);
			collezione.put(P1, D10, C2);
			collezione.put(P1, D11, C3);
			collezione.put(P1, D12, C2);
			
			collezione.printData(null);
			System.out.println("");
			
			//collezione.put(P3, D6, C3);	//NullPointerException(passw)
			//collezione.put(P1, D6, C5);	//NullPointerException(category)
			//collezione.put(P1, D4, C3);	//NullPointerException(dato)
			//collezione.put(P2, D6, C3);	//WrongPasswordException
			//collezione.put(P3, D6, C6);	//ElementNotFoundException(category)
		}
		catch(NullPointerException | WrongPasswordException | ElementNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//copia dei dati
		try {
			Data getD = collezione.get(P1, D2);
			
			System.out.println("Sono stati copiati dalla bacheca i seguenti dati:");
			getD.Display();
			System.out.println("");
			
			//collezione.get(P3, D3);	//NullPointerException(passw)
			//collezione.get(P1, D4);	//NullPointerException(dato)
			//collezione.get(P2, D3);	//WrongPasswordException			
		}
		catch(NullPointerException | WrongPasswordException e) {
			e.printStackTrace();
		}
		
		
		//rimozione di dati
		try {
			Data removeD = collezione.remove(P1, D7);
			
			System.out.println("Sono stati rimossi dalla bacheca i seguenti dati:");
			removeD.Display();
			collezione.printData(D7.getCategory());
			System.out.println("");
			
			//collezione.remove(P3, D2);	//NullPointerException(passw)
			//collezione.remove(P1, D4);	//NullPointerException(dato)
			//collezione.remove(P2, D2);	//WrongPasswordException
		}
		
		
		catch(NullPointerException | WrongPasswordException e) {
			e.printStackTrace();
		}
		
		
		//copia di una categoria
		try {
			List<Data> listCopy = collezione.getDataCategory(P1, C1);
			
			System.out.println("E' stata copiata la categoria " + C1 + " nella quale sono presenti i seguenti dati:");
			for (Data data : listCopy) 
                data.Display();
			System.out.println("");
            
			//collezione.remove(P3, C2);	//NullPointerException(passw)
			//collezione.remove(P1, C5);	//NullPointerException(category)
			//collezione.remove(P2, C2);	//WrongPasswordException
		}
		catch(NullPointerException | WrongPasswordException e) {
			e.printStackTrace();
		}
		
		//iterazione dei dati in base al numero dei like
		try {
			Iterator<Data> itrTest = collezione.getIterator(P1);
			
			System.out.println("I dati ordinati per numero di like presenti in bacheca sono:");
            while (itrTest.hasNext()) 
               itrTest.next().Display();
            System.out.println("");
            
			//collezione.getIterator(P3);	//NullPointerException(passw)
			//collezione.getIterator(P2);	//WrongPasswordException
		}
		catch(NullPointerException | WrongPasswordException e) {
			e.printStackTrace();
		}
		
		
		//aggiunta di un like
		try {
			collezione.insertLike(D1, A4);
			
			System.out.println("L'amico " + A4 + " ha aggiunto un like al seguente dato:");
			D1.Display();
			D1.printFriendLike(A4);
			System.out.println("");
			
			//collezione.insertLike(D4, A1);	//NullPointerException(dato)
			//collezione.insertLike(D2, A9);	//NullPointerException(friend)
			//collezione.insertLike(D6, A2);	//ElementNotFoundException(dato)
			//collezione.insertLike(D1, A7);	//ElementNotFoundException(friend)
		}
		catch(NullPointerException | ElementNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//iterazione dei dati visibili a un amico
		try {
			Iterator<Data> itrFriendTest = collezione.getFriendIterator(A4);
			
			System.out.println("I dati visibili dall'amico " + A4 + " presenti in bacheca sono:");
            while (itrFriendTest.hasNext()) 
               itrFriendTest.next().Display();
                        
			//collezione.getIterator(A9);	//NullPointerException(friend)
			//collezione.getIterator(A7);	//ElementNotFoundException(friend)
		}
		catch(NullPointerException | ElementNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}