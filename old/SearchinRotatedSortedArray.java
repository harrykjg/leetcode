//http://blog.csdn.net/linhuanmars/article/details/20525681
//https://jixiangsanbao.wordpress.com/2014/04/30/search-in-rotated-sorted-array/
public class SearchinRotatedSortedArray {
	//д�ĺ���
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
//�ұ�sorted��A[mid]<target<=A[e]���϶����ұߣ���8��9��3��4��5��6��7������Ҫ��6
		} else if (a[mid]<target&&a[mid]<=a[e]&& target <= a[e]) {
			return ssearch(a, mid + 1, e, target);
//���sorted��a[mid] < target���϶����ұߣ���4��5��6��7��8��9��1��2������Ҫ��9
		} else if (a[mid] < target && a[mid] > a[b]) {
			return ssearch(a, mid + 1, e, target);

//���sorted����4��5��6��7��8��9��1��2������Ҫ��1
		} else if (a[mid] >= a[b]&&a[mid] > target &&target < a[b]) {
			return ssearch(a, mid + 1, e, target);
		} else {
			return ssearch(a, b, mid - 1, target);
		}
		
	}
	//�ڶ���д�������ü������ַ�2��ifд�߼��ȽϺ�
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
		//��4��5��6��7��8��9��1��2������Ҫ��5��Ҫ��1��Ҫ��9
		if(A[mid]>=A[b]){//left sorted����ģ���������ȺžͿ�����
			if(A[b]<=target&&A[mid]>target){//�������Ҫ��6�����
				return bin(A,b,mid-1,target);
			}else{
				return bin(A,mid+1,e,target);//����Ͱ�����Ҫ��1��Ҫ��9�����
			}
			
		}else {//right sorted ��8��9��3��4��5��6��7������Ҫ��3��Ҫ��9��Ҫ��6
			if(A[mid]<target&&A[e]>=target){
				return bin(A,mid+1,e,target);
			}else{
				return bin(A,b,mid-1,target);
			}
		}
		
		
	}

}
