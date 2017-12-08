import java.util.ArrayList;
import java.util.List;
//http://blog.csdn.net/linhuanmars/article/details/23230657
//http://alexjixiang.com/2014/05/29/triangle/


//注意arraylist的toArray（）方法的使用
//http://blog.csdn.net/linhuanmars/article/details/23230657
//http://jixiangsanbao.wordpress.com/2014/05/29/triangle/
public class Triangle {

	public static void main(String[] args) {

		Triangle tr=new Triangle();
		
		List<List<Integer>> tt = new ArrayList<List<Integer>>();
		List<Integer> a1 = new ArrayList<Integer>();
		a1.add(-1);
		List<Integer> a2 = new ArrayList<Integer>();
		a2.add(2);
		a2.add(3);
		List<Integer> a3 = new ArrayList<Integer>();
		a3.add(1);
		a3.add(-1);
		a3.add(-3);
		// ArrayList<Integer> a4=new ArrayList<Integer>();
		// a4.add(4);
		// a4.add(1);
		// a4.add(8);
		// a4.add(3);
		tt.add(a1);
		tt.add(a2);
		tt.add(a3);
		// tt.add(a4);

		int k = tr.minimumTotal4(tt);
		System.out.println(k);

	}

	public static int minimumTotal(List<List<Integer>> triangle) {
		//我这个不是动态规划

		int[] max = null;
		int[] max2=new int[triangle.get(triangle.size()-1).size()];

		List<Integer> a = triangle.get(0);
		Integer[] aa = new Integer[a.size()];// 注意arraylist的toArray（）方法的使用
		aa = (Integer[]) a.toArray(aa);

		max = new int[] { aa[0] };

		max2=findmin(triangle, 0, max);
		int min=max2[0];
		for(int i=0;i<max2.length;i++){
			if(max2[i]<min){
				min=max2[i];
			}
		}

		return min;
	}

	private static int[] findmin(List<List<Integer>> tri, int i,
			int[] max) {

		if (i == tri.size() - 1) {
			return max;
		}
		List<Integer> a1 = tri.get(i);//不需要的

		List<Integer> a2 = tri.get(i + 1);

		int[] max2 = new int[max.length + 1];
		max2[0] = a2.get(0) + max[0];
		max2[max2.length - 1] = a2.get(a2.size() - 1) + max[max.length - 1];

		for (int j = 1; j < a2.size() - 1; j++) {
			if (max[j - 1] < max[j]) {
				max2[j] = a2.get(j) + max[j - 1];
			} else {
				max2[j] = a2.get(j) + max[j ];
			}
		}
		i++;
		
		return findmin(tri, i, max2);
		
	}
	public int minimumTotal2(ArrayList<ArrayList<Integer>> triangle) {  
		//别人动态规划的方法，其实也是把所有结果都试了的，每一层存了从根到该层所有的节点的和
	//再下一层就是用这上一层的这些结果，下一层的节点可以从上一层节点中2选1，选小的那个，就可以保证
	//该节点是最小的
	    if(triangle == null || triangle.size() == 0)  
	        return 0;  
	    if(triangle.size()==1)  
	        return triangle.get(0).get(0);  
	    int[] sums = new int[triangle.size()];  
	    sums[0] = triangle.get(0).get(0);  
	    for(int i=1;i<triangle.size();i++)  
	    {  
	        sums[i] = sums[i-1]+triangle.get(i).get(i);  
	        for(int j=i-1;j>=1;j--)  
	        {  
	            sums[j] = (sums[j]<sums[j-1]?sums[j]:sums[j-1]) + triangle.get(i).get(j);  
	        }  
	        sums[0] = sums[0]+triangle.get(i).get(0);  
	          
	    }  
	    int minimum = sums[0];  
	    for(int i=1;i<sums.length;i++)  //再遍历这最后一层的结果，选出最小的
	    {  
	        if(sums[i]<minimum)  
	        {  
	            minimum = sums[i];  
	        }  
	    }  
	    return minimum;  
	}  
//第二次写,这样可以，但是会超时，第一次写的居然能accept
	int rs=Integer.MAX_VALUE;
	public  int minimumTotal3(List<List<Integer>> triangle) {
		if(triangle.size()==0){
			return 0;
		}
		int cur=triangle.get(0).get(0);
		dfs(cur,0,triangle);
		return rs;
	}
	private void dfs(int cur,int curlevel,List<List<Integer>> triangle){
		if(curlevel+1==triangle.size()){
			rs=Math.min(cur, rs);
			return;
		}
		for(int i=0;i<triangle.get(curlevel).size();i++){
			
			dfs(cur+triangle.get(curlevel+1).get(i),curlevel+1,triangle);
			dfs(cur+triangle.get(curlevel+1).get(i+1),curlevel+1,triangle);
		}
	}
//dp的方法
	public  int minimumTotal4(List<List<Integer>> triangle) {
		if(triangle.size()==0){
			return 0;
		}
		if(triangle.size()==1){
			return triangle.get(0).get(0);
		}
		int rs=Integer.MAX_VALUE;
		for(int i=1;i<triangle.size();i++){
			List<Integer> level=triangle.get(i);
			//第一个位置的值就只能是上层第一个位置的值加这层第一个位置的值
			level.set(0, triangle.get(i-1).get(0)+level.get(0));
			//中间的值就是上层的第i-1和i个位置的值的小的那一个
			for(int j=1;j<level.size()-1;j++){
				int m=Math.min(triangle.get(i-1).get(j-1),triangle.get(i-1).get(j));
				level.set(j, m+level.get(j));
			}
			//该层最后一个位置的值就只能是上层最后一个位置的值加自己的值
			level.set(level.size()-1, triangle.get(i-1).get(triangle.get(i-1).size()-1)+level.get(level.size()-1));
		}
		//遍历最后一层
		for(int i=0;i<triangle.get(triangle.size()-1).size();i++){
			rs=Math.min(rs, triangle.get(triangle.size()-1).get(i));
		}
		return rs;
		
	}
	
}
