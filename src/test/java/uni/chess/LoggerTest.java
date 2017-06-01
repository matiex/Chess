package uni.chess;



import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ChessGame;
import logger.LoggedMove;
import logger.MoveLogger;
import model.Model;
import model.Model.Color;
import model.game.Board;
import model.game.Field;
import model.game.Player;

public class LoggerTest {
	
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
	public void testProperCreationForWhite() {
		Field whiteKingField = board.getField(4, 0);
		assertTrue(whiteKingField.getChessPiece().getName().equals(Model.Name.King));
		assertTrue(whiteKingField.getChessPiece().getColor().equals(Model.Color.white));
		
		Field blackKingField = board.getField(4, 7);
		assertTrue(blackKingField.getChessPiece().getName().equals(Model.Name.King));
		assertTrue(blackKingField.getChessPiece().getColor().equals(Model.Color.black));
	}
	
	@Test
	public void testProperCreationForBlack() {
		game = new ChessGame(board, logger, Model.Color.black, new Player(Model.Color.black, board), new Player(Model.Color.white, board));
		
		board.renumberFieldsColorBottom(Color.black);
		
		Field whiteKingField = board.getField(3, 7);
		assertTrue(whiteKingField.getChessPiece().getName().equals(Model.Name.King));
		assertTrue(whiteKingField.getChessPiece().getColor().equals(Model.Color.white));
		
		Field blackKingField = board.getField(3, 0);
		assertTrue(blackKingField.getChessPiece().getName().equals(Model.Name.King));
		assertTrue(blackKingField.getChessPiece().getColor().equals(Model.Color.black));
	}
	
	@Test
	public void testGetFieldByAbsoluteAddress(){
		Field testField = board.getFieldAbsolute(2, 0);
		assertTrue(testField.getChessPiece()!=null);
		assertTrue(testField.getChessPiece().getName().equals(Model.Name.Bishop));
		assertTrue(testField.getChessPiece().getColor().equals(Model.Color.white));
		
		testField = board.getFieldAbsolute(4, 7);
		assertTrue(testField.getChessPiece()!=null);
		assertTrue(testField.getChessPiece().getName().equals(Model.Name.King));
		assertTrue(testField.getChessPiece().getColor().equals(Model.Color.black));
	}
	
	
	@Test
	public void testFourMoveOpening() throws Exception{
		Field startField = board.getField(4, 1);
		Field endField = board.getField(4, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(4, 1);
		endField = board.getField(4, 3);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(4, 3);
		endField = board.getField(3, 4);
		game.movePieceAndLogMove(startField, endField);
		
		startField = board.getField(4, 0);
		endField = board.getField(4, 3);
		game.movePieceAndLogMove(startField, endField);
		
		assertTrue(board.getFieldAbsolute(4, 1).getChessPiece() == null);
		assertTrue(board.getFieldAbsolute(4, 3).getChessPiece() == null);
		assertTrue(board.getFieldAbsolute(3, 4).getChessPiece() != null);
		assertTrue(board.getFieldAbsolute(3, 4).getChessPiece().getName().equals(Model.Name.Queen));
		assertTrue(board.getFieldAbsolute(3, 7).getChessPiece() == null);
		
		List<LoggedMove> history = logger.getHistory();
		
		assertTrue(history.size() == 4);
		assertTrue(history.get(0).pieceKilled.equals(Model.Name.Pawn));
		assertTrue(history.get(0).playerColor.equals(Model.Color.black));
		assertTrue(history.get(1).playerColor.equals(Model.Color.white));
		assertTrue(history.get(1).pieceKilled.equals(Model.Name.Pawn));
	}


}
