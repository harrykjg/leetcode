//妈的以为是能自己做出来的题，结果还是得看别人
//http://blog.csdn.net/linhuanmars/article/details/21145429
public class ContainerWithMostWater {

	public int maxArea(int[] height) {

		if (height.length == 0) {
			return 0;
		}

		int l = 0;
		int r = height.length - 1;
		int max=0;
		while (l < r) {//思路就是从两边开始，如果左边的bar比右边的bar小的话，说明如果把右边的bar
			//向左移则不可能得到更大的面积，所以应该把左bar右移，反之亦然，维护一个max就行了
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
	//第三轮还是没想到

}
