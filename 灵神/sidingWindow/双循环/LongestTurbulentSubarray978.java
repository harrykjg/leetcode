package 灵神.sidingWindow.双循环;

public class LongestTurbulentSubarray978 {

    public static void main(String[] args) {

    }
    //不会，代码参考https://leetcode.cn/problems/longest-turbulent-subarray/solutions/596355/zui-chang-tuan-liu-zi-shu-zu-by-leetcode-t4d8/
    // https://leetcode.com/problems/longest-turbulent-subarray/editorial/ 这个答案简单，自己想不太好写
    public int maxTurbulenceSize(int[] arr) {
        int rs=1;
        int pre=0;
        if(arr.length==2){
            if(arr[1]!=arr[0]){
                return 2;
            }
        }
        for(int i=1;i<arr.length-1;i++){
            if(pre==i){
                if(arr[i]==arr[i+1]){
                    pre++;
                }
                int len=i+1-pre+1;
                rs=Math.max(rs,len);
            }else{
                if(arr[i-1]<arr[i]&&arr[i]>arr[i+1]){
                    int len=i+1-pre+1;
                    rs=Math.max(rs,len);
                }else if(arr[i-1]>arr[i]&&arr[i]<arr[i+1]){
                    int len=i+1-pre+1;
                    rs=Math.max(rs,len);
                }else{
                    pre=i;
                    i--;//这会使下个while循环进入pre==i的情况，但是用for还是不太好，看参考代码的用while
                }
            }

        }
        return rs;
    }
}
