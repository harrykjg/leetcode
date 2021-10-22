import java.util.*;

/**
 * Created by yufengzhu on 7/24/18.
 */
public class TaskScheduler {
    public static void main(String[] a){
        TaskScheduler ts=new TaskScheduler();
        char[] taks={'A','A','A','B','B','B'};
       System.out.println( ts.leastInterval(taks,2));
    }
    //https://www.youtube.com/watch?v=YCD_iYxyXoo  看了视频之后自己想用priorityqueue写，基本和答案差不多
    //其实是拼凑法，因为知道最后的排列是多个大小为n+1的bucket排在一起的。那么我就对于每一个bucket，从count多的task开始往里塞。没次从pq拿出一个task之后他的count--。
    //根本不需要care这个task到底是谁，反正是个task就行了
    // 在塞n+1这个bucket时，有2种情况，1，比如只有2个task，而n=2，那么第三个位置必须塞一个空。2，有很多歌task，n=2，那么没出场的task也无所谓，下个bucket再说。
    public int leastInterval(char[] tasks, int n) {
        if(tasks.length==0){
            return 0;
        }
        int[] count=new int[26];
        for(char c:tasks){
            count[c-'A']++;
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<count.length;i++){
            if(count[i]!=0){
                pq.offer(count[i]);
            }
        }
        int rs=0;
        while (!pq.isEmpty()){
            ArrayList<Integer> ls=new ArrayList<>();
            for(int i=0;i<=n;i++){

                if(pq.isEmpty()){
                    rs++;
                    continue;
                }
                int nn=pq.poll();
                nn--;
                if(nn>0){
                    ls.add(nn);
                }
                rs++;
                if(ls.size()==0&&pq.isEmpty()){//开始漏了pq.isEmpty这个条件就错了
                    return rs;
                }
            }
            for(int nn:ls){
                pq.offer(nn);
            }

        }
        return rs;

    }
    //7/7/2021，模拟，很慢但是accept了。以前的方法好。
    // 这里就是用一个大小为n的dequeue作为窗口，另一个pq装着按数量排序的task，每次便利poll出pq的task，看dequeue里有没有，没有的话
    //就可以加他，然后poll出的task count-1再放回pq里。dequeue大小不会超出n，等于n的话就removeFirst。当pq里所有元素都不符合的话，dq放入一个'#'。
    public int leastInterval2(char[] tasks, int n) {
        int rs=0;
        if (n==0){
            return tasks.length;
        }
        Deque<Character> dq=new LinkedList<>();
        PriorityQueue<node2> pq=new PriorityQueue<>(new Comparator<node2>() {
            @Override
            public int compare(node2 o1, node2 o2) {
                return o2.count-o1.count;
            }
        });
        PriorityQueue<node2> pq2=new PriorityQueue<>(pq);
        char[] count=new char[26];
        for (int i=0;i<tasks.length;i++){
            count[tasks[i]-'A']++;
        }
        for (int i=0;i<count.length;i++){
            if (count[i]!=0){
                pq.offer(new node2(count[i],(char)(i+'A')));
            }
        }
        while (!pq.isEmpty()){
            boolean found=false;

            while (!pq.isEmpty()){
                node2 cur=pq.poll();
                if (dq.isEmpty()||!dq.contains(cur.value)){
                    dq.addLast(cur.value);
                    found=true;
                    cur.count--;
                    if (cur.count>0){
                        pq2.offer(cur);
                    }
                    break;
                }else{
                    pq2.offer(cur);
                }
            }
            while (!pq2.isEmpty()){
                pq.offer(pq2.poll());
            }
            if (!found){
                dq.addLast('#');
            }
            if (dq.size()>n){
                dq.removeFirst();
            }

            rs++;
        }
        return rs;

    }
//8/21/2021改了好几次，忘记了不需要node2的class，pq里直接装的是count就行了 。其实答案1的greedy方法还不错
    public int leastInterval3(char[] tasks, int n) {
        int rs=0;
        int[] a=new int[26];
        for(char c:tasks){
            a[c-'A']++;
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int c:a){
            if(c!=0){//忘了这个就错了
                pq.offer(c);
            }
        }
        if(n==0){
            for(int c:a){
                rs+=c;
            }
            return rs;
        }
        while(!pq.isEmpty()){
            int count=0;
            List<Integer> temp=new ArrayList<>();
            while(count<n+1){//开始写成n就错了，其实是n+1才对
                if(!pq.isEmpty()){
                    int cur=pq.poll();
                    rs++;
                    count++;
                    cur--;
                    if(cur>0){
                        temp.add(cur);
                    }
                }else if(temp.size()!=0){//这个开始也忘了，如果pq是空并且temp里不是空，说明当前这一轮余下的都是idle，等下轮才能继续task
                    rs++;
                    count++;
                }else{
                    break;//否则就是pq是空，temp也是空，说明走完了
                }
            }
            pq.addAll(temp);
        }
        return rs;
    }
    class node2{
        int count;
        char value;
        public node2(int i,char value){
            count=i;
            this.value=value;
        }
    }
    //10/9/2021，还是没写对，2个while循环，里面的循环条件不是说要把pq遍历完才进入下一个n+1 bucket，而是维护一个count，当count到达
    //n+1的时候就把temp里的node加回进pq进入下一个循环
    public int leastInterval4(char[] tasks, int n) {
        if (n==0){
            return tasks.length;
        }
        int[] a=new int[26];
        for (char c:tasks){
            a[c-'A']++;
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for (int i:a){
            if(i!=0){
                pq.offer(i);
            }
        }
        int rs=0;
        while (true){
            List<Integer> temp=new ArrayList<>();
            int i=0;
            while (i<n+1){
                if (!pq.isEmpty()){
                    int t=pq.poll();
                    if (t-1>0){
                        temp.add(t-1);
                    }
                }
                i++;
                rs++;
                if(temp.isEmpty()&&pq.isEmpty()){
                    break;
                }

            }
            pq.addAll(temp);
            if (pq.isEmpty()){
                break;
            }
        }
        return rs;

    }

}
