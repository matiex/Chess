package model.game;

import java.util.HashMap;
import java.util.Map;

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

public class Player {
	protected final Model.Color color;
	protected final Map<FieldCoordinates, ChessPiece> pieces = new HashMap<>();
	protected final Board board;
	
	public Player(Model.Color color, Board board){
		this.color = color;
		this.board = board;
		fillPieces();
	}

	private void fillPieces() {
		fillPawns();
		fillFigures();
		
	}

	private void fillPawns() {
		for(int i = 0; i <= 7 ; i++){
			if(Model.Color.black.equals(color))
				pieces.put(new FieldCoordinates(i, 6), new Pawn(Model.Color.black, board));
			else
				pieces.put(new FieldCoordinates(i, 1), new Pawn(Model.Color.white, board));
		}
	}
	
	private void fillFigures() {
		if(Model.Color.white.equals(color)){
			pieces.put(new FieldCoordinates(0,0), new Rook(Model.Color.white, board));
			pieces.put(new FieldCoordinates(1,0), new Knight(Model.Color.white, board));
			pieces.put(new FieldCoordinates(2,0), new Bishop(Model.Color.white, board));
			pieces.put(new FieldCoordinates(3,0), new Queen(Model.Color.white, board));
			pieces.put(new FieldCoordinates(4,0), new King(Model.Color.white, board));
			pieces.put(new FieldCoordinates(5,0), new Rook(Model.Color.white, board));
			pieces.put(new FieldCoordinates(6,0), new Knight(Model.Color.white, board));
			pieces.put(new FieldCoordinates(7,0), new Rook(Model.Color.white, board));
		} else {
			pieces.put(new FieldCoordinates(0,7), new Rook(Model.Color.black, board));
			pieces.put(new FieldCoordinates(1,7), new Knight(Model.Color.black, board));
			pieces.put(new FieldCoordinates(2,7), new Bishop(Model.Color.black, board));
			pieces.put(new FieldCoordinates(3,7), new Queen(Model.Color.black, board));
			pieces.put(new FieldCoordinates(4,7), new King(Model.Color.black, board));
			pieces.put(new FieldCoordinates(5,7), new Rook(Model.Color.black, board));
			pieces.put(new FieldCoordinates(6,7), new Knight(Model.Color.black, board));
			pieces.put(new FieldCoordinates(7,7), new Rook(Model.Color.black, board));
		}
	}

	public void update() {
		pieces.clear();
		for(int x = 0; x <= 7; x++){
			for(int y = 0; y <= 7; y++){
				Field updatedField = board.getFieldAbsolute(x, y);
				if(updatedField.getChessPiece() != null &&
						updatedField.getChessPiece().getColor().equals(color))
					pieces.put(updatedField.getPermanentCoordinates(), updatedField.getChessPiece());
			}
		}
	}
	
	public Map<FieldCoordinates, ChessPiece> showPieces(){
		return pieces;
	}
	
	public Color getColor(){
		return color;
	}

	public Field getKingField() {
		FieldCoordinates kingCoords = null;
		for(Map.Entry<FieldCoordinates, ChessPiece> entry : pieces.entrySet()){
			if(entry.getValue().getName().equals(Name.King)){
				kingCoords = entry.getKey();
				break;
			}				
		}
		return board.getFieldAbsolute(kingCoords.x, kingCoords.y);
	}
	
}
