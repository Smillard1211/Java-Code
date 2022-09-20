
public interface PriorityQueueInterface<T extends Comparable<? super T>> {
	boolean isEmpty();
	boolean isFull();
	int size();
	void clear();
	void add(T entry);
	T peek();
	T remove();
	
}
