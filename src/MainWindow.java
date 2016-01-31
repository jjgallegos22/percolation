import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow 
{
    public static class Grid extends JPanel 
    {
        private List<Point> fillCells;

        public Grid() 
        {
            fillCells = new ArrayList<>(25);
        }

        @Override
        protected void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            for (Point fillCell : fillCells) 
            {
                int cellX = 10 + (fillCell.x * 10);
                int cellY = 10 + (fillCell.y * 10);
                g.setColor(Color.RED);
                g.fillRect(cellX, cellY, 10, 10);
            }
            g.setColor(Color.BLACK);
            g.drawRect(10, 10, 800, 500);

            for (int i = 10; i <= 800; i += 10) 
            {
                g.drawLine(i, 10, i, 510);
            }

            for (int i = 10; i <= 500; i += 10) 
            {
                g.drawLine(10, i, 810, i);
            }
        }

        public void fillCell(int x, int y) 
        {
            fillCells.add(new Point(x, y));
            repaint();
        }

    }
//////////////////////////////////////////////////////////////////////////////    
public static class RandomRun implements Runnable
{
    private Thread t;
    private String threadName;
    private Grid grid;
//----------------------------------------------------------------------------	   
    RandomRun() 
    { 
        threadName = "RandomRun";
    }
//----------------------------------------------------------------------------	   	
    public void run()
    {
    	System.out.println("Running " +  threadName );
    	grid = createGUI();
    	Random random = new Random();

        try 
        {
        	for( int i = 4; i < 500; i++ ) 
        	{
        		int x = random.nextInt(79+1);
            	int y = random.nextInt(49+1);
            	grid.fillCell(x, y);
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(200);
             }
         } catch (InterruptedException e) {
             System.out.println("Thread " +  threadName + " interrupted.");
         }
         System.out.println("Thread " +  threadName + " exiting.");		
    }
//----------------------------------------------------------------------------	   	
	    public void start()
	    {
	    	System.out.println("Starting " +  threadName );
	        if ( t == null )
	        {
	           t = new Thread (this, threadName);
	           t.start();
	        }
	    }
//----------------------------------------------------------------------------	   
	    public static Grid createGUI()
	    {
	    	Grid grid = new Grid();
	        JFrame window = new JFrame();
	        window.setSize(840, 560);
	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        window.add(grid);
	        window.setVisible(true);
	        return grid;
	    }
//----------------------------------------------------------------------------	
    }
    

    public static void main(String[] a) 
    {
        RandomRun r = new RandomRun();
        r.start();
    }
}