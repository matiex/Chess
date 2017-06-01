package controller;

import model.Model;
import model.Model.Color;
import model.Model.Name;
import model.figures.Bishop;
import model.figures.ChessPiece;
import model.figures.King;
import model.figures.Knight;
import model.figures.Pawn;
import model.figures.Queen;
import model.figures.Rook;
import model.game.Board;

public class Utils {
	
	public static Model.Color getOpposingColor(Model.Color color) {
		if (color.equals(Model.Color.white))
			return Model.Color.black;
		else
			return Model.Color.white;
	}

	public static ChessPiece createChessPiece(Color playerColor, Name movingChessPiece, Board board) {
		if(playerColor == null || movingChessPiece == null)
			return null;
		else{
			switch (movingChessPiece){
				case Pawn:
					return new Pawn(playerColor, board);
				case Rook:
					return new Rook(playerColor, board);
				case Knight:
					return new Knight(playerColor, board);
				case Bishop:
					return new Bishop(playerColor, board);
				case Queen:
					return new Queen(playerColor, board);
				case King:
					return new King(playerColor, board);
			}
		}
		return null;			
	}

}
