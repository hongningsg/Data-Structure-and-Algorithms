//Implement Queue using LinkedList
import java.util.LinkedList;
public class Queue
{
    private LinkedList list = new LinkedList();

    public void clear()
    {
  	   list.clear();
    }

    public boolean QueueEmpty()
    {
  	   return list.isEmpty();
    }

    public void enQueue(Object o)
    {
  	   list.addLast(o);
    }

    public Object deQueue()
    {
  	   if(!list.isEmpty())
  	    {
  		      return list.removeFirst();
  	     }
  	   return null;
    }

    public int QueueLength()
    {
  	     return list.size();
    }

    public Object QueuePeek()
    {
  	     return list.getFirst();
    }
}
