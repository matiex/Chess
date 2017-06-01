package algorithms;

import controller.ChessGame;
import model.game.Board;
import model.game.Player;

public class AlgorithmFactory {
	private final Board board;
	private final Player player;
	private final Player opponent;
	private final ChessGame chessGame;
	
	public AlgorithmFactory (Board board, Player player, Player opponent, ChessGame game) {
		this.chessGame = game;
		this.board = board;
		this.player = player;
		this.opponent = opponent;
	}
	
	public MinMaxAlgorithm getMinMaxInstance(){
		return new MinMaxAlgorithm(board, opponent, player, chessGame);
	}
	
	public RandomAlgorithm getRandomAlgorithmInstance(){
		return new RandomAlgorithm(board, opponent);
	}
	
	public RandomAlgorithm getRandomAlgorithmInstanceComputer(){
		return new RandomAlgorithm(board, player);
	}
	
}
