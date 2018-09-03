import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 8/31/18.
 */
public class PalindromePermutationII {
    //自己想了，还算顺改了几次accept了，//想法就是dfs去利用已有的字符的个数的信息，去从两头开始构造回文字符
    //感觉别人写的都挺复杂的还不如我的，我的排前百分之一。
    public List<String> generatePalindromes(String s) {
        List<String> rs=new ArrayList<>();
        int[] count=new int[128];
        char[] ch=s.toCharArray();
        int singleCount=0;
        for(char c:ch){
            count[c]++;
        }
        for(int i:count){//如果有大于等于2个出现次数单数的字符时，就不可能组成回文
            if(i%2!=0){
                singleCount++;
                if(singleCount>=2){
                    return rs;
                }
            }
        }
        char[] cc=new char[s.length()];
        dfs(0,s.length()-1,cc,count,rs);
        return rs;
    }
    void dfs(int b,int e,char[] cur,int[] count,List<String> rs){
        if(b>e){
            rs.add(new String(cur));
            return;
        }
        for(int i=0;i<count.length;i++){
            if(b==e){//如果只剩最后一个了，那么就只要符合count>=1就行了
                if(count[i]>=1){
                    cur[b]=(char)(i);
                    count[i]--;
                    dfs(b+1,e-1,cur,count,rs);
                    count[i]++;
                }
            }else{//否则，两端开始向内构造
                if(count[i]>=2){
                    cur[b]=(char)(i);
                    cur[e]=(char)(i);
                    count[i]-=2;
                    dfs(b+1,e-1,cur,count,rs);
                    count[i]+=2;
                }
            }
        }
    }
}
