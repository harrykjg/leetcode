package 灵神.图论.dfs;

import java.util.*;

public class FindAllPeopleWithSecret2092 {
    public static void main(String[] args) {
        int[][] meeting={{3,1,3},{1,2,2},{0,3,3}};
        List<Integer> rs=findAllPeople(4,meeting,3);
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


}
