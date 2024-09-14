/**
 *Brendan Lewis Proj1
 *Text.java implemented with linked list
 */

import java.util.NoSuchElementException;
import java.lang.Math.*;

public class MyText implements Text{
  private char [] currText = new char [1];
  private int cursorPosition = 0;

  public MyText(){

  }

  /** Returns the character at the current cursor position. */
  public char get(){
    return currText[cursorPosition];
  }

  /** Inserts a new character before the current cursor position. */
  public void insert(char c){
    if(cursorPosition >= currText.length){
      resizeArray();
    }
    for(int i = currText.length - 1; i > cursorPosition; i--){
        currText[i] = currText[i-1];
    }
    currText[cursorPosition] = c;
    moveRight();
  }


  /** Deletes the character at the current cursor position.
   * The cursor should move to the right of what was just deleted.
   * @throws NoSuchElementException if the cursor is at the end.
   */
  public void delete(){
    for(int i = cursorPosition; i < currText.length - 1; i++){
      currText[i] = currText[i+1];
    }
    moveLeft();
  }

  //check to see if this is O(1) amortized or not
  private void resizeArray(){
    char [] temp = new char [currText.length * 2];
    for(int i = 0; i < currText.length; i++){
      temp[i] = currText[i];
    }
    currText = temp;
  }

  /** Returns whether the cursor is NOT at the end. */
  public boolean canMoveRight(){
    return cursorPosition  !=  currText.length - 1;
  }

  public void moveRight(){
    cursorPosition++;
  }

  public boolean canMoveLeft(){
    return cursorPosition  !=  0;
  }
  public void moveLeft(){
    cursorPosition--;
  }

  /** Displays the current sequence of characters one one line, with the cursor underneath.
   *
   * Two lines should always be printed to System.out.
   *
   * For example, if the current characters are a, b, c, d, and the cursor is at the end,
   * we should see:
   *     abcd
   *         ^
   *
   * With the same characters, but the cursor under the 'b', we would see:
   *     abcd
   *      ^
   */
  public void print(){
    for(int i = 0; i < currText.length; i++){
      if(currText[i] == '\0'){
        break;
      }
      System.out.print(currText[i]);
    }
    System.out.println();
    for(int i = 0; i < cursorPosition; i++){
      System.out.print(" ");
    }
    System.out.println("^");
  }





}
