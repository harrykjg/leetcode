package SomeInterviews.snowflake;

import java.util.*;

public class ThreeColorMapColoring {
    /*
    Given a list of nodes locations, and a list of lists representing pairwise nodes adjacencies, determine whether it is possible to assign one of three colors ("red", "blue", "green") to each node such that no two adjacent nodes share the same color. Return true if such a coloring exists, otherwise return false.

Constraints:

Each list in the adjacencies list represents a bidirectional edge.
Assume no duplicate edges or self-loops are present.
Example 1:

Input: locations = ["A", "B", "C", "D"], adjacencies = [["A", "B"], ["A", "C"], ["B", "C"], ["C", "D"]]
Output: true
Explanation:

A valid coloring exists—for example, assigning A = "red", B = "blue", C = "green", and D = "blue" ensures that every pair of adjacent nodes has different colors.

Example 2:

Input: locations = ["A", "B", "C", "D"], adjacencies = [["A", "B"], ["A", "C"], ["A", "D"], ["B", "C"], ["B", "D"], ["C", "D"]]
Output: false


     */
    static void main() {
        List<String> loc=new ArrayList<>();
        loc.add("a");
        loc.add("b");
        loc.add("c");
        loc.add("d");
        loc.add("e");
        loc.add("f");

        List<List<String>> adj=new ArrayList<>();
        List<String> nei1=new ArrayList<>();
        nei1.add("a");
        nei1.add("b");
        adj.add(nei1);
        List<String> nei2=new ArrayList<>();
        nei2.add("a");
        nei2.add("c");
        adj.add(nei2);
        List<String> nei3=new ArrayList<>();
        nei3.add("b");
        nei3.add("d");
        adj.add(nei3);
        List<String> nei4=new ArrayList<>();
        nei4.add("b");
        nei4.add("e");
        adj.add(nei4);
        List<String> nei5=new ArrayList<>();
        nei5.add("c");
        nei5.add("f");
        adj.add(nei5);
        ThreeColorMapColoring tc=new ThreeColorMapColoring();
        System.out.println(tc.threeColor(loc,adj));
    }

    //就是dfs backtracking。还有题目没说是不是所有点都是相连的，但是代码应该都work才行吧
    //和isbiparty很像但不一样，但是三种颜色本质是backtracking，dfs写起来更难，那我的想法就是遍历所
    // 有的点作为起点（因为图可能不是全部链接的），那么如果当前点没被染色我就dfs去染色，否则略过。那么这个d
    // fs我的想法是他到底应该染那个颜色呢，肯定得一个一个试，但这个逻辑放在caller那还是dfs内部？我想放在caller那，
    // 那么进入dfs之后我把当前点染成参数给的颜色，然后看他的邻居，如果邻居没有颜色就同样给他试试另外两种颜色的dfs，否则检查一下是否和
    // 当前颜色冲突。如果有一条dfs完全走通了就可以返回true，否则耗尽所有尝试之后返回false。这样很难写，下面是错的
//    public boolean threeColor(List<String> locations, List<List<String>> adjacencies) {
//        Map<String, Set<String>> map=new HashMap<>();
//        Map<String,Integer> memo=new HashMap<>();
//        for (String s:locations){
//            map.put(s,new HashSet<>());
//            memo.put(s,0);
//        }
//
//        for (int i=0;i<adjacencies.size();i++){
//            map.get(adjacencies.get(i).get(0)).add(adjacencies.get(i).get(1));
//            map.get(adjacencies.get(i).get(1)).add(adjacencies.get(i).get(0));
//        }
//
//        for (int i=0;i<locations.size();i++){
//            String s=locations.get(i);
//            if(memo.get(s)==0){
//                //对于这个dfs，我是否需要一个parent的颜色参数？还有我给当前节点选颜色是在这里选还是在dfs里选？还有怎么知道遍历全部的点？
//                for(int j=1;j<=3;j++){
//                    if(dfs(j,s,map,memo)){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//    //
//    boolean dfs(int color,String loc,Map<String,Set<String>> map, Map<String,Integer> memo){
//        //这里其实应该说这个点
//        memo.put(loc,color);
//        Set<String> neighbour=map.get(loc);
//        if(neighbour!=null){
//            for(String nei:neighbour){
//                if(memo.get(nei)==0){
//                    for(int i=1;i<=3;i++){
//                        if(i==color){
//                            continue;
//                        }
//                        //其实这里不应该是这种 if（dfs）-》return true的写法，然后最后没有返回true就返回false，而是
//                        //
//                        if(dfs(i,nei,map,memo)){
//                            return true;
//                        }else{
//                            memo.put(nei,0);
//                        }
//                    }
//                }else if(memo.get(nei)==color){
//                    return false;
//                }
//            }
//        }
//        //这个写法有个难点，假如这个点被染色了，然后其邻居也是被染色了而且都ok，但是还没遍历完整张图，此时该返回true吗，而且保持现在的染色？
//        // 如果你这一部分是ok的，但是遍历更多边的时候发现这个染色是不行的，你咋办？
//        if(done(memo)){
//            return true;
//        }
//        memo.put(loc,0);
//        return false;
//
//    }
//
//    boolean done(Map<String,Integer> memo){
//        for(int val:memo.values()){
//            if(val==0){
//                return false;
//            }
//        }
//        return true;
//    }
    //gpt说应该用纯backtracking的写法，即按location的index一个一个试，而不是按某个location的邻居一个一个试。这个解法好
    public boolean threeColor(List<String> locations, List<List<String>> adjacencies) {
        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> memo = new HashMap<>();
        for (String s : locations) {
            map.put(s, new HashSet<>());
            memo.put(s, 0);
        }

        for (int i = 0; i < adjacencies.size(); i++) {
            map.get(adjacencies.get(i).get(0)).add(adjacencies.get(i).get(1));
            map.get(adjacencies.get(i).get(1)).add(adjacencies.get(i).get(0));
        }
        return dfs2(0,locations,map,memo);
    }
    boolean dfs2(int index,List<String> locations,Map<String,Set<String>> map,Map<String,Integer> memo){
        if(index==locations.size()){
            return true;
        }
        String cur=locations.get(index);
//        if(memo.get(cur)!=0){//不用这个，因为是按index推进的，不是按邻居推进的，所以肯定是没染色的
//            return true;
//        }
        boolean ok=true;
        for (int i=1;i<=3;i++){
            memo.put(cur,i);
            if(!checkNeighbour(i,cur,map,memo)){
                continue;
            }
            if(dfs2(index+1,locations,map,memo)){
                return true;
            }
        }
        memo.put(cur,0);
        return false;
    }
    boolean checkNeighbour(int c,String cur,Map<String,Set<String>> map,Map<String,Integer> memo){
        Set<String> neighbour=map.get(cur);
        if(neighbour!=null){
            for (String nei:neighbour){
                if(memo.get(nei)==c){
                   return false;
                }
            }
        }
        return true;
    }
}
