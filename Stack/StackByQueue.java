import java.util.LinkedList;
import java.util.Queue; 
class MyStack {
    private Queue<Integer> stack;
    private int front;

    public MyStack() {
        this.stack = new LinkedList<Integer>();
    }

    public void push(int x) {
        this.stack.add(x);
        this.front = x;
    }

    public int pop() {
        Queue<Integer> temp = new LinkedList<Integer>();
        while(this.stack.size() > 1){
            this.front = this.stack.poll();
            temp.add(this.front);
        }
        int ret = this.stack.poll();
        this.stack = temp;
        return ret;
    }

    public int top() {
        return this.front;
    }

    public boolean empty() {
        return this.stack.isEmpty();
    }
}
