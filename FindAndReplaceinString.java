import java.util.Arrays;

/**
 * Created by yufengzhu on 10/20/18.
 */
public class FindAndReplaceinString {
    public static void main(String[] args){
        FindAndReplaceinString fr=new FindAndReplaceinString();
        System.out.print(fr.findReplaceString("abcd",new int[]{0,2},new String[]{"a","cd"},new String[]{"eee","ffff"}));
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
}
