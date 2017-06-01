package uni.chess.figures;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidMoveException;
import model.Model;
import model.figures.Bishop;
import model.figures.ChessPiece;
import model.figures.Rook;
import model.game.Board;
import model.game.Field;

public class RookTest {
	private Board board;
	private ChessPiece rook;

	@Before
	public void setupBoard() {
		board = new Board();
		board.emptyBoard();
		Field testField = board.getField(4, 4);
		testField.setChessPiece(new Rook(Model.Color.white, board));
		board.getField(1, 4).setChessPiece(new Rook(Model.Color.white, board));
		board.getField(4, 1).setChessPiece(new Rook(Model.Color.black, board));
		rook = testField.getChessPiece();
	}

	@Test
	public void moveUp() throws Exception {
		Field start = board.getField(4, 4);
		Field end = board.getField(4, 7);
		rook = start.getChessPiece();
		rook.movePiece(start, end);

		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(rook));
	}

	@Test
	public void moveLeft() throws Exception {
		Field start = board.getField(4, 4);
		Field end = board.getField(2, 4);
		rook = start.getChessPiece();
		rook.movePiece(start, end);

		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(rook));
	}

	@Test
	public void moveDown() throws Exception {
		Field start = board.getField(4, 4);
		Field end = board.getField(4, 2);
		rook = start.getChessPiece();
		rook.movePiece(start, end);

		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(rook));
	}

	@Test
	public void moveRight() throws Exception {
		Field start = board.getField(4, 4);
		Field end = board.getField(6, 4);
		rook = start.getChessPiece();
		rook.movePiece(start, end);

		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(rook));
	}

	@Test(expected = InvalidMoveException.class)
	public void moveInvalid_Trajectory() throws Exception {
		Field start = board.getField(4, 4);
		Field end = board.getField(6, 6);
		rook = start.getChessPiece();
		rook.movePiece(start, end);
	}

	@Test(expected = InvalidMoveException.class)
	public void moveInvalid_Obstacle() throws Exception {
		Field start = board.getField(4, 4);
		Field end = board.getField(0, 4);
		rook = start.getChessPiece();
		rook.movePiece(start, end);
	}

	@Test(expected = InvalidMoveException.class)
	public void moveInvalid_KillInvalid() throws Exception {
		Field start = board.getField(4, 4);
		Field end = board.getField(1, 4);
		rook = start.getChessPiece();
		rook.movePiece(start, end);
	}

	@Test
	public void killValid() throws Exception {
		Field start = board.getField(4, 4);
		Field end = board.getField(4, 1);
		rook = start.getChessPiece();
		rook.movePiece(start, end);

		assertTrue(start.getChessPiece() == null);
		assertTrue(end.getChessPiece().equals(rook));
	}

	@Test
	public void getFields() throws Exception {
		List<Field> fields = rook.getPossibleMoves(board.getField(4, 4));
		assertTrue(fields.size()==11);
	}
	
	@Test
	public void getFieldsRookInCorner() throws Exception {
		board.getField(0, 0).setChessPiece(new Rook(Model.Color.white, board));
		board.getField(0, 3).setChessPiece(new Bishop(Model.Color.black, board));
		board.getField(4, 0).setChessPiece(new Bishop(Model.Color.black, board));
		
		List<Field> fields = board.getField(0, 0).getChessPiece().getPossibleMoves(board.getField(0, 0));
		assertTrue(fields.size() == 7);
	}
}
