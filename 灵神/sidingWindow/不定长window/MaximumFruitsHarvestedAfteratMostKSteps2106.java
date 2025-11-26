package 灵神.sidingWindow.不定长window;

public class MaximumFruitsHarvestedAfteratMostKSteps2106 {
    public static void main(String[] args) {
        int[][] nums={{5,8},{7,7},{8,7},{15,5},{18,8},{19,3},{25,4}};
        System.out.println(maxTotalFruits(nums,6,19));
    }
//很难写
    //https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/solutions/2254860/hua-dong-chuang-kou-jian-ji-xie-fa-pytho-1c2d/
    //思路就是先找出最左的起点b，然后设int e为runner，从最左开始往右扫。固定了左边的时候不是立马就能知道右边，因为数组的树不是连续的，因此还得扫才知道。
    //往右扫的时候就是判断是否能到，并且记录果实，然后开始缩b，缩b的条件是把b缩到能符合把e加进来的条件。
    //https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps/editorial/ 他这个和灵神的写的不一样，不用找lower bound直接从0开始
    public static int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int rs=0;
        int b=findbegin(fruits,startPos-k);//这个还得用二分法找。
        int e=b;

        int cur=0;
        //这个e的位置就代表这段window的最右边
        while (b<=e&&e<fruits.length&&fruits[e][0]<=startPos+k){//b<=e这个条件也容易漏
            //判断e是否能加进来
            while (e<fruits.length&&fruits[e][0]<=startPos+k){
                if(fruits[e][0]<=startPos&&startPos-fruits[e][0]<=k){
                    cur+=fruits[e][1];
                    e++;
                }else {
                    //就看先走左再左右，和先走右再走左，如果有一个行就行
                    int leftright=startPos-fruits[b][0]+fruits[e][0]-fruits[b][0]; //这里不好想，开始想成是(startPos-fruits[b][0])*2+fruits[e][0]-fruits[b][0]是错的因为right现在是否在starPos的左边还是右边还不知道
                    int rightleft=  fruits[e][0]-startPos+ fruits[e][0]-fruits[b][0];
                    if(Math.abs(leftright)>k&&Math.abs(rightleft)>k){//不能加进来，不加绝对值不行，会有负数存在比如{{0,100}}，startPos=1000，k=10，即开始位置太右则rightleft为负数，则不能进入这个break
                        break;
                    }else{
                        cur+=fruits[e][1];
                        e++;
                    }
                }
            }
            rs=Math.max(rs,cur);
            //现在开始缩b，右分两种情况，1：b<startpos则要缩。2：b>startpos则不用管
            if(fruits[b][0]>=startPos){
                continue;
            }else{
                cur-=fruits[b][1];//直接b++，等下一个循环再判断e能否加入
                b++;
            }

        }

        return rs;
    }
    static int findbegin(int[][] fruit,int target){
        if(fruit.length==1){
            return 0;
        }
        int b=0;
        int e=fruit.length;
        //找的就是>=startPos的第一个
        while (b<e-1){
            int mid=b+(e-b)/2;
            if(fruit[mid][0]<target){
                b=mid;
            }else {
                e=mid;
            }
        }
        if (fruit[b][0]>=target){//这里容易错
            return b;
        }else{
            return e;
        }
    }
}
