package amazon;


import java.util.*;

/**
 * Created by yufengzhu on 10/23/18.
 */
public class KLongSubstringWithKDistinctCharacter {

    public static void main(String[] args){
        KLongSubstringWithKDistinctCharacter ks=new KLongSubstringWithKDistinctCharacter();
        ks.findSubstrings("awaglknagawunagwkwag",4);
        //“awaglknagawunagwkwag” k = 4
//        你要输出：wagl, aglk, glkn, lkna, knag, gawu, awun, wuna, unag, nagw, agwk, kwag. 围观
    }

    //2 pointer
    public List<String> findSubstrings(String s,int k){
        List<String> rs=new ArrayList<>();
        HashSet<String> set=new HashSet<>();
        int run=0;
        int wal=0;
        char[] ch=s.toCharArray();
        int[] counts=new int[26];
        while (run<ch.length){
            counts[ch[run]-'a']++;
            if(counts[ch[run]-'a']>1){
                while (wal<=run){
                    counts[ch[wal]-'a']--;
                    if(ch[wal]==ch[run]){
                        wal++;
                        break;
                    }
                    wal++;
                }
                run++;
            }else{
                if(run-wal+1==k){
                    if(set.add(s.substring(wal,run+1))){
                        rs.add(s.substring(wal,run+1));
                    }
                    counts[ch[wal]-'a']--;
                    wal++;

                }
                run++;
            }
        }
        return rs;
    }
}
