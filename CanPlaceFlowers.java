public class CanPlaceFlowers {
    //7/16/2021直接检查左右点是否为1就行了。还改了好几次，主要是edge case，注意【0】的情况
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        for (int i=0;i<flowerbed.length;i++){
            if (flowerbed[i]==0){
                if (i>0&&i+1<flowerbed.length&&flowerbed[i-1]==0&&flowerbed[i+1]==0){
                    n--;
                    flowerbed[i]=1;
                }else if (i==0&&i+1<flowerbed.length&&flowerbed[i+1]==0){
                    n--;
                    flowerbed[i]=1;
                }else if (i>0&&flowerbed[i-1]==0&&i==flowerbed.length-1){
                    n--;
                    flowerbed[i]=1;
                }else if (i==0&&flowerbed[i]==0&&i==flowerbed.length-1){//数组只有1位的情况
                    n--;
                }

            }
        }
        return n<=0;//注意小于0也行。
    }
}
