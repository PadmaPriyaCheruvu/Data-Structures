package pac;

/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
       
    
    public void swap(int a,int b)
    {
    	
    	if(a<theSize && b<theSize)
    	{
    	Node first = beginMarker.next;
    	Node second = beginMarker.next;
    	
    	first=getNode(a);
    	
    	Node temp = new Node<AnyType>((AnyType)second.data, first.prev, first.next);
    	first.next.prev = temp;
    	first.prev.next = temp;
    	
    	temp = new Node<AnyType>((AnyType)first.data, second.prev, second.next);
    	second.next.prev = temp;
    	second.prev.next = temp;
    	}
    	else
    	{
    		throw new IndexOutOfBoundsException();
    	}
    	
    	   	
    }
    
    
    public void shift(int a)
	{
    	if(a<0)
    	{
    		a = Math.abs(a);
    		for(int i = 0;i<a;i++)
    		{
    			Node temp = endMarker.prev;
    			
    			temp.prev.next = endMarker;
    			endMarker.prev = temp.prev;
    			
    			temp.next = beginMarker.next;
    			beginMarker.next.prev = temp;
    			
    			beginMarker.next = temp;
    			temp.prev = beginMarker;
    		}
    	}
    	else
    	{
    		for(int i=0;i<a;i++)
    		{
    			Node temp = beginMarker.next;

    			beginMarker.next = beginMarker.next.next;
    			beginMarker.next.prev = beginMarker;
    			
    			temp.next=endMarker;
    			endMarker.prev.next = temp;
    			
    			temp.prev = endMarker.prev;
    			endMarker.prev = temp;
    		}
    	}    	
	}
    
    
    public void erase(int idx, int num)
    {
    	Node start = beginMarker.next;
    	Node end = beginMarker.next;
    	if(theSize==0 || idx<0)
    	{
    		throw new IndexOutOfBoundsException();
    	}
    	else if(idx+num<=theSize)
    	{
    			for(int i=0;i<idx;i++)
    			{
    				start = start.next;
    			}
    			
    			for(int j=0;j<num;j++)
    			{
    				start.prev.next = start.next;
    				start.next.prev = start.prev;
    				start = start.next;
    			}
    	}
    	else
    	{
    		throw new IndexOutOfBoundsException();
    	}
    }
    
    
    public void insertList(int idx, MyLinkedList newLst)
    {
    	if(idx<theSize)
    	{
    		Node start = beginMarker.next;
    		for(int i=0;i<idx;i++)
    		{
    			start = start.next;
    		}
    		
    		newLst.endMarker.prev.next = start;
    		newLst.beginMarker.next.prev = start.prev;
    		
    		start.prev.next = newLst.beginMarker.next;
    		start.prev = newLst.endMarker.prev;    			
    	}
    	else
    	{
    		throw new IndexOutOfBoundsException();
    	}
    }
    
    
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );
        

        
        MyLinkedList<Integer> lst2 = new MyLinkedList<>( );
        lst2.add(100);
        lst2.add(200);
        lst2.add(300);
        

        System.out.println("List elements");
        System.out.println(lst);
        
//		  SWAP TESTING
        int a = 0;
        int b = 5;
        //System.out.println( "\nSwapping elements in indices "+a+" and "+b );
        //lst.swap(a,b);
        //System.out.println( lst );
        
        
// 		  SHIFT TESTING
        System.out.println( "\nShift value -8" );
        lst.shift(-8);
        System.out.println( lst );
        
        System.out.println( "\nShift value +3" );
        lst.shift(3);
        System.out.println( lst );
        
        
//		  ERASE TESTING
        System.out.println( "\nErasing 4 elements starting from index 2" );
        lst.erase(2, 4);
        System.out.println( lst );
        
        
//		  INSERT LIST
        System.out.println("\nInserting list "+lst2+" at index 4");
        lst.insertList(4, lst2);
        System.out.println(lst);
        
        
        
//      lst.remove( 0 );
//      lst.remove( lst.size( ) - 1 );


//      java.util.Iterator<Integer> itr = lst.iterator( );
//      while( itr.hasNext( ) )
//      {
//              itr.next( );
//              itr.remove( );
//              System.out.println( lst );
//      }
        
        }
}
