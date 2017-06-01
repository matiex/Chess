package model.figures;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidMoveException;
import model.Model;
import model.game.Board;
import model.game.Field;
import model.game.FieldCoordinates;

/**
 * Wieża
 * */
public class Rook extends ChessPiece {
	
	private final int value = 5;

	public Rook(Model.Color color, Board board) {
		super(color, Model.Name.Rook, board);
	}

	@Override
	public void movePiece(Field pieceField, Field targetField) throws InvalidMoveException{
		if(!isMovePossible(pieceField, targetField))
			throw new InvalidMoveException();
		pieceField.removeChessPiece();
		targetField.setChessPiece(this);		
	}

	private int setDerivativeChange(int delta) {
		if(delta == 0)
			return 0;
		else
			return delta/Math.abs(delta);
	}

	@Override
	public boolean isMovePossible(Field pieceField, Field targetField) {
		if(pieceField == null || targetField == null)
			return false;
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = targetField.getFieldCoordintes();
		int deltaX = end.x - start.x;
		int deltaY = end.y - start.y;
		if(deltaX != 0 && deltaY != 0 || (end.x==start.x && end.y==start.y))
			return false;
		Field nextField;
		int dx = setDerivativeChange(deltaX);
		int dy = setDerivativeChange(deltaY);
		int delta =  Math.abs(Math.max(Math.abs(deltaX), Math.abs(deltaY)));
		for(int i = 1; i <= delta ; i++){
			nextField = board.getField(start.x + i*dx, start.y+i*dy);
			if(nextField.getChessPiece() != null 
					&& (i != delta 
					|| nextField.getChessPiece().getColor() == this.getColor()))
				return false;
		}
		return true;
	}

	@Override
	public List<Field> getPossibleMoves(Field pieceField) {
		List<Field> finalFields = getFinalFields(pieceField);
		List<Field> possibleFields = new ArrayList<>();
		for(Field finalField : finalFields)
			possibleFields.addAll(getPossibleFieldsInPath(pieceField, finalField));		
		return possibleFields;
	}

	private List<Field> getFinalFields(Field pieceField) {
		List<Field> finalFields = new ArrayList<>();
		FieldCoordinates startCoordinates = pieceField.getFieldCoordintes();
		finalFields.add(board.getField(startCoordinates.x, 0));
		finalFields.add(board.getField(startCoordinates.x, 7));
		finalFields.add(board.getField(0, startCoordinates.y));
		finalFields.add(board.getField(7, startCoordinates.y));
		return finalFields;
	}
	
	private List<Field> getPossibleFieldsInPath(Field pieceField, Field finalField) {
		List<Field> result = new ArrayList<>();
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = finalField.getFieldCoordintes();
		int deltaX = end.x - start.x;
		int deltaY = end.y - start.y;
		int dx = setDerivativeChange(deltaX);
		int dy = setDerivativeChange(deltaY);
		int delta =  Math.abs(Math.max(Math.abs(deltaX), Math.abs(deltaY)));
		Field nextField;
		for(int i = 1; i <= delta ; i++){
			nextField = board.getField(start.x + i*dx, start.y+i*dy);
			if(nextField.getChessPiece() != null){
				if(!this.getColor().equals(nextField.getChessPiece().getColor()))
					result.add(nextField);
				break;					
			}
			result.add(nextField);
		}
		return result;
	}	
	
	public int getValue(){
		return value;
	}
}
