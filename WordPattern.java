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
}
