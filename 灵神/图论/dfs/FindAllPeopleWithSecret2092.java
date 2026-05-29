package 灵神.图论.dfs;

import java.util.*;

public class FindAllPeopleWithSecret2092 {
    public static void main(String[] args) {
        int[][] meeting={{5,1,4},{0,4,18}};
//        int[][] meeting={{1,2,0},
//        {1,3,1},
//        {2,4,100},
//        {3,4,2},
//        {4,5,50}};
        FindAllPeopleWithSecret2092 fa=new FindAllPeopleWithSecret2092();
        List<Integer> rs=fa.findAllPeople2(11,meeting,1);
        for(int i:rs){
            System.out.println(i);
        }
    }

    //写不出来,关键就是多了时间这个维度。 就是用时间作为key，某个时间点的所有meeting作为一个图，从小到大每个时间点dfs那个时间点的图，这样时间这个维度就被分开了
    //https://leetcode.cn/problems/find-all-people-with-secret/ 参考他的，不同的地方就是我最外层是用treemap去排序时间，而他是用了一个list从小到大装map
    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
//        Arrays.sort(meetings,(a,b)->a[2]-b[2]); //treemap替代了这一步
        List<Integer> rs=new ArrayList<>();
        Map<Integer,Map<Integer,List<Integer>>> map=new TreeMap<>();
        for(int i=0;i<meetings.length;i++){//建图，key是某个时间点，value是这个时间发生的会议做成的图，其中key是人，value是他的邻居
            int time=meetings[i][2];
            int one=meetings[i][0];
            int two=meetings[i][1];
            map.putIfAbsent(time,new HashMap<>());
            map.get(time).putIfAbsent(one,new ArrayList<>());
            map.get(time).putIfAbsent(two,new ArrayList<>());
            map.get(time).get(one).add(two);
            map.get(time).get(two).add(one);
        }
        Set<Integer> know=new HashSet<>();//key->节点，value-》时间点
        know.add(0);
        know.add(firstPerson);
        for(int key:map.keySet()){//从每个时间节点开始dfs，dfs里又基于所有已知秘密的人开始找邻居
            for(int someone:map.get(key).keySet()){ //看来不能从已知secret的人开始弄，那样会concurrent modification error,那只能从meeting的人开始
                if(know.contains(someone)){//这里也不太好想，是从知道秘密的人去dfs，但是dfs里的遍历neighbour的那里就是从不知道秘密的人开始dfs
                    dfs(someone,key,know,map);
                }
            }
        }

        for(int k:know){
            rs.add(k);
        }
        return rs;

    }

    static void dfs(int known,int time,Set<Integer> know,Map<Integer,Map<Integer,List<Integer>>> graph){

       Map<Integer,List<Integer>> curgraph=graph.get(time);
       if (curgraph==null){
           return;
       }
       //从这个人的邻居去dfs
        List<Integer> neighbours=curgraph.get(known);
        if(neighbours!=null){
            for(int nei:neighbours){
                if(!know.contains(nei)){
                    know.add(nei);
                    dfs(nei,time,know,graph);
                }

            }
        }
    }
    //2/15/2026，自己想的是用一个map装某个人知道secret的时间，再建图，是某个人的所有要开的meeting，按时间顺序排序，这样起点就是0和first person
    //再按时间顺序遍历这个知道secret的人要开的会，但是这样会处理不了这种
    /*
n = 6
firstPerson = 1
meetings =
[
  [1,2,0],   // 1->2 在 0
  [1,3,1],   // 1->3 在 1
  [2,4,100], // 2->4 在 100
  [3,4,2],   // 3->4 在 2   (这是让 4 早知道的关键)
  [4,5,50]   // 4->5 在 50  (要靠 4 早知道才能传给 5)
  会漏掉5，因为在dfs2的时候会把4把4的know time设为100，再去看4的邻居5，发现是50，因此不能通知5，并且把这个meeting poll出来了，就算到了下次
  更新4的knowtime是2的时候，再dfs4，他已经没有邻居了。
]

     */
    public List<Integer> findAllPeople2(int n, int[][] meetings, int firstPerson) {
        List<Integer> rs = new ArrayList<>();
        Map<Integer, Map<Integer,Set<Integer>>> map = new TreeMap<>();
        Set<Integer> set=new HashSet<>();
        set.add(0);
        set.add(firstPerson);
        for (int i=0;i<meetings.length;i++){
            if(!map.containsKey(meetings[i][2])){
                map.put(meetings[i][2],new HashMap<Integer,Set<Integer>>());
            }
            map.get(meetings[i][2]).putIfAbsent(meetings[i][0],new HashSet<>());
            map.get(meetings[i][2]).putIfAbsent(meetings[i][1],new HashSet<>());
            map.get(meetings[i][2]).get(meetings[i][0]).add(meetings[i][1]);
            map.get(meetings[i][2]).get(meetings[i][1]).add(meetings[i][0]);
        }
        for (int time:map.keySet()){//开始想的是对于每一个时间点，只要遍历这个时间点有的所有meeting就行了，即dfs里只要遍历就行了，不需要
                                    //再trigger下一个dfs，但其实是错的,那样secret会没法传递
            for(int someone:map.get(time).keySet()) {
                if(set.contains(someone)){
                    dfs2(someone,map.get(time),set,rs);
                }
            }
        }
        rs.add(0);
        rs.add(firstPerson);


        return rs;
    }
    void dfs2(int begin, Map<Integer,Set<Integer>> map, Set<Integer> set,List<Integer> rs){

            Set<Integer> neighbour=map.get(begin);
            if(neighbour!=null){
                for (int nei:neighbour){
                    if(!set.contains(nei)){
                        set.add(nei);
                        rs.add(nei);
                        dfs2(nei,map,set,rs);
                    }
                }
            }


    }


}
