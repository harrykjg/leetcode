import java.util.HashMap;

/**
 * Created by yufengzhu on 8/7/18.
 */
public class WordPattern {
    //老得代码
    public boolean wordPattern(String pattern, String str) {
        if(pattern.length()==0||str.length()==0){
            return false;
        }
        HashMap<Character,String> map1=new HashMap<Character,String>();
        HashMap<String,Character> map2=new HashMap<String,Character>();

        char[] c=pattern.toCharArray();
        String[] s=str.split(" ");
        if(c.length!=s.length){
            return false;
        }

        for(int i=0;i<c.length;i++){
            if(!map1.containsKey(c[i])){
                if(!map2.containsKey(s[i])){
                    map1.put(c[i], s[i]);
                    map2.put(s[i], c[i]);
                }else{
                    if(!map2.get(s[i]).equals(c[i])){
                        return false;
                    }
                }

            }else{
                if(!map1.get(c[i]).equals(s[i])){
                    return false;
                }
            }


        }
        return true;

    }
    //10/9/2018 自己写的还是要用2个map
    // https://leetcode.com/problems/word-pattern/discuss/73399/Very-fast-(3ms)-Java-Solution-using-HashMap 别人的用一个map就行,用了map.containsValue这个方法
    public boolean wordPattern2(String pattern, String str) {
        HashMap<Character,String> map=new HashMap<>();
        HashMap<String,Character> map2=new HashMap<>();
        String[] s2=str.split(" ");
        char[] ch=pattern.toCharArray();
        if(ch.length!=s2.length){
            return false;
        }
        for(int i=0;i<ch.length;i++){
            if(!map.containsKey(ch[i])){
                map.put(ch[i],s2[i]);
            }else{
                if(!map.get(ch[i]).equals(s2[i])){
                    return false;
                }
            }
            if(!map2.containsKey(s2[i])){
                map2.put(s2[i],ch[i]);
            }else{
                if(!map2.get(s2[i]).equals(ch[i])){
                    return false;
                }
            }
        }
        return true;
    }
    //10/19/2018 还是想不到用一个map的方法https://leetcode.com/problems/word-pattern/discuss/73613/My-3ms-java-solution-using-only-one-hashmap
}
