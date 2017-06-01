package model.figures;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidMoveException;
import model.Model;
import model.game.Board;
import model.game.Field;
import model.game.FieldCoordinates;

/**
 * Hetman
 * */

public class Queen extends ChessPiece{
	
	private final int value = 9;

	public Queen(Model.Color color, Board board) {
		super(color, Model.Name.Queen, board);
	}

	@Override
	public void movePiece(Field pieceField, Field targetField) throws InvalidMoveException {
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = targetField.getFieldCoordintes();
		int deltaX = end.x - start.x;
		int deltaY = end.y - start.y;
		if((deltaX == 0 && deltaY != 0)
				|| (deltaX != 0 && deltaY == 0)){
			moveLikeRook(pieceField, targetField);
			return;
		}
		if(Math.abs(deltaX)==Math.abs(deltaY)){
			moveLikeBishop(pieceField, targetField);
			return;
		}
		throw new InvalidMoveException();		
	}

	private void moveLikeRook(Field pieceField, Field targetField) throws InvalidMoveException{
		if(!isMoveLikeRook(pieceField, targetField))
			throw new InvalidMoveException();
		pieceField.removeChessPiece();
		targetField.setChessPiece(this);		
	}
	
	private boolean isMoveLikeRook(Field pieceField, Field targetField){
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = targetField.getFieldCoordintes();
		int deltaX = end.x - start.x;
		int deltaY = end.y - start.y;
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
	
	private int setDerivativeChange(int delta) {
		if(delta == 0)
			return 0;
		else
			return delta/Math.abs(delta);
	}
	

	private void moveLikeBishop(Field pieceField, Field targetField) throws InvalidMoveException{
		if(!isMoveLikeBishop(pieceField, targetField))
			throw new InvalidMoveException();
		pieceField.removeChessPiece();
		targetField.setChessPiece(this);		
	}
	
	private boolean isMoveLikeBishop(Field pieceField, Field targetField) {
		int deltaX = targetField.getFieldCoordintes().x - pieceField.getFieldCoordintes().x;
		int deltaY = targetField.getFieldCoordintes().y - pieceField.getFieldCoordintes().y;
		if(Math.abs(deltaX) != Math.abs(deltaY))
			return false;
		int dx = deltaX/Math.abs(deltaX);
		int dy = deltaY/Math.abs(deltaY);
		int startX = pieceField.getFieldCoordintes().x;
		int startY = pieceField.getFieldCoordintes().y;
		Field nextField;
		for(int i = 1; i<= Math.abs(deltaX); i++){
			nextField = board.getField(startX + i*dx, startY + i*dy);
			if(nextField.getChessPiece() != null 
					&& (i != Math.abs(deltaX) 
						|| nextField.getChessPiece().getColor() == this.getColor())){
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean isMovePossible(Field pieceField, Field targetField) {
		if(pieceField == null || targetField == null)
			return false;
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = targetField.getFieldCoordintes();
		int deltaX = end.x - start.x;
		int deltaY = end.y - start.y;
		if ((deltaX == 0 && deltaY != 0) || (deltaX != 0 && deltaY == 0))
			return isMoveLikeRook(pieceField, targetField);
		if (Math.abs(deltaX) == Math.abs(deltaY))
			return isMoveLikeBishop(pieceField, targetField);
		return false;
	}

	@Override
	public List<Field> getPossibleMoves(Field pieceField) {
		List<Field> possibleFields = new ArrayList<>();
		int dx[] = { 1, 1, -1, -1, 1, -1, 0, 0 };
		int dy[] = { 1, -1, 1, -1, 0, 0, 1, -1 };
		for (int i = 0; i < 8; i++)
			possibleFields.addAll(getPossibleFieldsInDirection(pieceField, dx[i], dy[i]));
		return possibleFields;
	}

	private List<Field> getPossibleFieldsInDirection(Field pieceField, int dx, int dy) {
		List<Field> result = new ArrayList<>();
		int x = pieceField.getFieldCoordintes().x + dx;
		int y = pieceField.getFieldCoordintes().y + dy;
		Field nextField;
		while (x <= 7 && y <= 7 && x >= 0 && y >= 0) {
			nextField = board.getField(x, y);
			if (nextField.getChessPiece() != null) {
				if (!this.getColor().equals(nextField.getChessPiece().getColor()))
					result.add(nextField);
				break;
			}
			result.add(nextField);
			x = x + dx;
			y = y + dy;
		}
		return result;
	}

	public int getValue(){
		return value;
	}
}
