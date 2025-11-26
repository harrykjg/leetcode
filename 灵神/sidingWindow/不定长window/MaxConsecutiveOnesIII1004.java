package 灵神.sidingWindow.不定长window;

public class MaxConsecutiveOnesIII1004 {
    public static void main(String[] args) {
        int[] num={0,0,1,1,1,0,0};
        System.out.println(longestOnes(num,0));
    }
//容易漏k=0时，nums开头为0，则e无法前进
    public static int longestOnes(int[] nums, int k) {
        int b=0;
        int e=0;
        int rs=0;
        int cur=0;//用了几次了
        while (e<nums.length){
            int len=e-b;
            while (e<nums.length){
                if(nums[e]==1){
                    e++;
                    continue;
                }else{
                    if(cur<k){
                        cur++;
                        e++;
                    }else{
                        break;
                    }
                }
            }
            len=e-b;
            rs=Math.max(rs,len);
            //缩的时候要缩到第一个0那里
            while (b<e&&cur>=k){
                if (nums[b]==0){
                    cur--;
                    b++;//这里漏了
                    break;
                }else{
                    b++;
                }
            }
            if(cur>=k){//容易漏，k=0时并且数组开头是0，则导致e无法前进被卡
                e++;
                b++;
            }

        }
        return rs;
    }

}
