package linkedin;

public class FindtheCelebrity277 {
    static void main() {

    }
    //不会好的方法了,真是巧妙
    public int findCelebrity(int n) {
        int cand=0;
        //注意这里调用knows方法看cand是否know i，认识的话说明你肯定不是名人，i就是下一个候选人，那么继续这个for loop会验证cand是不是认识
        //剩下的所有人，如果有认识的就disqualify cand，选下一个candidate，现在这个最后的candidate继续loop完了，发现剩下的人他都不认识.
        //那为啥前面不会是别人是名人呢？因为前面要是名人的话那么candidate就不会边成别人了。
        //他还是合法。那么现在就要继续验证他是不是不认识前面所有的人。并且所与人都认识他。
        for (int i=1;i<n;i++){
            if(knows(cand,i)){
                cand=i;
            }
        }
        for (int i=0;i<n;i++){
            if(i==cand){
                continue;
            }
            if(!knows(i,cand)||knows(cand,i)){
                return -1;
            }
        }
        return cand;
    }
    boolean knows(int a, int b){
        if(a==0&&b==1){
            return false;
        }
        if(a==1&&b==0){
            return false;
        }
        return true;
    }
}
