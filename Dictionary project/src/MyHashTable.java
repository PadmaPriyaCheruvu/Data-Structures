
public class MyHashTable
{

    public MyHashTable( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    public MyHashTable( int size )
    {
        allocateArray( size );
        doClear( );
    }

    public HashEntry get(int i)
    {
    	return array[i];
    }
    
    public boolean insert( String x )
    {
            // Insert x as active
        int currentPos = findPos( x );
        if( isActive( currentPos ) )
            return false;

        if( array[ currentPos ] == null )
            ++occupied;
        array[ currentPos ] = new HashEntry( x, true );
        theSize++;
        
            // Rehash; see Section 5.5
        if( occupied > array.length / 2 )
            rehash( );
        
        return true;
    }


    public void rehash( )
    {
        HashEntry [ ] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray( 2 * oldArray.length );
        occupied = 0;
        theSize = 0;

            // Copy table over
        for( HashEntry entry : oldArray )
            if( entry != null && entry.isActive )
                insert( entry.element );
   }

    private int findPos( String x )
    {
        int offset = 1;
        int currentPos = myhash( x );
        
        while( array[ currentPos ] != null &&
                !array[ currentPos ].element.equals( x ) )
        {
            currentPos += offset;  // Compute ith probe
            if( currentPos >= array.length )
                currentPos -= array.length;
        }
        
        return currentPos;
    }


    public boolean remove( String x )
    {
        int currentPos = findPos( x );
        if( isActive( currentPos ) )
        {
            array[ currentPos ].isActive = false;
            theSize--;
            return true;
        }
        else
            return false;
    }
    
    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return theSize;
    }
    
    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains( String x )
    {
        int currentPos = findPos( x );
        return isActive( currentPos );
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActive( int currentPos )
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        doClear( );
    }

    private void doClear( )
    {
        occupied = 0;
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }
    
    public int myhash( String x )
    {
    	int hashVal = 7;
    	
    	for (int i = 0; i < x.length(); i++)
    	{
    		hashVal = hashVal*31 + x.charAt(i);
    	}

        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }
    
    public static class HashEntry
    {
        public String  element;   // the element
        public boolean isActive;  // false if marked deleted
        public boolean isWord;
        public boolean isPrefix;

        public HashEntry( String e )
        {
            this( e, true );
        }

        public HashEntry( String e, boolean i )
        {
            element  = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry [ ] array; // The array of elements
    private int occupied;                 // The number of occupied cells
    private int theSize;                  // Current size


    private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }
    
    void print()
    {
    	for(int i = 0;i<array.length;i++)
    		if(array[i]!=null)System.out.println(array[i].element + " , "+i);
    }
    
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }
    
    
//    public static void main( String [ ] args )
//    {
//        MyHashTable H = new MyHashTable( );
//
//        
//        long startTime = System.currentTimeMillis( );
//        
//        H.insert("apple");
//        H.insert("orange");
//        H.insert("banana");
//        H.insert("grapes");
//        
//        H.print();
//        
//        long endTime = System.currentTimeMillis( );
//        
//        System.out.println( "Elapsed time: " + (endTime - startTime) );
//    }

}