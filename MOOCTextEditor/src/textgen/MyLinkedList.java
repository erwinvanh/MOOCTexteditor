package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0 ;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
//		size ++;
//		LLNode<E> n = new LLNode<E>(element);
//		n.prev = tail.prev;
//		n.next = n.prev.next;
//		n.prev.next = n;
//		tail.prev = n;
		// Add new element at last position, always equal to size
		int i = size;
		add (i, element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index < 0 || index >= size ) {
			throw new IndexOutOfBoundsException();
		}
		//LLNode<E> tmp = head.next;
		//int i;
        //for ( i = 0; i < index; i ++) {
        //	tmp = tmp.next;
        //}
		LLNode<E> tmp = getElement(index);
		return tmp.data;
	}

	private LLNode<E> getElement(int index) 
	{
		LLNode<E> tmp = head.next;
		int i;
        for ( i = 0; i < index; i ++) {
        	tmp = tmp.next;
        }
		return tmp;
	}
	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (index < 0 || ( index > size && size != 0) ) {
			//System.out.println("Size " + size + "  index " + index);
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}

		LLNode<E> tmp = getElement(index);
		//int i;
        //for ( i = 0; i < index; i ++) {
        //	tmp = tmp.next;
        //}
		LLNode<E> n = new LLNode<E>(element);
		n.prev = tmp.prev;
		n.next = tmp.prev.next;
		tmp.prev.next = n;
		tmp.prev = n;
		
		size ++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 ||  index >= size ) {
			throw new IndexOutOfBoundsException();
		}

		LLNode<E> tmp = getElement(index);
		tmp.prev.next = tmp.next;
		tmp.next.prev =  tmp.prev;
		// Decrease size
		size --;
		return tmp.data;
		
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index < 0 || index >= size ) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		LLNode<E> tmp = getElement(index);
		E returnValue = tmp.data; 
		tmp.data = element;
		return returnValue;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
