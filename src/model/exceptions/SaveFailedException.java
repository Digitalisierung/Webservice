package model.exceptions;

public class SaveFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SaveFailedException() {
		
	}
	
	public SaveFailedException(String message) {
		super(message);
	}

}
