public class RectangleOverlap {
    //还想不出怎么做，是挺麻烦的
    //https://leetcode.com/problems/rectangle-overlap/discuss/161497/Java-One-Line-with-explanation
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if(rec1[2]<=rec2[0]){
            return false;//rec1在左边
        }
        if(rec1[0]>=rec2[2]){
            return false;//rec1在右边
        }
        if(rec1[3]<=rec2[1]){//rec1在下面
            return false;
        }
        if(rec1[1]>=rec2[3]){//rec1在上面
            return false;
        }
        return true;

    }
}
