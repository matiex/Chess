package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.game.Board;

public class View {
	
	private ViewBoard viewBoard;
    /** glowny panel w ktorym beda wyswietlane pola i figury */
    public JPanel mainPanel;
    /** ramka w ktorej beda wszystkie elementy */
    public JFrame frame;
    
    public InfoPanel infoPanel;

    public View(final Board board, int[] x, long[] y) {

        frame = new JFrame("Szachy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300, 0, 920, 720);
        mainPanel = viewBoard = new ViewBoard(board, x);
        
        infoPanel = new InfoPanel(y);
        
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.EAST);
        mainPanel.setVisible(true);
        frame.setVisible(true);
    }
    
    public void setChecked(int x, int y)
    {
    	viewBoard.fields[x][y].setBackground(Color.GREEN);
    }
    
    public void setUnChecked(int x, int y)
    {
    	if( x%2 == y%2 ) 
    		viewBoard.fields[x][y].setBackground(Color.YELLOW);
		else
			viewBoard.fields[x][y].setBackground(Color.ORANGE);
    }

    /**
     * funkcja tworzy nowy watek aby zmienic widok
     * 
     * @param tab - tablica na podstawie ktorej rysuje pola
     */
    public void update(final Board board) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	
            	frame.repaint();
            	/*
                frame.remove(mainPanel);
                mainPanel = new ViewBoard(board);
                frame.add(mainPanel);
                frame.validate();
                */
            }
        });
    }
    
    public void close(){
    	frame.setVisible(false);
    	frame.dispose();
    }

}
