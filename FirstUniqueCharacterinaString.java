import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 502575560 on 5/16/17.
 */
public class FirstUniqueCharacterinaString {

    //这题自己想的,和那个InsertDeleteGetRandomO1 做法几乎一样
    //http://blog.csdn.net/u013325815/article/details/52299762  别人的方法好想更好一点
    public int firstUniqChar(String s) {
        if(s.length()==0){
            return -1;
        }
        ArrayList<Integer> al=new ArrayList<Integer>();
        HashMap<Character,Integer> map=new HashMap<Character,Integer>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(!map.containsKey(c)){
                al.add(i);
                map.put(c,al.size()-1);
            }else{
                int index=map.get(c);
                if(index!=-1){
                    int last=al.get(al.size()-1);
                    char lastChar=s.charAt(last);
                    map.put(lastChar,index);
                    al.set(index,last);
                    al.remove(al.size()-1);
                }
                map.put(c,-1);
            }
        }
        if(al.size()==0){
            return -1;
        }
        return al.get(0);
    }
    //7/15/2018
    public int firstUniqChar2(String s) {
        if(s == null || s.length() ==0){
            return -1;
        }
        int[] a=new int[256];
        char[] ch=s.toCharArray();
        for(int i=0;i<ch.length;i++){
            int temp=ch[i]-'a';
            a[temp]+=1;
        }
        for(int i=0;i<ch.length;i++){
            int temp=ch[i]-'a';
            if(a[temp]==1){
                return i;
            }
        }
        return -1;
    }
}
