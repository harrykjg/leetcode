package GraphAndSearch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class OpentheLock {
//9/21/2021 看提示用bfs 然后自己写的，改了几次过了。就是把每个位置看作一个节点，这个节点有2个邻居，+1或减1，然后bfs。用memo记录访问过的和deadends，
    //麻烦的是把char 的0，1，2，3。。变成下一位，我是先把char-'0'变成一个数字然后+1成为next，然后再把这个数字+'0'变回char，(char)(next+'0')
    public int openLock(String[] deadends, String target) {
        HashSet<String> set=new HashSet<>();
        for (String s:deadends){
            set.add(s);
        }
        Queue<String> q=new LinkedList<>();
        if (!set.contains("0000")){
            q.offer("0000");
            set.add("0000");
        }
        int rs=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                String cur=q.poll();
                if (cur.equals(target)){
                    return rs;
                }
                getNeibour(cur,q,set);
            }
            rs++;
        }
        return -1;
    }
    void getNeibour(String cur,Queue<String> q,HashSet<String> memo){

        for (int i=0;i<4;i++){
            StringBuilder sb=new StringBuilder(cur);
            char c=sb.charAt(i);
            int next=(c-'0'+1)%10;
            int pre=c-'0'-1;
            if (pre<0){
                pre+=10;
            }
            sb.setCharAt(i,(char)(next+'0'));
            String n=sb.toString();
            sb.setCharAt(i,(char)(pre+'0'));
            String p=sb.toString();
            if (!memo.contains(n)){
                q.offer(n);
                memo.add(n);
            }
            if (!memo.contains(p)){
                q.offer(p);
                memo.add(p);
            }
        }
    }
}
