package model.figures;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidMoveException;
import model.Model;
import model.game.Board;
import model.game.Field;
import model.game.FieldCoordinates;

/**
 * Koń
 */
public class Knight extends ChessPiece {

	private final int value = 3;
	
	public Knight(Model.Color color, Board board) {
		super(color, Model.Name.Knight, board);
	}

	@Override
	public void movePiece(Field pieceField, Field targetField) throws InvalidMoveException {
		if (!isMovePossible(pieceField, targetField))
			throw new InvalidMoveException();
		pieceField.removeChessPiece();
		targetField.setChessPiece(this);
	}

	@Override
	public boolean isMovePossible(Field pieceField, Field targetField) {
		if(pieceField == null || targetField == null)
			return false;
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = targetField.getFieldCoordintes();
		int deltaX = Math.abs(end.x - start.x);
		int deltaY = Math.abs(end.y - start.y);
		if (!((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)))
			return false;
		if (targetField.getChessPiece() != null && this.getColor().equals(targetField.getChessPiece().getColor()))
			return false;
		return true;
	}

	@Override
	public List<Field> getPossibleMoves(Field pieceField) {
		List<Field> possibleFields = new ArrayList<>();
		addPossibleMove(possibleFields, pieceField, -2, 1);
		addPossibleMove(possibleFields, pieceField, -2, -1);
		addPossibleMove(possibleFields, pieceField, -1, 2);
		addPossibleMove(possibleFields, pieceField, -1, -2);
		addPossibleMove(possibleFields, pieceField, 1, 2);
		addPossibleMove(possibleFields, pieceField, 1, -2);
		addPossibleMove(possibleFields, pieceField, 2, 1);
		addPossibleMove(possibleFields, pieceField, 2, -1);
		return possibleFields;
	}

	private void addPossibleMove(List<Field> possibleFields, Field pieceField, int moveX, int moveY) {
		FieldCoordinates fieldCoordinates = pieceField.getFieldCoordintes();
		if (isMovePossible(pieceField, board.getField(fieldCoordinates.x + moveX, fieldCoordinates.y + moveY)))
			possibleFields.add(board.getField(fieldCoordinates.x + moveX, fieldCoordinates.y + moveY));
	}

	public int getValue(){
		return value;
	}
}
