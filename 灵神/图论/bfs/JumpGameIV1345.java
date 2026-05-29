package 灵神.图论.bfs;

import java.util.*;

public class JumpGameIV1345 {
    public static void main(String[] args) {

    }
    public int minJumps(int[] arr) {
        int rs=0;
        Map<Integer, List<Integer>> map=new HashMap<>();
        for (int i=0;i<arr.length;i++){
            map.putIfAbsent(arr[i],new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        Queue<Integer> q=new LinkedList<>();
        Set<Integer> set=new HashSet<>();
        q.offer(0);
        set.add(0);
        while (!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int cur=q.poll();
                if(cur==arr.length-1){
                    return rs;
                }
                if(cur+1<arr.length&&!set.contains(cur+1)){
                       q.offer(cur+1);
                       set.add(cur+1);
                }
                if(cur-1>=0&&!set.contains(cur-1)){
                    q.offer(cur-1);
                    set.add(cur-1);
                }
                List<Integer> neighbour=map.get(arr[cur]);
                for(int nei:neighbour){
                    if(!set.contains(nei)){
                        q.offer(nei);
                        set.add(nei);
                    }
                }
                map.get(arr[cur]).clear();//少了这个会超时，因为比如说 1111119这个例子，第一个1把所有1都加进来了，然后第二个1又要便利所有的neighbour（尽管会被set去重，但是还是要遍历）
                                        //因此直接把这个list clean掉
            }
            rs++;
        }
        return -1;
    }

    //2/15/2026 直接建图bfs超时。超时原因见上面解释
    public int minJumps2(int[] arr) {
        Map<Integer,Set<Integer>> map=new HashMap<>();
        for (int i=0;i<arr.length;i++){
            map.putIfAbsent(arr[i],new HashSet<>());
            map.get(arr[i]).add(i);
        }
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        boolean[] memo=new boolean[arr.length];
        memo[0]=true;
        int rs=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int cur=q.poll();
                if(cur==arr.length-1){
                    return rs;
                }
                Set<Integer> neighbour=map.get(arr[cur]);
                for (Integer nei:neighbour){
                    if(nei==cur){
                        continue;
                    }
                    if(!memo[nei]){
                        memo[nei]=true;
                        q.offer(nei);
                    }
                }
                map.get(arr[cur]).clear();
                if(cur+1<arr.length&&!memo[cur+1]){
                    memo[cur+1]=true;
                    q.offer(cur+1);
                }
                if(cur-1>=0&&!memo[cur-1]){
                    memo[cur-1]=true;
                    q.offer(cur-1);
                }
            }
            rs++;
        }
        return rs;
    }
}
