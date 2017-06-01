package model.figures;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidMoveException;
import model.Model;
import model.game.Board;
import model.game.Field;
import model.game.FieldCoordinates;

/**
 * Pionek
 */
public class Pawn extends ChessPiece {
	
	private final int value = 1;

	public Pawn(Model.Color color, Board board) {
		super(color, Model.Name.Pawn, board);
	}

	@Override
	public void movePiece(Field pieceField, Field targetField) throws InvalidMoveException {
		FieldCoordinates startCoordinates = pieceField.getFieldCoordintes();
		FieldCoordinates endCoordinates = targetField.getFieldCoordintes();
		if (startCoordinates.x == endCoordinates.x && startCoordinates.y == endCoordinates.y - 1) {
			if (targetField.getChessPiece() != null)
				throw new InvalidMoveException();
			else {
				pieceField.removeChessPiece();
				targetField.setChessPiece(this);
				return;
			}
		}
		if (startCoordinates.x == endCoordinates.x && startCoordinates.y == endCoordinates.y - 2
				&& startCoordinates.y == 1) {
			Field transitField = board.getField(startCoordinates.x, 2);
			if (transitField.getChessPiece() != null || targetField.getChessPiece() != null)
				throw new InvalidMoveException();
			else {
				pieceField.removeChessPiece();
				targetField.setChessPiece(this);
				return;
			}
		}
		if (Math.abs(startCoordinates.x - endCoordinates.x) == 1 && startCoordinates.y == endCoordinates.y - 1
				&& targetField.getChessPiece() != null && targetField.getChessPiece().getColor() != getColor()) {
			pieceField.removeChessPiece();
			targetField.setChessPiece(this);
			return;
		}
		throw new InvalidMoveException();
	}

	@Override
	public boolean isMovePossible(Field pieceField, Field targetField) {
		if(pieceField == null || targetField == null)
			return false;
		FieldCoordinates startCoordinates = pieceField.getFieldCoordintes();
		FieldCoordinates endCoordinates = targetField.getFieldCoordintes();
		if (startCoordinates.x == endCoordinates.x && startCoordinates.y == endCoordinates.y - 1 && targetField.getChessPiece() == null)
			return true;
		if (startCoordinates.x == endCoordinates.x && startCoordinates.y == endCoordinates.y - 2
				&& startCoordinates.y == 1 && targetField.getChessPiece() == null) {
			return true;
		}
		if (Math.abs(startCoordinates.x - endCoordinates.x) == 1 && startCoordinates.y == endCoordinates.y - 1
				&& targetField.getChessPiece() != null && targetField.getChessPiece().getColor() != getColor())
			return true;
		return false;
	}

	@Override
	public List<Field> getPossibleMoves(Field pieceField) {
		List<Field> possibleFields = new ArrayList<>();
		addPossibleMove(possibleFields, pieceField, 0, 1);
		addPossibleMove(possibleFields, pieceField, 0, 2);
		addPossibleMove(possibleFields, pieceField, 1, 1);
		addPossibleMove(possibleFields, pieceField, -1, 1);
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
