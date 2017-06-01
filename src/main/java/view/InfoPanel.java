package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Font DefaultFont;
	private final int height;
	private final int width;
	private long tab[];
	
	public InfoPanel(long x[])
	{
		this.width = 120;
		this.height = 720;
		this.setPreferredSize(new Dimension(width, height));
		this.DefaultFont = new Font("Arial", Font.PLAIN, 20);
		this.tab = x;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{    
		super.paintComponent(g);
		drawInfo(g);
	}
	/**
	 * Rysowanie liczby zyc i punktow
	 * 
	 */
	private void drawInfo(final Graphics g)
	{
		g.setColor(new Color(222, 184, 135));
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, width, height);
		
		g.setFont(DefaultFont);
		g.setColor(Color.white);
		
		String s1 = "Koniec";
        g.drawString(s1, 10, 35 );
        
        String s2 = "gry: ";
        g.drawString(s2, 10, 55 );
        
        g.setColor(new Color(165, 42, 42));
        String s5 = String.valueOf(tab[0]/60) + "min " + String.valueOf(tab[0]%60) + "s";
        g.drawString(s5, 10, 80);

        g.setColor(Color.white);
        String s3 = "Koniec";
        g.drawString(s3, 10, 150);
        
        String s4 = "tury: ";
        g.drawString(s4, 10, 170);
        
        g.setColor(new Color(165, 42, 42));
        String s6 = String.valueOf(tab[1]) + "s";
        g.drawString(s6, 10, 195);
        
    } 

}
