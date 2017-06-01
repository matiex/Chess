package exceptions;

@SuppressWarnings("serial")
public class ImpossiblePathException extends Exception{
	public ImpossiblePathException(){
		super("This path is forbidden for this piece");
	}
}
