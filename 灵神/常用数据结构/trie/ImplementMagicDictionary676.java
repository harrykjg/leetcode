package 灵神.常用数据结构.trie;

import java.util.HashSet;
import java.util.Set;

public class ImplementMagicDictionary676 {
    static void main() {
        ImplementMagicDictionary676 im=new ImplementMagicDictionary676();
        im.buildDict(new String[]{"hello"});
        System.out.println(im.search("hello"));
    }
    Set<String> set=new HashSet<>();
    public ImplementMagicDictionary676() {

    }
//这个是暴力枚举所有dictionary里的单词的所有差一位的词放进set里
    //答案是用trie，没仔细看
    public void buildDict(String[] dictionary) {
        for(int i=0;i<dictionary.length;i++){
            char[] ch=dictionary[i].toCharArray();

            for(int j=0;j<ch.length;j++){
                char ori=ch[j];
                for(char k='a';k<='z';k++){
                    if(k==ori){
                        continue;
                    }
                    ch[j]=k;
                    set.add(new String(ch));
                }
                ch[j]=ori;
            }

        }
    }
    public boolean search(String searchWord) {
        return set.contains(searchWord);
    }
}
