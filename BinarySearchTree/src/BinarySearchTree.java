
// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */


public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	static final int COUNT = 10;  
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     * @throws Exception 
     */
    public AnyType findMin( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     * @throws Exception 
     */
    public AnyType findMax( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
    
    //COUNT
    private int nodeCount( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
        	count++;
        	nodeCount( t.left );
        	nodeCount( t.right );
        }
        return count;
    }
    
    public int nodeCount()
    {
    	count = 0;
    	if (isEmpty())
    		return 0;
    	else
    		return nodeCount(root);
    }

    //FULL TREE
    public boolean isFull()
    {
    	if(isEmpty())
    		return true;
    	else
    		return isFull(root);
    }
    
    private boolean isFull( BinaryNode<AnyType> t )
    {
    	//Empty tree
    	if(isEmpty())
    	{
    		return true;
    	}
    	
    	if(isFull==true)
    	{
	    	if(t.left != null && t.right != null)
	    	{
	    		isFull(t.left);
	    		isFull(t.right);
	    	}
	    	else if((t.left != null && t.right == null) || (t.left == null && t.right != null))
	    	{
	    		isFull = false;
	    	}
    	}
    	
    	return isFull;
    }
    
    //COMPARE TREE STRUCTURE
    private boolean compareStructure ( BinaryNode<AnyType> t1 , BinaryNode<AnyType> t2 )
    {

    	
    	if(sameStructure!=false)
    	{
    		if(t1!=null && t2!=null && t1.left != null && t2.left != null)
    		{
    			compareStructure(t1.left,t2.left);
    		}
    		else if (t1.left==null && t2.left==null) {}
    		else sameStructure = false;
    		
    		if(t1!=null && t2!=null && t1.right != null && t2.right != null)
    		{
    			compareStructure(t1.right,t2.right);
    		}
    		else if (t1.right==null && t2.right==null) {}
    		else sameStructure = false;
    		
    	}
    	
    	return sameStructure;
    }
    
    public boolean compareStructure ( BinarySearchTree<AnyType> tree )
    {
    	boolean f = true;
    	if(isEmpty() && tree.isEmpty())
    		return true;
    	else if(isEmpty() || tree.isEmpty())
    		return false;
    	else
    	{
    		f = compareStructure(root , tree.root);
    	}
    	
    	return f;
    	
    }
    
    //EQUALS
    private boolean equals ( BinaryNode<AnyType> t1 , BinaryNode<AnyType> t2 )
    {
    	if(sameTree!=false)
    	{
    		if(t1!=null && t2!=null && t1.element == t2.element)
    		{
    			if (t1.left==null && t2.left==null) {}
	    		else if(t1.left!=null && t2.left!=null && t1.left.element == t2.left.element )
	    		{
	    			equals(t1.left,t2.left);
	    		}
	    		else sameTree = false;
	    		
    			
    			if (t1.right==null && t2.right==null) {}
	    		else if(t1.right!=null && t2.right!=null && t1.right.element == t2.right.element)
	    		{
	    			equals(t1.right,t2.right);
	    		}
	    		else sameTree = false;
    		}
    		else
    			sameTree = false;
    		
    	}
    	
    	return sameTree;
    }
    
    public boolean equals ( BinarySearchTree<AnyType> tree )
    {
    	boolean f = true;
    	if(isEmpty() && tree.isEmpty())
    		return true;
    	else if (isEmpty() && tree.isEmpty())
    		return false;
    	else
    	{
    		f = equals(root , tree.root);
    	}
    	
    	return f;
    	
    }
    
    //COPY
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t)
    {
    	BinaryNode<AnyType> newNode = new BinaryNode<AnyType>(t.element);
    		 if (t == null) return null;
    	     if(t.left!=null)   newNode.left = copy(t.left);
    	     if(t.right!=null)  newNode.right = copy(t.right);
    	return newNode;
    }
    
    public BinaryNode<AnyType> copy(BinarySearchTree<AnyType> newTree)
    {
    	if(isEmpty())
    		return null;
    	else
    		return copy(root);
    }
    
    //MIRROR
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t)
    {
    	BinaryNode<AnyType> newNode = null;
    	BinaryNode<AnyType> temp = new BinaryNode<AnyType>(null);
    		 if(t!=null)
    		 {
    			 newNode = new BinaryNode<AnyType>(t.element);
    			 newNode.left = t.right;
    			 newNode.right = t.left;
    			 newNode.left = mirror(newNode.left);
    			 newNode.right = mirror(newNode.right);
    		 }
    	return newNode;
    }
    
    public BinaryNode<AnyType> mirror()
    {
    	if(isEmpty())
    		return new BinaryNode<AnyType>(null);
    	else
    		return mirror(root);
    }
    
    //ISMIRROR
    public boolean isMirror(BinarySearchTree<AnyType> t1, BinarySearchTree<AnyType> t2)
    {
    	if(t1.isEmpty() && t2.isEmpty()) return true;
    	
    	  BinarySearchTree<AnyType> temp = new BinarySearchTree<>( );
    	  temp.root = t2.mirror();
		  return t1.equals(temp);
    }
    
    public BinaryNode<AnyType> searchParent(AnyType x)
    {
    	
    	BinaryNode<AnyType> temp = root;
    	if((temp.left!=null && temp.left.element.equals(x)) || (temp.right!=null && temp.right.element.equals(x)))
    		return temp;
    	else
    		{
    		if(x.compareTo(temp.element) < 0)
    			temp = temp.left;
    		else
    			temp = temp.right;
    		}
    	return temp;
    }
    
    //ROTATE RIGHT
    public void rotateRight(AnyType x)
    {
    	
    	if(isEmpty())
    		System.out.println("Empty tree");
    	else
    	{
    	if(contains(x))
	    	{
		    	BinaryNode<AnyType> target = new BinaryNode<AnyType>(null);
		    	BinaryNode<AnyType> temp = new BinaryNode<AnyType>(null);
		    	BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(null);
		    	if(root.element.equals(x))
		    	{
		    		if(root.left == null)
		    		{
		    			System.out.println("Cannot rotate right!! "+x+" has no left child.");
		    		}
		    		else
		    		{
			    		target = root;
			    		temp = target.left.right;
			    		temp2 = target.left;
			    		target.left.right = target;
			    		target.left = temp;
			    		root = temp2;
		    		}
		    	}
		    	else
		    	{
		        	BinaryNode<AnyType> parent = new BinaryNode<AnyType>(null);
		    		parent = searchParent(x);
		    		
		    		if(parent.left !=null && parent.left.element == x) target = parent.left;
		    		else target = parent.right;
		    		
		    		if(target.left == null)
		    		{
		    			System.out.println("Cannot rotate right!! "+x+" has no left child.");
		    		}
		    		else 
		    		{
		    			temp = target.left.right;
			    		target.left.right = target;
			    		temp2 = target.left;
			    		target.left = temp;
			    		
			    		if(parent.left !=null && parent.left.element == x)  parent.left = temp2;
			    		else parent.right = temp2;		    			
		    		}
		    	}
	    	}
    	else
    		System.out.println("Node not present");
    	}
    }
    
    //ROTATE LEFT
    public void rotateLeft(AnyType x)
    {
    	
    	if(isEmpty())
    		System.out.println("Empty tree");
    	else
    	{
    	if(contains(x))
	    	{
		    	BinaryNode<AnyType> target = new BinaryNode<AnyType>(null);
		    	BinaryNode<AnyType> temp = new BinaryNode<AnyType>(null);
		    	BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(null);
		    	if(root.element.equals(x))
		    	{
		    		if(root.right == null)
		    		{
		    			System.out.println("Cannot rotate left!! "+x+" has no right child.");
		    		}
		    		else
		    		{
			    		target = root;
			    		temp = target.right;
		    			temp2=target.right.left;
		    			target.right = temp2;
		    			temp.left=target;
		    			root = temp;
		    		}
		    	}
		    	else
		    	{
		        	BinaryNode<AnyType> parent = new BinaryNode<AnyType>(null);
		    		parent = searchParent(x);
		    		
		    		if(parent.left!=null && parent.left.element == x) target = parent.left;
		    		else target = parent.right;
		    		
		    		if(target.right == null)
		    		{
		    			System.out.println("Cannot rotate left!! "+x+" has no right child.");
		    		}
		    		else 
		    		{
		    			temp = target.right;
		    			temp2=target.right.left;
		    			target.right = temp2;
		    			temp.left=target;
			    		
			    		if(parent.left!=null && parent.left.element == x)  parent.left = temp;
			    		else parent.right = temp;		    			
		    		}
		    	}
	    	}
    	else
    		System.out.println("Node not present");
    	}
    	
    }
       
      /** The tree root. */
    private BinaryNode<AnyType> root;
    private int count = 0;
    private boolean isFull = true;
    private boolean sameStructure = true;
    private boolean sameTree = true;
    private boolean rootFlag = true;
    
    int depth(BinaryNode<AnyType> node)  
    { 
        if (node == null) 
            return 0; 
        else 
        { 
            int l = depth(node.left); 
            int r = depth(node.right); 
   
            if (l > r)  return (l + 1); 
            else  return (r + 1); 
        } 
    } 

    //PRINT LEVELS
     void printLevels() 
    { 
        int d = depth(root); 
        int i; 
        for (i=1; i<=d; i++) 
        { 
        	printSingleLevel(root, i); 
            System.out.println(); 
        } 
    } 

    void printSingleLevel(BinaryNode<AnyType> node, int lvl) 
    { 
        if (node == null) 
            return; 
        if (lvl == 1) 
            System.out.print(node.element + " "); 
        else if (lvl > 1) 
        { 
        	printSingleLevel(node.left, lvl-1); 
        	printSingleLevel(node.right, lvl-1); 
        } 
    } 
    
    public void print2DUtil(BinaryNode<AnyType> root, int space)  
    {  
        // Base case  
        if (root == null)  
            return;  
      
        // Increase distance between levels  
        space += COUNT;  
      
        // Process right child first  
        print2DUtil(root.right, space);  
      
        // Print current node after space  
        // count  
        System.out.print("\n");  
        for (int i = COUNT; i < space; i++)  
            System.out.print(" ");  
        System.out.print(root.element + "\n");  
      
        // Process left child  
        print2DUtil(root.left, space);  
    }
    
        // Test program
    public static void main( String [ ] args ) throws Exception
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> t3 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> copyTree = new BinarySearchTree<>( );
        BinarySearchTree<Integer> mirrorTree = new BinarySearchTree<>( );
        final int NUMS = 4000;
        final int GAP  =   37;
        int c = 0;
        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        { t.insert( i ); c++;}

        for( int i = 1; i < NUMS; i+= 2 )
        {  t.remove( i ); c--;}
        
        
        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
                     
        t2.insert(60);
        t2.insert(70);
        t2.insert(40);
        t2.insert(20);
        t2.insert(50);
        t2.insert(10);
        t2.insert(30);
        
        t3.insert(6);
        t3.insert(7);
        t3.insert(4);
        t3.insert(2);
        t3.insert(5);
        t3.insert(1);
        t3.insert(3);
               
        System.out.println("Number of nodes is "+t.nodeCount());
        System.out.println("Full state is "+t.isFull());
        System.out.println("Same structure state is "+t.compareStructure(t2));

        copyTree.root = t.copy(copyTree);
        System.out.println("Equals "+t.equals(copyTree));        
        System.out.println("Copy check - "+t.equals(copyTree));
        
		mirrorTree.root = t.mirror();
		System.out.println("isMirror "+t.isMirror(t, mirrorTree));
	
		System.out.println("Rotating right on 6");
		t3.rotateRight(6);
		t3.print2DUtil(t3.root, 0);
        System.out.println("------------------");
		System.out.println("Rotating left on 4");
        t3.rotateLeft(4);
        t3.print2DUtil(t3.root, 0);
        
        System.out.println("Rotating left on 5");
        t3.rotateLeft(5);
        
        System.out.println("Printing levels");
        t3.printLevels();

        }
}