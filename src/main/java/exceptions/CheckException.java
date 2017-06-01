package exceptions;

@SuppressWarnings("serial")
public class CheckException extends Exception{
	public CheckException(){
		super("You will be checked after such a move");
	}
}
