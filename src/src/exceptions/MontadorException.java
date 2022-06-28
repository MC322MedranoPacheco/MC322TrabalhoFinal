package exceptions;

public class MontadorException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MontadorException(String erro) {
		super(erro);
	}
	
}
