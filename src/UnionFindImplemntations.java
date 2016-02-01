///////////////////////////////////////////////////////
public class UnionFindImplementations
{
    public static void main( String[] args )
    {
        // Testing purposes
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF();
        System.out.println( uf );
        uf.union( 6, 3 );
        uf.union( 2, 8 );
        uf.union( 2, 4 );
        uf.union( 9, 7 );
        uf.union( 2, 0 );
        uf.union( 7, 6 );
        uf.union( 1, 2 );
        uf.union( 8, 9 );
        uf.union( 4, 5 );
        System.out.println("after unions\n" + uf );
    }
}
///////////////////////////////////////////////////////
class QuickFindUF
{
    private int[] id;  // represent paths between objects
    private int count; // number of connected components
//-----------------------------------------------------
     public QuickFindUF( int n )
     {
        id = new int[n];
        for ( int i = 0; i < id.length; i++ ) { id[i] = i; }
        count = n;
    }
//-----------------------------------------------------
    public QuickFindUF()
    {
        final int DEFAULT_SIZE = 10;
        id = new int[DEFAULT_SIZE];
        for ( int i = 0; i < id.length; i++ ) { id[i] = i; }
        count = DEFAULT_SIZE;
    }
//-----------------------------------------------------
    public void union( int p, int q )
    {
        int pID = find( p );
        int qID = find( q );
        
        if ( pID == qID ) return;
        
        // Updates all id[p] connections to connect to
        // id[q]...thus when calling union(p,q) p connections
        // then point to q. objects point directly to the
        // object they are connected to.
        for ( int i = 0; i < id.length; i++ )
        {
            if ( id[i] == pID )
            {
                id[i] = qID;
            }
        }
        count--;
    }
//-----------------------------------------------------
    public boolean connected( int p , int q )
    {
        return ( id[p] == id[q] );
    }
//-----------------------------------------------------
    public int find( int p ) { return id[p]; }
//-----------------------------------------------------
    public int count() { return count; }
//-----------------------------------------------------
    public String toString()
    {
        String str = "";
        for ( int i = 0; i < id.length; i++ )
        {
            str += id[i] + " ";
        }
        
        return str;
    }
//-----------------------------------------------------
}
///////////////////////////////////////////////////////
class QuickUnionUF
{
    private int[] id;
    private int count; //number of components
//-----------------------------------------------------
    public QuickUnionUF( int n )
    {
        id = new int[n];
        for ( int i = 0; i < id.length; i++ ) { id[i] = i; }
        count = n;
    }
//-----------------------------------------------------
    public QuickUnionUF()
    {
        final int DEFAULT_SIZE = 10;
        id = new int[DEFAULT_SIZE];
        for ( int i = 0; i < id.length; i++ ) { id[i] = i; }
        count = DEFAULT_SIZE;
    }
//-----------------------------------------------------
    public void union( int p, int q )
    {
        int pID = root( p );
        int qID = root( q );
        
        if ( pID == qID ) return;
        
        // QuickUnion is very similar to QuickFind
        // implementation except connections are laid
        // out in a tree type structure where all
        // objects in one component connect to one root.
        id[pID] = qID;
        
        count--;
    }
//-----------------------------------------------------
    public boolean connected( int p , int q )
    {
        return ( root(p) == root(q) );
    }
//-----------------------------------------------------
    public int find( int p ) { return id[p]; }
//-----------------------------------------------------
    public int count() { return count; }
//-----------------------------------------------------
    private int root( int i )
    {
        while ( i != id[i] ) { i = id[i]; }
        return i;
        
    }
//-----------------------------------------------------
    public String toString()
    {
        String str = "";
        for ( int i = 0; i < id.length; i++ )
        {
            str += id[i] + " ";
        }
        
        return str;
    }
//-----------------------------------------------------
}
///////////////////////////////////////////////////////
// WeightedQuickUnionUF is an improvement to QuickUnion
// where a union will cause the root of one(smaller)
// tree to point to the root of the other(bigger) tree.
//
// Note there can even be one more improvement to this
// called path compression which is only one line of
// change! Inside root function while: id[i] = id[id[i]];
// After computing the root of a node set the id of each
// examined node to point to that root--making balanced trees.
//
///////////////////////////////////////////////////////
class WeightedQuickUnionUF
{
    private int[] id;
    private int[] size; // keeps track of size of tree root at i
    private int count;  // number of components
//-----------------------------------------------------
    public WeightedQuickUnionUF( int n )
    {
        id = new int[n];
        size = new int[n];
        for ( int i = 0; i < id.length; i++ ) { id[i] = i; size[i] = 1; }
        count = n;
    }
//-----------------------------------------------------
    public WeightedQuickUnionUF()
    {
        final int DEFAULT_SIZE = 10;
        id = new int[DEFAULT_SIZE];
        size = new int[DEFAULT_SIZE];
        for ( int i = 0; i < id.length; i++ ) { id[i] = i; size[i] = 1; }
        count = DEFAULT_SIZE;
    }
//-----------------------------------------------------
    public void union( int p, int q )
    {
        int pID = root( p );
        int qID = root( q );
        
        // Depending on which tree is bigger. Make that
        // smaller tree's root point to the bigger on.
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
//-----------------------------------------------------
    public boolean connected( int p , int q )
    {
        return ( root(p) == root(q) );
    }
//-----------------------------------------------------
    public int find( int p ) { return id[p]; }
//-----------------------------------------------------
    public int count() { return count; }
//-----------------------------------------------------
    private int root( int i )
    {
        while ( i != id[i] ) { i = id[i]; }
        return i;
        
    }
//-----------------------------------------------------
    public String toString()
    {
        String str = "";
        for ( int i = 0; i < id.length; i++ )
        {
            str += id[i] + " ";
        }
        
        return str;
    }
//-----------------------------------------------------
}
///////////////////////////////////////////////////////