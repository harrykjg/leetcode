package contest;

public class ThekthFactorofn {
    public int kthFactor(int n, int k) {
        if(k==1){
            return 1;
        }
        int count=0;
        int div=1;
        while (count<k&&div<=n){
            if(n%div==0){
                count++;
            }

            if(count==k){
                return div;
            }
            div++;
        }
        return -1;
    }
}
