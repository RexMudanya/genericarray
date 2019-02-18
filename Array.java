@SuppressWarnings("unchecked")//stop annoying java generics 

public class Array <T> implements Iterable <T> {

	private T [] arr;
	private int len = 0; // length user thinks/home/rex/Desktop/hashcode2019/arrays/Array.java the array is
	private int capacity = 0; // Actual array size(array might have more free slots)

	public Array() { this(16);}// initialize array

	public Array(int capacity){ //gives capacity
		if (capacity<0)throw new IllegalArgumentException("Illegal capacity: "+capacity);
		this.capacity=capacity;
		arr =(T[]) new Object[capacity];//casting to a type T
	}

	public int size(){return len;}//size of the array
	public boolean isEmpty() {return size() == 0;}//check if empty

	public T get(int index){return arr[index];} //no bounds check!!
	public void set(int index, T elem) {arr[index] = elem;}

	public void clear(){ //remove all array data
		for(int i=0; i<capacity; i++)
			arr[i] = null;
		len = 0; //reset the length
	}

	public void add(T elem){

		//resize array
		if (len+1>= capacity) {
			if(capacity == 0) capacity=1;
			else capacity *=2;// double the size
			T[] new_arr = (T[]) new Object[capacity];//new array new capacity
			for (int i=0; i<len;i++)//copy old values into new array
				new_arr[i] = arr[i]; //set old array to be new array
			arr= new_arr; // array has extra nulls padded
		}

		arr[len++] = elem; //copy new value into array
	}

	// Removes an element at the specified index in this array. 
  public T removeAt(int rm_index) {
    if (rm_index >= len && rm_index < 0) throw new IndexOutOfBoundsException();
    T data = arr[rm_index];
    T[] new_arr = (T[]) new Object[len-1];
    for (int i = 0, j = 0; i < len; i++, j++)
      if (i == rm_index) j--; // Skip over rm_index by fixing j temporarily
      else new_arr[j] = arr[i];
    arr = new_arr;
    capacity = --len;
    return data;
  }

  public boolean remove(Object obj) {//scan object in array and remove
    int index = indexOf(obj);
    if (index == -1) return false;
    removeAt(index);
    return true;
  }

  public int indexOf(Object obj) {
    for (int i = 0; i < len; i++) {
      if (obj == null) {
        if (arr[i] == null)
          return i;
      } else {
        if (obj.equals(arr[i]))
          return i;
      }
    }
    return -1;
  }

  public boolean contains(Object obj) { //checks if index is not equal to -1
    return indexOf(obj) != -1;
  }

  // Iterator is still fast but not as fast as iterative for loop
  @Override public java.util.Iterator <T> iterator () {//return iterator for the array
    return new java.util.Iterator <T> () {
      int index = 0;//check for concurrent modification
      @Override public boolean hasNext() { return index < len; }
      @Override public T next() { return arr[index++]; } //return next element in the array next to the iterator
      @Override public void remove() { throw new UnsupportedOperationException(); }
    };
  }

  @Override public String toString() { //string rep of the array
    if (len == 0) return "[]";
    else {
      StringBuilder sb = new StringBuilder( len ).append("[");
      for (int i = 0; i < len-1; i++ )
        sb.append(arr[i] + ", " );
      return sb.append(arr[len-1] + "]").toString();
    }
  }

}

}