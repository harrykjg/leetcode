package 灵神.二分法.二分答案.求最小;

public class KokoEatingBananas875 {
    public static void main(String[] args) {
        int[] nums={3,6,7,11};
        System.out.println(minEatingSpeed(nums,8));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int b=Integer.MAX_VALUE;
        int e=Integer.MIN_VALUE;
        for(int i:piles){
            b=1;
            e=Math.max(i,e);
        }

        while (b+1<e){
            int m=e-(e-b)/2;
            boolean good=good(piles,m,h);
            if(good){
                e=m;
            }else{
                b=m;
            }
        }
        if(good(piles,b,h)){
            return b;
        }else {
            return e;
        }

    }
    static boolean good(int[] p,int speed,int hour){
        int cur=0;
        for(int i=0;i<p.length;i++){
            int pile=p[i];
            int mod=pile%speed;//取余
            if(pile>=speed){
                if(mod==0){
                    cur+=pile/speed;
                }else{
                    cur+=pile/speed+1;
                }
            }else{
                cur++;
            }
            if(cur>hour){
                return false;
            }
        }
        if (cur>hour){
            return false;
        }
        return true;
    }
}
