import java.util.*;

/**
 * Created by yufengzhu on 7/4/18.
 */
//拓扑 topology
public class SequenceReconstruction {
    public static void main(String[] a){
        SequenceReconstruction sr=new SequenceReconstruction();
        int[] org={1};
        List<List<Integer>> al=new ArrayList<>();
        List<Integer> a1=new ArrayList<Integer>();
        a1.add(1);
//        a1.add(2);
        List<Integer> a2=new ArrayList<Integer>();
        a2.add(2);a2.add(3);
        List<Integer> a3=new ArrayList<Integer>();
        a3.add(3);a3.add(2);
        al.add(a1);al.add(a2);
        al.add(a3);

       System.out.print( sr.sequenceReconstruction(org,al));
    }
    //题目意思不好理解，就是seqs这里面的数字，比如[[1,2], [1,3]]，就代表org必须满足2在1的右边，3在1 的右边，这样得出有两种org可能，即123或132，不唯一所以false
    //https://leetcode.com/problems/sequence-reconstruction/discuss/92580/Java-Solution-using-BFS-Topological-Sort
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        HashMap<Integer,Integer> degree=new HashMap<>();
        HashMap<Integer,Set<Integer>> graph=new HashMap<>();//这个map实际上就是来寸key的邻居的，代表了图
        for(int i=0;i<seqs.size();i++){
            if(seqs.get(i).size()==1){
                if(!graph.containsKey(seqs.get(i).get(0))){
                    graph.put(seqs.get(i).get(0),new HashSet<>());
                }
                if(!degree.containsKey(seqs.get(i).get(0))){
                    degree.put(seqs.get(i).get(0),0);
                }
            }
            for(int j=0;j<seqs.get(i).size()-1;j++){//这里构造图和入读还是相当恶心的
                int cur=seqs.get(i).get(j);
                int next=seqs.get(i).get(j+1);
                if(!graph.containsKey(cur)){//，如果cur不在grap里，则新建他，
                    graph.put(cur,new HashSet<>());
                    //graph.get(cur).add(next)；//这里很恶心，不能马上就加上next作为邻居，虽然是肯定对的，但是因为还要更新入度，那时候要看这个next之前是不是已经有cur这个人指向他了，所以要把这个逻辑移到下面
                }
                if(!degree.containsKey(cur)){//如果cur不在degree里，则新建他
                    degree.put(cur,0);
                }
                //之前漏了把next也加进graph里，因为这个forloop是只去到倒数第二位
                if(!graph.containsKey(next)){
                    graph.put(next,new HashSet<>());
                }

                if(!degree.containsKey(next)){//如果nexrt不在degree里，则新建他，否则还要看next是不是已经在cur的邻居里，有的话就不更新入度了，很恶心！
                    degree.put(next,0);//这里之前写成1就搞死认了，想着如果degree都没有next存在，那么这个cur肯定是第一个指向他的人，所以写1肯定对啊，但是那样就没法写下面的逻辑了
                }

                if(graph.get(cur).add(next)){
                    degree.put(next,degree.get(next)+1);
                }
            }
        }
        ArrayList<Integer> sort=new ArrayList<>();
        Queue<Integer> q=new LinkedList<>();
        Iterator<Integer> it=degree.keySet().iterator();
        while (it.hasNext()){
            int cur=it.next();
            if(degree.get(cur)==0){
                q.offer(cur);
            }
        }

        while (!q.isEmpty()){
            if(q.size()>1){
                return false;
            }
            int cur=q.poll();
            sort.add(cur);
//            Set<Integer> nei=graph.get(cur);
//            Iterator<Integer> nit=nei.iterator();
//            while (nit.hasNext()){
//                int temp=nit.next();
//                degree.put(temp,degree.get(temp)-1);
//                if(degree.get(temp)==0){
//                    q.offer(temp);
//                }
//            }
            for(int next: graph.get(cur)) {//上面的也对，但是这样的写法就生了iterator
                degree.put(next, degree.get(next) - 1);
                if(degree.get(next) == 0) {
                    q.offer(next);
                }
            }
        }
        if(sort.size()!=org.length||graph.keySet().size()!=sort.size()){//后来加了这个graph.keySet().size()!=sort.size()条件，因为可能给的input就没有通路，导致sort到一般就完事了，
            return false;
        }
        for(int i=0;i<sort.size();i++){
            if(org[i]!=sort.get(i)){
                return false;
            }
        }
        return true;
    }
}
