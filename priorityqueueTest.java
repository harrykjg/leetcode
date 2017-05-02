import java.util.PriorityQueue;

/**
 * Created by 502575560 on 4/30/17.
 */
//试验priorityqueue有重复元素的时候,remove确实只删除一个
    public class priorityqueueTest {
        public static void main(String args[]) {
            // create priority queue
            PriorityQueue< Integer > prq = new PriorityQueue < Integer > ();

            // insert values in the queue
            for ( int i = 3; i  <  10; i++ ){
                prq.add (new Integer (i)) ;
            }
            prq.add(4);

            System.out.println ( "Initial priority queue values are: "+ prq);

            // remove 7 from the queue
            boolean isremoved = prq.remove(4);

            System.out.println ( "Return value after remove: "+ isremoved);

            System.out.println ( "Priority queue values after remove: "+ prq);
        }
    }

