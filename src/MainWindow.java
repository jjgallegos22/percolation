import java.util.Random;
import javax.swing.JFrame;

//////////////////////////////////////////////////////////////////////////////
public class MainWindow
{
    public static void main(String[] a)
    {
        RandomRun r = new RandomRun();
        r.start();
    }
}
//////////////////////////////////////////////////////////////////////////////
class RandomRun implements Runnable
{
    private Thread t;
    private String threadName;
    private Grid grid;
//----------------------------------------------------------------------------
    RandomRun() { threadName = "RandomRun"; }
//----------------------------------------------------------------------------
//   Method: void run()
//   Description: main process for the Run Thread. Will random open cells on
//   the grid until it percolates.
//----------------------------------------------------------------------------
    public void run()
    {
        System.out.println("Running " +  threadName );
        grid = createGUI();
        Random random = new Random();

        try 
        {
            while ( !grid.percolates() )
            {
                int x = random.nextInt(49+1);
                int y = random.nextInt(49+1);
                grid.openCell(x, y);
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }
//----------------------------------------------------------------------------
//   Method: void start()
//   Description: starts the RandomRun Thread.
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
//   Method: Grid createGUI()
//   Description: Creates a window and grid to display on the screen.
//----------------------------------------------------------------------------
    public static Grid createGUI()
    {
        int size = 50;
        Grid grid = new Grid( 50 );
        JFrame window = new JFrame();
        window.setSize( 517, 539 );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.add( grid );
        window.setVisible( true );
        return grid;
    }
//----------------------------------------------------------------------------
}
//////////////////////////////////////////////////////////////////////////////