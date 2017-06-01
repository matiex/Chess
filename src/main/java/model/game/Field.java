package model.game;

import model.figures.ChessPiece;

public class Field {
	
	private ChessPiece chessPiece;
	private FieldCoordinates fieldCoordinates;
	private final FieldCoordinates permanentCoordinates;
	
	public Field(int x, int y)
	{
		this.chessPiece=null;
		this.fieldCoordinates = new FieldCoordinates(x, y);
		this.permanentCoordinates = new FieldCoordinates(x, y);
	}
	
	public Field(int x, int y, ChessPiece chessman)
	{
		this.chessPiece=chessman;
		this.fieldCoordinates = new FieldCoordinates(x, y);
		this.permanentCoordinates = new FieldCoordinates(x, y);
	}
	
	public ChessPiece getChessPiece()
	{
		return this.chessPiece;
	}
	
	public void setChessPiece(ChessPiece chessman)
	{
		this.chessPiece = chessman;
	}
	
	public void removeChessPiece()
	{
		this.chessPiece=null;
	}
	
	public FieldCoordinates getFieldCoordintes(){
		return this.fieldCoordinates;
	}
	
	public void setFieldCoordinates(int x, int y){
		this.fieldCoordinates.x = x;
		this.fieldCoordinates.y = y;
	}
	
	public FieldCoordinates getPermanentCoordinates(){
		return this.permanentCoordinates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chessPiece == null) ? 0 : chessPiece.hashCode());
		result = prime * result + ((fieldCoordinates == null) ? 0 : fieldCoordinates.hashCode());
		result = prime * result + ((permanentCoordinates == null) ? 0 : permanentCoordinates.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (chessPiece == null) {
			if (other.chessPiece != null)
				return false;
		} else if (!chessPiece.equals(other.chessPiece))
			return false;
		if (fieldCoordinates == null) {
			if (other.fieldCoordinates != null)
				return false;
		} else if (!fieldCoordinates.equals(other.fieldCoordinates))
			return false;
		if (permanentCoordinates == null) {
			if (other.permanentCoordinates != null)
				return false;
		} else if (!permanentCoordinates.equals(other.permanentCoordinates))
			return false;
		return true;
	}
}
