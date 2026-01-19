package 灵神.贪心;

import java.util.*;

public class MinimumDeletionstoMakeCharacterFrequenciesUnique1647 {
    static void main() {
        System.out.println(minDeletions("accdcdadddbaadbc"));
    }
    //12/31/2025 自己想的改了几次过了,但是复杂度不小
    public static int minDeletions(String s) {
        Map<Integer,Integer> map=new TreeMap<>(Collections.reverseOrder());
        char[] ch=s.toCharArray();
        int[] count=new int[26];
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());//从大到小
        for(int i=0;i<ch.length;i++){
            count[ch[i]-'a']++;
        }
        int max=0;
        for(int i=0;i<count.length;i++){
            if(count[i]>0){
                map.put(count[i],map.getOrDefault(count[i],0)+1);
                max=Math.max(max,count[i]);
            }
        }
        //存map里没出现过的freq
        for(int i=1;i<=max;i++){
            if(!map.containsKey(i)){
                pq.offer(i);
            }
        }
        int rs=0;
        for(int key:map.keySet()){
            int num=map.get(key);
            if(num>1){
                while (!pq.isEmpty()&&pq.peek()>key){//把大于key的候选值丢掉
                    pq.poll();
                }
                while (num>1){
                    if(!pq.isEmpty()){
                        rs+=key-pq.poll();
                        num--;
                    }else{
                        break;
                    }
                }
                if(num>1){//pq用光了，就要把key减到0
                    rs+=(num-1)*key;
                }
            }
        }
        return rs;
    }
    /*
    gpt的写法，更简单，但是没那么好理解就是用set，里面装freq，如果set没有这个fre就加进去，如果有的话就把这个fre--，
    再看set里有没有，一直减到set里没有为止。不好想的点在于这个set是无序的，即小的freq减减之后可能先把某个数字占了，大的freq再减的时候
    就得减到更小，但是得出的答案却是最优的
    public int minDeletions(String s) {
    int[] cnt = new int[26];
    for (char c : s.toCharArray()) cnt[c - 'a']++;

    java.util.HashSet<Integer> used = new java.util.HashSet<>();
    int deletions = 0;

    for (int f : cnt) {
        while (f > 0 && used.contains(f)) {
            f--;
            deletions++;
        }
        if (f > 0) used.add(f);
    }
    return deletions;
}
     */
}
