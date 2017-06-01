package controller;

import algorithms.AlgorithmFactory;
import exceptions.InvalidMoveException;
import exceptions.SurrenderException;
import logger.MoveLogger;
import model.Model;
import model.Move;
import model.game.Board;
import model.game.MoveResult;
import model.game.Player;

public class HumanComputerGameController {
	private Board board;
	private ChessGame chessGame;
	private Player humanPlayer;
	private ArtificialPlayer artificialOpponent;
	private MoveLogger logger;
	private AlgorithmFactory algorithmFactory;

	public HumanComputerGameController(Model.Color playerColor) {
		
		board = new Board();
		humanPlayer = new Player(playerColor, board);
		artificialOpponent = new ArtificialPlayer(Utils.getOpposingColor(playerColor), board);
		logger = new MoveLogger();
		chessGame = new ChessGame(board, logger, playerColor, humanPlayer, artificialOpponent);
		algorithmFactory = new AlgorithmFactory(board, humanPlayer, artificialOpponent, chessGame);
		setControllerToRandom();
	}
	
	public void setControllerToRandom() {
		artificialOpponent.setChessAlgorithm(algorithmFactory.getRandomAlgorithmInstance());
	}

	public void setControllerToMinMax() {
		artificialOpponent.setChessAlgorithm(algorithmFactory.getMinMaxInstance());
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

	public Board getBoard() {
		return board;
	}

	public void printLoggerHistory() {
		logger.printHistory();
	}

}
