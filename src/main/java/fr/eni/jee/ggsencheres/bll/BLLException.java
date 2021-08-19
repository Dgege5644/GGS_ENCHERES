package fr.eni.jee.ggsencheres.bll;

import java.util.List;

public class BLLException extends Exception{

	
	private List<String> messages;
	private String message;
	
	
	public BLLException(String message) {
		super(message);
		
	}


	public List<String> getMessages() {
		return messages;
	}


	public String getMessage() {
		return message;
	}
	
	
	}
	
	
	
	
	


