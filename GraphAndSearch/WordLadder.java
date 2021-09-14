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

        System.out.println(wl.ladderLength4("hot", "cog", set));
    }
    //我自己想的其实是dfs,貌似是会超时,看回答案应该用bfs,不知道为啥更新后leetcode把dict换成list(可能是因为以前的方法要把set转成array再做，所以leetcode改了),这样用list的contains方法好像会慢导致超时,
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

    //3/17/2018,九章第二轮，忘了写set去记录访问过的单词,改了之后就对了，还有就是每次这个层数的count1和count2都会觉得别扭，还是写对了
    public int ladderLength2(String start, String end, List<String> dict) {
        if(start.equals(end)){
            return 1;
        }
        HashSet<String> set=new HashSet<>(dict);
        if(!set.contains(end)){
            return 0;
        }

        int rs=1;
        int count1=1;
        int count2=0;

        Queue<String> q=new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()){
            String temp=q.poll();
            count1--;

            char[] ch=temp.toCharArray();
            for(int i=0;i<ch.length;i++){
                char origin=ch[i];
                for(char c='a';c<='z';c++){
                    if(ch[i]==c){
                        continue;
                    }
                    ch[i]=c;
                    String next=new String(ch);
                    if(next.equals(end)){
                        return rs+1;
                    }
                    if(set.contains(next)){
                        count2++;
                        q.offer(next);
                        set.remove(next);
                    }
                }
                ch[i]=origin;
            }
            if(count1==0){

                count1=count2;
                count2=0;
                rs++;
            }

        }
        return 0;
    }

    //6/12/2021,改了几次accept了，开始多用了一个额外的set去去重，其实用本来wordlist里遇到一个删除一个也可以去重
    public int ladderLength3(String start, String end, List<String> wordList) {
        if (start.equals(end)){
            return 1;
        }
        Set<String> dict=new HashSet<>(wordList);
        if(!dict.contains(end)){
            return 0;
        }
        Queue<String> q=new LinkedList<>();
        q.offer(start);
        int rs=1;
        int count1=1;
        int count2=0;
        while (!q.isEmpty()){
            char[] cur=q.poll().toCharArray();
            count1--;
            for (int i=0;i<cur.length;i++){
                char ori=cur[i];
                for (char j='a';j<='z';j++){
                    if (cur[i]==j){
                        continue;
                    }
                    cur[i]=j;
                    String temp=new String(cur);
                    if (temp.equals(end)){
                        return rs+1;
                    }
                    if (dict.contains(temp)){
                        q.add(temp);
                        dict.remove(temp);
                        count2++;
                    }
                }
                cur[i]=ori;
            }
            if (count1==0){
                count1=count2;
                count2=0;
                rs+=1;
            }

        }
        return 0;
    }
    //8/14/2021
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
        Set<String> set=new HashSet<>();
        for(String s:wordList){
            set.add(s);
        }
        if(!set.contains(endWord)){
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        Queue<String> q=new LinkedList<>();
        q.offer(beginWord);
        int rs=1;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                String cur=q.poll();
                set.remove(cur);
                char[] ch=cur.toCharArray();
                for(int j=0;j<ch.length;j++){
                    char ori=ch[j];
                    for(char c='a';c<='z';c++){
                        if(ch[j]==c){
                            continue;
                        }
                        ch[j]=c;
                        String news=new String(ch);
                        if(news.equals(endWord)){
                            return rs+1;
                        }
                        if (set.contains(news)){
                            q.offer(news);
                        }
                    }
                    ch[j]=ori;
                }
            }
            rs++;
        }
        return 0;
    }
}
