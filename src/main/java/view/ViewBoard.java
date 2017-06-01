package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import model.game.Board;

public class ViewBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ViewChessman [][] fields = new ViewChessman[8][8];
	
	/**
	 * konstruktor inicjujacy glowny panel
	 * @param plansza 
	 */
	public ViewBoard(Board board, int[] x)
	{	
		setLayout(new GridLayout(8, 8));
		for(int i = 0 ; i < 8 ; i++){
			for(int j = 0 ; j < 8 ; j++){
				fields[i][j] = new ViewChessman(board.getField(j,7-i), x);
				fields[i][j].setSize(100, 100);
				if( i%2 == j%2 ) 
					fields[i][j].setBackground(Color.YELLOW);
				else
					fields[i][j].setBackground(Color.ORANGE);
				
				fields[i][j].setVisible(true);
				add(fields[i][j]);
			}
		}
	}

}
