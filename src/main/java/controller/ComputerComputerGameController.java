package controller;

import algorithms.AlgorithmFactory;
import exceptions.InvalidMoveException;
import exceptions.SurrenderException;
import logger.MoveLogger;
import model.Model;
import model.Move;
import model.game.Board;
import model.game.MoveResult;

public class ComputerComputerGameController {
	private Board board;
	private ChessGame chessGame;
	private ArtificialPlayer artificialOpponent;
	private ArtificialPlayer artificialOpponentRandom;
	private MoveLogger logger;
	private AlgorithmFactory algorithmFactory;

	public ComputerComputerGameController(Model.Color playerColor) {
		
		board = new Board();
		artificialOpponentRandom = new ArtificialPlayer(playerColor, board);
		artificialOpponent = new ArtificialPlayer(Utils.getOpposingColor(playerColor), board);
		logger = new MoveLogger();
		chessGame = new ChessGame(board, logger, playerColor, artificialOpponentRandom, artificialOpponent);
		algorithmFactory = new AlgorithmFactory(board, artificialOpponentRandom, artificialOpponent, chessGame);
		artificialOpponent.setChessAlgorithm(algorithmFactory.getMinMaxInstance());
		artificialOpponentRandom.setChessAlgorithm(algorithmFactory.getRandomAlgorithmInstanceComputer());
	}
	
	public MoveResult playerMove(Move move) throws InvalidMoveException {
		return chessGame.movePieceAndLogMove(move.startField, move.endField);
	}

	public MoveResult opponentMove() throws SurrenderException {
		Move suggestedMove = null;
		while (true) {
			try {
				suggestedMove = artificialOpponent.move();
				MoveResult result = chessGame.movePieceAndLogMove(suggestedMove.startField, suggestedMove.endField);
				artificialOpponent.acceptMove();
				return result;
			} catch (InvalidMoveException invalidMoveException) {
				boolean gaveUp = artificialOpponent.denyMove(suggestedMove);
				if (gaveUp)
					break;
				continue;
			}
		}
		throw new SurrenderException();
	}
	
	public MoveResult opponentMoveRandom() throws SurrenderException {
		Move suggestedMove = null;
		while (true) {
			try {
				suggestedMove = artificialOpponentRandom.move();
				MoveResult result = chessGame.movePieceAndLogMove(suggestedMove.startField, suggestedMove.endField);
				artificialOpponentRandom.acceptMove();
				return result;
			} catch (InvalidMoveException invalidMoveException) {
				boolean gaveUp = artificialOpponent.denyMove(suggestedMove);
				if (gaveUp)
					break;
				continue;
			}
		}
		throw new SurrenderException();
	}

	public Board getBoard() {
		return board;
	}

	public void printLoggerHistory() {
		logger.printHistory();
	}

}