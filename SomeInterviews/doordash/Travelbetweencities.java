package SomeInterviews.doordash;

import java.util.*;

public class Travelbetweencities {

    public static void main(String[] args){
        Travelbetweencities tb=new Travelbetweencities();
        List<int[]> roads=new ArrayList<>();
        roads.add(new int[]{1, 2, 1});
        roads.add(new int[]{2, 3, 1});
        roads.add(new int[]{3, 4, 1});
        roads.add(new int[]{4, 5, 1});
        roads.add(new int[]{5, 1, 3});
        roads.add(new int[]{1, 3, 2});
        roads.add(new int[]{5, 3, 1});

        boolean[]rs=tb.shortestPaths3(roads,1,5);
        for (boolean b:rs){
            System.out.println(b);
        }
    }
    //https://www.1point3acres.com/bbs/thread-778790-1-1.html
    //https://leetcode.com/discuss/interview-question/299983/Check-Your-Route/281201
     //9/15/2021 写法1。dijistra变形，在判断最短路径的时候，发现相等的，也要把相等的节点记录上，最后dfs搞出来。真tm难写
    public boolean[] shortestPaths(List<int[]> roads,int source, int target){
        HashMap<Integer,List<int[]>> map=new HashMap<>();
        for (int i=0;i<roads.size();i++){
            int from=roads.get(i)[0]-1;
            int to=roads.get(i)[1]-1;
            int cost=roads.get(i)[2];
            if (!map.containsKey(from)){
                map.put(from,new ArrayList<>());
            }
            map.get(from).add(new int[]{to,cost,i});//这个value还存上了roads的index，以便后面找回是那条road的

            if (!map.containsKey(to)){
                map.put(to,new ArrayList<>());
            }
            map.get(to).add(new int[]{from,cost,i});
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{source-1,0});
        Set<Integer> set=new HashSet<>();
        int[] dist=new int[map.size()];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source-1]=0;
        List<Set<Integer>> path=new ArrayList<>();
        for (int i=0;i<map.size();i++){
            path.add(new HashSet<>());
        }
        int count=0;
        while (count<map.size()){
            int[] cur=pq.poll();
            set.add(cur[0]);
            if (cur[0]==target){
                break;
            }
            if (map.get(cur[0])!=null){
                for (int[] nei:map.get(cur[0])){
                    int n=nei[0];
                    if (set.contains(n)){
                        continue;
                    }
                    int temp=dist[cur[0]]+nei[1];//这里是dist【cur【0】】， 容易写成cur【1】
                    if (temp<=dist[n]){
                        if (temp<dist[n]){//严格小于的话，就要把之前的父节点清空
                            path.get(n).clear();
                        }
                        dist[n]=temp;
                        path.get(n).add(cur[0]);
                    }
                    pq.offer(new int[]{n,temp});//记住这个offer是放在外面的
                }
            }
            count++;
        }
        int shortest=dist[target-1];
        System.out.println(shortest);
        boolean[] rs=new boolean[roads.size()];//他是要求所有最短路径经过那几条路，而不是经过哪几个点。
        dfs(source-1,target-1,path,rs,map);
        return rs;
    }
    void dfs(int source,int cur,List<Set<Integer>> path,boolean[] rs,HashMap<Integer,List<int[]>> map){
        if (cur==source){
            return ;
        }
        for (int parent:path.get(cur)){
            //cur的父节点是parent，那么现在要找parent到cur的这条road，map里记录的是from到to的road的index,那么我就可以
            for (int[] nei:map.get(parent)){ //map。get（parent），得到的是于parent联通的所有节点，这个节点=cur的话就找到了
                if (nei[0]==cur){
                    rs[nei[2]]=true;
                    break;
                }
            }
            dfs(source,parent,path,rs,map);
        }
    }
    // 写法2。应该是直接dijistra算出最短距离，然后dfs把全部可能搞出来
    //https://www.1point3acres.com/bbs/thread-778790-1-1.html dfs参考他的
    public boolean[] shortestPaths2(List<int[]> roads,int source, int target){
        HashMap<Integer,List<int[]>> map=new HashMap<>();
        for (int i=0;i<roads.size();i++){
            int from=roads.get(i)[0];
            int to=roads.get(i)[1];
            int cost=roads.get(i)[2];
            if (!map.containsKey(from)){
                map.put(from,new ArrayList<>());
            }
            map.get(from).add(new int[]{to,cost,i});//这个value还存上了roads的index，以便后面找回是那条road的

            if (!map.containsKey(to)){
                map.put(to,new ArrayList<>());
            }
            map.get(to).add(new int[]{from,cost,i});
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{source,0});
        Set<Integer> set=new HashSet<>();
        int[] dist=new int[map.size()+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source]=0;

        int count=0;
        while (count<map.size()){
            int[] cur=pq.poll();
            set.add(cur[0]);
            if (cur[0]==target){
                break;
            }
            if (map.get(cur[0])!=null){
                for (int[] nei:map.get(cur[0])){
                    int n=nei[0];
                    if (set.contains(n)){
                        continue;
                    }
                    int temp=dist[cur[0]]+nei[1];//这里是dist【cur【0】】， 容易写成cur【1】
                    if (temp<dist[n]){
                        dist[n]=temp;
                    }
                    pq.offer(new int[]{n,temp});//记住这个offer是放在外面的
                }
            }
            count++;
        }
        int shortest=dist[target];
        System.out.println(shortest);
        boolean[] rs=new boolean[roads.size()];//他是要求所有最短路径经过那几条路，而不是经过哪几个点。
        dfs2(source,target,shortest,rs,map,new HashSet<>());
        return rs;
    }
    void dfs2 (int cur,int target,int cost,boolean[] rs,Map<Integer,List<int[]>> map,HashSet<Integer> path){
        if (cost<0){
            return;
        }
        if (cur==target){
            for (int p:path){
                rs[p]=true;
            }
        }
        for (int[] nei:map.get(cur)){
            int n=nei[0];
            int c=nei[1];
            int index=nei[2];
            if (!path.contains(index)){//他这个path很巧妙，即用来装road的index，也用来当memo用,因为这个road就是包含from到to，和to 到from
                path.add(index);
                dfs2(n,target,cost-c,rs,map,path);
                path.remove(index);
            }
        }
    }

    //10/6/2021
    public boolean[] shortestPaths3(List<int[]> roads,int source, int target){
        HashMap<Integer,List<int[]>> map=new HashMap<>();
        for (int i=0;i<roads.size();i++){
            if (!map.containsKey(roads.get(i)[0])){
                map.put(roads.get(i)[0],new ArrayList<>());
            }
            if (!map.containsKey(roads.get(i)[1])){
                map.put(roads.get(i)[1],new ArrayList<>());
            }
            map.get(roads.get(i)[0]).add(new int[]{roads.get(i)[1],roads.get(i)[2],i});
            map.get(roads.get(i)[1]).add(new int[]{roads.get(i)[0],roads.get(i)[2],i});
        }
        HashSet<Integer> set=new HashSet<>();
        int[] dist=new int[roads.size()+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source]=0;
        List<Set<Integer>> path=new ArrayList<>();
        for (int i=0;i<map.size()+1;i++){
            path.add(new HashSet<>());
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{source,0});
        while (set.size()<map.size()){
            int[] cur=pq.poll();
            set.add(cur[0]);
            if (cur[0]==target){
                break;
            }
            if (map.containsKey(cur[0])){
                for (int[] nei:map.get(cur[0])){
                    if (set.contains(nei[0])){
                        continue;
                    }
                    int temp=nei[1]+dist[cur[0]];
                    if (temp<=dist[nei[0]]){
                        if (temp<dist[nei[0]]){
                            path.get(nei[0]).clear();
                        }
                        dist[nei[0]]=temp;
                        path.get(nei[0]).add(cur[0]);
                    }
                    pq.offer(new int[]{nei[0],temp});
                }
            }
        }

        System.out.println(dist[target]);
        boolean[] rs=new boolean[roads.size()];
        dfs3(target,source,path,map,rs);
        return rs;
    }
    void dfs3(int source,int target,List<Set<Integer>> path,HashMap<Integer,List<int[]>> map,boolean[] rs){
        if (source==target){
            return;
        }
        for (int p:path.get(source)){
            List<int[]> nei=map.get(p);
            for (int[] n:nei){
                if (n[0]==source){
                    rs[n[2]]=true;
                }
            }

            dfs3(p,target,path,map,rs);
        }

    }

}
