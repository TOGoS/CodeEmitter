package togos.codeemitter;

/**
 * Can be thrown when an emitter is asked to do something
 * that does not make sense in its current state, which
 * probably indicates a bug in calling code.
 */
public class InvalidEmittanceException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public InvalidEmittanceException( String message ) {
		super(message);
	}
}
