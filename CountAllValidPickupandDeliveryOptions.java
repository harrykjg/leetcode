import java.util.HashSet;
import java.util.Set;

public class CountAllValidPickupandDeliveryOptions {
    public static void main(String[] args){
        CountAllValidPickupandDeliveryOptions ca=new CountAllValidPickupandDeliveryOptions();
        System.out.println(ca.countOrders(3));
    }
    //9/5/2021  自己想的dfs，差一点能想出来，就是dfs里面先后如何遍历下一种可能那不好想。会超时
    //https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/discuss/941175/Evolve-from-brute-force
    //https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/discuss/516933/C%2B%2BPython-1-line-Simple-permutation-with-explanation  数学方法
    //https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/discuss/516951/C%2B%2BJava-Simple-Math-Formula-with-Explanation
    int mod=(int) Math.pow(10,9)+7;
    long rs=0;
    public int countOrders(int n) {
        boolean[] memo1=new boolean[n];
        boolean[] memo2=new boolean[n];
        dfs(0,memo1,memo2);
        return (int)(rs%mod);
    }
    void dfs(int cur,boolean[] m1,boolean[] m2){
        if (cur==m1.length*2){
            rs++;
            return;
        }
        for (int i=0;i<m1.length;i++){
            if (!m1[i]){
                m1[i]=true;
                dfs(cur+1,m1,m2);
                m1[i]=false;
            }

        }
        for (int j=0;j<m2.length;j++){
            if (!m2[j]&&m1[j]){
                m2[j]=true;
                dfs(cur+1,m1,m2);
                m2[j]=false;
            }
        }
    }
    long rs2=0;
    public int countOrders2(int n) {
        boolean[] memo1=new boolean[n];
        boolean[] memo2=new boolean[n];
        dfs2(0,memo1,memo2);
        return (int)rs2%mod;
    }
    void dfs2(int count, boolean[] memo1,boolean[] memo2){
        if (count==2*memo1.length){
            rs2++;
        }
        for (int i=0;i<memo1.length;i++){
            if (!memo1[i]){
                memo1[i]=true;
                dfs2(count+1,memo1,memo2);
                memo1[i]=false;
            }
        }
        for (int i=0;i<memo1.length;i++){
            if (memo1[i]&&!memo2[i]){
                memo2[i]=true;
                dfs2(count+1,memo1,memo2);
                memo2[i]=false;
            }
        }
    }
}
