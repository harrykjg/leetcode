package SomeInterviews.roblox;

public class SubarraywithMostTargetElement {

    static void main() {
        int[] a={1, 2, 1, 2, 1, 2};
        System.out.println(SubarraywithMostTargetElement.findBestStartingIndex(a,0,2));
    }

    //就是sliding window
    public static int findBestStartingIndex(int[] nums, int targetIndex, int windowSize) {
        int max=0;
        int rs=0;
        int e=0;
        int b=0;
        int count=0;
        int target=nums[targetIndex];
        while (e<nums.length){
            if(nums[e]==target){
                count++;
            }


            if(e-b+1==windowSize){
                if(count>max){
                    max=count;
                    rs=b;
                }

                if(nums[b]==target){
                    count--;
                }
                b++;
            }
            e++;
        }
        return rs;
    }
}
