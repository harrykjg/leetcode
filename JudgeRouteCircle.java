import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yufengzhu on 7/1/18.
 */
public class JudgeRouteCircle {
    //我这还用了个数组，其实应该就用2个变量x，y就行了 https://leetcode.com/problems/judge-route-circle/solution/
    public boolean judgeCircle(String moves) {
        int[] a=new int[4];
        char[] ch=moves.toCharArray();
        for(int i=0;i<ch.length;i++){
           if(ch[i]=='U'){
               a[0]++;
           }else if(ch[i]=='D'){
               a[1]++;
           }else if(ch[i]=='L'){
               a[2]++;
           }else{
               a[3]++;
           }
        }
        return a[0]==a[1]&&a[2]==a[3];
    }
}
