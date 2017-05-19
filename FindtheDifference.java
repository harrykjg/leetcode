import java.util.HashMap;

/**
 * Created by 502575560 on 5/18/17.
 */
public class FindtheDifference {
    //题目test case 有点不清楚,加入多个字母时返回是non sense的答案,貌似只是加入一个字母,而且不会有不加入字幕的情况
    //自己写的,改了两下
    //http://www.360doc.com/content/16/0920/20/10408243_592336785.shtml
    public char findTheDifference(String s, String t) {
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),1);
            }else{
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }
        }
        for(int i=0;i<t.length();i++){
            if(!map.containsKey(t.charAt(i))){
                return t.charAt(i);
            }else if (map.get(t.charAt(i))<=0){
                return t.charAt(i);
            }else{
                map.put(t.charAt(i),map.get(t.charAt(i))-1);
            }
        }
        return ' ';//最后这里返回啥都可以,因为题目肯定有东西让你在上面的for就返回了
    }
}
