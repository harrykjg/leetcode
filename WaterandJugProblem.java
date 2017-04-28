/**
 * Created by 502575560 on 4/27/17.
 */
public class WaterandJugProblem {
    //自己想不出
    //http://www.cnblogs.com/grandyang/p/5628836.html
    //http://www.cnblogs.com/godlei/p/5614992.html
    //http://bookshadow.com/weblog/2016/06/24/leetcode-water-and-jug-problem/
    public boolean canMeasureWater(int x, int y, int z) {
        if(x+y<z){
            return false;
        }
        if(z==0){
            return true;
        }
        if(x==0){
            return y==z;
        }
        if(y==0){
            return x==z;
        }
        //自己想的求公约数
        int temp=2;
        int gcd=1;
        int big=Math.max(x,y);
        while (temp<big){
            if(x%temp==0&&y%temp==0){
                gcd=Math.max(gcd,temp);
            }
            temp++;
        }
        return z%gcd==0;

    }
}
