//�����Ϊ�����Լ����������⣬������ǵÿ�����
//http://blog.csdn.net/linhuanmars/article/details/21145429
public class ContainerWithMostWater {

	public int maxArea(int[] height) {

		if (height.length == 0) {
			return 0;
		}

		int l = 0;
		int r = height.length - 1;
		int max=0;
		while (l < r) {//˼·���Ǵ����߿�ʼ�������ߵ�bar���ұߵ�barС�Ļ���˵��������ұߵ�bar
			//�������򲻿��ܵõ���������������Ӧ�ð���bar���ƣ���֮��Ȼ��ά��һ��max������
			int low=Math.min(height[l], height[r]);
			max=Math.max(max, (r-l)*low);
			if(height[l]<height[r]){
				l++;
			}else{
				r--;
			}

		}
		return max;

	}
	//�����ֻ���û�뵽

}
