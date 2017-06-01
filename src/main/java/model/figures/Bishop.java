package model.figures;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidMoveException;
import model.Model;
import model.game.Board;
import model.game.Field;

/**
 * Goniec
 */
public class Bishop extends ChessPiece {
	
	private final int value = 3;

	public Bishop(Model.Color color, Board board) {
		super(color, Model.Name.Bishop, board);
	}

	@Override
	public void movePiece(Field pieceField, Field targetField) throws InvalidMoveException {
		if (!isMovePossible(pieceField, targetField))
			throw new InvalidMoveException();
		else {
			pieceField.removeChessPiece();
			targetField.setChessPiece(this);
		}
	}

	@Override
	public boolean isMovePossible(Field pieceField, Field targetField) {
		if(pieceField == null || targetField == null)
			return false;
		int deltaX = targetField.getFieldCoordintes().x - pieceField.getFieldCoordintes().x;
		int deltaY = targetField.getFieldCoordintes().y - pieceField.getFieldCoordintes().y;
		if (Math.abs(deltaX) != Math.abs(deltaY))
			return false;
		int dx = deltaX / Math.abs(deltaX);
		int dy = deltaY / Math.abs(deltaY);
		int startX = pieceField.getFieldCoordintes().x;
		int startY = pieceField.getFieldCoordintes().y;
		Field nextField;
		for (int i = 1; i <= Math.abs(deltaX); i++) {
			nextField = board.getField(startX + i * dx, startY + i * dy);
			if (nextField.getChessPiece() != null
					&& (i != Math.abs(deltaX) || nextField.getChessPiece().getColor() == this.getColor())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Field> getPossibleMoves(Field pieceField) {
		List<Field> possibleFields = new ArrayList<>();
		int dx[] = { 1, 1, -1, -1 };
		int dy[] = { 1, -1, 1, -1 };
		for (int i = 0; i < 4; i++)
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