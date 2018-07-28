import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 7/24/18.
 */
public class TaskScheduler {
    public static void main(String[] a){
        TaskScheduler ts=new TaskScheduler();
        char[] taks={'A','A','A','B','B','B'};
       System.out.println( ts.leastInterval(taks,10));
    }
    //https://www.youtube.com/watch?v=YCD_iYxyXoo  看了视频之后自己想用priorityqueue写，基本和答案差不多，但是还是有错，关键是我以为要按frequency排序，frequency高的先出来，然后再放进queue
    //的时候他的frequency不变，变的是count，结果就跑了50多个case之后错了。其实应该按count排序，这个count用过之后要更新再放进queue里
    public int leastInterval(char[] tasks, int n) {
        if(tasks.length==0){
            return 0;
        }
        int[] count=new int[26];
        int lower=0;
        for(char c:tasks){
            count[c-'A']++;
            lower++;
        }
        PriorityQueue<node> pq=new PriorityQueue<>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return o2.count-o1.count;
            }
        });
        for(int i=0;i<count.length;i++){
            if(count[i]!=0){
                node no=new node(count[i],count[i]);
                pq.offer(no);
            }
        }
        int rs=0;
        while (!pq.isEmpty()){
            ArrayList<node> ls=new ArrayList<>();
            for(int i=0;i<=n;i++){

                if(pq.isEmpty()){
                    rs++;
                    continue;
                }
                node nn=pq.poll();
                nn.count--;
                if(nn.count>0){
                    ls.add(nn);
                }
                rs++;
                if(ls.size()==0&&pq.isEmpty()){//开始漏了pq.isEmpty这个条件就错了
                    return rs;
                }
            }
            for(node nn:ls){
                pq.offer(nn);
            }

        }
        return rs;

    }
    class node{
        int count;
        int freq;
        public node(int i,int f){
            count=i;
            freq=f;
        }
    }
}
