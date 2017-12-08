//http://blog.csdn.net/linhuanmars/article/details/20525681
//https://jixiangsanbao.wordpress.com/2014/04/30/search-in-rotated-sorted-array/
public class SearchinRotatedSortedArray {
	//写的很烂
	public static void main(String[] args) {
		SearchinRotatedSortedArray sr = new SearchinRotatedSortedArray();
		int[] a = {3,1};
		System.out.println(sr.search2(a, 1));

	}

	public int search(int[] A, int target) {
		if (A.length == 0) {
			return -1;
		}
		int i = ssearch(A, 0, A.length - 1, target);
		return i;
	}

	public int ssearch(int[] a, int b, int e, int target) {
		int mid=(b+e)/2;
		if (a[mid] == target) {
			return mid;
		} else if (b == e || b > e) {
			return -1;
//右边sorted，A[mid]<target<=A[e]，肯定在右边，拿8，9，3，4，5，6，7来看，要搜6
		} else if (a[mid]<target&&a[mid]<=a[e]&& target <= a[e]) {
			return ssearch(a, mid + 1, e, target);
//左边sorted，a[mid] < target，肯定在右边，拿4，5，6，7，8，9，1，2来看，要搜9
		} else if (a[mid] < target && a[mid] > a[b]) {
			return ssearch(a, mid + 1, e, target);

//左边sorted，拿4，5，6，7，8，9，1，2来看，要搜1
		} else if (a[mid] >= a[b]&&a[mid] > target &&target < a[b]) {
			return ssearch(a, mid + 1, e, target);
		} else {
			return ssearch(a, b, mid - 1, target);
		}
		
	}
	//第二次写，还是用吉祥那种分2层if写逻辑比较好
	public int search2(int[] A, int target) {
		if (A.length == 0) {
			return -1;
		}
		
		int i = bin(A, 0, A.length - 1, target);
		return i;
	}
	private int bin(int[] A,int b,int e,int target){
		if(b>e){
			return -1;
		}
		int mid=(b+e)/2;
		if(A[mid]==target){
			return mid;
		}
		//拿4，5，6，7，8，9，1，2来看，要搜5，要搜1，要搜9
		if(A[mid]>=A[b]){//left sorted，妈的，加了这个等号就可以了
			if(A[b]<=target&&A[mid]>target){//这个就是要搜6的情况
				return bin(A,b,mid-1,target);
			}else{
				return bin(A,mid+1,e,target);//这个就包含了要搜1和要搜9的情况
			}
			
		}else {//right sorted 拿8，9，3，4，5，6，7来看，要搜3，要搜9，要搜6
			if(A[mid]<target&&A[e]>=target){
				return bin(A,mid+1,e,target);
			}else{
				return bin(A,b,mid-1,target);
			}
		}
		
		
	}

}
