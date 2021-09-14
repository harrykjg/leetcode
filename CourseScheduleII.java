import java.util.*;

/**
 * Created by yufengzhu on 9/6/18.
 */
public class CourseScheduleII {
    public static void main(String[] args){
        CourseScheduleII cs=new CourseScheduleII();
        List<List<Integer>> rs=cs.findOrder4(4,new int[][]{{1,0},{2,0},{3,1},{3,2}});
    }
    //9/6/2018,居然一次过
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer,HashSet<Integer>> map=new HashMap<>();
        HashMap<Integer,Integer> degree=new HashMap<>();
        for(int i=0;i<prerequisites.length;i++){
            int a=prerequisites[i][0];
            int b=prerequisites[i][1];
            if(!map.containsKey(a)){
                HashSet<Integer> set=new HashSet<>();
                set.add(b);
                map.put(a,set);
            }else{
                map.get(a).add(b);
            }
            if(!degree.containsKey(a)){
                degree.put(a,1);
            }else{
                degree.put(a,degree.get(a)+1);
            }
        }
        int[] rs=new int[numCourses];
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(!degree.containsKey(i)){
                q.offer(i);
            }
        }
        int index=0;
        while (!q.isEmpty()){
            int cur=q.poll();
            rs[index]=cur;
            index++;
            for(int a:map.keySet()){
                if(map.get(a).contains(cur)){
                    map.get(a).remove(cur);
                    degree.put(a,degree.get(a)-1);
                    if(degree.get(a)==0){
                        q.offer(a);
                    }
                }
            }
        }
        if(index<numCourses){
            return new int[0];
        }
        return rs;
    }
////05/29/2020,改了一次过了
    public int[] findOrder2(int numCourses, int[][] prerequisites) {

        int[] rs=new int[numCourses];
        Map<Integer,Integer> degree=new HashMap<>();
        HashMap<Integer,Set<Integer>> map=new HashMap<>();
        for(int i=0;i<prerequisites.length;i++){
            if(!map.containsKey(prerequisites[i][1])){//比如要学3的话要先学1，2，则1里面存了个3，2里面也存了3，说明3依赖1，3也依赖2，当把1去除的时候
                HashSet<Integer> set=new HashSet<>(); //就发现3依赖于1，则去3的degree那里减1。上面那个方法不是这样写，反正后面能写对也行
                set.add(prerequisites[i][0]);
                map.put(prerequisites[i][1],set);
            }else{
                map.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }
            if(!degree.containsKey(prerequisites[i][0])){//比如要学3的话要先学1，2，则是1，2指向3，即3的入度为2。
                degree.put(prerequisites[i][0],1);
            }else{
                degree.put(prerequisites[i][0],degree.get(prerequisites[i][0])+1);
            }
        }
        Queue<Integer> q=new LinkedList<>();
        int index=0;
        for(int i=0;i<numCourses;i++){
            if(!degree.containsKey(i)){
                q.add(i);
            }
        }
        while (!q.isEmpty()){
            int cur=q.poll();
            rs[index]=cur;
            index++;
            if(map.containsKey(cur)){
                Set<Integer> set=map.get(cur);
                for(int i:set){
                    degree.put(i,degree.get(i)-1);
                    if(degree.get(i)==0){
                        q.offer(i);
                    }
                }
            }
        }
        if(index<numCourses){//这个漏了就处理不了无解的
            return new int[0];
        }
        return rs;
    }

    //7/28/2021 基本一次过
    public int[] findOrder3(int numCourses, int[][] prerequisites) {
        int[] rs=new int[numCourses];
        HashMap<Integer,Set<Integer>> map=new HashMap<>();
        HashMap<Integer,Integer> degree=new HashMap<>();
        for (int[] pre:prerequisites){
            if (!map.containsKey(pre[1])){
                Set<Integer> set=new HashSet<>();
                set.add(pre[0]);
                map.put(pre[1],set);
            }else {
                map.get(pre[1]).add(pre[0]);
            }
            if (!degree.containsKey(pre[0])){
                degree.put(pre[0],1);
            }else {
                degree.put(pre[0],degree.get(pre[0])+1);
            }
        }
        Queue<Integer> q=new LinkedList<>();
        for (int i=0;i<numCourses;i++){
            if (!degree.containsKey(i)){
                q.offer(i);
            }
        }
        int count=0;
        while (!q.isEmpty()){
            int cur=q.poll();
            rs[count]=cur;
            count++;
            if (map.containsKey(cur)){
                for (int nei:map.get(cur)){
                    if (degree.containsKey(nei)){
                        degree.put(nei,degree.get(nei)-1);
                        if (degree.get(nei)==0){
                            q.offer(nei);
                        }
                    }
                }
            }

        }
        if (count<numCourses-1){
            return new int[0];
        }
        return rs;

    }
//写一个要print出所有组合的,挺恶心的。应该对但是应该有更好的写法
    public List<List<Integer>> findOrder4(int numCourses, int[][] prerequisites) {
        List<List<Integer>> rs=new ArrayList<>();
        HashMap<Integer,Set<Integer>> map=new HashMap<>();
        HashMap<Integer,Integer> degree=new HashMap<>();
        for (int[] pre:prerequisites){
            if (!map.containsKey(pre[1])){
                Set<Integer> set=new HashSet<>();
                set.add(pre[0]);
                map.put(pre[1],set);
            }else {
                map.get(pre[1]).add(pre[0]);
            }
            if (!degree.containsKey(pre[0])){
                degree.put(pre[0],1);
            }else {
                degree.put(pre[0],degree.get(pre[0])+1);
            }
        }
        LinkedHashSet<Integer> a=new LinkedHashSet<>();
        for (int i=0;i<numCourses;i++){
            if (!degree.containsKey(i)){
                a.add(i);
            }
        }
        boolean[] memo=new boolean[numCourses];
        List<Integer> al=new ArrayList<>();
        dfs(al,a,memo,rs,map,degree);
        int count=0;
        return rs;
    }
    void dfs(List<Integer> al,LinkedHashSet<Integer> a,boolean[] memo,List<List<Integer>> rs,HashMap<Integer,Set<Integer>> map,HashMap<Integer,Integer> degree){
        if (al.size()==memo.length){
            rs.add(new ArrayList<>(al));
            return;
        }
        for (int i:a){
            if (memo[i]){
                continue;
            }
            memo[i]=true;
            al.add(i);
            LinkedHashSet<Integer> next=new LinkedHashSet<>(a);//这个a相当于原来的q，当前层取了一个之后，下一层就要删除当前取的，所以我建了一个新的set去装
            next.remove(i);//下一层的候选节点，不然会然concurrentmodificationexception后还没完，此时会更新入度，把入度为0的点加入下一层的候选，
            if (map.containsKey(i)){
                for (int nei:map.get(i)){
                    if (degree.containsKey(nei)){
                        degree.put(nei,degree.get(nei)-1);
                        if (degree.get(nei)==0){
                            next.add(nei);
                        }
                    }
                }
            }
            dfs(al,next,memo,rs,map,degree);
            al.remove(al.size()-1);
            memo[i]=false;
            if (map.containsKey(i)){//最关键还是这里dfs完之后，还要复原之前被减少的入度！
                for (int nei:map.get(i)){
                    if (degree.containsKey(nei)){
                        degree.put(nei,degree.get(nei)+1);
                        if (degree.get(nei)==0){
                            next.add(nei);
                        }
                    }
                }
            }
        }
    }
}
