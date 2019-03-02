//Inspired by LeetCode question, a queue implemention using stack
import java.util.Stack;
//no pop or peek operations will be called on an empty queue
public class Queue_ImplementByStack{
  private Stack<Integer> queue = new Stack<Integer>();
  private int front;
  public MyQueue() {
  }

  /** Push element x to the back of queue. */
  public void push(int x) {
      if (this.queue.isEmpty()) front = x;
      this.queue.push(x);
  }

  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
      Stack<Integer> storage = new Stack<Integer>();
      while(!this.queue.isEmpty()){
          storage.push(this.queue.pop());
      }
      int result = storage.pop();
      //storage.pop();
      if (!storage.isEmpty()){
          this.front = storage.peek();
      }
      while(!storage.isEmpty()){
          this.queue.push(storage.pop());
      }
      return result;
  }

  /** Get the front element. */
  public int peek() {
      return this.front;
  }

  /** Returns whether the queue is empty. */
  public boolean empty() {
      return queue.isEmpty();
  }
}
