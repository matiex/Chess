package model.figures;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidMoveException;
import model.Model;
import model.game.Board;
import model.game.Field;
import model.game.FieldCoordinates;

/**
 * Król
 */
public class King extends ChessPiece {
	
	private final int value = 10;

	private boolean wasMoved;

	public King(Model.Color color, Board board) {
		super(color, Model.Name.King, board);
	}

	@Override
	public void movePiece(Field pieceField, Field targetField) throws InvalidMoveException {
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = targetField.getFieldCoordintes();
		if (!wasMoved && checkForCastle(pieceField, targetField)) {
			performCastle(targetField);
			wasMoved = true;
			return;
		}
		int deltaX = Math.abs(end.x - start.x);
		int deltaY = Math.abs(end.y - start.y);
		if (!((deltaX <= 1 && deltaY <= 1) && !(deltaX == 0 && deltaY == 0)))
			throw new InvalidMoveException();
		if (targetField.getChessPiece() != null && targetField.getChessPiece().getColor().equals(this.getColor()))
			throw new InvalidMoveException();
		wasMoved = true;
		pieceField.removeChessPiece();
		targetField.setChessPiece(this);
	}

	private boolean checkForCastle(Field pieceField, Field targetField) {
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = targetField.getFieldCoordintes();
		if (start.x == 4 && start.y == 0 && end.x == 6 && end.y == 0 && board.getField(5, 0).getChessPiece() == null
				&& board.getField(6, 0).getChessPiece() == null && isRookAt(7, 0))
			return true;
		if (start.x == 4 && start.y == 0 && end.x == 1 && end.y == 0 && board.getField(1, 0).getChessPiece() == null
				&& board.getField(2, 0).getChessPiece() == null && board.getField(3, 0).getChessPiece() == null
				&& isRookAt(0, 0))
			return true;
		return false;
	}

	private boolean isRookAt(int i, int j) {
		Field rookField = board.getField(i, j);
		ChessPiece possibleRook = rookField.getChessPiece();
		if (possibleRook != null && possibleRook.getColor().equals(this.getColor())
				&& possibleRook.getName().equals(Model.Name.Rook))
			return true;
		return false;
	}

	private void performCastle(Field targetField) {
		if (targetField.getFieldCoordintes().x != 6) {
			moveRook(0, 0, 3, 0);
			moveKingTo(1, 0);
		} else {
			moveRook(7, 0, 5, 0);
			moveKingTo(6, 0);
		}
	}

	private void moveRook(int startX, int startY, int endX, int endY) {
		Field rookStartField = board.getField(startX, startY);
		ChessPiece rook = rookStartField.getChessPiece();
		rookStartField.removeChessPiece();
		Field rookEndField = board.getField(endX, endY);
		rookEndField.setChessPiece(rook);
	}

	private void moveKingTo(int endX, int endY) {
		Field kingStart = board.getField(4, 0);
		Field kingEnd = board.getField(endX, endY);
		kingStart.removeChessPiece();
		kingEnd.setChessPiece(this);
	}

	@Override
	public boolean isMovePossible(Field pieceField, Field targetField) {
		if (pieceField == null || targetField == null)
			return false;
		FieldCoordinates start = pieceField.getFieldCoordintes();
		FieldCoordinates end = targetField.getFieldCoordintes();
		if (!wasMoved && checkForCastle(pieceField, targetField)) {
			return true;
		}
		int deltaX = Math.abs(end.x - start.x);
		int deltaY = Math.abs(end.y - start.y);
		if (!((deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1) || (deltaX == 1 && deltaY == 1) ))
			return false;
		if (targetField.getChessPiece() != null && targetField.getChessPiece().getColor().equals(this.getColor()))
			return false;
		return true;
	}

	@Override
	public List<Field> getPossibleMoves(Field pieceField) {
		List<Field> possibleFields = new ArrayList<>();
		addPossibleMove(possibleFields, pieceField, -1, 0);
		addPossibleMove(possibleFields, pieceField, 1, 0);
		addPossibleMove(possibleFields, pieceField, 0, -1);
		addPossibleMove(possibleFields, pieceField, 0, 1);
		addPossibleMove(possibleFields, pieceField, -1, -1);
		addPossibleMove(possibleFields, pieceField, 1, 1);
		addPossibleMove(possibleFields, pieceField, 1, -1);
		addPossibleMove(possibleFields, pieceField, -1, 1);
		if (this.getColor().equals(Color.white)) {
			addPossibleMove(possibleFields, pieceField, 2, 0);
			addPossibleMove(possibleFields, pieceField, -3, 0);
		} else {
			addPossibleMove(possibleFields, pieceField, -2, 0);
			addPossibleMove(possibleFields, pieceField, 3, 0);
		}
		return possibleFields;
	}

	private void addPossibleMove(List<Field> possibleFields, Field pieceField, int moveX, int moveY) {
		FieldCoordinates fieldCoordinates = pieceField.getFieldCoordintes();
		if (isMovePossible(pieceField, board.getField(fieldCoordinates.x + moveX, fieldCoordinates.y + moveY)))
			possibleFields.add(board.getField(fieldCoordinates.x + moveX, fieldCoordinates.y + moveY));
	}

	@Override
	public int getValue(){
		return value;
	}

}