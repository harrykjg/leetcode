public class KthMissingPositiveNumber {
    //8/17/2021 基本一次过
    public int findKthPositive(int[] arr, int k) {
        int rs=1;
        int i=0;
        while (k>0&&i<arr.length){//就是初始化rs=1边遍历数组看是否存在，是的话就即没有miss，rs++并且看下一位，不是的话说明miss了，那么rs++看下一位是否miss
            if (arr[i]==rs){    //同时k--说明搞定一位miss的数了，数组完了还有miss的话就继续走几步
                rs++;
                i++;
            }else{
                rs++;
                k--;
            }
        }
        if (k==0){
            return rs-1;//刚好是rs-1，开始写的rs就错了
        }
        return rs+k-1;
    }
//8/23/2021 发现和Missing Element in Sorted Array很像，也可以用二分法做.写法类似但是不同，也不好写！missing=arr[m]-1-(m-0)，注意不是（m-b）因为要取第一个位置而不是b
//https://leetcode.com/problems/kth-missing-positive-number/discuss/780201/Java-O(lgN)-binary-Search
    public int findKthPositive2(int[] arr, int k) {//别人的代码，不好理解
        int l = 0, r = arr.length;//他们这都是写length而不是length-1，减一就错了
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] - (mid + 1) >= k) r = mid;  //missed more or equal than k numbers, left side;
            else l = mid + 1;   // missed less than k numbers, must be in the right side;
        }
        return l + k;//他这里不是return arr【l】，很神奇
        /*
        Explanation on why l + k:
We use binary search to find the smallest index, l, such that there are more than k missing numbers in [0, A[l]]. The actual number of
missing numbers in [0, A[l-1]] is A[l-1] - (l - 1) - 1 = A[l-1] - l. Counting from A[l-1], The k-th missing number is therefore
 (A[l-1] + k - (A[l-1] - l) = l + k这个解释就说得通了。A[l-1] + k 的意义就是啥呢
         */
    }

    //答案的代码。注意他这right是length-1，while条件变成是有等号的，然后left和right都要+-1
    public int findKthPositive3(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int pivot = left + (right - left) / 2;
            // If number of positive integers
            // which are missing before arr[pivot]
            // is less than k -->
            // continue to search on the right.
            if (arr[pivot] - pivot - 1 < k) {
                left = pivot + 1;
                // Otherwise, go left.
            } else {
                right = pivot - 1;
            }
        }

        // At the end of the loop, left = right + 1,
        // and the kth missing is in-between arr[right] and arr[left].
        // The number of integers missing before arr[right] is
        // arr[right] - right - 1 -->
        // the number to return is
        // arr[right] + k - (arr[right] - right - 1) = k + left  注意他这个while循环是left <= right，因此退出的时候left=right+1。arr[right]+k是啥意义呢
        return left + k;
    }
//10/9/2021 n的方法，比第一次的方法应该快点，那里有gap的话是一个一个加上去的
    public int findKthPositive4(int[] arr, int k) {
        int low=1;
        for(int i=0;i<arr.length;i++){
            if(low<arr[i]){
                int gap=arr[i]-low;
                if(gap>=k){
                    return low+k-1;
                }else{
                    low=arr[i]+1;
                    k-=gap;
                }
            }else{
                low++;
            }
        }
        return low+k-1;
    }
}
