package controller;

import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidMoveException;
import logger.LoggedMove;
import logger.MoveLogger;
import model.Model;
import model.figures.ChessPiece;
import model.game.Board;
import model.game.Field;
import model.game.FieldCoordinates;
import model.game.MoveResult;
import model.game.Player;

public class ChessGame {
	
	private Board board;
	private MoveLogger logger;
	private Model.Color currentPlayerColor;
	private Map<Model.Color, Player> players = new HashMap<>();
	
	public ChessGame(Board board, MoveLogger logger, Model.Color humanColor, Player player, Player opponent){
		this.board = board;
		this.logger = logger;
		this.currentPlayerColor = Model.Color.white;
		board.renumberFieldsColorBottom(currentPlayerColor);
		insertPlayers(player, opponent);
	}
	
	private void insertPlayers(Player player, Player opponent) {
		players.put(player.getColor(), player);
		players.put(opponent.getColor(), opponent);
	}
	
	public MoveResult movePieceAndLogMove(Field startField, Field targetField) throws InvalidMoveException {
		MoveResult moveResult;
		checkMovePreconditions(startField, targetField);
		logger.beginLogTransaction(startField, targetField);
		movePiece(startField, targetField);
		updatePlayers();
		moveResult = checkMoveExtraResults();
		logger.commitLogTransaction(moveResult);
		switchPlayers();
		board.renumberFields();
		return moveResult;		
	}
	
	private void checkMovePreconditions(Field startField, Field endField) throws InvalidMoveException {
		if(startField.getChessPiece() == null)
			throw new InvalidMoveException();
		if(!startField.getChessPiece().getColor().equals(currentPlayerColor))
			throw new InvalidMoveException();
		if(startField.equals(endField))
			throw new InvalidMoveException();
	}
	
	public MoveResult checkMoveExtraResults() throws InvalidMoveException {
		if(isPlayerChecked(currentPlayerColor)){
			revertUncommitedMove();
			updatePlayers();
			throw new InvalidMoveException();
		}
		if(isPlayerChecked(getOpponentColor())){
			if(isPlayerMated(getOpponentColor()))
				return MoveResult.CHECK_MATE;
			else
				return MoveResult.CHECK;
		}
		if(isPlayerMated(getOpponentColor()))
			return MoveResult.MATE;
		return MoveResult.OK;
	}

	private void updatePlayers(){
		for(Map.Entry<Model.Color, Player> entry : players.entrySet())
			entry.getValue().update();
	}

	public void movePiece(Field startField, Field targetField) throws InvalidMoveException{
		ChessPiece movedPiece = startField.getChessPiece();
		if(!movedPiece.isMovePossible(startField, targetField) )
			throw new InvalidMoveException();
		movedPiece.movePiece(startField, targetField);
	}
	
	private void revertUncommitedMove(){
		LoggedMove lastMove = logger.getCurrentTransaction();
		Field endField = board.getFieldAbsolute(lastMove.endPosition.x, lastMove.endPosition.y);
		Field startField = board.getFieldAbsolute(lastMove.startPosition.x, lastMove.startPosition.y);
		ChessPiece movedPiece = Utils.createChessPiece(lastMove.playerColor, lastMove.movingChessPiece, board);
		ChessPiece killedPiece = Utils.createChessPiece(Utils.getOpposingColor(lastMove.playerColor), lastMove.pieceKilled, board);
		startField.setChessPiece(movedPiece);
		endField.setChessPiece(killedPiece);	
	}
	
	private void revertCommitedMove(){
		LoggedMove lastMove = logger.getLastMove();
		Field endField = board.getFieldAbsolute(lastMove.endPosition.x, lastMove.endPosition.y);
		Field startField = board.getFieldAbsolute(lastMove.startPosition.x, lastMove.startPosition.y);
		ChessPiece movedPiece = Utils.createChessPiece(lastMove.playerColor, lastMove.movingChessPiece, board);
		ChessPiece killedPiece = Utils.createChessPiece(Utils.getOpposingColor(lastMove.playerColor), lastMove.pieceKilled, board);
		startField.setChessPiece(movedPiece);
		endField.setChessPiece(killedPiece);	
		logger.revertLastMove();
	}
	
	public void revertMove(){
		revertCommitedMove();
		updatePlayers();
		board.renumberFields();
		switchPlayers();
	}
	
	public boolean isPlayerChecked(Model.Color color){
		board.renumberFieldsColorBottom(Utils.getOpposingColor(color));
		Player checkedPlayer = players.get(color);
		Field checkedKingField = checkedPlayer.getKingField();
		Player checkingPlayer = players.get(Utils.getOpposingColor(color));
		Map<FieldCoordinates, ChessPiece> pieces = checkingPlayer.showPieces();
		for(Map.Entry<FieldCoordinates, ChessPiece> entry : pieces.entrySet()){
			ChessPiece piece = entry.getValue();
			Field pieceField = board.getFieldAbsolute(entry.getKey().x, entry.getKey().y);
			if(piece.isMovePossible(pieceField, checkedKingField)){
				System.out.println("Player "+ color + " checked");
				board.renumberFieldsColorBottom(currentPlayerColor);
				return true;	
			}						
		}
		board.renumberFieldsColorBottom(currentPlayerColor);
		return false;
	}
	
	public boolean isPlayerMated(Model.Color color){
		return false;
	}
	
	private void switchPlayers(){
		currentPlayerColor = Utils.getOpposingColor(currentPlayerColor);
	}
	
	public Model.Color getPlayerColor(){
		return currentPlayerColor;
	}
	
	public Model.Color getOpponentColor(){
		return Utils.getOpposingColor(currentPlayerColor);
	}

}
