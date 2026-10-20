package SomeInterviews.reddit;

import java.util.*;

public class WordLadder {
    /*
    验证给定序列是否合法 [hot，dot，cog] 是 invalid, [hot，dot，dot] 也是 invalid ，[hot，dot，dog] 是 valid
    应该是127简化版，就是每一次只能变一个字母，否则invalid
     */
    public boolean isValidSequence(List<String> words) {
        String pre=words.getFirst();
        for (int i=1;i<words.size();i++){
            if(!good(pre,words.get(i))){
                return false;
            }
            pre=words.get(i);
        }
        return true;
    }
    boolean good(String s1,String s2){
        if(s1.length()!=s2.length()){
           return false;
        }
        int count=0;
        for (int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                count++;
                if(count>1){
                    return false;
                }
            }
        }
        return count==1;
    }

    //lc 原题
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int dist=0;
        Queue<String> q=new LinkedList<>();
        Set<String> memo=new HashSet<>();//再enqueue的时候用if(set.remove("abc"))判断就可以去重了，并且少用memo这个set
        Set<String> set=new HashSet<>(wordList);
        q.offer(beginWord);
        memo.add(beginWord);
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                String cur=q.poll();
                if(cur.equals(endWord)){
                    return dist+1;
                }
                StringBuilder sb=new StringBuilder(cur);
                for (int j=0;j<cur.length();j++){
                    char orig=sb.charAt(j);
                    for (char c='a';c<='z';c++){
                        if(sb.charAt(j)==c){
                            continue;
                        }
                        sb.setCharAt(j,c);
                        if(set.contains(sb.toString())&&!memo.contains(sb.toString())){
                            q.offer(sb.toString());
                            memo.add(sb.toString());
                        }
                    }
                    sb.setCharAt(j,orig);//容易漏revert char
                }
            }
            dist++;
        }
        return 0;
    }
}
