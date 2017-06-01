package exceptions;

@SuppressWarnings("serial")
public class SurrenderException extends Exception{
	
	public SurrenderException(){
		super("I surrender!");
	}

}
