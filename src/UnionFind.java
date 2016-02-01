///////////////////////////////////////////////////////
public class UnionFind // Weighted Quick Union Find
{
	private int[] id;
	private boolean[] open;
	private boolean[] full;
	private int[] size; // keeps track of size of tree root at i
	private int count;  // number of components
//-----------------------------------------------------	
	public UnionFind( int n )
	{
		id = new int[n];
		open = new boolean[n];
		full = new boolean[n];
		size = new int[n];
		for ( int i = 0; i < id.length; i++ ) { id[i] = i; size[i] = 1; }
		count = n;
	}
//-----------------------------------------------------	
	public UnionFind()
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
//-----------------------------------------------------
	public boolean connected( int p , int q )
	{
		return ( root(p) == root(q) );
	}
//-----------------------------------------------------
	public void open( int i ) { open[i] = true; }
//-----------------------------------------------------
	public boolean isOpen( int i ) { return open[i]; }
//-----------------------------------------------------
	public void full( int i ) 
	{
		int pID = root(i);
		full[pID] = true; 
	}
//-----------------------------------------------------
	public boolean isFull( int i ) 
	{
		int pID = root(i);
		return full[pID]; 
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
}
///////////////////////////////////////////////////////