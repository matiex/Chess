package model;

import model.game.Field;

public class Move {
	public final Field startField;
	public final Field endField;
	public int moveValue;
	
	public Move(Field startField, Field endField){
		this.startField = startField;
		this.endField = endField;
		
		if(endField.getChessPiece()!=null){
			moveValue = endField.getChessPiece().getValue();
		} else {
			moveValue = 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endField == null) ? 0 : endField.hashCode());
		result = prime * result + ((startField == null) ? 0 : startField.hashCode());
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
		Move other = (Move) obj;
		if (endField == null) {
			if (other.endField != null)
				return false;
		} else if (!endField.equals(other.endField))
			return false;
		if (startField == null) {
			if (other.startField != null)
				return false;
		} else if (!startField.equals(other.startField))
			return false;
		return true;
	}
}
