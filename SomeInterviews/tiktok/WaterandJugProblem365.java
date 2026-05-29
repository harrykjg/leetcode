package SomeInterviews.tiktok;

public class WaterandJugProblem365 {
    //不会，直接看答案
    //https://www.cnblogs.com/grandyang/p/5628836.html
    public boolean canMeasureWater(int x, int y, int z) {
        //limit brought by the statement that water is finallly in one or both buckets
        if(x + y < z) return false;
        //case x or y is zero
        if( x == z || y == z || x + y == z ) return true;

        //get GCD, then we can use the property of Bézout's identity
        return z%GCD(x, y) == 0;
    }

    public int GCD(int a, int b){
        while(a%b != 0 ){
            int temp = a%b;
            a = b;
            b = temp;
        }
        return b;
    }
//Follow up 是找出最短的路径，关键是状态转移，有6个：要设一个state class， bfs的queue里就是装这个state
    /*
    Fill X：(x, b)
    Fill Y：(a, y)
    Empty X：(0, b)
    Empty Y：(a, 0)
    Pour X -> Y：倒到 Y 满或 X 空
    t = min(a, y - b) → (a - t, b + t)
    Pour Y -> X：
    t = min(b, x - a) → (a + t, b - t)
     */
}
