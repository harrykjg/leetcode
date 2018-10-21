package GameTheory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yufengzhu on 10/8/18.
 */
public class PredicttheWinner {
    public static void main(String[]args){
        PredicttheWinner pw=new PredicttheWinner();
       System.out.print( pw.PredictTheWinner(new int[]{1,1,1}));
    }

    //照着can i win想的brute force的方法,不好
    public boolean PredictTheWinner(int[] nums) {
        if(nums.length<=1){
            return true;
        }

        return dfs(0,nums.length-1,0,0,0,nums);
    }
    boolean dfs(int left,int right,int one,int two,int flag,int[] nums){
       if(right==left){//这里还要留着最后一个元素才好判断，否则还不好写！
           if(flag==0){
               if(one+nums[left]>=two){
                   return true;
               }
               return false;
           }else{
               if(two+nums[left]>one){//想清楚这个return true／false的意义是当前玩家能不能赢，而不是说判断玩家1能不能赢
                   return true;
               }
               return false;
           }
       }
        if(flag==0){
            int temp1=nums[left]+one;
            if(!dfs(left+1,right,temp1,two,1,nums)){
                return true;
            }
            temp1=nums[right]+one;
            if(!dfs(left,right-1,temp1,two,1,nums)){
                return true;
            }
            return false;
        }else{
            int temp1=nums[left]+two;
            if(!dfs(left+1,right,one,temp1,0,nums)){
                return true;
            }
            temp1=nums[right]+two;
            if(!dfs(left,right-1,one,temp1,0,nums)){
                return true;
            }
            return false;
        }

    }
}
