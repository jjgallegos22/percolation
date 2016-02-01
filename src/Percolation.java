//////////////////////////////////////////////////////////////////////////////
public class Percolation 
{
    private UnionFind uf;
    private int size;
//----------------------------------------------------------------------------
    public Percolation(int N)
    {
        uf = new UnionFind( N*N );
        size = N;
    }
//----------------------------------------------------------------------------
//   Method void open()
//   Description: Opens the given space if not already open and creates
//   connections to to open spaces around it (no diagonal connections allowed)
//----------------------------------------------------------------------------
    public void open(int i, int j)
    {
        if ( !isOpen(i, j) )
        {
            uf.open( getID( i, j ) );
                        
            if ( i == 0 )
            {
                uf.full( getID( i, j ) );
            }
    
            // Check if north, west, east, south are open if
            // so then connect them. 
            if ( i != 0 && isOpen( i-1, j ) )        // Connect north if open
            {
                uf.union( getID(i,j), getID(i-1, j) );
            }
            if ( j != 0 && isOpen( i, j-1 ) )        // Connect west if open
            {
                uf.union( getID(i,j), getID(i, j-1) );
            }
            if ( j != (size-1) && isOpen( i, j+1 ) ) // Connect east if open
            {
                uf.union( getID(i,j), getID(i, j+1) );
            }
            if ( i != (size-1) && isOpen( i+1, j ) ) // Connect south if open
            {
                uf.union( getID(i,j), getID(i+1, j) );
            }
        }
    }
//----------------------------------------------------------------------------
//   Method boolean isFull()
//   Description: Return true if the given space is a "FULL" space, ie. if
//   the space has a path back to the matrix it's said to be FULL.
//----------------------------------------------------------------------------
    public boolean isOpen(int i, int j) { return uf.isOpen( getID( i, j ) ); }
//----------------------------------------------------------------------------
//   Method boolean isOpen()
//   Description: Return true if the given space in the matrix is "OPEN"
//----------------------------------------------------------------------------
    public boolean isFull(int i, int j) { return uf.isFull( getID( i, j ) ); }
//----------------------------------------------------------------------------
//   Method boolean percolates()
//   Description: Return true if there is a path the percolates
//----------------------------------------------------------------------------
    public boolean percolates()
    {
        int i = size - 1;
        for ( int j = 0; j < size; j++ )
        {
            if ( uf.isFull( getID( i, j ) ) )
            {
                return true;
            }
        }
        return false;
    }
//----------------------------------------------------------------------------
//   Method: int getID()
//   Description: We are storing paths for a NxN matrix to Union Find data
//   structures which stores its path on a 1D array thus we need to, convert
//   2D array coordinates to 1D index. Formula: ( i * n ) + j == index
//----------------------------------------------------------------------------
    private int getID( int i , int j ) { return ( ( i * size ) + j ); }
//----------------------------------------------------------------------------
}
//////////////////////////////////////////////////////////////////////////////