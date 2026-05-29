package SomeInterviews.roblox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemovePrefixString {
    /*
    Remove Prefix String
Given a list of unique strings words. You need to remove every string that is a prefix of any other string in the list.
A string a is a prefix of string b if b starts with a and the length of a is strictly less than the length of b.
Your output should contain only the strings from words that are not a prefix of any other string in the list, and the relative order of the strings in the output list must match their order in the original input list.
Constraints:
1 ≤ words.length ≤ 10⁴
1 ≤ words[i].length ≤ 100
Each words[i] consists of lowercase English letters, spaces, or printable characters.
All strings in words are distinct.
Example 1:
Input: words = ["a", "abc", "abc hello", "bc"]
Output: ["abc hello", "bc"]
Explanation:
"a" is a prefix of both "abc" and "abc hello", so it is removed.
"abc" is a prefix of "abc hello", so it is removed.
"abc hello" and "bc" are not prefixes of any other string.
Example 2:
Input: words = ["a", "ab", "abc"]
Output: ["abc"]
Example 3:
Input: words = ["cat", "dog", "bird", "fish"]
Output: ["cat", "dog", "bird", "fish"]
     */
    public List<String> removePrefix(List<String> ls){
        List<String> rs=new ArrayList<>();
        Trie root=new Trie(' ',false);
        for (String s:ls){
            root.insert(s);
        }
        for (String s:ls){
            if(root.hasPrefix(s)){
                continue;
            }
            rs.add(s);
        }
        return rs;

    }
    class Trie{
        char c;
        Map<Character,Trie> children;
        boolean isEnd;
        public Trie(char c,boolean isEnd){
            this.c=c;
            this.isEnd=isEnd;
            children=new HashMap<>();
        }
        void insert(String word){
            if(word.length()==0){
                return;
            }
            char[] ch=word.toCharArray();
            Trie t=this;
            for (int i=0;i<ch.length;i++){
                Trie next=new Trie(ch[i],i==ch.length-1?true:false);
                t.children.putIfAbsent(ch[i],next);
                t=t.children.get(ch[i]);//这里很容易写漏，写成 t=children.get(ch[i])
            }
            t.isEnd=true;
        }
        boolean hasPrefix(String word){
            if(word.length()==0){
                return false;
            }
            char[] ch=word.toCharArray();
            Trie t=this;
            for (int i=0;i<ch.length;i++){
                if(!t.children.containsKey(ch[i])){
                   return false;
                }else{
                    t=t.children.get(ch[i]);
                }
                if(i==ch.length-1&&t.children.isEmpty()){//如果trie有abc，现在检查abc，则应该有这个判断排除自己是自己的prefix
                    return false;
                }
            }
            return true;
        }
    }
}
