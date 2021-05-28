package edu.miracosta.cs113;
import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
  @Override
  	public void add(int index, E obj) {
	  	ListIterator<E> iter = listIterator(index);
	  	if(index > size || index < 0) {
	  		throw new IndexOutOfBoundsException("Invalid index " + index);
	  	}
	  	iter.add(obj);
	  
  	}
  	
  	public void addFirst(E obj) {
  		add(0, obj);
	  
  	}
  	
  	public void addLast(E obj) {
  		add(size, obj);
  	}

  	@Override
  	public E get(int index) { 	
  		if(index > size - 1 || index < 0) {
  			throw new IndexOutOfBoundsException("Invalid index " + index);
  		}
  		ListIterator<E> iter = listIterator(index); 
	  
  		return iter.next();
  	}  
  	
  	public E getFirst() {
  		if(head == null) {
  			return null;
  		}
  		return head.data;
  	}
  	
  	public E getLast() {
  		if(tail == null) {
  			return null;
  		}
  		return tail.data;
  	}

  	@Override
  	public int size() {
  		return size;
  	} // Fill Here
 
  	@Override
  	public E remove(int index){    
  		E returnValue = null;
  		ListIterator<E> iter = listIterator(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
  	}
  	
  	@Override
  	public Iterator iterator() { 
  		return new ListIter(0);  
  		
  	}
  
  	@Override
  	public ListIterator listIterator() { 
  		return new ListIter(0);  
  	}
  
  	@Override
  	public ListIterator listIterator(int index){
  		return new ListIter(index);
  	}
  	
  	public ListIterator listIterator(ListIterator iter) {     
	  return new ListIter( (ListIter) iter);  
	  
  	}
  	private static class Node<E> {     
  		private E data;
  		private Node<E> next = null;
        private Node<E> prev = null;
        
        private Node(E dataItem) { //constructor
        	data = dataItem;   
        }
  	}
  	
  	public class ListIter implements ListIterator<E> {
	  
  		private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;
        public ListIter(int i)  {   // constructor for ListIter class
        	if (i < 0 || i > size) {
        		throw new IndexOutOfBoundsException("Invalid index at " + i); 
        	}
    	
        	lastItemReturned = null;
 
        	if (i == size) {// Special case of last item
        		index = size;    
        		nextItem = null;     
        	} else { // start at the beginning
        		nextItem = head;
        		for (index = 0; index < i; index++) {
        			nextItem = nextItem.next;   
        		}  
        	}// end else
        }  // end constructor
        public ListIter(ListIter other) {  
        	nextItem = other.nextItem;
        	index = other.index;    
        }
    
        @Override
        public boolean hasNext() {
        	return nextItem != null;
        }
    
        @Override
        public boolean hasPrevious() {    
        	return size != 0 && (nextItem == null || nextItem.prev != null);
        	
        }
    
        @Override
        public int previousIndex() {
        	return index - 1;
        }
    
        @Override
        public int nextIndex() {
        	return index;
        }
    
        @Override
        public void set(E o)  {
        	if(lastItemReturned == null) {
        		throw new IllegalStateException("Help");
        	}
        	if(index >= size || index < 0) {
        		throw new IndexOutOfBoundsException("Invalid index at " + index);
        	}
        	lastItemReturned.data = o;
        }
    
        @Override
        public void remove(){
        	if(lastItemReturned == null) {
        		throw new IllegalStateException("Help");
        	}
        	if(lastItemReturned == head && size == 1) {
        		head = null;
        		tail = null;
        	}else if(lastItemReturned == head) {
        		lastItemReturned.next.prev = null;
        		head = lastItemReturned.next;
    		
        	} else if(lastItemReturned == tail) {
        		lastItemReturned.prev.next = null;
        		tail = lastItemReturned.prev;
        		
        	} else {
        		lastItemReturned.prev.next = lastItemReturned.next;
        		lastItemReturned.next.prev = lastItemReturned.prev;
        	}
        	size --;
        	index --;
        }
    
        @Override
        public E next()
        {  	
        	if(!hasNext()) {
        		throw new NoSuchElementException();
        	}
    	
        	lastItemReturned = nextItem;
        	nextItem = nextItem.next;
    	
        	index++;
    	
        	return lastItemReturned.data;
        }
    
        @Override
        public E previous() 
        {  
        	if(!hasPrevious()) {
        		throw new NoSuchElementException();
        	}
    	
        	nextItem = nextItem == null ? tail : nextItem.prev;   	
    	
        	lastItemReturned = nextItem;
        	index--;
        	return lastItemReturned.data;
        }
    
        @Override
        public void add(E obj) {
        	//if head is empty fill list
            if (head == null) {
                head = new Node<>(obj);
                tail = head;
            } else if (nextItem == head) {
                Node<E> newNode = new Node<>(obj);
                newNode.next = nextItem;
                nextItem.prev = newNode;
                head = newNode;
            } else if (nextItem == null) {
                Node<E> newNode = new Node<>(obj);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                Node<E> newNode = new Node<>(obj);
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                // Link it to the nextItem
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            size++;
            index++;
            lastItemReturned = null;
        }
    }
}