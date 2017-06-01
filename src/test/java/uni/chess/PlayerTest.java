package uni.chess;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Model;
import model.Model.Color;
import model.figures.ChessPiece;
import model.game.Board;
import model.game.Field;
import model.game.FieldCoordinates;
import model.game.Player;


public class PlayerTest {
	
	private Board board;
	private Player playerWhite;
	private Player playerBlack;
	
	@Before
	public void setupBoard() {
		board = new Board();
		playerWhite = new Player(Color.white, board);
		playerBlack = new Player(Color.black, board);
	}
	
	@Test
	public void testFourMoveOpening() throws Exception{
		Field startField = board.getField(4, 1);
		Field endField = board.getField(4, 3);
		startField.getChessPiece().movePiece(startField, endField);
		updatePlayers();
		board.renumberFields();
		
		startField = board.getField(4, 1);
		endField = board.getField(4, 3);
		startField.getChessPiece().movePiece(startField, endField);
		updatePlayers();
		board.renumberFields();
		
		startField = board.getField(4, 3);
		endField = board.getField(3, 4);
		startField.getChessPiece().movePiece(startField, endField);
		updatePlayers();
		board.renumberFields();
		
		startField = board.getField(4, 0);
		endField = board.getField(4, 3);
		startField.getChessPiece().movePiece(startField, endField);
		updatePlayers();
		board.renumberFields();
		
		assertTrue(board.getFieldAbsolute(4, 1).getChessPiece() == null);
		assertTrue(board.getFieldAbsolute(4, 3).getChessPiece() == null);
		assertTrue(board.getFieldAbsolute(3, 4).getChessPiece() != null);
		assertTrue(board.getFieldAbsolute(3, 4).getChessPiece().getName().equals(Model.Name.Queen));
		assertTrue(board.getFieldAbsolute(3, 7).getChessPiece() == null);
		
		Map<FieldCoordinates, ChessPiece> whitePieces = playerWhite.showPieces();
		assertTrue(whitePieces.size() == 15);
		ChessPiece piece2 = whitePieces.get(board.getFieldAbsolute(4, 1).getFieldCoordintes());
		assertTrue(piece2 == null);
		assertTrue(whitePieces.get(board.getFieldAbsolute(4, 3).getFieldCoordintes()) == null);
		assertTrue(whitePieces.get(board.getFieldAbsolute(3, 4).getFieldCoordintes()) == null);
		
		Map<FieldCoordinates, ChessPiece> blackPieces = playerBlack.showPieces();
		assertTrue(blackPieces.size() == 15);
		assertTrue(blackPieces.get(board.getFieldAbsolute(3, 7).getFieldCoordintes()) == null);
		assertTrue(blackPieces.get(board.getFieldAbsolute(3, 6).getFieldCoordintes()) == null);
		assertTrue(blackPieces.get(board.getFieldAbsolute(3, 4).getFieldCoordintes()).equals(board.getFieldAbsolute(3, 4).getChessPiece()));
	}
	
	private void updatePlayers(){
		playerWhite.update();
		playerBlack.update();
	}

}
