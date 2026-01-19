package 灵神.sidingWindow.不定长window;

public class MaxConsecutiveOnesIII1004 {
    public static void main(String[] args) {
        int[] num={1,1,1,0,0,0,1,1};
        System.out.println(longestOnes2(num,0));
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


    //1/14/2026,没想对怎么缩，比如k=2时，111000和001110现在遇到第三个0了，应该是缩到第一个0之后就行了.还是k=0漏了
    public static int longestOnes2(int[] nums, int k) {
        int b=0;
        int e=0;
        int rs=0;
        int zeros=0;
        while (e<nums.length){
            if(nums[e]==1){
                e++;
                int len=e-b;
                rs=Math.max(rs,len);
                continue;
            }
            if(zeros<k){
                zeros++;
                e++;
                int len=e-b;
                rs=Math.max(rs,len);
                continue;
            }
            if(k==0){
                e++;
                b=e;
                continue;
            }
            if(k==zeros){
                while (b<e&&nums[b]==1){
                    b++;
                }
                if (b<e&&nums[b]==0){
                    b++;
                }
                zeros--;

            }

        }
        return rs;
    }
}
