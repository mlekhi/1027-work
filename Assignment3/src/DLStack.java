
/**
 * This class represents an extended stack ADT implemented using a doubly linked list
 * @author Maya Lekhi
 */

public class DLStack<T> implements DLStackADT<T> {
	
	/**
	 * Reference to the node at the top of the stack
	 */
	private DoubleLinkedNode<T> top;
	
	/**
	 * Number of data items stored in the stack
	 */
	private int numItems;
	 
	/**
	 * Constructor creates an empty stack
	 */
	public DLStack() {
		// setting the stack to null
		top = null;
		// setting the number of items to 0
		numItems = 0;
	}
	
	/**
	 * Adds the given dataItem to the top of the stack
	 * @param dataItem	item to be added to the top of the stack
	 */
	public void push(T dataItem) {
		// setting the top to a new dataItem node if it's empty
        if (isEmpty()) {
            top = new DoubleLinkedNode<T>(dataItem);
        } else {
        	// creating a new node for dataItem if stack is not empty
            DoubleLinkedNode<T> addNode = new DoubleLinkedNode<T>(dataItem);
            // setting top to the new node's previous item
        	addNode.setPrevious(top);
        	// setting the new node as top's next item
            top.setNext(addNode);
            // setting the new node to the new top of the stack
            top = addNode;
        }
        // incrementing the number of items in the stack
        numItems++;
	}
	
	/**
	 * Removes and returns the data item at the top of the stack
	 * Throws EmptyStackException if stack is empty
	 * @return item at the top of the stack
	 */
	public T pop() throws EmptyStackException {
		// checking if the stack is empty and returning corresponding error
		if (isEmpty()) {
			throw new EmptyStackException("Stack is empty");
		}
		
		// storing the data in the top node of the stack to be returned after it is popped
		T popped = top.getElement();
		
		// checking if there are multiple nodes in the stack after the data in top
		if (top.getPrevious() != null) {
			// setting top to the item before it
			top = top.getPrevious();
			// updating the reference after top to null and erasing the old top node data
			top.setNext(null);
		} else {
			// if there is only one item on stack, it sets the top of the stack to null
			top = null;
		}

		// decrementing the number of items in the stack
		numItems--;
		// returning the item that was removed from the stack
		return popped;
	}
	
	
	/**
	 * Removes and returns the kth item from the top of the stack
	 * Throws InvalidItemException if k's value is invalid
	 * @param k	the number of the data item to be removed from the stack
	 * @return kth data item removed from the stack
	 */
	public T pop(int k) throws InvalidItemException {
		// checking if k greater than the items in the stack or zero or negative
		if (k > numItems || k <= 0) {
			// throwing the exception if k is an invalid entry 
			throw new InvalidItemException("Invalid item");
		}
		
		// storing the data in the top node of the stack to be returned after it is popped
		T popped = top.getElement();

		// checking if k wants to pop the top item from the stack
		if (k == 1) {
			// checking if there are multiple nodes in the stack after the data in top
			if (top.getPrevious() != null) {
				// setting top to the item before it
				top = top.getPrevious();
				// updating the reference after top to null and erasing the old top node data
				top.setNext(null);
			}
		} else {
			// assigning the top node to a temporary variable
			DoubleLinkedNode<T> temp = top;
			// iterating through the list k times
			for (int i = 1; i < k; i++) {
				// traversing the stack and updating temp get to the node that k was referencing
				temp = temp.getPrevious();
			}
			
			// updating the data to be returned after popping to the kth element data
			popped = temp.getElement();
			// storing the previous and next nodes of the node to be popped
		    DoubleLinkedNode<T> prev = temp.getPrevious();
		    DoubleLinkedNode<T> next = temp.getNext();
		    
		    // setting the next of temp's previous value to temp's next value if the previous value isn't null
		    if (prev != null) {
		    	prev.setNext(next);
		    }
		    
		    // setting the previous of temp's next value to temp's previous value if the next value isn't null
		    if (next != null) {
		    	next.setPrevious(prev);
		    }
		    // updating top to the new stack's top node
		    top = next;
		}

		// decrementing the number of items in the stack
		numItems--;
		// returning the item that was removed from the stack
		return popped;
	}
	
	/**
	 * Returns the data item at the top of the stack without removing it
	 * Throws EmptyStackException if stack is empty
	 * @return item at the top of the stack
	 */
	public T peek() throws EmptyStackException {
		// throwing an exception if the stack is empty
		if (isEmpty()) {
			throw new EmptyStackException("Stack is empty");
		}
		
		// returning the top element data if the stack is not empty
		return top.getElement();
	}
	
	/**
	 * Returns true if the stack is empty and it returns false otherwise
	 * @return boolean representing whether or not the stack is empty
	 */
	public boolean isEmpty() {
		// returning whether there are 0 items in the stack or not
		return numItems == 0;
	}
	
	/**
	 * Accessor method to get the size of the stack
	 * @return the number of items in the stack
	 */
	public int size() {
		return numItems;
	}
	
	/**
	 * Accessor method to get the top of the stack
	 * @return the node at the top of the stack
	 */
	public DoubleLinkedNode<T> getTop() {
		return top;
	}
	
	/**
	 * Returns a string of the form “[data1 data2 … datan]”
	 * @return ordered string representing the data stored in the stack
	 */
	public String toString() {
		// creating empty string to be returned
		String toPrint = "";
		// assigning the top of the stack to a temporary variable
		DoubleLinkedNode<T> temp = top;
		
		// iterating through the number of items in the stack (save the last one)
		for (int i = 0; i < numItems - 1; i++) {
			// adding the formatted data from the current node into the string
			toPrint = toPrint + temp.getElement().toString() + " ";
			// getting the next node in the stack
			temp = temp.getPrevious();
		}
		
		// adding the last string representation to the string without a space at the end
		toPrint = toPrint + temp.getElement().toString();
		
		// returning the formatted string of the node data
		return "[" + toPrint + "]";
	}
}
