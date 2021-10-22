import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yufengzhu on 10/20/18.
 */
public class FindAndReplaceinString {
    public static void main(String[] args){
        FindAndReplaceinString fr=new FindAndReplaceinString();
        System.out.print(fr.findReplaceString2("abcd",new int[]{0,2},new String[]{"a","cd"},new String[]{"eee","ffff"}));
    }

    //这题恶心在于index还不是升序的，可以是乱来的
    //看答案的方法比较好
    // https://leetcode.com/problems/find-and-replace-in-string/solution/
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb=new StringBuilder();
        if(indexes.length!=sources.length||sources.length!=targets.length){
            return S;
        }
        int[] match=new int[S.length()];
        Arrays.fill(match,-1);
        for(int i=0;i<indexes.length;i++){
            if(S.indexOf(sources[i],indexes[i])==indexes[i]){//先对原始字符串检查对应的位置上match与否，match的地方记录开头的那个位置就行了，因为后面while循环里，发现match的位置就直接append target的字符串，
                match[indexes[i]]=i;                        //然后index加上对应的原来的source的长度就行了
            }
        }
        int i=0;
        while (i<S.length()){
            if(match[i]<0){
                sb.append(S.charAt(i));
                i++;
            }else{
                sb.append(targets[match[i]]);
                i+=sources[match[i]].length();
            }

        }

        return sb.toString();
    }

    //10/21/2021
    //https://leetcode.com/problems/find-and-replace-in-string/discuss/134758/Java-O(n)-solution 参考这个然后自己写的，和他不太一样，我建了个class
    //思路就是建一个class把indices和sources和targets装起来，放进map里，注意map的key是indices[i]而不是i。然后遍历输入string（for循环但是i++的条件不写，而是下面
    // 判断是否match的时候才决定），同时构建另一个结果集（不是在原string上replace），每遇到一个char，看map里是否存在这个index，存在的话用startwith检测
    // source是否符合，符合的话append上对应target，然后i+=source的长度，否则append上原来的char，i++
    public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
        StringBuilder sb=new StringBuilder();
        HashMap<Integer,Pair> map=new HashMap<>();
        for (int i=0;i<indices.length;i++){
            Pair p=new Pair(indices[i],sources[i],targets[i]);
            map.put(indices[i],p);
        }
        for (int i=0;i<s.length();){
            if (map.containsKey(i)&&s.startsWith(map.get(i).source,i)){
                sb.append(map.get(i).target);
                i+=map.get(i).source.length();
            }else{
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
    class Pair{
        int index;
        String source;
        String target;
        public Pair(int index,String source,String target){
            this.index=index;
            this.source=source;
            this.target=target;
        }
    }
}
