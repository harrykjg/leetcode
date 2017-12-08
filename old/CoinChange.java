import java.util.ArrayList;
import java.util.List;
//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/DynProg/money-change.html
//这题都是假设最小面值是1，而要找的钱是int的
public class CoinChange {
	
	public static void main(String[] args) {
		 CoinChange coinChange=new CoinChange();  
//	        int res=coinChange.findM(81, new int[]{1,5,7,10,11});
		 int res=coinChange.minChange(new int[]{1,5,7,10,11}, 17);
	        System.out.println(res);  
	}
	
	
	public int change(int[] coinValue,int totalValue){//这个是贪心算法，网上的  
        List<Integer> coins=new ArrayList<Integer>();  
        coins.add(0);  
        for(int i=1;i<=totalValue;i++){  
            int coin=nearestCoin(i,coinValue);  
            int coinNum=coins.get(i-coin)+1;  
            coins.add(coinNum);  
        }  
        return coins.get(totalValue);  
    }  
    /** 
     * 获取最接近找零钱数的硬币面值 
     */  
    private int nearestCoin(int value,int[] coinValues){  
        int res=0;  
        int nearest=Integer.MAX_VALUE;  
        for(int coinValue:coinValues){  
            if(coinValue<=value){  
                int distance=value-coinValue;  
                if(distance<nearest){  
                    nearest=distance;  
                    res=coinValue;  
                }  
            }  
        }  
        return res;  
    }  
    static int M(int j, int[] v)//这个是英文那个教程的，非动态规划方法,连gredy都不算吧？
    //他不是从最大那个面值去试的，是从最小的开始试，把所有结果存在mysol数组里，再从mysol里选出
    //最小的值作为结果
    {
       int[] sol, mySol;
       int   myFinalSol;
       int k;

       sol = new int[v.length];
       mySol = new int[v.length];

       /* ---------------------------
          Base cases
          --------------------------- */
       if ( j == 0 )
       {
           return(0);
       }
          
       /* ==================================================
          Initialize mySol[]
	  ================================================== */
       for ( k = 0; k < v.length; k++ )
          mySol[k] = -1;                   // -1 means: no solution

       /* --------------------------------------------------------
          Try every denomination k = 1,2,..,C for the last coin
          -------------------------------------------------------- */   
       for ( k = 0; k < v.length; k++ )
       {
          /* --------------------------------------------
             Check if we can use the k-th denomination
             -------------------------------------------- */
          if ( v[k] <= j )
          {
             /* ------------------------
                Divide step
                ------------------------ */
             sol[k] = M(j - v[k], v);  // Use coin v[k] as last coin

             mySol[k] = sol[k] + 1;    // Solution to make change for $j   
          }
       }

       /* --------------------------------------------------------
          Find the minimum for ALL mySol[...] values

	  Note: -1 means do NOT use !
          -------------------------------------------------------- */
       myFinalSol = -1;

       for ( k = 0; k < v.length; k++ )
       {
          if ( mySol[k] >= 0 /* Don't use -1 values */ )
          {
             if ( myFinalSol == -1 || mySol[k] < myFinalSol )
                myFinalSol = mySol[k];
          }
       }

       return(myFinalSol);   // Return best solution
   }
    
    static int findM(int Am, int v[])//动态规划,只用了一位数组，网上的
    {
       int[] dp;
       int[] temp, mySol;
       int j, k, min;
       dp = new int[Am+1];              // 用来存0到am块钱所用的最小数量

       temp = new int[v.length];  
       mySol = new int[v.length];

       dp[0] = 0;       
       for ( j = 1; j <= Am; j++ )//从第一块钱开始到最后
       {
          for ( k = 0; k < v.length; k++ )//注意这3个数组的相互利用，每一块钱都把mySol初始化了一次
          { mySol[k] = -1;   }            //然后试当前块钱数所能用的不同面额，存到mysol里，然后
                                          //在遍历这个mysol找到最小值存在dp里   
          for ( k = 0; k < v.length; k++ )//遍历每种面值，计算出当前块钱数每种面值所要用的数量
          {
             if ( v[k] <= j ) //如果该面值小于找钱数才能用上
             {
            	 temp[k] = dp[j - v[k]];     // temp就代表当前块钱数j减去当前面值后的块钱数所用
            	                            //的最小数量
                mySol[k] = temp[k] + 1;    // 因此这个temp值再加1就是当前块钱数j所用的最小块钱数    
             }                             //注意这都是指当前面值的，而这里每种面值都这样算了
                                           //存到mySol里去，因此后面还要再从mySol中选出最小
                                           //的块钱数作为j块钱的最优解
          }
          dp[j] = Integer.MAX_VALUE;
          for ( k = 0; k <mySol.length; k++ )//
          {
             if ( mySol[k] > 0 )
             {
                if (  mySol[k] < dp[j] )
                   dp[j] = mySol[k];   //mysol里存的是当前块钱数所能用的不同面额张数，找到最小的
                                       //存到dp里
             }
          }
       }

       return( dp[Am] ); 
   }



    public int minChange(int[] denom, int targetAmount) {//这个是动态规划,也是网上的,用的二维数组
       //它这个是一行一行赋值的，看不大懂
        int actualAmount;
        int m = denom.length+1;
        int n = targetAmount + 1;
        int inf = Integer.MAX_VALUE-1;

        int[][] table = new int[m][n];
        for (int j = 1; j < n; j++)
            table[0][j] = inf;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j - denom[i-1] >= 0)
                    actualAmount = table[i][j - denom[i-1]];
                else
                    actualAmount = inf;
                table[i][j] = Math.min(table[i-1][j], 1 + actualAmount);
            }
        }

        return table[m-1][n-1];

    }
    public int change2(int[] v, int target) {//这个是按我自己的想法填二维数组的

        int[][] dp = new int[v.length][target+1];

        for(int i=1;i<target+1;i++){//每列每列的填
        	for(int j=0;j<v.length;j++){
        		if(v[j]<=i&&j==0){//j等于0即第一行时，即用面值为1块钱时，肯定是一个一个递增的
        			dp[j][i]=dp[j][i-v[j]]+1;
        		}else if(v[j]<=i){//j>0时，则dp[i][j]的值应该是：用当前面值v[j]以及i-v[i]块钱
        			//的最优解，即dp[j][i-v[j]]+1，与不用当前面值，而用他当前列的上一行的值，这
        			//二者中小的那个
        			dp[j][i]=Math.min(dp[j][i-v[j]]+1,dp[j-1][i]);
        		}
        		else{//第一列的情况
        			dp[j][i]=dp[j-1][i];
        		}
        	}
        }
        return dp[v.length-1][target];
            

    }

}
