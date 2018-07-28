import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yufengzhu on 7/23/18.
 */
public class FriendsOfAppropriateAges {
    //还是做不出来，我以为要用线段树，先对所有ages建线段树，然后loop ages，计算出每个age会request的age范围，再去线段树中找
    //http://www.cnblogs.com/tobeabetterpig/p/9328609.html
    //https://leetcode.com/problems/friends-of-appropriate-ages/discuss/127341/10ms-concise-Java-solution-O(n)-time-and-O(1)-space
    public int numFriendRequests(int[] ages) {
        if(ages.length==0){
            return 0;
        }
        int[] count=new int[121];//第一位为dummy，使得一一对应
        for(int a:ages){
            count[a]++;
        }
        int rs=0;
//        for(int a:ages){
//            int lower=a/2+7;
//            for(int i=lower+1;i<=a;i++){
//                if(i==a){
//                    rs+=count[i]-1;
//                }else{
//                    rs+=count[i];
//                }
//            }
//        }
        //上面这样写也能accept，但是下面这样更好
        for(int i=1;i<count.length;i++){
            int lower=i/2+7;
            for(int j=lower+1;j<=i;j++){
                if(i==j){
                    rs+=(count[i]-1)*count[j];
                }else{
                    rs+=count[i]*count[j];
                }
            }
        }
        return rs;

    }
}
