package SomeInterviews.purestorage;

public class SortColors75 {
    static void main() {
        SortColors75 sc=new SortColors75();
        int[] a=new int[]{2,0,2,1,1,0};
        sc.sortColors(a);
        for (int i:a){
            System.out.println(i);
        }
    }
    //还是改了好几次才ac
    public void sortColors(int[] nums) {
        int b=0;
        int e=nums.length-1;
        int i=0;
        while (i<=e){
            if(nums[i]==0){//这里i什么时候推进是不对称的，由于开始是从左到右，因此遇到0可以i++，否则001这样就死循环了，但是遇到2时，就不i++，因为后面换过来的
                swap(nums,b,i);  //的数还要检查。
                b++;
                i++;
            }else if(nums[i]==2){
                swap(nums,e,i);
                e--;
            }else{
                i++;
            }

        }
    }
    void swap(int[] nums,int a,int b){
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }
    //面筋说如果要找swap最少得次数的话，目前只想这个n方的方法
    public void sortColors2(int[] nums) {
        int count0=0;
        int count1=0;
        int rs=0;
        for (int i:nums){
            if(i==0){count0++;}
            else if(i==1){
                count1++;
            }
        }
        for (int i=0;i<nums.length;i++){
            int expect=0;
            if(i<count0){expect=0;}
            else if(i<count0+count1){
                expect=1;
            }else{
                expect=2;
            }
            if(nums[i]==expect){
                continue;
            }
            int j=i+1;
            while (j<nums.length){
                int expect2=0;
                if(j<count0){
                    expect2=0;
                }else if(j<count0+count1){
                    expect2=1;
                }else{
                    expect2=2;
                }
                if (nums[j]==expect&&nums[j]!=expect2){//这句的意思是找到j，这nums[j]就是i这个位置要的值，并且nums[j]也再错的位置上
                    swap(nums,i,j);
                    rs++;
                    break;
                }else {
                    j++;
                }
            }
        }
    }

}
