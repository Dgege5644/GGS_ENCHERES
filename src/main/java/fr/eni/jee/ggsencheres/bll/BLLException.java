package fr.eni.jee.ggsencheres.bll;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception{

	//On définit le conteneur de messages
	private List<String> messages;
	
	private static final long serialVersionUID = 1L;
	//constructeur d'un message d'erreur unique (exemple connexion)
	public BLLException(String message) {
		super(message);
		
	}


	public BLLException() {
		// on construit le conteneur de messages
		messages= new ArrayList<String>();
	}
	
	//On définit des méthodes de gestion du conteneur de messages
		/**
		 * pour ajouter un message d'erreur dans le conteneur
		 */
	public void addMessage(String message) {
		if (message != null  ) {
			messages.add(message);
		}
	}
	
	/**
	 * pour récuperer le conteneur et son contenu
	 */
	public final List<String> getMessages() {
		return messages;
	}
	/**
	 * pour savoir si le conteneur contient des messages
	 */
	public boolean isEmpty() {
		return messages.isEmpty();
	}

	
	
	
	
	
	}
	
	
	
	
	


