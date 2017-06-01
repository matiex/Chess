package uni.chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.ChessGame;
import exceptions.InvalidMoveException;
import logger.MoveLogger;
import model.Model;
import model.game.Board;
import model.game.Field;
import model.game.MoveResult;
import model.game.Player;

public class CheckMateTest {

	private Board board;
	private ChessGame game;
	private MoveLogger logger;

	@Before
	public void setupBoard() {
		board = new Board();
		logger = new MoveLogger();
		game = new ChessGame(board,logger, Model.Color.white, new Player(Model.Color.white, board), new Player(Model.Color.black, board));
	}
	
	@Test
	public void testCheckOpponent() throws Exception {
		System.out.println("CheckOpponent");
		Field startField = board.getField(4, 1);
		Field endField = board.getField(4, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(3, 1);
		endField = board.getField(3, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(3, 0);
		endField = board.getField(7, 4);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(4, 1);
		endField = board.getField(4, 2);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(7, 4);
		endField = board.getField(4, 4);
		MoveResult result = game.movePieceAndLogMove(startField, endField);
		
		assertTrue(result.equals(MoveResult.CHECK));
	}
	
	@Test
	public void testCheckEvadeCheck() throws Exception {
		System.out.println("CheckEvadeCheck");
		Field startField = board.getField(4, 1);
		Field endField = board.getField(4, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(3, 1);
		endField = board.getField(3, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(3, 0);
		endField = board.getField(7, 4);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(4, 1);
		endField = board.getField(4, 2);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(7, 4);
		endField = board.getField(4, 4);
		MoveResult result = game.movePieceAndLogMove(startField, endField);
		assertTrue(result.equals(MoveResult.CHECK));
		
		startField = board.getField(4, 0);
		endField = board.getField(3, 1);
		result = game.movePieceAndLogMove(startField, endField);
		assertTrue(result.equals(MoveResult.OK));

		startField = board.getField(4, 4);
		endField = board.getField(1, 4);
		result = game.movePieceAndLogMove(startField, endField);
		assertTrue(result.equals(MoveResult.CHECK));
	}
	

	@Test(expected=InvalidMoveException.class)
	public void testCheckToCheckMove() throws Exception {
		System.out.println("CheckToCheck");
		Field startField = board.getField(4, 1);
		Field endField = board.getField(4, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(3, 1);
		endField = board.getField(3, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(3, 0);
		endField = board.getField(7, 4);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(4, 1);
		endField = board.getField(4, 2);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(7, 4);
		endField = board.getField(4, 4);
		MoveResult result = game.movePieceAndLogMove(startField, endField);
		assertTrue(result.equals(MoveResult.CHECK));
		
		startField = board.getField(3, 0);
		endField = board.getField(3, 1);
		result = game.movePieceAndLogMove(startField, endField);		
	}
	
	@Test(expected=InvalidMoveException.class)
	public void testMoveCausesCheck() throws Exception {
		System.out.println("MoveCausesCheck");
		Field startField = board.getField(4, 1);
		Field endField = board.getField(4, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(3, 1);
		endField = board.getField(3, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(3, 0);
		endField = board.getField(7, 4);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(4, 1);
		endField = board.getField(4, 2);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(7, 4);
		endField = board.getField(4, 4);
		MoveResult result = game.movePieceAndLogMove(startField, endField);
		assertTrue(result.equals(MoveResult.CHECK));
		
		startField = board.getField(4, 0);
		endField = board.getField(3, 1);
		result = game.movePieceAndLogMove(startField, endField);
		assertTrue(result.equals(MoveResult.OK));

		startField = board.getField(0, 1);
		endField = board.getField(0, 2);
		result = game.movePieceAndLogMove(startField, endField);
		assertTrue(result.equals(MoveResult.OK));
		
		startField = board.getField(3, 1);
		endField = board.getField(2, 2);
		result = game.movePieceAndLogMove(startField, endField);
	}
	
	
	
}
