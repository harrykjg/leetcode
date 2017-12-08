import java.util.ArrayList;
import java.util.List;
//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/DynProg/money-change.html
//���ⶼ�Ǽ�����С��ֵ��1����Ҫ�ҵ�Ǯ��int��
public class CoinChange {
	
	public static void main(String[] args) {
		 CoinChange coinChange=new CoinChange();  
//	        int res=coinChange.findM(81, new int[]{1,5,7,10,11});
		 int res=coinChange.minChange(new int[]{1,5,7,10,11}, 17);
	        System.out.println(res);  
	}
	
	
	public int change(int[] coinValue,int totalValue){//�����̰���㷨�����ϵ�  
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
     * ��ȡ��ӽ�����Ǯ����Ӳ����ֵ 
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
    static int M(int j, int[] v)//�����Ӣ���Ǹ��̵̳ģ��Ƕ�̬�滮����,��gredy������ɣ�
    //�����Ǵ�����Ǹ���ֵȥ�Եģ��Ǵ���С�Ŀ�ʼ�ԣ������н������mysol������ٴ�mysol��ѡ��
    //��С��ֵ��Ϊ���
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
    
    static int findM(int Am, int v[])//��̬�滮,ֻ����һλ���飬���ϵ�
    {
       int[] dp;
       int[] temp, mySol;
       int j, k, min;
       dp = new int[Am+1];              // ������0��am��Ǯ���õ���С����

       temp = new int[v.length];  
       mySol = new int[v.length];

       dp[0] = 0;       
       for ( j = 1; j <= Am; j++ )//�ӵ�һ��Ǯ��ʼ�����
       {
          for ( k = 0; k < v.length; k++ )//ע����3��������໥���ã�ÿһ��Ǯ����mySol��ʼ����һ��
          { mySol[k] = -1;   }            //Ȼ���Ե�ǰ��Ǯ�������õĲ�ͬ���浽mysol�Ȼ��
                                          //�ڱ������mysol�ҵ���Сֵ����dp��   
          for ( k = 0; k < v.length; k++ )//����ÿ����ֵ���������ǰ��Ǯ��ÿ����ֵ��Ҫ�õ�����
          {
             if ( v[k] <= j ) //�������ֵС����Ǯ����������
             {
            	 temp[k] = dp[j - v[k]];     // temp�ʹ���ǰ��Ǯ��j��ȥ��ǰ��ֵ��Ŀ�Ǯ������
            	                            //����С����
                mySol[k] = temp[k] + 1;    // ������tempֵ�ټ�1���ǵ�ǰ��Ǯ��j���õ���С��Ǯ��    
             }                             //ע���ⶼ��ָ��ǰ��ֵ�ģ�������ÿ����ֵ����������
                                           //�浽mySol��ȥ����˺��滹Ҫ�ٴ�mySol��ѡ����С
                                           //�Ŀ�Ǯ����Ϊj��Ǯ�����Ž�
          }
          dp[j] = Integer.MAX_VALUE;
          for ( k = 0; k <mySol.length; k++ )//
          {
             if ( mySol[k] > 0 )
             {
                if (  mySol[k] < dp[j] )
                   dp[j] = mySol[k];   //mysol�����ǵ�ǰ��Ǯ�������õĲ�ͬ����������ҵ���С��
                                       //�浽dp��
             }
          }
       }

       return( dp[Am] ); 
   }



    public int minChange(int[] denom, int targetAmount) {//����Ƕ�̬�滮,Ҳ�����ϵ�,�õĶ�ά����
       //�������һ��һ�и�ֵ�ģ�������
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
    public int change2(int[] v, int target) {//����ǰ����Լ����뷨���ά�����

        int[][] dp = new int[v.length][target+1];

        for(int i=1;i<target+1;i++){//ÿ��ÿ�е���
        	for(int j=0;j<v.length;j++){
        		if(v[j]<=i&&j==0){//j����0����һ��ʱ��������ֵΪ1��Ǯʱ���϶���һ��һ��������
        			dp[j][i]=dp[j][i-v[j]]+1;
        		}else if(v[j]<=i){//j>0ʱ����dp[i][j]��ֵӦ���ǣ��õ�ǰ��ֵv[j]�Լ�i-v[i]��Ǯ
        			//�����Ž⣬��dp[j][i-v[j]]+1���벻�õ�ǰ��ֵ����������ǰ�е���һ�е�ֵ����
        			//������С���Ǹ�
        			dp[j][i]=Math.min(dp[j][i-v[j]]+1,dp[j-1][i]);
        		}
        		else{//��һ�е����
        			dp[j][i]=dp[j-1][i];
        		}
        	}
        }
        return dp[v.length-1][target];
            

    }

}
