//http://blog.csdn.net/linhuanmars/article/details/19712333
//http://jixiangsanbao.wordpress.com/2014/04/01/merge-sorted-array/

public class mergeTwoSortedArray {

	
	public static void main(String[] args) {
		
	
		// TODO Auto-generated method stub

		 int[] a={1};
		 int[] b={2};
	
		
		merge(a,1,b,1);
		for(int k:a){
			System.out.println(k);
		}
		
	}
	
	
	public static void merge(int A[], int m, int B[], int n) {
		int i = 0;
		int k = 0;
		int l = 0;
		int temp[] = new int[A.length + B.length];
		if (n == 0) {
			return;
		}
		if (m == 0) {
			while (k < B.length) {
				A[l] = B[k];
				k++;
				l++;
			}
			return;
		}
		while (i < A.length && k < B.length) {
			if (A[i] < B[k]) {
				temp[l] = A[i];
				i++;
				l++;
			} else {
				temp[l] = B[k];
				k++;
				l++;
			}
		}
		if (l < A.length + B.length && i == A.length) {
			while (k < B.length) {
				temp[l] = B[k];
				k++;
				l++;
			}
		}
		if (l < A.length + B.length && k == B.length) {
			while (i < A.length) {
				temp[l] = A[i];
				i++;
				l++;
			}
		}
      A=new int[A.length+B.length];
		 for(int x=0;x<temp.length;x++){
	          A[x]=temp[x];
	      }
	        
	        return;
	}
	
	//1/30不会，要看答案
	public  void merge2(int A[], int m, int B[], int n) {
		int i1=m-1;
		int i2=n-1;
		int i3=m+n-1;
		while(i1>=0&&i2>=0){
			if(A[i1]>B[i2]){
				A[i3]=A[i1];
				i1--;
				i3--;
				continue;
			}
			else{
				A[i3]=B[i2];
				i2--;
				i3--;
				continue;
			}
			
		}
		while(i1>=0){
			A[i3]=A[i1];
			i3--;
			i1--;
		}
		while(i2>=0){
			A[i3]=B[i2];
			i3--;
			i2--;
		}
		
	}

}
