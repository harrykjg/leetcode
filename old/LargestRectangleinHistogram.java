import java.util.LinkedList;

public class LargestRectangleinHistogram {
//http://blog.csdn.net/doc_sgl/article/details/11805519  这个讲得好	

//http://www.cnblogs.com/avril/archive/2013/08/24/3278873.html	
	

	//我这个方法是对的，但是会超时
	public static void main(String[] args) {
		LargestRectangleinHistogram lr=new LargestRectangleinHistogram();
		int[] h={4,2};
		System.out.println(lr.largestRectangleArea2(h));
		
	}
	
     int max=0;
	public int largestRectangleArea(int[] height) {//思路就是先看第一个bar，然后从他往后扫1，2,3。。
		//位，看的出的面积是否大于max，然后看第二个bar，从他往后扫1,2,3位。。。
		if(height.length==0){
			return 0;
		}
		for(int i=0;i<height.length;i++){
				cal(height,i,i,height[i],height[i]);
			
		}
		return max;
	}
	
	public void cal(int[] height, int begin, int end, int cursum, int low){
		if(begin>=height.length||end>=height.length){
			return;
		}
		if(height[end]<low){
			low=height[end];
		}
		cursum=Math.max(cursum, (end-begin+1)*low);//就是当前最大面积和：(end-begin+1)*low，height[begin]
		//height[end]这3个数分别比较取最大值
		cursum=Math.max(cursum, height[begin]);
		cursum=Math.max(cursum, height[end]);
		if(cursum>max){
			max=cursum;
		}
		cal(height,begin,end+1,cursum,low);
	}
	int mmax=0;
	
	public int largestRectangleArea2(int[] height) {//总的来说是很神奇的，规律自己找的话得找好久
		if(height.length==0){
			return 0;
		}
		LinkedList<Integer> st=new LinkedList<Integer>();
		for(int i=0;i<height.length;i++){
			if(st.isEmpty()||height[i]>=height[st.peek()]){
				st.push(i);
			}else{
				while(!st.isEmpty()&&height[i]<height[st.peek()]){
					int t=height[st.pop()];
					int a=t*(st.isEmpty()?i:(i-st.peek()-1));//由于是他这种遇到比前一个bar矮就
					//不把当前bar加进去的原因，如果stack是空的话那么前一个bar的左边肯定
					//没有比他矮的bar，所以面积是height[st.pop()]乘以i（即i左边全都是不小于他的bar），
					//如果stack不是空的话，代表前面肯定有比他小的bar，所以面积只能是
					//height[st.pop()]*(i-st.peek()-1)（即前一个bar到当前i-1的宽度）
					mmax=Math.max(mmax, a);
				}
				st.push(i);
			}
		}
		while(!st.isEmpty()){//因为把所有的bar之后想象成有一个0的bar（即这的bar全都大于等于0）,
			//所以扫完bar之后，stack里还剩bar的话，就要计算
			int t=height[st.pop()];//这里的height.length其实就相当于上面while循环里i=最后一个bar的
			//下标
			int a=t*(st.isEmpty()?height.length:(height.length-st.peek()-1));
			mmax=Math.max(mmax, a);
		}
		return mmax;
		
	}
	

}
