import java.util.*;
public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
public void add(int index, E obj)
  {
	  listIterator(index).add(obj);
   }
  public void addFirst(E obj) { // Fill Here 
	  Node newHead = new Node(obj);
	  if (head !=null) {
		  newHead.next = head;
		  newHead.prev = newHead;
  }
	  else {
		  head = newHead;
		  size++;
	  }
}
  public void addLast(E obj) { 
	  Node newTail = new Node(obj);
	  if (tail != null) {
		  newTail.prev = tail;
		  tail.next = newTail;
	  }
	  else {
		  tail = newTail;
		  size++;
	  }  
  }
  @Override
  public E get(int index) 
  { 
	  if (index < 0  || index > size) {
		  throw new  IndexOutOfBoundsException("Invalid index: " + index);
	  }
	  ListIterator<E> iter = listIterator(index);
	  return iter.next();
  }  
  public E getFirst() { return head.data;  }
  public E getLast() { return tail.data;  }

  public int size() {  return size;  } // Fill Here

  public E remove(int index)
  {     E returnValue = null;
        ListIterator<E> iter = listIterator(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
  }

  public Iterator iterator() { return new ListIter(0);  }
  public ListIterator listIterator() { return new ListIter(0);  }
  public ListIterator listIterator(int index){return new ListIter(index);}
  public ListIterator listIterator(ListIterator iter)
  {     return new ListIter( (ListIter) iter);  }

  // Inner Classes
  private static class Node<E>
  {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
        {   data = dataItem;   
        }
  }  // end class Node

  public class ListIter implements ListIterator<E> 
  {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   // 

    public ListIter(int i)
    {   if (i < 0 || i > size)
        {     throw new IndexOutOfBoundsException("Invalid index " + i); }
        	lastItemReturned = null;
 
        if (i == size)     // Special case of last item
        {     index = size;     nextItem = null;      
        }
        else          // start at the beginning
        {   nextItem = head;
            for (index = 0; index < i; index++)  nextItem = nextItem.next;   
        }// end else
    }  // end constructor

    public ListIter(ListIter other)
    {   nextItem = other.nextItem;
        index = other.index;    }

    public boolean hasNext() {   
    	return (nextItem != null);
    } // Fill Here
    public boolean hasPrevious()
    {   if(size!=0){
    	return (nextItem == null || nextItem.prev != null);
    		}
    	else {
    		return false;
    	}
    }
    public int previousIndex() {  return index - 1;    } // Fill Here
    public int nextIndex() {  return index;    } // Fill here
    public void set(E o)  {
    	if (lastItemReturned != null)
    	{
    		lastItemReturned.data = o;
    	}
    	else
    	{
    		throw new IllegalStateException();
    	}
    	size--;
    	index--;
    }  // not implemented
    public void remove(){
    	if(lastItemReturned != null)
    	{
    		if(lastItemReturned.prev !=null)
    		{
    			lastItemReturned.prev.next = lastItemReturned.next;
    		}
    		else
    		{
    			head = lastItemReturned.next;
    			if(tail == null)
    			{
    				head = null;
    			} else {
    				tail.next = null;
    			}
    		}
    		if (lastItemReturned.next != null)
    		{
    			lastItemReturned.next.prev = lastItemReturned.prev;}
    		else {
    			tail = lastItemReturned.prev;
    			
    			if(tail == null)
    			{
    				head = null;
    			}
    			else {
    				tail.next = null;
    			}
    		}
    		lastItemReturned = null;
    		index--;
    		size--;		
    	}
    	else {
    		throw new IllegalStateException();
    	}
    		
   }
    // not implemented

    public E next()
    {  if(!hasNext())
    	{
    	
    	}
        return lastItemReturned.data; // Fill Here 
    }

    public E previous() 
    {  
    	if(!hasPrevious())
    	{
    		throw new NoSuchElementException();
    	}
    	if(nextItem == null)
    	{
    		nextItem = tail;
    	}
    	else {
    		nextItem = nextItem.prev;
    	}
    	lastItemReturned = nextItem;
    	index--;
    	return lastItemReturned.data;
    }

    public void add(E obj) {
    	Node<E> newNode = new Node<E>(obj);
    	
    	if(head == null) {
    		head = newNode;
    		tail = newNode;
    	}
    	else if(nextItem == head)
    	{
    		newNode.next = nextItem;
    		nextItem.prev = newNode;
    		head = newNode;
    	}
    	else {
    		newNode.prev = nextItem.prev;
    		nextItem.prev.next = newNode;
    		newNode.next = nextItem;
    		nextItem.prev = newNode;	
    	}
    	size++;
    	index++;
    // Fill Here
    }
  }
}
