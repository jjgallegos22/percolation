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
	public void open(int i, int j)
	{
		if ( !isOpen(i, j) )
		{
			uf.open( getID( i, j ) );
						
			if ( i == 0 )
			{
				uf.full( getID( i, j ) );
			}

			// Check if noth, west, east, south are open if
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
	public boolean isOpen(int i, int j)
	{
		return uf.isOpen( getID( i, j ) );
	}
//----------------------------------------------------------------------------
	public boolean isFull(int i, int j)
	{
		return uf.isFull( getID( i, j ) );
	}
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
	private int getID( int i , int j )
	{
		// Formula for NxN matrix: ( i * n ) + j == index
		return ( ( i * size ) + j );
	}
//----------------------------------------------------------------------------
}
//////////////////////////////////////////////////////////////////////////////