import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 7/2/18.
 */
public class NumberofMatchingSubsequences {
    public static void main(String[] args){
        NumberofMatchingSubsequences nm=new NumberofMatchingSubsequences();
//        String s="btovxbkumc";
//        String[] w={"btovxbku","to","zueoxxxjme","yjkclbkbtl"};
        String s="abcde";
        String[] w={"a","bb","acd","ace"};
        nm.numMatchingSubseq(s,w);
    }
    //不会，https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation/ 真是吊
    //自己写debug了很久才高对，是关于concurrentModification的东西，
    public int numMatchingSubseq(String S, String[] words) {
        HashMap<Character,List<String>> map=new HashMap<>();
        for(int i=0;i<words.length;i++){
            char[] ch=words[i].toCharArray();
            if(!map.containsKey(ch[0])){
                List<String> ls=new ArrayList<>();
                ls.add(new String(ch,1,ch.length-1));
                map.put(ch[0],ls);
            }else{
                map.get(ch[0]).add(new String(ch,1,ch.length-1));
            }
        }
        int rs=0;
        char[] s=S.toCharArray();
        for(char c:s){
            if(map.containsKey(c)){
                List<String> ls=map.get(c);//map里的list会在遍历的过程中需要删除，那样会错，所以要建一个copy，单纯来遍历，然后把原来的list清除掉，再加或者不加如子串
                List<String> copy=new ArrayList<>(ls);
                ls.clear();
                for(int i=0;i<copy.size();i++){
                    char[] chss=copy.get(i).toCharArray();
                    if(chss.length==0){
                        rs++;//已经是最后一个了，不需要加到map的list里

                    }
                    else{
                        char newc=chss[0];
                        if(map.containsKey(newc)){
                            map.get(newc).add(new String(chss,1,chss.length-1));//这里加到原来那个list里，由于list己经被clear了，所以没事，否则会错！
                        }else{
                            List<String> newls=new ArrayList<>();
                            newls.add(new String(chss,1,chss.length-1));
                            map.put(newc,newls);
                        }
                    }
                }
            }
        }
        wrap()
        return rs;
    }
}
