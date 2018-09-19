import java.util.HashMap;

/**
 * Created by yufengzhu on 9/9/18.
 */
//uber面经,不好写，需要debug改几次才对，我这写的应该能过大多数case把
public class Findthelongestsubstringwithkuniquecharacters {
    public static void main(String[] args){
        Findthelongestsubstringwithkuniquecharacters fl=new Findthelongestsubstringwithkuniquecharacters();
      System.out.print(  fl.find("aaccbbbefffgac",3));
    }

    public int find(String s,int k){
        HashMap<Character,Integer> map=new HashMap<>();
        char[] ch=s.toCharArray();
        int wal=0;
        int run=0;
        int count=0;
        int rs=Integer.MIN_VALUE;
        while (run<ch.length){
            if(!map.containsKey(ch[run])&&count<k){
                map.put(ch[run],1);
                count++;
                run++;
                rs=Math.max(rs,run-wal);
                continue;
            }
            if(count<=k&&map.containsKey(ch[run])){
                map.put(ch[run],map.get(ch[run])+1);
                run++;
                rs=Math.max(rs,run-wal);
                continue;
            }

            char c=ch[wal];//注意比如acab，k=2，现在wal是0，run到了b了，因此要除掉run之前的所有a，那么也就要把c也除了
            while (map.containsKey(c)){
                map.put(ch[wal],map.get(ch[wal])-1);
                if(map.get(ch[wal])==0){
                    count--;
                    map.remove(ch[wal]);//这里要从map里删掉，否则前面不好判断当前string是否已经有某个字符了
                }
                wal++;
            }
        }
        return rs;
    }
}
