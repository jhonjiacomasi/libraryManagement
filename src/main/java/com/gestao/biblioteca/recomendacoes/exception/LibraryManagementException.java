package com.gestao.biblioteca.recomendacoes.exception;

public class LibraryManagementException extends RuntimeException{
	 public LibraryManagementException(String message) {
	        super(message);
	    }

	    public LibraryManagementException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
