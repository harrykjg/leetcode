package 灵神.sidingWindow.不定长window;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumWhiteTilesCoveredbyaCarpet2271 {
    public static void main(String[] args) {
        int[][] nums={{1,5},{10,11},{12,18},{20,25},{30,32}};
        System.out.println(maximumWhiteTiles(nums,10));
    }

    //应该就是把carpet 试着从每一个tiles【i】的第一个tile开始试他能cover多长，并不需要从tiles【i】每一个tile开始，因为你这个tiles[i]少cover了一个，
    // 后面的tiles[i+1]最多也就给你加回一个tile，所以没必要。还不是很好想，看他的
    //https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/solutions/2038534/sliding-window/
    //代码参考https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/solutions/2038199/java-sorting-sliding-window-o-nlogn/ 下面ezpzm9的评论
    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int rs=0;
        int b=0;
        int e=0;
        int cur=0;
        while (b<=e&&e<tiles.length){
            int reach=tiles[b][0]+carpetLen-1;//画图理解为啥要-1.假如第一款砖是1，则1+10=11，但实际只能cover到10
            //我觉得只要reach大于这个砖的第一个，就是cover了这个砖的第一个就行。他们的答案都是要完整cover的才算，完了在判断partial的。我觉得一块应该就行了但这还真不行
            //再看[1,5],[10,11],[12,18]，k=10这个例子，开始carpet放在1，则cover第一块大砖（5块），然后e++，也cover10这个位置上的小砖，既cur=6了。此时e++，e=3
            //停止while循环，然后b++,把5块砖减去。现在cur=1.到外层while循环，则reach变成19.现在问题来了，当我e=3时，cur+=Math.min(reach,tiles[e][1])-tiles[e][0]+1这个
            //就是1+（18-12）+1=8，而实际上应该等于9.原因就是上一次循环第10到12块砖的时候，这个砖应该判断为 partial cover，不应该加到cur上，而是单独判断一下加上partial和rs比。
            while (e<tiles.length&&reach>=tiles[e][1]){
                cur+=tiles[e][1]-tiles[e][0]+1;
                e++;
            }
            if(e<tiles.length&&reach>=tiles[e][0]){
                rs=Math.max(cur+reach-tiles[e][0]+1,rs);
            }

            rs=Math.max(cur,rs);

            cur-=tiles[b][1]-tiles[b][0]+1;
            b++;
        }

        return rs;

    }
}
