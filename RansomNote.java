import java.util.HashMap;

/**
 * Created by 502575560 on 5/11/17.
 */
public class RansomNote {
    //自己想的,简单题,但是还改了几次
    //http://blog.csdn.net/wisimer/article/details/52186480  不用map,用数组统计每个字符的出现次数然后比较就行了
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> map=new HashMap<>();
        if(magazine==null){
            return false;
        }
        if(ransomNote==null||ransomNote.length()==0){
            return true;
        }
        char[] ch=magazine.toCharArray();
        for (int i=0;i<ch.length;i++){
            char c=ch[i];
            if(!map.containsKey(c)){
                map.put(c,1);
            }else{
                map.put(c,map.get(c)+1);
            }
        }
        ch=ransomNote.toCharArray();
        for(int i=0;i<ch.length;i++){
            char c=ch[i];
            if(!map.containsKey(c)){
                return false;
            }
            else{
                int temp=map.get(c);
                if(temp==1){
                    map.remove(c);
                }else{
                    map.put(c,map.get(c)-1);
                }

            }
        }
        return true;

    }
}
