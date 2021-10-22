import java.util.*;

/**
 * Created by yufengzhu on 8/17/18.
 */
public class ReorganizeString {
    public static void main(String[] args){
        ReorganizeString rs=new ReorganizeString();
        rs.reorganizeString2("lblflxl");
    }
    //看了提示要sort和hashmap还有greedy，统计每个字符出现的数字，按出现的多少排序，最多的排最前，开始想的是然后按这个顺序每次从一个字符那娶一个，其实娶一个是不行的，要取2个，看答案2
    //https://leetcode.com/problems/reorganize-string/solution/
    //https://www.cnblogs.com/grandyang/p/8799483.html
    public String reorganizeString(String S) {
        if(S.length()==0){
            return "";
        }
        HashMap<Character, Pair> map=new HashMap<>();
        char[] ch=S.toCharArray();
        PriorityQueue<Pair> pq=new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.count-o1.count;
            }
        });
        for(int i=0;i<S.length();i++){
            if(!map.containsKey(ch[i])){
                Pair p=new Pair(ch[i],1);
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
            Pair p1=pq.poll();
            if(pq.isEmpty()){
                sb.append(p1.c);
                return sb.toString();
            }
            Pair p2=pq.poll();
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
    class Pair {
        char c;
        int count;
        public Pair(char a, int b){
            c=a;
            count=b;
        }
    }
    //9/11/2018,改了一次过
    public String reorganizeString2(String S) {
        PriorityQueue<Pair> pq=new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.count-o1.count;
            }
        });
        if(S.length()==0){
            return "";
        }
        String rs="";
        char[] ch=S.toCharArray();
        int[] count=new int[128];
        for(int i=0;i<ch.length;i++){
            count[ch[i]]++;
        }
        for(int i=0;i<count.length;i++){
            if(count[i]>0){
                pq.offer(new Pair((char)i,count[i]));
            }
        }
        while (!pq.isEmpty()){
            Pair p1=pq.poll();
            if(pq.isEmpty()&&p1.count>1){
                return "";
            }
            if(pq.isEmpty()){
                rs+=p1.c;
                return rs;
            }
            rs+=p1.c;
            Pair p2=pq.poll();
            rs+=p2.c;
            p1.count--;
            p2.count--;
            if(p1.count>0){
                pq.offer(p1);
            }
            if(p2.count>0){
                pq.offer(p2);
            }
        }
        return rs;
    }

    //10/11/2021 想到是pq
    //https://leetcode.com/problems/reorganize-string/discuss/232469/Java-No-Sort-O(N)-0ms-beat-100
    public String reorganizeString3(String S) {
        PriorityQueue<Pair> pq=new PriorityQueue<Pair>((a,b)->a.count==b.count?a.c-b.c:b.count-a.count);
        HashMap<Character,Integer> map=new HashMap<>();
        char[] ch=S.toCharArray();
        for (int i=0;i<ch.length;i++){
            map.put(ch[i],map.getOrDefault(ch[i],0)+1);
        }
        int max=0;
        for (Map.Entry<Character,Integer> entry:map.entrySet()){
            pq.offer(new Pair(entry.getKey(),entry.getValue()));
            max=Math.max(entry.getValue(),max);
        }
        if ((ch.length+1)/2<max){//这个注意容易写错成ch。length/2+1
            return "";
        }
        StringBuilder sb=new StringBuilder();
        while (true){
            List<Pair> temp=new ArrayList<>();
            int count=0;
            while (count<2&&!pq.isEmpty()){
                if (sb.length()==0||pq.peek().c!=sb.charAt(sb.length()-1)){
                    Pair p=pq.poll();
                    sb.append(p.c);
                    p.count--;
                    if (p.count>0){
                        temp.add(p);
                    }
                    count++;
                }
            }
            if(temp.size()==0&&pq.isEmpty()){
                return sb.toString();
            }
            pq.addAll(temp);
        }
    }
}
