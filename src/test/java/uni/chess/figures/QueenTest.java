package uni.chess.figures;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidMoveException;
import model.Model;
import model.figures.ChessPiece;
import model.figures.Queen;
import model.game.Board;
import model.game.Field;


public class QueenTest {

	private Board board;
	private ChessPiece queen;
	
	@Before
	public void setupBoard(){
		board = new Board();
		board.emptyBoard();
		Field testField = board.getField(3, 3);
		testField.setChessPiece(new Queen(Model.Color.white, board));
		board.getField(1, 3).setChessPiece(new Queen(Model.Color.white, board));
		board.getField(6, 6).setChessPiece(new Queen(Model.Color.black, board));
		queen = testField.getChessPiece();
	}
	
	@Test
	public void testMoveUpLeft() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		queen.movePiece(startField, board.getField(1, 5));
		
		Field testField = board.getField(1, 5);
		assertTrue(testField.getChessPiece().getName().equals(queen.getName()));
		assertTrue(testField.getChessPiece().getColor().equals(queen.getColor()));
	}
	
	@Test
	public void testMoveUpRight() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		queen.movePiece(startField, board.getField(5,5));
		
		Field testField = board.getField(5,5);
		assertTrue(testField.getChessPiece().getName().equals(queen.getName()));
		assertTrue(testField.getChessPiece().getColor().equals(queen.getColor()));
	}
	
	@Test
	public void testMoveDownLeft() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		queen.movePiece(startField, board.getField(0, 0));
		
		Field testField = board.getField(0, 0);
		assertTrue(testField.getChessPiece().getName().equals(queen.getName()));
		assertTrue(testField.getChessPiece().getColor().equals(queen.getColor()));
	}
	
	@Test
	public void testMoveDownRight() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		queen.movePiece(startField, board.getField(6, 0));
		
		Field testField = board.getField(6, 0);
		assertTrue(testField.getChessPiece().getName().equals(queen.getName()));
		assertTrue(testField.getChessPiece().getColor().equals(queen.getColor()));
	}
	
	@Test (expected = InvalidMoveException.class)
	public void testMoveUpLeft_Obstacle() throws Exception{
		Field startField = board.getField(3, 3);
		Queen queen = (Queen)startField.getChessPiece();
		board.getField(2, 4).setChessPiece(new Queen(Model.Color.black, board));
		queen.movePiece(startField, board.getField(1, 5));
	}
	
	@Test(expected = InvalidMoveException.class)
	public void testMoveUpRight_Obstacle() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		board.getField(4, 4).setChessPiece(new Queen(Model.Color.black, board));
		queen.movePiece(startField, board.getField(6,6));
	}
	
	@Test(expected = InvalidMoveException.class)
	public void testMoveDownLeft_Obstacle() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		board.getField(1, 1).setChessPiece(new Queen(Model.Color.black, board));
		queen.movePiece( startField, board.getField(0, 0));
	}
	
	@Test(expected = InvalidMoveException.class)
	public void testMoveDownRight_Obstacle() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		board.getField(5,1).setChessPiece(new Queen(Model.Color.black, board));
		queen.movePiece(startField, board.getField(6, 0));
	}
	
	@Test(expected = InvalidMoveException.class)
	public void testInvalidMove() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		queen.movePiece(startField, board.getField(1, 2));
	}
	
	@Test(expected = InvalidMoveException.class)
	public void testKill() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		board.getField(6, 6).setChessPiece(new Queen(Model.Color.white, board));
		queen.movePiece(startField, board.getField(6,6));
	}
	
	@Test
	public void testKillValid() throws Exception{
		Field startField = board.getField(3, 3);
		ChessPiece queen = startField.getChessPiece();
		board.getField(6, 6).setChessPiece(new Queen(Model.Color.black, board));
		queen.movePiece(startField, board.getField(6,6));
		
		Field testField = board.getField(6, 6);
		assertTrue(testField.getChessPiece().getName().equals(queen.getName()));
		assertTrue(testField.getChessPiece().getColor().equals(queen.getColor()));
	}
	
	@Test
	public void moveUp() throws Exception{
		Field start = board.getField(3, 3);
		Field end = board.getField(3, 7);
		queen = start.getChessPiece();
		queen.movePiece(start, end);
		
		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(queen));
	}
	
	@Test
	public void moveLeft() throws Exception{
		Field start = board.getField(3, 3);
		Field end = board.getField(2, 3);
		queen = start.getChessPiece();
		queen.movePiece(start, end);
		
		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(queen));
	}
	
	@Test
	public void moveDown() throws Exception{
		Field start = board.getField(3, 3);
		Field end = board.getField(3, 1);
		queen = start.getChessPiece();
		queen.movePiece(start, end);
		
		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(queen));
	}
	
	@Test
	public void moveRight() throws Exception{
		Field start = board.getField(3, 3);
		Field end = board.getField(6, 3);
		queen = start.getChessPiece();
		queen.movePiece(start, end);
		
		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(queen));
	}
	
	@Test (expected = InvalidMoveException.class)
	public void moveInvalid_Trajectory() throws Exception{
		Field start = board.getField(3, 3);
		Field end = board.getField(6, 5);
		queen = start.getChessPiece();
		queen.movePiece(start, end);
	}
	
	@Test (expected = InvalidMoveException.class)
	public void moveInvalid_Obstacle() throws Exception{
		Field start = board.getField(3, 3);
		Field end = board.getField(0, 3);
		queen = start.getChessPiece();
		queen.movePiece(start, end);
	}
	
	@Test (expected = InvalidMoveException.class)
	public void moveInvalid_KillInvalid() throws Exception{
		Field start = board.getField(3, 3);
		Field end = board.getField(1, 3);
		queen = start.getChessPiece();
		queen.movePiece(start, end);
	}
	
	@Test 
	public void killValid() throws Exception{
		Field start = board.getField(3, 3);
		Field end = board.getField(6, 6);
		queen = start.getChessPiece();
		queen.movePiece(start, end);
		
		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(queen));
	}
	
	@Test
	public void possibleMoves() throws Exception{
		Field start = board.getField(3, 3);
		queen = start.getChessPiece();
		List<Field> fields = queen.getPossibleMoves(start);
		assertTrue(fields.size() == 24 );
	}
	
	@Test
	public void possibleMovesFromCorner() throws Exception{
		Field start = board.getField(3, 3);
		start.removeChessPiece();
		start = board.getField(0, 0);
		start.setChessPiece(new Queen(Model.Color.white, board));
		List<Field> fields = queen.getPossibleMoves(start);
		assertTrue(fields.size() == 20 );
	}
	
}
