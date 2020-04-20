package dp;

public class SoupServings {
    //写不对，要练
//https://leetcode.com/problems/soup-servings/discuss/121740/Straightforward-Java-Recursion-with-Memorization
    //https://leetcode.com/problems/soup-servings/discuss/123451/JavaTop-down-search-with-hashMap-memorized
    public static void main(String[] args){
        SoupServings soupServings=new SoupServings();
        System.out.println("Xxx"+(double)50/100);
        System.out.println(soupServings.soupServings(1));
    }


    public double soupServings(int N) {
        if (N > 5000) {  // 这个看答案的没研究为什么
            return 1.0;
        }

        Double[][] memo=new Double[N+1][N+1];

        return dfs( N, N,memo);


    }


    double dfs(int a, int b,Double[][] memo){
        if(a>=0&&b>=0&&memo[a][b]!=null){
            return memo[a][b];
        }
        if(a<=0&&b>0){
            return 1.0;
        }
        if(a<=0&&b<=0){
            return 0.5;
        }
        if (a>0&&b<=0){
            return 0;
        }
        double p=0;
        p+=dfs(a-100,b,memo);
        p+=dfs(a-75,b-25,memo);
        p+=dfs(a-50,b-50,memo);
        p+=dfs(a-25,b-75,memo);

        p/=4;
        memo[a][b]=p;
        return memo[a][b];




    }
}
