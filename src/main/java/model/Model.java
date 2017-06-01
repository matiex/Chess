package model;

import model.game.Board;

/**
 * Model gry. Zawiera wszystkie struktury danych reprezentujace elementy gry i zasady
 * dotyczace rozgrywki.
 *
 */

public class Model {
	/** plansza */
	private Board board;
	
	public enum Color 
	{
		white,
		black
	}
	
	public enum Name 
	{
		Pawn,
		Rook,
		Knight,
		Bishop,
		Queen,
		King
	}
	
	
	public Model()
	{
		this.board = new Board();
	}
	
	public Board getBoard()
	{
		return this.board;
	}
}
