package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Model.Color;
import model.Model.Name;
import model.figures.ChessPiece;
import model.game.Field;

/**
 * Klasa do wyswietlania figur
 *
 */

public class ViewChessman extends JPanel implements MouseListener {
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Field field;
	private int tab[];
	
	private final Image blackPawn;
	private final Image blackRook;
	private final Image blackKnight;
	private final Image blackBishop;
	private final Image blackQueen;
	private final Image blackKing;
	
	private final Image whitePawn;
	private final Image whiteRook;
	private final Image whiteKnight;
	private final Image whiteBishop;
	private final Image whiteQueen;
	private final Image whiteKing;
	
	int x;
	int y;
	    /**
	     * @param pole
	     */
	    public ViewChessman(Field field, int[] x) {
	        this.field = field;
	        this.tab=x;
	        
	        this.blackPawn = new ImageIcon("src/main/java/materials/blackPawn.png").getImage();
	        this.blackRook = new ImageIcon("src/main/java/materials/blackRook.png").getImage();
	        this.blackKnight = new ImageIcon("src/main/java/materials/blackKnight.png").getImage();
	        this.blackBishop = new ImageIcon("src/main/java/materials/blackBishop.png").getImage();
	        this.blackQueen = new ImageIcon("src/main/java/materials/blackQueen.png").getImage();
	        this.blackKing = new ImageIcon("src/main/java/materials/blackKing.png").getImage();
	    	
	        this.whitePawn = new ImageIcon("src/main/java/materials/whitePawn.png").getImage();
	        this.whiteRook = new ImageIcon("src/main/java/materials/whiteRook.png").getImage();
	        this.whiteKnight = new ImageIcon("src/main/java/materials/whiteKnight.png").getImage();
	        this.whiteBishop = new ImageIcon("src/main/java/materials/whiteBishop.png").getImage();
	        this.whiteQueen = new ImageIcon("src/main/java/materials/whiteQueen.png").getImage();
	        this.whiteKing = new ImageIcon("src/main/java/materials/whiteKing.png").getImage();
	        
	        this.x = 17;
	        this.y = 10;
	        
	        this.addMouseListener(this);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {   	
	    	super.paintComponent(g);
	    	
	    	ChessPiece chessPiece = field.getChessPiece();
	    	if (chessPiece != null) {
	    		paintChessPiece(chessPiece, g);
	        }
	    }
	    
	    private void paintChessPiece(ChessPiece chessPiece, Graphics g) {
	    	Name name = chessPiece.getName();
    		Color color = chessPiece.getColor();
        	switch(name)
        	{
        		case Pawn:
        			paintPawn(color, g);    	    		
    	    		break;
        		case Rook:
        			paintRook(color, g);     			
        			break;
        		case Knight:
        			paintKnight(color, g);
        			break;
        		case Bishop:
        			paintBishop(color, g);
        			break;
        		case Queen:
        			paintQueen(color, g);
        			break;
        		case King:
        			paintKing(color, g);
        			break;        		
        		default:
        			break;
        			
        	}			
		}

		private void paintKing(Color color, Graphics g) {
			if(color.equals(Color.black))
				g.drawImage(blackKing, x, y, this);
			else
				g.drawImage(whiteKing, x, y, this);			
		}

		private void paintQueen(Color color, Graphics g) {
			if(color.equals(Color.black))
				g.drawImage(blackQueen, x, y, this);
			else
				g.drawImage(whiteQueen, x, y, this);
		}

		private void paintBishop(Color color, Graphics g) {
			if(color.equals(Color.black))
				g.drawImage(blackBishop, x, y, this);
			else
				g.drawImage(whiteBishop, x, y, this);
		}

		private void paintKnight(Color color, Graphics g) {
			if(color.equals(Color.black))
				g.drawImage(blackKnight, x, y, this);
			else
				g.drawImage(whiteKnight, x, y, this);
		}

		private void paintRook(Color color, Graphics g) {
			if(color.equals(Color.black))
				g.drawImage(blackRook, x+1, y, this);
			else
				g.drawImage(whiteRook, x+1, y, this);
		}

		private void paintPawn(Color color, Graphics g) {
			if(color.equals(Color.black))
				g.drawImage(blackPawn, x+2, y, this);
			else 
				g.drawImage(whitePawn, x+2, y, this);			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub	

			this.tab[0] = this.field.getFieldCoordintes().getX();
			this.tab[1] = this.field.getFieldCoordintes().getY();
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}		
}