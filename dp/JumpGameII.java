package dp;

/**
 * Created by 502575560 on 9/10/17.
 */
public class JumpGameII {

    //九章第二轮,还是没写对,只用2个变量貌似写不出来,而且,for循环从1开始好像也写不出来
    //[3,4,1,1,2,2,1,1],看这个例子,首先curScope为0,意味着i超过0的话step就要++,开始i在0,没超curScope,所以是0step没毛病,同时发现新的scope是0+3=3,
    //然后i=1,超过了curScope所以step+1,现在走了一步了,即进入了一个新的scope,此事更新curScope,意味着在着curScope期间step不用++了,目测可知i在1,2,3
    //时都只需要1步,没毛病,且有i在1时已经更新furtherScope变成5,现在i到4了,超了3的scope,step++,进入了scope为5的范围,更新curScope为5,
    public int jump(int[] A) {
        if(A.length<=1){
            return 0;
        }
        int step=0;
        int furtherScope=A[0];//furtherScope开始为0或者A[0]其实都一样,关键是curScope不能是A[0]
        int curScope=0;//curScope应该理解为当前scope,
        for(int i=0;i<A.length;i++){
           if(i>curScope){
               step++;
               curScope=furtherScope;//更新scope,之前的循环中就应该更新了scope的值
           }
            furtherScope=Math.max(furtherScope,i+A[i]);//更新furtherscope在这里,就保证了如果能到达数组末尾的话肯定过是当前step+1
            if(furtherScope>=A.length-1){
                return step+1;
            }

        }
        return -1;
    }
}
