package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import controller.HumanComputerGameController;
import exceptions.InvalidMoveException;
import exceptions.SurrenderException;
import model.Move;
import model.game.Field;
import view.View;

public class HumanVsRandom {
	
	View view;
	HumanComputerGameController game;
	private int tab[];
	private long times[];
	
	static boolean play=true;
	static boolean roundCondition=true;
	static boolean isLasting=false;
	
	static boolean flaga=false;
	static boolean round=false;
	
	static boolean timer1=false;
	static boolean timer2=false;
	
	static long startTime = 0;
	static long roundTime = 0;
	
	static long generalViewTime = 0;
	static long roundViewTime = 0;
	
	public HumanVsRandom (HumanComputerGameController a, View b, int c[], long d[] ) {
		this.game = a;
		this.view = b;
		this.tab = c;
		this.times = d;
	}
	
	private Timer timer = new Timer(50, new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(isLasting==true){
				view.update(game.getBoard());
			}
			
			if(flaga==true)
			{
				startTime = System.currentTimeMillis();
				flaga = false;
				timer1 = true;
			}
			
			if(round==true)
			{
				roundTime = System.currentTimeMillis();
				round = false;
				timer2 = true;
			}
			
			if( (System.currentTimeMillis() - roundTime) > 60000 && timer2==true) //dodano warunek na potrzeby debugowania
			{
				roundCondition = false;
				System.out.println("PRZEKROCZYLES CZAS NA RUCH - PORAŻKA");
			}
			
			if( (System.currentTimeMillis() - startTime) > 3600000 && timer1==true)
			{
				play = false;
			}
			
			if (timer1)
			{
				generalViewTime = (System.currentTimeMillis() - startTime) / 1000;
				times[0] = 3600 - generalViewTime;
			}
			
			if (timer2)
			{
				roundViewTime = (System.currentTimeMillis() - roundTime)/1000;
				times[1] = 60-roundViewTime;
			}
		}
	});
	
	public void sleep(){
        try {
            Thread.sleep(500);                 //1000 milisekund = 1 sekunda
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
	
	public void play(){
		view.update(game.getBoard());
		boolean ret = false;
				
		int sourceX=-1;
		int sourceY=-1;
		int destinationX=-1;
		int destinationY=-1;
		
        Field source=null;
        Field destination=null;
	    
    	if(flaga==false)
    	{
    		flaga=true;
    		timer.start();
    	}

	    while(play && roundCondition){
	    	isLasting=true;
	    	if(ret==false)
	    		round = true;

	    	while (tab[0]==-1 || tab[1]==-1) {
	    		sleep();
	    	}
	    	
	    	sourceX = tab[0];
	    	sourceY = tab[1];

	    	view.setChecked((7 - Math.abs(sourceY)), sourceX);
	    	
	    	tab[0]=-1;
	    	tab[1]=-1;
    	
	    	while (tab[0]==-1 || tab[1]==-1){
	    		sleep();
	    	}
	    	
	    	destinationX = tab[0];
	    	destinationY = tab[1];
	    	
	    	tab[0]=-1;
	    	tab[1]=-1;
	    	
	    	source = game.getBoard().getFieldAbsolute(sourceX, sourceY);
	    	destination = game.getBoard().getFieldAbsolute(destinationX, destinationY);
	    	
	    	Move move = new Move (source, destination);
	    	
	        for (int i=0;i<2;++i)
	        {
	        	tab[i] = -1; 
	        }

	    	try {
	    		game.playerMove(move);
	    	} catch (InvalidMoveException e) {
	    		view.setUnChecked((7 - Math.abs(sourceY)), sourceX);
	    		System.out.println("wybierz ponownie");	
	    		ret = true;
	    		continue;
	    	}
	    	view.setUnChecked((7 - Math.abs(sourceY)), sourceX);
	    	ret = false;
	    	isLasting=false;
	    	view.update(game.getBoard());
	    	sleep();

	     	try {
	     		game.opponentMove();
	    	} catch (SurrenderException e) {
	    		System.out.println("Wygrales!!! GRATULACJE ;-) ");	
				return;
			}
	     	view.update(game.getBoard());
	     	sleep();
	    	//view.update(game.getBoard());	
	    }
	    	timer.stop();
	    	System.out.println("Koniec czasu - REMIS!!!");
	    	view.close();
	}

}