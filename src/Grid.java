import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

//////////////////////////////////////////////////////////////////////////////
public class Grid extends JPanel 
    {
        private List<Point> fillOpenCells;
        private List<Point> fillFullCells;
        private Percolation percolation;
        private int size;
//----------------------------------------------------------------------------
        public Grid( int N ) 
        {
        	fillOpenCells = new ArrayList<>(N/2);
        	fillFullCells = new ArrayList<>(N/2);
        	percolation = new Percolation(N);
        	size = N;
        }
//----------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) 
        {
            super.paintComponent( g );
            for ( Point fillOpenCell : fillOpenCells ) 
            {
                int cellX =  ( fillOpenCell.x * 10 );
                int cellY =  ( fillOpenCell.y * 10 );
                g.setColor( Color.WHITE );
                g.fillRect( cellX, cellY, 10, 10 );
            }
            
            for ( Point fillFullCell : fillFullCells ) 
            {
                int cellX = ( fillFullCell.x * 10 );
                int cellY = ( fillFullCell.y * 10 );
                g.setColor( Color.BLUE );
                g.fillRect( cellX, cellY, 10, 10 );
            }
            
	        this.setBackground( Color.BLACK );
            g.setColor( Color.BLACK );
            g.drawRect( 0, 0, 500, 500 );
	        
            for ( int i = 0; i <= 500; i += 10 ) 
            {
                g.drawLine( i, 0, i, 500) ;
                g.drawLine( 0, i, 500, i );
            }

        }
//----------------------------------------------------------------------------
        public void openCell( int x, int y ) 
        {
        	fillOpenCells.add( new Point( x, y ) );
        	percolationOpen( y, x );
            repaint();
        }
//----------------------------------------------------------------------------
        public void percolationOpen( int x, int y )
        {
        	if ( !percolation.isOpen( x, y ) )
        	{
	        	percolation.open( x, y );
	        	if ( percolation.isFull( x, y ) )
	        	{
	        		fillFullCells.add( new Point( y, x ) );
	        		updateOpenCells();
	        	}
        	}
        }
//----------------------------------------------------------------------------
        public void updateOpenCells()
        {
        	for ( int i = 0; i < size; i++ )
        	{
        		for ( int j = 0; j < size; j++ )
        		{
        			if ( percolation.isFull(i, j) )
        			{
        				fillFullCells.add( new Point( j, i ) );
        			}
        		}
        	}
        }
//----------------------------------------------------------------------------
        public boolean percolates()
        {
        	return percolation.percolates();
        }
//----------------------------------------------------------------------------
    }
//////////////////////////////////////////////////////////////////////////////