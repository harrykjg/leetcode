/**
 * Created by yufengzhu on 7/31/18.
 */
//array
public class FindtheCelebrity {
    public static void main(String[] args){
        FindtheCelebrity fc=new FindtheCelebrity();
       System.out.print( fc.findCelebrity(2));
    }
    //不会，这个是暴力法，n方，也过了，有n复杂度的方法
    //https://www.cnblogs.com/grandyang/p/5310649.html
    //https://segmentfault.com/a/1190000006001292
    //https://leetcode.com/problems/find-the-celebrity/discuss/71233/Java-solution-beats-100-10ms-run-time.
    public int findCelebrity(int n) {
        int rs=-1;
        for(int i=0;i<n;i++){//对于每个i，检查i认不认识j，如果认识的话说明i不是celerbrity
            int j=0;
            boolean good=true;
            for( ;j<n;j++){
                if (i!=j&&knows(i,j)){
                    good=false;
                }
            }
            if(good){//说明i谁都不认识
                rs=i;
                break;//题目说了只有一个celerbrity，找到一个谁都不认识的人就可以break了
            }
        }
        if(rs==-1){
            return -1;
        }
        for(int i=0;i<n;i++){
            if(i!=rs&&knows(rs,i)||!knows(i,rs)){//开始漏了!knows(i,rs)这个条件，即i还一定要认识rs才行，否则就没有celerbrity
                return -1;
            }
        }
        return rs;
    }
//n复杂度的方法
    public int findCelebrity2(int n) {
        int rs=0;
        for(int i=1;i<n;i++){
            if(knows(rs,i)){//如果rs认识i，说明rs肯定不是候选人，所以再试试i是不是候选人
                rs=i;
            }
        }
        //现在有了候选人rs，他不认识他所在位置之后的所有人，但是还要检查一遍看是不是所有人认识他和他不认识所有人
        for(int i=0;i<n;i++){
            if(i!=rs&&(knows(rs,i)||!knows(i,rs))){
                return -1;
            }
        }
        return rs;

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
//9/22/2018,大概记得，举个例子试试,就是写起来不太好写，而且验证容易漏一个条件
    public int findCelebrity3(int n) {
        int candidate=0;
        for(int i=1;i<n;i++){
            if(knows(candidate,i)){
                candidate=i;
            }
        }
        for(int i=0;i<n;i++){
            if(i==candidate){
                continue;
            }
            if(!knows(i,candidate)||knows(candidate,i)){//容易漏knows(candidate,i)这个条件
                return -1;
            }
        }
        return candidate;
    }
//7/16/2021,O(n)解法不好想，要记
    public int findCelebrity4(int n) {
        int can=0;
        for (int i=1;i<n;i++){
            if (knows(can,i)){
                can=i;
            }
        }
        for (int i=0;i<n;i++){
            if (i==can){
                continue;
            }
            if (!knows(i,can)||knows(can,i)){
                return -1;
            }
        }
        return can;

    }
}
