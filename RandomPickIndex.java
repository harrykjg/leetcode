import java.util.Random;

/**
 * Created by 502575560 on 6/2/17.
 */
public class RandomPickIndex {
    int[] a;
    Random ran=new Random();
    public RandomPickIndex(int[] nums) {
        a=nums;
    }

    public int pick(int target) {
        int count=0;
        int rs=0;
        for(int i=0;i<a.length;i++){
            if(a[i]==target){
                count++;
                int r=ran.nextInt(count);
                if(r==0){
                    rs=i;
                }
            }
        }
        return rs;
    }

}
