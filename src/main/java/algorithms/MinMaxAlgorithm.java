package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import controller.ChessGame;
import exceptions.InvalidMoveException;
import exceptions.SurrenderException;
import model.Model.Color;
import model.Move;
import model.figures.ChessPiece;
import model.game.Board;
import model.game.Field;
import model.game.FieldCoordinates;
import model.game.Player;

public class MinMaxAlgorithm implements ChessAlgorithm {

	private Map<FieldCoordinates, ChessPiece> pieces;
	private final Board board;
	private final Player player;
	private final Player opponent;
	private final List<Move> movesAlreadySuggested = new ArrayList<>();
	private final ChessGame chessGame;
	private int depth = 3;

	public MinMaxAlgorithm(Board board, Player player, Player opponent, ChessGame chessgame) {
		this.board = board;
		this.player = player;
		this.opponent = opponent;
		this.chessGame = chessgame;
	}

	@Override
	public Move suggestMove() throws SurrenderException {
		Move pickedMove = null;
		pieces = player.showPieces();
		List<FieldCoordinates> listOfFieldCoordinates = new ArrayList<>();
		listOfFieldCoordinates.addAll(pieces.keySet());
		for (FieldCoordinates fieldCoordinates : listOfFieldCoordinates) {
			Field candidateField = board.getFieldAbsolute(fieldCoordinates.x, fieldCoordinates.y);
			ChessPiece piece = candidateField.getChessPiece();
			List<Field> possibilitiesFields = piece.getPossibleMoves(candidateField);
			for (Field field : possibilitiesFields) {
				Move suggestedMove = new Move(candidateField, field);

				if (depth > 0) {
					try {
						if(suggestedMove.endField.getChessPiece() !=null){
							suggestedMove.moveValue += 2;
						}
						chessGame.movePieceAndLogMove(suggestedMove.startField, suggestedMove.endField);
						suggestedMove.moveValue -= min(depth - 1);
						chessGame.revertMove();
					} catch (InvalidMoveException e) {
						continue;
					}
				}

				if (pickedMove == null) {
					pickedMove = suggestedMove;
				}

				if (suggestedMove.moveValue > pickedMove.moveValue) {
					pickedMove = suggestedMove;
				}

			}
		}

		if (pickedMove != null) {
			return pickedMove;
		} else {
			throw new SurrenderException();
		}
	}

	private int min(int depth) {
		Move pickedMove = null;
		pieces = opponent.showPieces();
		List<FieldCoordinates> listOfFieldCoordinates = new ArrayList<>();
		listOfFieldCoordinates.addAll(pieces.keySet());
		for (FieldCoordinates fieldCoordinates : listOfFieldCoordinates) {
			Field candidateField = board.getFieldAbsolute(fieldCoordinates.x, fieldCoordinates.y);
			ChessPiece piece = candidateField.getChessPiece();
			List<Field> possibilitiesFields = piece.getPossibleMoves(candidateField);
			for (Field field : possibilitiesFields) {
				Move suggestedMove = new Move(candidateField, field);

				if (depth > 0) {
					try {
						chessGame.movePieceAndLogMove(suggestedMove.startField, suggestedMove.endField);
						suggestedMove.moveValue -= max(depth - 1);
						chessGame.revertMove();
					} catch (InvalidMoveException e) {
						continue;
					}
				}

				if (pickedMove == null) {
					pickedMove = suggestedMove;
				}

				if (suggestedMove.moveValue > pickedMove.moveValue) {
					pickedMove = suggestedMove;
				}

			}
		}

		if (pickedMove != null) {
			return pickedMove.moveValue;
		} else if(chessGame.isPlayerChecked(Color.white)){
			return -10000;
		}else {
			return 0;
		}
	}

	private int max(int depth) {
		Move pickedMove = null;
		pieces = player.showPieces();
		List<FieldCoordinates> listOfFieldCoordinates = new ArrayList<>();
		listOfFieldCoordinates.addAll(pieces.keySet());
		for (FieldCoordinates fieldCoordinates : listOfFieldCoordinates) {
			Field candidateField = board.getFieldAbsolute(fieldCoordinates.x, fieldCoordinates.y);
			ChessPiece piece = candidateField.getChessPiece();
			List<Field> possibilitiesFields = piece.getPossibleMoves(candidateField);
			for (Field field : possibilitiesFields) {
				Move suggestedMove = new Move(candidateField, field);

				if (depth > 0) {
					try {
						chessGame.movePieceAndLogMove(suggestedMove.startField, suggestedMove.endField);
						suggestedMove.moveValue -= min(depth - 1);
						chessGame.revertMove();
					} catch (InvalidMoveException e) {
						continue;
					}
				}

				if (pickedMove == null) {
					pickedMove = suggestedMove;
				}

				if (suggestedMove.moveValue > pickedMove.moveValue) {
					pickedMove = suggestedMove;
				}

			}
		}

		if (pickedMove != null) {
			return pickedMove.moveValue;
		} else if (chessGame.isPlayerChecked(Color.black)) {
			return -10000;
		} else {
			return 0;
		}
	}

	@Override
	public void moveAccepted() {
		movesAlreadySuggested.clear();

	}

	@Override
	public List<Move> getSuggestedMovesThisTurn() {
		return movesAlreadySuggested;
	}
}
