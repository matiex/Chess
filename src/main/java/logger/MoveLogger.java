package logger;

import java.util.LinkedList;

import model.game.Field;
import model.game.MoveResult;

public class MoveLogger {
	private LinkedList<LoggedMove> history = new LinkedList<>();
	private LoggedMove currentTransaction;

	public void beginLogTransaction(Field startField, Field targetField) {
		currentTransaction = new LoggedMove();
		currentTransaction.playerColor = startField.getChessPiece().getColor();
		currentTransaction.movingChessPiece = startField.getChessPiece().getName();
		currentTransaction.startPosition = startField.getPermanentCoordinates();
		currentTransaction.endPosition = targetField.getPermanentCoordinates();
		if(targetField.getChessPiece() != null)
			currentTransaction.pieceKilled = targetField.getChessPiece().getName();
		else
			currentTransaction.pieceKilled = null;		
	}
	

	public void commitLogTransaction(MoveResult moveResult) {
		currentTransaction.moveResult = moveResult;
		history.push(currentTransaction);
	}
	
	public LoggedMove getCurrentTransaction(){
		return currentTransaction;
	}
	
	public LinkedList<LoggedMove> getHistory(){
		return history;
	}


	public void printHistory() {
		for(int i = 0; i<history.size(); i++)
			System.out.println(history.get(i).toString());
	}
	
	public LoggedMove getLastMove(){
		return history.peek();
	}
	
	public LoggedMove revertLastMove(){
		return history.pop();
	}

}
