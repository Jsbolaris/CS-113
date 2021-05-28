
DoubleLinkedList
##  Double Linked List Implementation

*[Insert Build Status Image]*

> **"A list is only as strong as its weakest link."**
> — Donald Knuth

>**"If you find that you're spending almost all your time on theory, start turning some attention to practical things; it will improve your theories. If you find that you're spending almost all your time on practice, start turning some attention to theoretical things; it will improve your practice."**
> — Donald Knuth 

**Provide your implementation for a `DoubleLinkedList<E>`**.  Apart from a default constructor and `toString()` method, make sure to implement the methods from the `List` interface and create an inner class implementing `ListIterator` interface (in addition to the necessary static inner class `Node<E>`).  **You will be graded on having all the methods below implemented and passing all JUnit tests for the `List` interface in addition to the JUnit tests for its `ListIterator`.** Feel free to use code from the book (`KWLinkedList`) or slides.

`ListIterator` interface [methods](https://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html) to implement:

| return type | method + description |
|--|--|
| `void` | `add(E e)` |
| | Inserts the specified element into the list (optional operation). |
| `boolean` | `hasNext()` |
| | Returns true if this list iterator has more elements when traversing the list in the forward direction. |
| `boolean` | `hasPrevious()` |
| | Returns true if this list iterator has more elements when traversing the list in the reverse direction. |
| `E` | `next()` |
| | Returns the next element in the list and advances the cursor position. |
| `int` | `nextIndex()` |
| | Returns the index of the element that would be returned by a subsequent call to next(). |
| `E` | `previous()` |
| | Returns the previous element in the list and moves the cursor position backwards. |
| `int` | `previousIndex()` |
| | Returns the index of the element that would be returned by a subsequent call to previous(). |
| `void` | `remove()` |
| | Removes from the list the last element that was returned by next() or previous() (optional operation). |
| `void` | `set(E e)` |
| | Replaces the last element returned by next() or previous() with the specified element (optional operation). |

`List` Interface [methods](https://docs.oracle.com/javase/7/docs/api/java/util/List.html) to implement:

| return type | method + description |
|--|--|
| `Iterator<E>` | `iterator()` |
| | Returns an iterator over the elements in this list in proper sequence. |
| `ListIterator<E>` | `listIterator()` |
| | Returns a list iterator over the elements in this list (in proper sequence). |
| `ListIterator<E>` | `listIterator(int index)` |
| | Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list. |
| `boolean` | `add(E e)` |
| | Appends the specified element to the end of this list (optional operation). |
| `void` | `add(int index, E element)` |
| | Inserts the specified element at the specified position in this list (optional operation). |
| `void` | `clear()` |
| | Removes all of the elements from this list (optional operation). |
| `boolean` | `equals(Object o)` |
| | Compares the specified object with this list for equality. |
| `boolean` | `contains(Object o)` |
| | Returns true if this list contains the specified element. |
| `E` | `get(int index)` |
| | Returns the element at the specified position in this list. |
| `int` | `indexOf(Object o)` |
| | Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| `int` | `lastIndexOf(Object o)` |
| | Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| `boolean` | `isEmpty()` |
| | Returns true if this list contains no elements. |
| `E` | `remove(int index)` |
| | Removes the element at the specified position in this list (optional operation). |
| `boolean` | `remove(Object o)` |
| | Removes the first occurrence of the specified element from this list, if it is present (optional operation). |
| `E` | `set(int index, E element)` |
| | Replaces the element at the specified position in this list with the specified element (optional operation). |
| `int` | `size()` |
| | Returns the number of elements in this list. |



These are the rest of  the`List` interface methods (**do not build these**, we will implement them later):

| return type | method + description |
|--|--|
| `List<E>` | `subList(int fromIndex, int toIndex)` |
| | Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. |
| `Object[]` | `toArray()` |
| | Returns an array containing all of the elements in this list in proper sequence (from first to last element). |
| `boolean` | `addAll(Collection<? extends E> c)` |
| | Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation). |
| `boolean` | `addAll(int index, Collection<? extends E> c)` |
| | Inserts all of the elements in the specified collection into this list at the specified position (optional operation). |
| `boolean` | `containsAll(Collection<?> c)` |
| | Returns true if this list contains all of the elements of the specified collection. |
| `boolean` | `removeAll(Collection<?> c)` |
| | Removes from this list all of its elements that are contained in the specified collection (optional operation). |
| `int` | `hashCode()` |
| | Returns the hash code value for this list. |
| `default void` | `replaceAll(UnaryOperator<E> operator)` |
| | Replaces each element of this list with the result of applying the operator to that element. |
| `boolean` | `retainAll(Collection<?> c)` |
| | Retains only the elements in this list that are contained in the specified collection (optional operation). |
| `<T> T[]` | `toArray(T[] a)` |
| | Returns an array containing all of the elements in this list in proper sequence (from first to last element); the runtime type of the returned array is that of the specified array. |
| `default void` | `sort(Comparator<? super E> c)` |
| | Sorts this list according to the order induced by the specified Comparator. |
| `default Spliterator<E>` | `spliterator()` |
| | Creates a Spliterator over the elements in this list. |


----------


### Make sure to commit + push *before* the deadline to have your code be considered for grading.

>Pro-Tips:
>- Build the constructor and `toString` first, then build the `ListIterator` implementation to help you build the `List` interface methods.
>- **You don't need to document any methods that are overridden!** By using the `@Override` JavaDoc tag, when you generate the JavaDoc it will automatically connect the inherited documentation! Just don't forget to document constructors, helper methods, etc. ***#thanksJavaDoc***
>- Note that out of the ***"Do not implement"*** `List` interface methods, you have the knowledge to get the first 6 working! Since the parameters are `Collection` types (and every data structure we have built/will build implements `Collection`), you are familiar with most of the [standard methods](https://docs.oracle.com/javase/7/docs/api/java/util/Collection.html) it contains.  Try implementing them if you have some spare time!
