import java.util.LinkedList;

public class LargestRectangleinHistogram {
//http://blog.csdn.net/doc_sgl/article/details/11805519  ������ú�	

//http://www.cnblogs.com/avril/archive/2013/08/24/3278873.html	
	

	//����������ǶԵģ����ǻᳬʱ
	public static void main(String[] args) {
		LargestRectangleinHistogram lr=new LargestRectangleinHistogram();
		int[] h={4,2};
		System.out.println(lr.largestRectangleArea2(h));
		
	}
	
     int max=0;
	public int largestRectangleArea(int[] height) {//˼·�����ȿ���һ��bar��Ȼ���������ɨ1��2,3����
		//λ�����ĳ�������Ƿ����max��Ȼ�󿴵ڶ���bar����������ɨ1,2,3λ������
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
		cursum=Math.max(cursum, (end-begin+1)*low);//���ǵ�ǰ�������ͣ�(end-begin+1)*low��height[begin]
		//height[end]��3�����ֱ�Ƚ�ȡ���ֵ
		cursum=Math.max(cursum, height[begin]);
		cursum=Math.max(cursum, height[end]);
		if(cursum>max){
			max=cursum;
		}
		cal(height,begin,end+1,cursum,low);
	}
	int mmax=0;
	
	public int largestRectangleArea2(int[] height) {//�ܵ���˵�Ǻ�����ģ������Լ��ҵĻ����Һþ�
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
					int a=t*(st.isEmpty()?i:(i-st.peek()-1));//������������������ǰһ��bar����
					//���ѵ�ǰbar�ӽ�ȥ��ԭ�����stack�ǿյĻ���ôǰһ��bar����߿϶�
					//û�б�������bar�����������height[st.pop()]����i����i���ȫ���ǲ�С������bar����
					//���stack���ǿյĻ�������ǰ��϶��б���С��bar���������ֻ����
					//height[st.pop()]*(i-st.peek()-1)����ǰһ��bar����ǰi-1�Ŀ�ȣ�
					mmax=Math.max(mmax, a);
				}
				st.push(i);
			}
		}
		while(!st.isEmpty()){//��Ϊ�����е�bar֮���������һ��0��bar�������barȫ�����ڵ���0��,
			//����ɨ��bar֮��stack�ﻹʣbar�Ļ�����Ҫ����
			int t=height[st.pop()];//�����height.length��ʵ���൱������whileѭ����i=���һ��bar��
			//�±�
			int a=t*(st.isEmpty()?height.length:(height.length-st.peek()-1));
			mmax=Math.max(mmax, a);
		}
		return mmax;
		
	}
	

}
