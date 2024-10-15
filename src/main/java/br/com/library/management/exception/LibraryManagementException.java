package br.com.library.management.exception;

public class LibraryManagementException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public LibraryManagementException(String message) {
	        super(message);
	    }

	    public LibraryManagementException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
