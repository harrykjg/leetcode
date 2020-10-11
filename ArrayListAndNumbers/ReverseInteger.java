package ArrayListAndNumbers;

public class ReverseInteger {
//04/20/2020,写的一般般
    public int reverse(int x) {
        int copy=Math.abs(x);
        int rs=0;
        while (copy>0){
            int tail=copy%10;
            copy/=10;
            if(rs>Integer.MAX_VALUE/10){//这里容易漏
                return 0;
            }
            rs=rs*10+tail;
        }
        if(x<0){
            return -rs;
        }
        return rs;
    }
}
