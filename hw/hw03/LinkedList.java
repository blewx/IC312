//Brendan Lewis HW03
// ANY SOURCES OR COLLABORATION YOU USED HERE

import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
  // TODO your private inner classes and fields...
  private Node head = null;
  private Node tail = null;
  private int currSize = 0;

  public LinkedList(){
  }

  public class Node {
    private T data;
    private Node next = null;
    private Node(T data){
      this.data = data;
    }

  }
  private Node NodeSearch(Node N, int index){
    if (N == null) {
      return null;
    }
    if(index == 0){
      return N;
    }
    return NodeSearch(N.next, index - 1);
  }

  private Node SetNode(Node Current, int index, T ToAdd){
    if(index == 0){
      Current.data = ToAdd;
      return Current;
    }

    if(Current == null){
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    Current.next = SetNode(Current.next, index - 1, ToAdd);
    return Current;
  }

  private Node AddNode(Node Current, int index, T ToAdd){
    if(index == 0){
      Node newNode = new Node(ToAdd);
      newNode.next = Current;
      return newNode;
    }

    if(Current == null){
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    Current.next = AddNode(Current.next, index - 1, ToAdd);
    return Current;
  }
  private Node RemoveNode(Node Current, int index){
  if(index == 0){
      return Current.next;
    }
    if(index == 1){
      Current.next = Current.next.next;
      return Current;
    }

    if(Current == null){
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    Current.next = RemoveNode(Current.next, index - 1);
    return Current;
  }

  public void removeAll(T toCompare){
    head = removeAll(head, toCompare);
    updateTail();
  }

  private Node removeAll(Node Current, T toCompare){

    if(Current == null){
      return null;
    }
    if(Current.data.equals(toCompare)){
      currSize--;
      return removeAll(Current.next, toCompare);

    }
    else{
      Current.next = removeAll(Current.next, toCompare);
      return Current;
    }
  }

  public void updateTail(){
    if(head == null){
      tail = null;
    }
    else {
      tail = NodeSearch(head, currSize - 1);
    }
  }
  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    if (index < 0){
      throw new IndexOutOfBoundsException(" -- negative index");
    }
        if (index > size()){
      throw new IndexOutOfBoundsException(" -- adding more than +1 the current size");
    } 

    Node temp = head;
    temp = NodeSearch(temp,index);
    if(temp == null)
      throw new UnsupportedOperationException(); // TODO erase this and get it working

    return temp.data;
  }

  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    if( index >= size()){
    
      throw new IndexOutOfBoundsException(); // TODO erase this and get it working
    }
    Node temp = head;
    temp = NodeSearch(temp,index);
    if(temp == null)
      throw new IndexOutOfBoundsException(); // TODO erase this and get it working
    head = SetNode(head,index,data);
  }

  @Override
  public void add(int index, T data) throws IndexOutOfBoundsException {
    if(1 == 0){
      throw new UnsupportedOperationException(); // TODO erase this and get it working
    }
    head = AddNode(head,index,data);

    updateTail();
    currSize++;

    return;
  }

  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    if(1 == 0){
      throw new UnsupportedOperationException(); // TODO erase this and get it working
    }
    head = RemoveNode(head,index);
    updateTail();
    currSize--;
    return;
  }

  @Override
  public int size() {
    return currSize;
    //throw new UnsupportedOperationException(); // TODO erase this and get it working
  }
  
  /** Gets the 2nd-to-last element.
   *
   * @return The data in the second-to-last node in the list (if any)
   * @throws NoSuchElementException if the list size is less than 2
   */
  public T penultimate() throws NoSuchElementException {
    
    if(currSize < 2){
      throw new NoSuchElementException(); 
    }
    return get(currSize - 2);

    //aif (1 == 0 )throw new UnsupportedOperationException(); // TODO erase this and get it working
  }

  public void printList(Node n){
    if(n == null){
      return;
    }
    System.out.print(n.data + " ");
    printList(n.next);
  }
  public void printList(){
    printList(head);
    System.out.println();
  }

  public static void main(String[] args){
    //had to change firs tto linked list in order to use print list
    LinkedList<Integer> lst = new LinkedList<Integer>(); // [ ] -- empty list
    try{
      lst.add(0, 1);   // [ 1 ]
      lst.add(0, 2);   // [ 2 1 ]
      lst.add(1, 3);   // [ 2 3 1 ]
      lst.printList();
      //System.out.println(jead);
      //lst.add(4, 4);   // IndexOutOfBoundsException -- adding more than +1 the current size
      //lst.add(5, 5);   // IndexOutOfBoundsException -- outside the bounds of the fixedList at size 5
      //lst.add(-1, -1); // IndexOutOfBoundsException -- negative index
      lst.add(3, 4);   // [ 2 3 1 4 ]
      lst.add(0, 5);   // [ 5 2 3 1 4 ]
      lst.printList();
      //System.out.println(lst.toString());
      lst.remove(2);
      lst.printList();
      lst.add(0, 1);   // IllegalStateException -- list is full

      lst.printList();
      lst.removeAll(1);
      lst.printList();
      System.out.println(lst.penultimate());
      //*/                                                
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

}
