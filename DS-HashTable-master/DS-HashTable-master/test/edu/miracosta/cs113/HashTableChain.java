package edu.miracosta.cs113;
import java.util.*;

/**
 * HashTable implementation using chaining to tack a pair of key and value pairs.
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class HashTableChain<K, V> implements Map<K, V>  {

    private LinkedList<Entry<K, V>>[] table ;
    private  int numKeys ;
    private static final int CAPACITY = 101 ;
    private static final double LOAD_THRESHOLD = 1.5 ;

    ///////////// ENTRY CLASS ///////////////////////////////////////

    /**
     * Contains key-value pairs for HashTable
     * @param <K> the key
     * @param <V> the value
     */
    private static class Entry<K, V> implements Map.Entry<K, V>{
        private K key ;
        private V value ;

        /**
         * Creates a new key-value pair
         * @param key the key
         * @param value the value
         */
        public Entry(K key, V value) {
            this.key = key ;
            this.value = value ;
        }

        /**
         * Returns the key
         * @return the key
         */
        public K getKey() {
            return  key;
        }

        /**
         * Returns the value
         * @return the value
         */
        public V getValue() {
            return value ;
        }

        /**
         * Sets the value
         * @param val the new value
         * @return the old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val ;
            return oldVal ;
        }
        @Override
        public String toString() {
            return  key + "=" + value  ;
        }



    }

    ////////////// end Entry Class /////////////////////////////////

    ////////////// EntrySet Class //////////////////////////////////

    /**
     * Inner class to implement set view
     */
    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {


        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new SetIterator();
        }

        @Override
        public int size() {
            return numKeys ;
        }
    }

    ////////////// end EntrySet Class //////////////////////////////

    //////////////   SetIterator Class ////////////////////////////

    /**
     * Class that iterates over the table. Index is table location
     * and lastItemReturned is entry
     */
    private class SetIterator implements Iterator<Map.Entry<K, V>> {

        private int index = 0 ;
        private Entry<K,V> lastItemReturned = null;
        private Iterator<Entry<K, V>> iter = null;

        @Override
        public boolean hasNext() {
        	if (iter != null) {
        		if(iter.hasNext())
        		{
        			return true;
        		}
        		else {
        			iter = null;
        			index++;
        		}
        	}
        	while (index < table.length && table[index] == null) {
        		index++;
        	}
        	if (index == table.length)
        	{
        		return false;
        	}
        	iter = table[index].iterator();
        	return iter.hasNext();
        }

        @Override
        public Map.Entry<K, V> next() {
        	return (Map.Entry<K, V>) iter.next();
        }

        @Override
        public void remove() {
            iter.remove();
            if (table[index].size() == 0) {
                table[index] = null;
            }
            numKeys--;
        }
    }

    ////////////// end SetIterator Class ////////////////////////////

    /**
     * Default constructor, sets the table to initial capacity size
     */
    public HashTableChain() {
        table = new LinkedList[CAPACITY] ;
    }

    // returns number of keys
    @Override
    public int size() {
    	return numKeys;
    }

    // returns boolean if table has no keys
    @Override
    public boolean isEmpty() {
    	return (numKeys == 0);
    }

    // returns boolean if table has the searched for key
    @Override
    public boolean containsKey(Object key) {
    	// Fill Here
    	if(get(key) == null)
    		return false;
    	return true;
    }

    // returns boolean if table has the searched for value
    @Override
	public boolean containsValue(Object value) {
		for(int i = 0; i < table.length; i++) {
			if(table[i] == null) {
				continue;
			}
			for(Entry<K, V> nextItem : table[i]) {
				if(nextItem.getValue().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

    // returns Value if table has the searched for key
    @Override
    public V get(Object key) {
    	int index = key.hashCode() % table.length;
    	if(index < 0)
    		index += table.length;
    	
    	if(table[index] == null)
    		return null;
    	//case: key is not in table
    	//return null
    	//iterate list at index to find key
    	for(Entry<K, V> nextItem: table[index])
    	{
    		if(nextItem.key.equals(key))
    			return nextItem.value;
    	}
    	return null;
    }
    // adds the key and value pair to the table using hashing
    @Override
    public V put(K key, V value) {
    	int index = key.hashCode() % table.length;
    	if(index < 0) {
    		index += table.length;
    	}
    	//create new empty linked list at index]
    	if (table[index] == null)
    		table[index] = new LinkedList<Entry<K, V>>();
    	//search list at index to find the key
    	for (Entry<K, V> nextItem : table[index])
    	{
    		//if key is in the table
    		if(nextItem.key.equals(key))
    		{
    			//replace value for this key
    			V oldVal = nextItem.value;
    			nextItem.setValue(value);
    			return oldVal;	
    		}
    	}
    	//key is not in the table
    	//insert new item
    	table[index].addFirst(new Entry <K, V>(key, value));
    	numKeys++;
    	if (numKeys > (LOAD_THRESHOLD * table.length))
    		rehash();
    	return null;
    }


    /**
     * Resizes the table to be 2X +1 bigger than previous
     */
	private void rehash() {
    	LinkedList<Entry<K, V>>[] oldTable = table;
    	//new table double the size +1
    	table = new LinkedList[oldTable.length * 2 + 1];
    	numKeys = 0;
    	//Reinserts all table entries into a new table
    	for(int i = 0; i < oldTable.length; i ++){
            if(oldTable[i] != null){
                for( Entry<K, V> nextItem : oldTable[i]){
                    put(nextItem.key, nextItem.value);
                }
            }
    	}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        for (int i = 0 ; i < table.length ; i++ ) {
            if (table[i] != null) {
                for (Entry<K, V> nextItem : table[i]) {
                    sb.append(nextItem.toString() + " ") ;
                }
                sb.append(" ");
            }
        }
        return sb.toString() ;

    }

    // remove an entry at the key location
    // return removed value
    @Override
	public V remove(Object key) {
		int index = key.hashCode() % table.length;
		
		if(index < 0) {
			index+= table.length;
		}
		if(table[index] == null) {
			return null;
		}
		Entry<K, V> item;
		for(Entry<K, V> nextItem : table[index]) {
			if(nextItem.key.equals(key)) {
				item = nextItem;
				table[index].remove(item);
				if(table[index].isEmpty()) {
					table[index] = null;
				}
				return item.getValue();
				
			}
		}
		return null;
	}

    // throws UnsupportedOperationException
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException() ;
    }

    // empties the table
    @Override
    public void clear() {
    	table = new LinkedList[CAPACITY];
    	numKeys = 0;
    }

    // returns a view of the keys in set view
    @Override
    public Set<K> keySet() {
    	//new set
    	Set<K> keySet = new HashSet<K>(size());
    	for(int i = 0; i < table.length; i++)
    	{
    		if (table[i] == null) {continue;}
    		for(Entry<K,V> entry : table[i])
    		{
    			if(entry !=null)
    				keySet.add(entry.getKey());
    		}
    	}
    	return keySet;
    }

    // throws UnsupportedOperationException
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException() ;
    }


    // returns a set view of the hash table
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
    	return new EntrySet();


    }

    @Override
    public boolean equals(Object o) {
		if(o == null) return false;
		Map<K, V> temp = (Map) o;
		
		for(int i = 0; i < table.length; i++) {
			if(table[i] == null) {
				continue;
			}
			for(Entry<K, V> nextItem : table[i]) {
				if(!temp.containsValue(nextItem.getValue())) {
					return false;
				}
			}
		}
		return true;

		
	}

    @Override
    public int hashCode() {
    	Iterator<Map.Entry<K, V>> iter = entrySet().iterator();
		int sum = 0;
		while(iter.hasNext()) {
			Map.Entry<K, V> tempEntry = iter.next();
			sum += tempEntry.getKey().hashCode() + tempEntry.getValue().hashCode();
		}
		return sum;
    }
}
