import java.util.*;

/**
 * Created by yufengzhu on 8/17/18.
 */
public class ReorganizeString {
    public static void main(String[] args){
        ReorganizeString rs=new ReorganizeString();
        rs.reorganizeString("lblflxl");
    }
    //看了提示要sort和hashmap还有greedy，统计每个字符出现的数字，按出现的多少排序，最多的排最前，开始想的是然后按这个顺序每次从一个字符那娶一个，其实娶一个是不行的，要取2个，看答案2
    //https://leetcode.com/problems/reorganize-string/solution/
    //https://www.cnblogs.com/grandyang/p/8799483.html
    public String reorganizeString(String S) {
        if(S.length()==0){
            return "";
        }
        HashMap<Character,pair> map=new HashMap<>();
        char[] ch=S.toCharArray();
        PriorityQueue<pair> pq=new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o2.count-o1.count;
            }
        });
        for(int i=0;i<S.length();i++){
            if(!map.containsKey(ch[i])){
                pair p=new pair(ch[i],1);
                map.put(ch[i],p);
            }else{
                map.get(ch[i]).count++;
                if(map.get(ch[i]).count>(S.length()+1)/2){
                    return "";
                }
            }
        }
        pq.addAll(map.values());
        StringBuilder sb=new StringBuilder();
        while (!pq.isEmpty()){
            pair p1=pq.poll();
            if(pq.isEmpty()){
                sb.append(p1.c);
                return sb.toString();
            }
            pair p2=pq.poll();
            sb.append(p1.c);
            sb.append(p2.c);
            p1.count--;
            p2.count--;
            if(p1.count!=0){
                pq.offer(p1);
            }
            if(p2.count!=0){
                pq.offer(p2);
            }
        }

        return sb.toString();

    }
    class pair{
        char c;
        int count;
        public pair(char a,int b){
            c=a;
            count=b;
        }
    }
}
