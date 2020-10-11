package contest;

import java.util.Arrays;

public class MaximumAraofaPieceofCakeAfterHorizontalandVerticalCuts {
    public static void main(String[] args){
        MaximumAraofaPieceofCakeAfterHorizontalandVerticalCuts ma=new MaximumAraofaPieceofCakeAfterHorizontalandVerticalCuts();
        ma.maxArea(5,4,new int[]{1,2,4},new int[]{1,3});

    }
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long max1=0;
        long max2=0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        for(int i=0;i<horizontalCuts.length;i++){
            if(i==0){
                max1=Math.max(max1,horizontalCuts[i]-0);
                continue;
            }
            max1=Math.max(max1,horizontalCuts[i]-horizontalCuts[i-1]);
        }
        max1=Math.max(h-horizontalCuts[horizontalCuts.length-1],max1);

        for(int i=1;i<verticalCuts.length;i++){
            max2=Math.max(max2,verticalCuts[i]-verticalCuts[i-1]);
        }
        max2=Math.max(max2,Math.max(verticalCuts[0]-0,w-verticalCuts[verticalCuts.length-1]));
        long rs=max1*max2;
        if(rs>=Integer.MAX_VALUE){
            return (int)(rs%(Math.pow(10,9)+7));
        }
        return (int)(max1*max2);
    }
}
