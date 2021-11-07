package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> temp = new QueueNode<>(e);
		if (size == 0) last = null;
		if (last == null) {
		last = temp;
		last.next = last;
		} else {
			temp.next = last.next;
			last.next = temp;
			last = temp;
		}
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size == 0) return null;
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (last == null) return null;
		size--;
		QueueNode<E> temp = last.next;
		last.next = temp.next;
		return temp.element;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FifoQueue<?> fifoQueue = (FifoQueue<?>) o;
		return Objects.equals(last, fifoQueue.last);
	}

	public void append(FifoQueue<E> q) {
		if (this.equals(q)) throw new IllegalArgumentException();

		this.addAll(q);
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		private QueueIterator() {
			if (size == 0) pos = null;
			else pos = last.next;
		}

		@Override
		public boolean hasNext() {
			int i = size;

			return pos != null;
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();

			QueueNode<E> temp = pos;

			if (pos == last) pos = null;
			else {
				pos = pos.next;
			}
			return temp.element;
		}
	}
}
