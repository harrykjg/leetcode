import java.util.*;

/**
 * Created by yufengzhu on 7/8/18.
 */
public class CourseSchedule {
    public static void main(String[] a){
        CourseSchedule cs=new CourseSchedule();
        int[][] pre={{1,0},{2,0}};
        cs.canFinish2(3,pre);
    }
    //16年初写的
    //https://www.cnblogs.com/yrbbest/p/4493547.html
    //https://blog.csdn.net/wongleetion/article/details/79433101
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //这题可以转换成拓扑排序求有没有环存在，有2个方法，kahn算法和dfs算法，kahn算法是每次先找入度为0的点（即没有其他边指向他）,
        //，只要入度是0就行了，顺序没关系（而dfs是每次先找出度为0的点），找到这个点之后把他所有的outgoing的边移除，
        //即他所指向的所有节点的入度都减一，然后再找下一个入度是0的节点，如果找不到就说明有环，return false

        //先初始化邻接表,比如[1,0]则把他想象成图的0->1，即到达1要先去0，所以题目中好像说了是pair，所以应该没有[2,1,0]这样包含
        //2个以上的的数组
        List<Set> list=new ArrayList<Set>();
        for(int i=0;i<numCourses;i++){
            list.add(new HashSet<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++){
            list.get(prerequisites[i][1]).add(prerequisites[i][0]);//比如[2,1],[1,0],[2,0]，i=0时，则
            //list.get(prerequisites[i][1])，得到的是节点1，即【2,1】中的1,而1的后驱就是2，然后i=1，得到节点0,0的后驱是1，
        }//然后i=2；得到节点0,0的后驱加上2

        //然后记录每个节点的入度,这个有点绕，
        int[] in=new int[numCourses];
        for(int i=0;i<in.length;i++){
            Set set=list.get(i);// 他先得到一个集合，这个集合就是某个节点的后继节点们，那就说明这些后继节点们都有这个前驱
            Iterator<Integer> it=set.iterator();//所以把这些后继节点们的前驱数量都+1
            while(it.hasNext()){
                in[it.next()]++;
            }
        }

        //然后开始每次去除入度为0的节点,并且把他的outgoing边也去掉，即他所指向的所有节点的入度都减一
        for(int i=0;i<numCourses;i++){//注意这个外循环，因为最后要把每个节点都去掉，所以要这个循环
            int j=0;
            for(;j<in.length;j++){
                if(in[j]==0){
                    break;
                }
            }

            if(j==in.length){
                return false;
            }
            in[j]=-1;//防止下次又找到他
            Set set=list.get(j);
            Iterator<Integer> it=set.iterator();
            while(it.hasNext()){
                in[it.next()]--;
            }
        }
        return true;

    }
    //7/8/2018,还是改了好久才对，因为这题不是单纯的拓扑排序，写的还是不好
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if(prerequisites.length==0){
            return true;
        }
        HashMap<Integer,Set<Integer>> graph=new HashMap<>();
        HashMap<Integer,Integer> degree=new HashMap<>();
        for(int i=0;i<prerequisites.length;i++){
            int a=prerequisites[i][0];
            int b=prerequisites[i][1];
            if(!graph.containsKey(b)){
                Set<Integer> set=new HashSet<>();
                set.add(a);
                graph.put(b,set);
            }else{
                graph.get(b).add(a);
            }
            if(!degree.containsKey(a)){
                degree.put(a,1);
            }else{
                degree.put(a,degree.get(a)+1);
            }
        }
        Queue<Integer> q=new LinkedList<>();
        //寻找入度为0的点，这里还是不太好想，在prerequisites里所有第一个节点，肯定是有入度的，一共有numCourses个课，所以说degree里没有的i（i属于0到numCourse-1），就是入度为0
        for(int i=0;i<numCourses;i++){
            if(!degree.containsKey(i)){
                q.offer(i);
            }
        }

        int count=0;
        while (!q.isEmpty()){//就是看最后能不能有一个长度为numCourse的结果集，有的话就说明这所有的点可以一笔走过，如果中间没有找到入度为0的点就失败了，那么count自然不会等于numCourse
            int cur=q.poll();
            count++;
            if(graph.containsKey(cur)){
                for(int a:graph.get(cur)){
                    degree.put(a,degree.get(a)-1);
                    if(degree.get(a)==0){
                        q.offer(a);
                    }
                }
            }

        }
        return count==numCourses;

    }
//04/10/2020,改了一次过了，就是时间复杂度太大了
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        HashMap<Integer,HashSet<Integer>> degree=new HashMap<>();
        for(int i=0;i<numCourses;i++){
            degree.put(i,new HashSet<>());
        }
        for(int i=0;i<prerequisites.length;i++){
           for(int j=1;j<prerequisites[0].length;j++){
               degree.get(prerequisites[i][0]).add(prerequisites[i][j]);//和上面的那个写法貌似是相反了，
           }
        }

        Queue<Integer> q=new LinkedList<>();

        boolean[] memo=new boolean[numCourses];
        for(int i=0;i<numCourses;i++){
            if(degree.get(i).size()==0){
                q.add(i);
                memo[i]=true;
            }
        }
        int count=0;
        while (!q.isEmpty()){
            int cur=q.poll();
            count++;
            for(int i=0;i<numCourses;i++){
                degree.get(i).remove(cur);//因为我不管这个cur是否是i的prerequiste都把他拿出来remove，因此导致慢？
                if(degree.get(i).size()==0&&!memo[i]){
                    q.add(i);
                    memo[i]=true;
                }
            }
        }
        return count==numCourses;

    }
}
