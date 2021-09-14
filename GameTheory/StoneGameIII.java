package GameTheory;

public class StoneGameIII {
    public static void main(String[] args){
        StoneGameIII sg=new StoneGameIII();
        System.out.println(sg.stoneGameIII(new int[]{1,2,3,7}));
    }
    //9/6/2021 居然自己搞了几次ac了，就是按以前stonegame 或者coinsinalineii的思路
    public String stoneGameIII(int[] stoneValue) {
        int[] dp=new int[stoneValue.length];
        if (stoneValue.length==1){
            int sum=stoneValue[0];
            if (sum>0){
                return "Alice";
            }
            if (sum<0){
                return "Bob";
            }
            return "Tie";
        }
        if (stoneValue.length==2){
            if (stoneValue[0]+stoneValue[1]>0){
                return "Alice";
            }
            if (stoneValue[0]>stoneValue[1]){
                return "Alice";
            }
            if (stoneValue[0]<stoneValue[1]){
                return "Bob";
            }
            return "Tie";
        }
        int n=dp.length;
        dp[n-1]=stoneValue[n-1];
        dp[n-2]=Math.max(stoneValue[n-2]-dp[n-1],stoneValue[n-2]+stoneValue[n-1]);
        dp[n-3]=Math.max(stoneValue[n-3]-dp[n-2],Math.max(stoneValue[n-3]+stoneValue[n-2]-dp[n-1],stoneValue[n-1]+stoneValue[n-2]+stoneValue[n-3]));
        for (int i=n-4;i>=0;i--){
            dp[i]=Math.max(stoneValue[i]-dp[i+1],Math.max(stoneValue[i]+stoneValue[i+1]-dp[i+2],stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]-dp[i+3]));
        }
        if (dp[0]>0){
            return "Alice";
        }else if (dp[0]<0){
            return "Bob";
        }
        return "Tie";
    }
}
