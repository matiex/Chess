package model.game;

import model.figures.ChessPiece;

public class PieceFieldPair {
	public ChessPiece piece;
	public Field field;
	
	public PieceFieldPair(ChessPiece piece, Field field){
		this.piece = piece;
		this.field = field;
	}
}
