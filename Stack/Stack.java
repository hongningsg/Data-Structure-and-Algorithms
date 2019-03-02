import java.util.ArrayList;
import java.util.EmptyStackException;
public class Stack<E>{
        private ArrayList<E> stack;
        public Stack(){
                this.stack = new ArrayList<E>();
        }

        public void push(E element){
              this.stack.add(element);
        }

        public E pop(){
              if(!this.stack.isEmpty()){
                      retrun this.stack.remove(this.stack.size() - 1);
              }else{
                      throw new EmptyStackException();
              }
        }

        public E peek(){
              if(!this.stack.isEmpty()){
                      return this.stack.get(this.stack.size() - 1);
              }else{
                      throw new EmptyStackException();
              }
        }

        public int size(){
              return this.stack.size();
        }

        public boolean isEmpty(){
              return this.stack.isEmpty();
        }
}
