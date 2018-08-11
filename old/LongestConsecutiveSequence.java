import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
//array //increasing subsequence/subarray
//http://blog.csdn.net/linhuanmars/article/details/22964467
//http://jixiangsanbao.wordpress.com/2014/06/19/longest-consecutive-sequence/ 吉祥的好理解

//如果允许 O(n log n) 的复杂度，那么可以先排序，可是本题要求 O(n)。由于序列里的元素是
//无序的，又要求 O(n)，首先要想到用哈希表。用一个哈希表 unordered_map<int, bool> used 记录
//每个元素是否使用，对每个元素，以该元素为中心，往左右扩张，直到不连续为止，记录下最长的长度。
//这是我自己写的，过了，但是是nlogn的
public class LongestConsecutiveSequence {
	
	 public int longestConsecutive(int[] num) {
	        
		 if(num.length==0){
			 return 0;
		 }
		 Arrays.sort(num);
		 int count=1;
		 int max=1;
		 for(int i=0;i<num.length-1;i++){
			 if(num[i]+1==num[i+1]){
				 count++;
				 if(max<count){
					 max=count;
				 }
			 }
			 else if(num[i]==num[i+1]){
				 continue;
			 }
			 else{
				 count=1;
			 }
		 }
		 return max;
	    }
//第三轮还是不会
//吉祥的代码
 public int longestConsecutive2(int[] num) {
        //add all elements into hashset for O(1) retrieval
        Set<Integer> set = new HashSet<Integer>();
        for(int n : num){
            set.add(n);
        }
        int maxLen = 0;
        for(int n : num){
            if(set.contains(n)){
                set.remove(n);
                int count = 1;
                int left = n-1, right = n+1;
                //keep searching for left neighbor
                while(set.contains(left)){
                    set.remove(left);
                    count++;
                    left--;
                }
                //keep searching for right neighbor
                while(set.contains(right)){
                    set.remove(right);
                    count++;
                    right++;
                }
                //update the maxLen if neccessary
                maxLen = Math.max(maxLen, count);
            }
        }
        return maxLen;    
    }
}
