package model.game;

import model.Model;
import model.figures.Bishop;
import model.figures.King;
import model.figures.Knight;
import model.figures.Pawn;
import model.figures.Queen;
import model.figures.Rook;

public class Board {
	/**tablica pól*/
	private Field field [][];

	public Board(){
		
		field = new Field[8][8];
		
		for (int i=0;i<8;++i) {
			for(int j=0;j<8;++j) 		
				field[i][j] = new Field(j, i);
		}
		
		placeBlackPieces();
		placeBlackPawns();
		placeWhitePieces();
		placeWhitePawns();
	}
	
	public void emptyBoard(){
		for (int i=0;i<8;++i) {
			for(int j=0;j<8;++j) 		
				getField(i,j).removeChessPiece();
		}
	}
	
	public Field getField(int x, int y)
	{
		if(x<0||x>7||y<0||y>7)
			return null;
		Field possible = field[y][x];
		FieldCoordinates possibleCoords = possible.getFieldCoordintes();
		if(possibleCoords.x == x && possibleCoords.y == y){
			return possible;
		}
		int invertedX = Math.abs(x-7);
		int invertedY = Math.abs(y-7);
		possible = field[invertedY][invertedX];
		possibleCoords = possible.getFieldCoordintes();
		if(possibleCoords.x == x && possibleCoords.y == y){
			return possible;
		}
		return null;
	}
	
	public Field getFieldAbsolute(int x, int y)
	{
		if(x<0||x>7||y<0||y>7)
			return null;
		return field[y][x];
	}
	
	private void placeBlackPieces () 
	{
		this.field[0][0].setChessPiece(new Rook(Model.Color.white, this));
		this.field[0][1].setChessPiece(new Knight(Model.Color.white, this));
		this.field[0][2].setChessPiece(new Bishop(Model.Color.white, this));
		this.field[0][3].setChessPiece(new Queen(Model.Color.white, this));
		this.field[0][4].setChessPiece(new King(Model.Color.white, this));
		this.field[0][5].setChessPiece(new Bishop(Model.Color.white, this));
		this.field[0][6].setChessPiece(new Knight(Model.Color.white, this));
		this.field[0][7].setChessPiece(new Rook(Model.Color.white, this));

	}
	
	private void placeBlackPawns () 
	{
		for (int i=0;i<8;++i)
			this.field[1][i].setChessPiece(new Pawn(Model.Color.white, this));
	}
	
	private void placeWhitePieces () 
	{
		this.field[7][0].setChessPiece(new Rook(Model.Color.black, this));
		this.field[7][1].setChessPiece(new Knight(Model.Color.black, this));
		this.field[7][2].setChessPiece(new Bishop(Model.Color.black, this));
		this.field[7][3].setChessPiece(new Queen(Model.Color.black, this));
		this.field[7][4].setChessPiece(new King(Model.Color.black, this));
		this.field[7][5].setChessPiece(new Bishop(Model.Color.black, this));
		this.field[7][6].setChessPiece(new Knight(Model.Color.black, this));
		this.field[7][7].setChessPiece(new Rook(Model.Color.black, this));
	}
	
	private void placeWhitePawns () 
	{
		for (int i=0;i<8;++i)
			this.field[6][i].setChessPiece(new Pawn(Model.Color.black, this));
	}

	public void renumberFields() {
		for(int i = 0; i < 8; i++){
			for( int j = 0; j < 8; j++){
				FieldCoordinates originalCoordinates = field[i][j].getFieldCoordintes();
				int newX = Math.abs(originalCoordinates.x-7);
				int newY = Math.abs(originalCoordinates.y-7);
				field[i][j].setFieldCoordinates(newX, newY);
			}
		}		
	}
	
	public void renumberFieldsColorBottom(Model.Color bottomColor){
		if(Model.Color.white.equals(bottomColor)){
			for(int i = 0; i < 8; i++){
				for( int j = 0; j < 8; j++){
					field[i][j].setFieldCoordinates(j, i);
				}
			}
		}else{
			for(int i = 0; i < 8; i++){
				for( int j = 0; j < 8; j++){
					field[i][j].setFieldCoordinates(7-j, 7-i);
				}
			}
		}
	}
	
}
