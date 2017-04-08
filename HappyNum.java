

import java.util.HashSet;

/**
 * Created by 502575560 on 6/25/16.
 */
public class HappyNum {

    public static void main(String[] args){
        System.out.print(isHappy(19));
    }

    public static boolean isHappy(int n) {
        if(n==0){
            return false;
        }
        HashSet<Integer> set=new HashSet<>();

        int nn=n;

        while(true){

            if(set.contains(nn)){
                return false;
            }else{
                set.add(nn);
            }
            int cur=0;
            while(nn!=0){
                cur+=Math.pow(nn%10,2);
                nn/=10;
            }
            if(cur==1){
                return true;
            }
            nn=cur;

        }

    }
}
