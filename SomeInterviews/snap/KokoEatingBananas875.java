package SomeInterviews.snap;

public class KokoEatingBananas875 {
    public int minEatingSpeed(int[] piles, int h) {
        int max=0;
        for (int i:piles){
            max=Math.max(max,i);
        }
        int b=1;
        int e=max;
        while (b+1<e){
            int m=e-(e-b)/2;
            if(good(piles,h,m)){
                e=m;
            }else{
                b=m;
            }
        }
        if(good(piles,h,b)){
            return b;
        }
        return e;
    }
    boolean good(int[] piles,int h,int m){
        int count=0;
        for (int i=0;i<piles.length;i++){
            if(piles[i]<=m){
                count++;
            }else {
                count+=piles[i]/m;
                if(piles[i]%m!=0){
                    count++;
                }
            }
            if(count>h){
                return false;
            }
        }
        return true;
    }
}
