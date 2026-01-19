package 灵神.链表二叉树回溯.树.一般树dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumFuelCosttoReporttotheCapital2477 {
    static void main() {

    }
    //12/27/2025 写不出，dfs的细节不太好想。
    //https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/solutions/1981361/kao-lu-mei-tiao-bian-shang-zhi-shao-xu-y-uamv/
    long rs=0;
    public long minimumFuelCost(int[][] roads, int seats) {
        if(roads.length==0){
            return 0;
        }
        Map<Integer, Set<Integer>> map=new HashMap<>();
        for (int i=0;i<roads.length;i++){
            map.putIfAbsent(roads[i][0],new HashSet<>());
            map.putIfAbsent(roads[i][1],new HashSet<>());
            map.get(roads[i][0]).add(roads[i][1]);
            map.get(roads[i][1]).add(roads[i][0]);
        }
        dfs(0,-1,map,seats);
        return rs;
    }
    int dfs(int node,int parent,Map<Integer,Set<Integer>> map,int seat){
        int count=1;
        if(map.get(node)==null){

        }
        for(int nei:map.get(node)){
            if(nei==parent) {
                continue;
            }
            count+=dfs(nei,node,map,seat);
        }
        if(node>0){//小于seat就加1，大于等于seat就是取车数量的celing
            rs+=Math.ceil(count*1.0/seat);//少了*1.0就不对了，零神的写法(size - 1) / seats + 1 其实也就是这个意思
        }
        return count;
    }
}
