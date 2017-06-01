package uni.chess.figures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidMoveException;
import model.Model;
import model.figures.ChessPiece;
import model.figures.King;
import model.figures.Rook;
import model.game.Board;
import model.game.Field;

public class KingTest {
	
private Board board;
	
	@Before
	public void setupBoard(){
		board = new Board();
		board.emptyBoard();
		Field testField = board.getField(4, 0);
		testField.setChessPiece(new King(Model.Color.white, board));
		board.getField(0, 0).setChessPiece(new Rook(Model.Color.white, board));
		board.getField(7, 0).setChessPiece(new Rook(Model.Color.white, board));
	}
	
	@Test
	public void moveKing() throws Exception{
		Field kingField = board.getField(4, 0);
		Field targetField = board.getField(3, 0);
		ChessPiece king = kingField.getChessPiece();
		king.movePiece(kingField, targetField);
		
		assertTrue(targetField.getChessPiece() != null);
	}
	
	@Test
	public void moveDiagonal() throws Exception {
		Field kingField = board.getField(4, 0);
		Field targetField = board.getField(5, 1);
		ChessPiece king = kingField.getChessPiece();
		king.movePiece(kingField, targetField);
		
		assertTrue(targetField.getChessPiece() != null && kingField.getChessPiece() == null);
	}
	
	@Test(expected = InvalidMoveException.class)
	public void moveKing_KillNotAllowed() throws Exception{
		board.getField(3, 0).setChessPiece(new Rook(Model.Color.white, board));
		Field kingField = board.getField(4, 0);
		Field targetField = board.getField(3, 0);
		ChessPiece king = kingField.getChessPiece();
		king.movePiece(kingField, targetField);
	}
	
	@Test(expected = InvalidMoveException.class)
	public void moveKing_Invalid() throws Exception{
		Field kingField = board.getField(4, 0);
		Field targetField = board.getField(2, 0);
		ChessPiece king = kingField.getChessPiece();
		king.movePiece(kingField, targetField);
	}
	
	@Test
	public void castleRight() throws Exception{
		Field kingField = board.getField(4, 0);
		Field targetField = board.getField(6, 0);
		ChessPiece king = kingField.getChessPiece();
		king.movePiece(kingField, targetField);
		
		kingField = board.getField(6, 0);
		targetField = board.getField(5, 0);
		assertTrue(kingField.getChessPiece().equals(king));
		assertTrue(targetField.getChessPiece().getName().equals(Model.Name.Rook));
	}
	
	@Test
	public void castleLeft() throws Exception{
		Field kingField = board.getField(4, 0);
		Field targetField = board.getField(1, 0);
		ChessPiece king = kingField.getChessPiece();
		king.movePiece(kingField, targetField);
		
		kingField = board.getField(1, 0);
		targetField = board.getField(3, 0);
		assertTrue(kingField.getChessPiece().equals(king));
		assertTrue(targetField.getChessPiece().getName().equals(Model.Name.Rook));
	}
	
	@Test(expected = InvalidMoveException.class)
	public void invalidCastle() throws Exception{
		Field kingField = board.getField(4, 0);
		Field targetField = board.getField(3, 0);
		ChessPiece king = kingField.getChessPiece();
		king.movePiece(kingField, targetField);
		king.movePiece(targetField, kingField);
		Field castleField = board.getField(6, 0);
		king.movePiece(kingField, castleField);
		
		kingField = board.getField(6, 0);
		targetField = board.getField(5, 0);
		assertTrue(kingField.getChessPiece().equals(king));
		assertTrue(targetField.getChessPiece().getName().equals(Model.Name.Rook));
	}

}
