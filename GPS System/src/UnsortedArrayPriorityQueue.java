import java.util.Arrays;
import java.util.NoSuchElementException;



public class UnsortedArrayPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

	
	
	
	//=====================================================properties
	private T[] data;
	private int size;
	private static final int DEFAULT_CAPACITY = 11;
	
	
	//=====================================================constructors
	public UnsortedArrayPriorityQueue() {
		this(DEFAULT_CAPACITY);
		
	}
	public UnsortedArrayPriorityQueue(int intiSize) {
		if(intiSize <= 0) intiSize = DEFAULT_CAPACITY;
		data =(T[]) new Comparable[intiSize];
		size = 0;
	}
	public UnsortedArrayPriorityQueue(T[] entry) {
		data = Arrays.copyOf(entry, entry.length * 2);
		size = entry.length;
	}
	
	
	
	
	
	//=====================================================Helper methods
	@Override
	public String toString() {
		return Arrays.toString(data);
	}
	
	private int nextIndex() {
		int ret = 0;
		for(int i = 1; i < size; i++)
			if(data[i].compareTo(data[ret]) < 0) ret = i;
		
		return ret;
	}
	private void verifyCapacity() {
		if(isFull()) data = Arrays.copyOf(data, data.length * 2);
	}
	
	//=====================================================Implemented Methods
	@Override
	public boolean isEmpty() {
		
		return size == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return size == data.length;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void clear() {
		Arrays.fill(data, 0, size, null);
		
	}

	@Override
	public void add(T entry) {
		verifyCapacity();
		
		data[size++] = entry;
		
	}

	@Override
	public T peek() {
		return (isEmpty() ? null : data[nextIndex()]);
		
	}

	@Override
	public T remove() {
		if(isEmpty()) throw new NoSuchElementException();
		int i = nextIndex();
		T ret = data[i];
		data[i] = data[--size];
		data[size] = null;
		
		return ret;
	}

}



