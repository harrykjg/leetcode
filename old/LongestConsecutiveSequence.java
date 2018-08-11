import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
//array //increasing subsequence/subarray
//http://blog.csdn.net/linhuanmars/article/details/22964467
//http://jixiangsanbao.wordpress.com/2014/06/19/longest-consecutive-sequence/ ����ĺ����

//������� O(n log n) �ĸ��Ӷȣ���ô���������򣬿��Ǳ���Ҫ�� O(n)�������������Ԫ����
//����ģ���Ҫ�� O(n)������Ҫ�뵽�ù�ϣ����һ����ϣ�� unordered_map<int, bool> used ��¼
//ÿ��Ԫ���Ƿ�ʹ�ã���ÿ��Ԫ�أ��Ը�Ԫ��Ϊ���ģ����������ţ�ֱ��������Ϊֹ����¼����ĳ��ȡ�
//�������Լ�д�ģ����ˣ�������nlogn��
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
//�����ֻ��ǲ���
//����Ĵ���
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
