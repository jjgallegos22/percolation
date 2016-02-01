//////////////////////////////////////////////////////////////////////////////
public class UnionFind // Weighted Quick Union Find
{
    private int[] id;
    private boolean[] open; // Keeps track of which spaces are open
    private boolean[] full; // Keeps track of which spaces are FULL (remember root is most important)
    private int[] size;     // keeps track of size of tree root at i
    private int count;      // number of components
//----------------------------------------------------------------------------
    public UnionFind( int n )
    {
        id = new int[n];
        open = new boolean[n];
        full = new boolean[n];
        size = new int[n];
        for ( int i = 0; i < id.length; i++ ) { id[i] = i; size[i] = 1; }
        count = n;
    }
//----------------------------------------------------------------------------
    public UnionFind()
    {
        final int DEFAULT_SIZE = 10;
        id = new int[DEFAULT_SIZE];
        size = new int[DEFAULT_SIZE];
        for ( int i = 0; i < id.length; i++ ) { id[i] = i; size[i] = 1; }
        count = DEFAULT_SIZE;
    }
//----------------------------------------------------------------------------
//   Method: void union()
//   Description: connects p and q. The smaller tree is connected to the larger
//   tree.
//----------------------------------------------------------------------------
    public void union( int p, int q )
    {
        int pID = root( p );
        int qID = root( q );
    
        if ( isFull(pID) || isFull(qID) )
        {
            full( pID );
            full( qID );
        }
        
        if ( pID == qID )
        {
            return;
        }
        else if ( size[pID] < size[qID] )
        {
            id[pID] = qID;
            size[qID] += size[pID];
        }
        else
        {
            id[qID] = pID;
            size[pID] += size[qID];
        }
        count--;
    }
//----------------------------------------------------------------------------
//   Method: boolean connected()
//   Description: checks if p and q are in the same connected component
//----------------------------------------------------------------------------
    public boolean connected( int p , int q )
    {
        return ( root(p) == root(q) );
    }
//----------------------------------------------------------------------------
//   Method: void open()
//   Description: open's the i space
//----------------------------------------------------------------------------
    public void open( int i ) { open[i] = true; }
//----------------------------------------------------------------------------
//   Method: boolean isOpen()
//   Description: checks whether i space is OPEN.
//----------------------------------------------------------------------------
    public boolean isOpen( int i ) { return open[i]; }
//----------------------------------------------------------------------------
//   Method: void full()
//   Description: marks i's root as full and by doing so, itself and anything
//   else in the same component as full.
//----------------------------------------------------------------------------
    public void full( int i )
    {
        int pID = root(i);
        full[pID] = true; 
    }
//----------------------------------------------------------------------------
//   Method: boolean isFull()
//   Description: checks whether i is in a FULL connection.
//----------------------------------------------------------------------------
    public boolean isFull( int i )
    {
        int pID = root(i);
        return full[pID]; 
    }
//----------------------------------------------------------------------------
//   Method: int find()
//   Description: returns where p is connected to.
//----------------------------------------------------------------------------
    public int find( int p ) { return id[p]; }
//----------------------------------------------------------------------------
//   Method: int count()
//   Description: returns the number of connected components
//----------------------------------------------------------------------------
    public int count() { return count; }
//----------------------------------------------------------------------------
//   Method: int root()
//   Description: internal method used to return the root of the component
//   i is in.
//----------------------------------------------------------------------------
    private int root( int i )
    {
        while ( i != id[i] ) { i = id[i]; }
        return i;
        
    }
//----------------------------------------------------------------------------
}
//////////////////////////////////////////////////////////////////////////////