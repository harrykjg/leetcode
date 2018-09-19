import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 9/6/18.
 */
public class CourseScheduleII {
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
}
