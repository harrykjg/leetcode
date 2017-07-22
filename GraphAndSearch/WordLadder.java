package GraphAndSearch;

import java.util.*;

/**
 * Created by 502575560 on 7/14/17.
 */
public class WordLadder {
    public static void main(String[]args){
        WordLadder wl=new WordLadder();
        List<String> set=new ArrayList<>();
//        set.add("hit");
//        set.add("hik");
        set.add("hot");
        set.add("dot");
        set.add("dog");
        set.add("lot");
        set.add("log");
        set.add("cog");

        System.out.println(wl.ladderLength("hot", "cog", set));
    }
    //我自己想的其实是dfs,貌似是会超时,看回答案应该用bfs,不知道为啥更新后leetcode把dict换成list,这样用list的contains方法好像会慢导致超时,
    //那么我先把dict复制到一个set里就可以accept了
    //如果end不在dict的话就是没有路径了,除非start==end
    public int ladderLength(String start, String end, List<String> dict) {
        if(start.equals(end)){
            return 1;
        }
        int len=start.length();
        LinkedList<String> q=new LinkedList<>();
        int rs=1;
        int count=1;
        int count2=0;
        Set<String> set=new HashSet<>();
        for(String s:dict){
            set.add(s);
        }
        q.add(start);
        while (!q.isEmpty()){
            String s=q.poll();
            count--;

            for(int i=0;i<len;i++){
                char[] ch=s.toCharArray();
                for(int j=0;j<26;j++){
                    char c=(char)('a'+j);
                    if(ch[i]==c){
                        continue;
                    }
                    ch[i]=c;
                    String temp=new String(ch);
                    if(set.contains(temp)&&temp.equals(end)){
                        return rs+1;
                    }else if(set.contains(temp)){
                        set.remove(temp);
                        q.offer(temp);
                        count2++;
                    }
                }
            }
            if(count==0){
                rs++;
                count=count2;
                count2=0;
            }
        }
        return 0;
    }

}
