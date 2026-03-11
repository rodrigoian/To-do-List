package exception;

public class SenhaInvalidaException extends BusinessException {
	private static final long serialVersionUID = 1L;
	
	public SenhaInvalidaException(String msg) {
		super(msg);
	}
}
