package exception;


//Exception Pai (Melhor para escalação do projeto e com try catch)
public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);
	}

}
