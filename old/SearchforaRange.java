//http://blog.csdn.net/linhuanmars/article/details/20593391
//http://www.2cto.com/kf/201311/256472.html
//二分法搜中间的，再用二分法搜左右边界
public class SearchforaRange {
	
	public static void main(String[] args) {
		SearchforaRange sf=new SearchforaRange();
		int[] a={1,1,2};
		int[] s=sf.searchRange(a, 1);
		System.out.println(s[0]);
		System.out.println(s[1]);
	}
	
	
	
	int[] rs= {-1,-1};
	public int[] searchRange(int[] A, int target) {

		if(A.length==0){
			return rs;
		}
		int b=0;
		int e=A.length-1;
		bsearch(A,b,e,target);
		return rs;
		
	}
	public void bsearch(int[] A,int b,int e,int target){
		if(b>A.length-1||e<0||b>e){
			return;
		}
		int m=(b+e)/2;
		if(A[m]==target){
			if(m-1<0){//左边到了头
				rs[0]=m;
			}
			else if(m-1>=0&&A[m-1]!=target&&rs[0]==-1){//左边的数与其不同
				rs[0]=m;
			}
			else if(m-1<0&&rs[0]==-1){//这个是废的
				rs[0]=m;
			}
			else if(rs[0]==-1){//左边没到头，左边的数与其相同，左边的结果没赋值过
				bsearch(A,b,m-1,target);
			}
			if(m+1>A.length-1){
				rs[1]=m;
			}
			else if(m+1<=A.length-1&&A[m+1]!=target&&rs[1]==-1){
				rs[1]=m;
			}
			else if(m+1<=A.length-1&&rs[0]==-1){
				rs[1]=m;
			}
			else if(rs[1]==-1){
				bsearch(A,m+1,e,target);
			}
		}
		
		else if(A[m]<target&&b!=e){
			bsearch(A,m+1,e,target);
		}
		else if(A[m]>target&&b!=e){
			bsearch(A,b,m-1,target);
		}
		return;
		
	}
//第二次就没写了，把第一次的修改了一下，把废的判断去掉
	public void bsearch2(int[] A,int b,int e,int target){
		if(b>A.length-1||e<0||b>e){
			return;
		}
		int m=(b+e)/2;
		if(A[m]==target){
			if(m-1<0){//左边到了头
				rs[0]=m;
			}
			else if(m-1>=0&&A[m-1]!=target){//左边的数与其不同
				rs[0]=m;
			}
			
			else{
				bsearch(A,b,m-1,target);
			}
			if(m+1>A.length-1){
				rs[1]=m;
			}
			else if(m+1<=A.length-1&&A[m+1]!=target){
				rs[1]=m;
			}
			else {
				bsearch(A,m+1,e,target);
			}
		}
		
		else if(A[m]<target&&b!=e){
			bsearch(A,m+1,e,target);
		}
		else if(A[m]>target&&b!=e){
			bsearch(A,b,m-1,target);
		}
		return;
		
	}

}
