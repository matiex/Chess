package exceptions;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {
	public InvalidMoveException(){
		super("This move is forbidden");
	}

}
