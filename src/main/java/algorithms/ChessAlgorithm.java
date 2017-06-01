package algorithms;

import java.util.List;

import exceptions.SurrenderException;
import model.Move;

public interface ChessAlgorithm {
	public Move suggestMove() throws SurrenderException;
	
	public void moveAccepted();
	
	public List<Move> getSuggestedMovesThisTurn();
}
