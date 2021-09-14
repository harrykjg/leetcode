import jdk.nashorn.internal.runtime.WithObject;

import java.util.*;

public class WordAbbreviation {
    public static void main(String[] args){
        WordAbbreviation wa=new WordAbbreviation();
        List<String> ls=new ArrayList<>();
        ls.add("intension");
        ls.add("intrusion");
        wa.wordsAbbreviation(ls);
    }
    //7/31/2021没想出来，看答案思路自己想的，到先按第一个和最后一个字符和长度相同的字符串分成一组.然后以第一个字符+中间长度+最后一个字符得出的string为key，
    //创建一个map，把这个key相同的词group起来，因为只有key相同的才需要用trie取处理找出prefix，然后每组相同key的词建树，找出unique的prefix，通过trie里记录
    //每一个字符出现的次数，找到第一个出现1次的就说明是unique的prefix了
    //https://leetcode.com/problems/word-abbreviation/discuss/99792/HashMap-%2B-Trie-greater-O(nL)-solution
    Trie root=new Trie(' ');
    public List<String> wordsAbbreviation(List<String> words) {
        HashMap<String,List<Pair>> map=new LinkedHashMap<>();
        for (int i=0;i<words.size();i++){
            char first=words.get(i).charAt(0);
            char last=words.get(i).charAt(words.get(i).length()-1);
            int len=words.get(i).length()-2;
            String key=String.valueOf(first)+String.valueOf(len)+String.valueOf(last);
            if (!map.containsKey(key)){
                ArrayList<Pair> al=new ArrayList<>();
                map.put(key,al);
            }
            map.get(key).add(new Pair(words.get(i),i));//加了一个pair class存对应的index，不然放回结果集的时候对不上
        }
        String[] rs=new String[words.size()];
        for (String key:map.keySet()){
            if (map.get(key).size()==1){//单个的，就判断一下中间变数字会不会比原来的短，加入结果集
                if(map.get(key).get(0).word.length()>key.length()){
                    rs[map.get(key).get(0).index]=key;
                }else{
                    rs[map.get(key).get(0).index]=(map.get(key).get(0).word);
                }

            }else{
                root=new Trie(' ');//注意还要每个组建一个trie，不然找unique prefix的时候找不对。
                for(Pair w:map.get(key)){
                    root.insert(w.word);
                }
                for(Pair w:map.get(key)){
                    String prefix=root.findUnique(w.word);
                    int num=w.word.length()-prefix.length()-1;
                    System.out.println(num);
                    if (num<=1){
                        rs[w.index]=w.word;
                    }else{
                        rs[w.index]=prefix+num+w.word.charAt(w.word.length()-1);
                    }
                }

            }
        }
        return Arrays.asList(rs);
    }
    class Pair {
        String word;
        int index;
        public Pair(String w,int i){
            word=w;
            index=i;
        }
    }
    class Trie{
        char val;
        int count=1;
        boolean isEnd;
        Map<Character,Trie> children=new HashMap<>();
        public Trie(char val){
            this.val=val;
        }
        boolean hasPrefix(String s){
            if (s.length()==0){
                return true;
            }
            char[] ch=s.toCharArray();
            Trie cur=root;
            for (int i=0;i<ch.length;i++){
                if (!cur.children.containsKey(ch[i])){
                    return false;
                }
                cur=cur.children.get(ch[i]);
            }
            return true;
        }

        void insert(String s){
            char[] ch=s.toCharArray();
            Trie cur=root;
            for (int i=0;i<ch.length;i++){
                if (!cur.children.containsKey(ch[i])){
                    Trie node=new Trie(ch[i]);
                    cur.children.put(ch[i],node);
                }else{
                    cur.children.get(ch[i]).count+=1;
                }
                if (i==ch.length-1){
                    cur.children.get(ch[i]).isEnd=true;
                }
                cur=cur.children.get(ch[i]);
            }
        }
        String findUnique(String s){
            Trie cur=root;
            for (int i=0;i<s.length();i++){
                if (cur.children.containsKey(s.charAt(i))){
                    if (cur.children.get(s.charAt(i)).count==1){
                        return s.substring(0,i+1);
                    }
                }
                cur=cur.children.get(s.charAt(i));
            }
            return s;

        }
    }
}
