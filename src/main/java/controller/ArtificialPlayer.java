package controller;

import java.util.List;

import algorithms.ChessAlgorithm;
import exceptions.SurrenderException;
import model.Model.Color;
import model.Move;
import model.game.Board;
import model.game.Player;

public class ArtificialPlayer extends Player{
	private ChessAlgorithm algorithm;
	
	public ArtificialPlayer(Color color, Board board) {
		super(color, board);
	}
	
	public void setChessAlgorithm(ChessAlgorithm algorithm){
		this.algorithm = algorithm;
	}
	
	public Move move() throws SurrenderException{
		return algorithm.suggestMove();
	}
	
	public boolean denyMove(Move move){
		List<Move> suggestedMovesThisTurn = algorithm.getSuggestedMovesThisTurn();
		suggestedMovesThisTurn.add(move);
		if(suggestedMovesThisTurn.size() > 5000)
			return true;
		return false;
	}
	
	public void acceptMove(){
		algorithm.moveAccepted();
	}

}
