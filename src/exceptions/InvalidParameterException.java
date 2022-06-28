package exceptions;

public class InvalidParameterException extends RuntimeException{

	private static final long serialVersionUID = 1L;
		
	public InvalidParameterException(String erro) {
		super(erro);
	}
	
}
