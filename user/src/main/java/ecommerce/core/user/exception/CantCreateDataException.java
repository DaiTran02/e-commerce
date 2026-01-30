package ecommerce.core.user.exception;

public class CantCreateDataException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CantCreateDataException() {
		super("Can't create data, please contact to admin");
	}

}
