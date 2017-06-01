package uni.chess;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Model;
import model.figures.Bishop;
import model.figures.Queen;
import model.figures.Rook;
import model.game.Board;
import model.game.Field;

public class BoardTest {

	private Board board;

	@Before
	public void setupBoard() {
		board = new Board();
		board.emptyBoard();
		Field testField = board.getField(3, 3);
		testField.setChessPiece(new Bishop(Model.Color.black, board));
		
		testField = board.getField(6, 1);
		testField.setChessPiece(new Rook(Model.Color.black, board));
		
		testField = board.getField(7, 5);
		testField.setChessPiece(new Queen(Model.Color.white, board));
	}
	
	@Test
	public void renumberFields() {
		board.renumberFields();
		
		Field testField = board.getField(4, 4);
		assertTrue(testField.getChessPiece() != null);
		assertTrue(testField.getChessPiece().getName().equals(Model.Name.Bishop));
		assertTrue(testField.getChessPiece().getColor().equals(Model.Color.black));
		
		testField = board.getField(1, 6);
		assertTrue(testField.getChessPiece() != null);
		assertTrue(testField.getChessPiece().getName().equals(Model.Name.Rook));
		assertTrue(testField.getChessPiece().getColor().equals(Model.Color.black));
		
		testField = board.getField(0, 2);
		assertTrue(testField.getChessPiece() != null);
		assertTrue(testField.getChessPiece().getName().equals(Model.Name.Queen));
		assertTrue(testField.getChessPiece().getColor().equals(Model.Color.white));
	}
}
