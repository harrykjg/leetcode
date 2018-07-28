/**
 * Created by yufengzhu on 7/20/18.
 */
public class PalindromicSubstrings {
    //brute force 方法
    //还可以用dp，还有别的方法
    //https://segmentfault.com/a/1190000003914228
    //https://blog.csdn.net/huanghanqian/article/details/76577138 这个最后非dp也是n平方复杂度把
    //https://www.cnblogs.com/Deribs4/p/7228768.html  马拉车算法 没看
    int count=0;
    public int countSubstrings(String s) {
        if(s.length()==0){
            return count;
        }
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<=s.length()-i;j++){
                String temp=s.substring(j,j+i);
                valid(temp);
            }
        }
        return count;
    }
    void valid(String s){
        int b=0;
        int e=s.length()-1;
        while (b<e){
            if(s.charAt(b)!=s.charAt(e)){
                return;
            }
            b++;
            e--;
        }
        count++;
    }
}
