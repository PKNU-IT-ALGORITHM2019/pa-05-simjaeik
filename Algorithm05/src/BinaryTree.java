public class BinaryTree<E> {

	protected static class Node<E> {
		protected E data;
		protected Node<E> left;
		protected Node<E> right;
		protected Node<E> parent;

		public Node(E data) {
			this.data = data;
			parent = null;
			left = null;
			right = null;
		}

		public String toString() {
			return data.toString();
		}
	}

	public Node<E> root;
	public static int size = 0;

	public BinaryTree() {
		root = null;
		size ++;
	}

	public BinaryTree(E data) {
		Node<E> buf = new Node<E> (data);
		this.root = buf;
		size ++;
	}

	protected BinaryTree(Node<E> root) {
		this.root = root;
		size++;
	}

	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		size++;
		root = new Node<E>(data);
		if (leftTree != null) {
			root.left = leftTree.root;
			leftTree.root.parent = root;
		}
		else
			root.left = null;

		if (rightTree != null) {
			root.right = rightTree.root;
			rightTree.root.parent = root;	
		}
		else
			root.right = null;
	}

	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left != null)
			return new BinaryTree<E>(root.left);
		else
			return null;
	}

	public BinaryTree<E> getRightSubtree() {
		if (root != null && root.right != null)
			return new BinaryTree<E>(root.right);
		else
			return null;
	}

	public Node<Word> searchTree(Node<Word> x, String find ){	

		if ( x == null ) 
			return null;
		else if ( find.equals( x.data.word ) )
			return x;

		if ( find.compareTo( x.data.word ) < 0 )
			return searchTree(x.left, find);
		else
			return searchTree(x.right, find);
		
	}

	public Node<Word> minTree(Node<Word> x){
		while ( x.left != null)
			x = x.left;
		return x;
	}

	public Node<Word> maxTree(Node<Word> x){
		while ( x.right != null)
			x = x.right;
		return x;
	}

	public Node<Word> successorTree(Node<Word> z){
		if( z.right != null)
			return minTree(z.right);
		Node<Word> y = z.parent;
		while( y != null && z == y.right) {
			z = y;
			y = y.parent;
		}
		return y;
	}

	public Node<Word> preSuccessorTree(Node<Word> key){
		if( key.left != null)
			return maxTree(key.left);
		Node<Word> y = key.parent;
		while( y != null && key == y.left) {
			key = y;
			y = y.parent;
		}
		return y;
	}

	public void insertTree(BinaryTree<Word> tree, Node<Word> insert){
		Node<Word> prev = null;
		Node<Word> x = tree.root;

		while( x != null) {
			prev = x;
			if( insert.data.word.compareTo(x.data.word ) < 0 )
				x = x.left;
			else if ( insert.data.word.compareTo(x.data.word ) > 0 )
				x = x.right;
			else if ( insert.data.word.compareTo( x.data.word ) == 0 ) {
				x.data.size++;
				
				int a = insert.data.line.indexOf("(");
				int b = insert.data.line.indexOf(")");

				x.data.wordClass.add( insert.data.line.substring(a,b) );
				if( b != insert.data.line.length() - 1 )
					x.data.meaning.add( insert.data.line.substring( b ) );
				else
					x.data.meaning.add(" ");
				
				break;
			}
		}
		insert.parent = prev;
		if( prev == null) 
			tree.root = insert;
		else if ( insert.data.word.compareTo(prev.data.word ) < 0 )
			prev.left = insert;
		else 
			prev.right = insert;
	}

	public Node<Word> deleteTree(BinaryTree<Word> tree, Node<Word> z) {
		Node<Word> y;
		Node<Word> x;

		if ( z.left == null || z.right == null)
			y = z;
		else 
			y = successorTree(z);

		if( y.left != null)
			x = y.left;
		else 
			x = y.right;

		if ( x != null)
			x.parent = y.parent;

		if ( y.parent == null)
			tree.root = x;
		else if ( y == y.parent.left )
			y.parent.left = x;
		else
			y.parent.right = x;

		if ( y != z ) {
			z.data = y.data;

		}

		return y;
	}

}
