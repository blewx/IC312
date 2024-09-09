/** A List implementation with fixed, bounded capacity.
 *
 * When an instance of this class is constructed, the chosen capacity
 * represents the maximum size the list can ever grow to.
 */
public class BoundedList<T> implements List<T> {
  private T[] elements;
  private int currentSize = 0;
  // TODO you might want another private field...

  /** Create a new BoundedList with the given maximum capacity.
   */
  public BoundedList(int capacity) {
    @SuppressWarnings("unchecked")
    T[] elements = (T[]) new Object[capacity];
    this.elements = elements;
    
  }

  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    /*if (index >= currentLength){
      throw new UnsupportedOperationException("outside the bounds of the fixedList at size" + capacity);
    }*/
    if (index < 0){
      throw new IndexOutOfBoundsException(" -- negative index");
    }
    if (index >= elements.length){
      throw new IndexOutOfBoundsException(" -- outside the bounds of the fixedList at size " + elements.length);
    }
    if (index > size()){
      throw new IndexOutOfBoundsException(" -- adding more than +1 the current size");
    }
    return elements[index];
  }

  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    elements[index] = data;
    return;
    //throw new UnsupportedOperationException("DELETE THIS!");
  }

  @Override
  public void add(int index, T data) throws IndexOutOfBoundsException, IllegalStateException {
    if(size() == elements.length){
      throw new IllegalStateException(" -- list is full");
    }
    get(index);
    for(int i = index; i <= size(); i++){
      T temp = get(i);
      set(i, data);
      if(temp == null){
        break;
      }
      data = temp;
    }
    currentSize++;
    

    
    //throw new UnsupportedOperationException("DELETE THIS!");
  }

  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    
    get(index);
    if(size() == elements.length - 1){
      set(index, null);
      return;
    }
    for(int i = index; i < size() - 1; i++){
      T temp = get(i+1);
      set(i, temp);
      if(temp == null){
        return;
      }
    }
    currentSize--;
  }

  @Override
  public int size() {
    return currentSize;
    //throw new UnsupportedOperationException("DELETE THIS!");
  }

  @Override
  // this produces a string like "[ 1 2 3 4 ]"
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size(); ++i) {
      sb.append(get(i).toString());
      sb.append(' ');
    }
    sb.append(']');
    return sb.toString();
  }



  public static void main(String[] args){
    List<Integer> lst = new BoundedList<Integer>(5); // [ ] -- empty list
    try{
    lst.add(0, 1);   // [ 1 ]
    lst.add(0, 2);   // [ 2 1 ]
    lst.add(1, 3);   // [ 2 3 1 ]
    System.out.println(lst.toString());
    //lst.add(4, 4);   // IndexOutOfBoundsException -- adding more than +1 the current size
    lst.add(5, 5);   // IndexOutOfBoundsException -- outside the bounds of the fixedList at size 5
    //lst.add(-1, -1); // IndexOutOfBoundsException -- negative index
    lst.add(3, 4);   // [ 2 3 1 4 ]
    lst.add(0, 5);   // [ 5 2 3 1 4 ]
    System.out.println(lst.toString());
    lst.remove(2);
    lst.add(0, 6);   // IllegalStateException -- list is full

    System.out.println(lst.toString());
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
}
