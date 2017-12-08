//http://jixiangsanbao.wordpress.com/2014/04/30/search-in-rotated-sorted-array-ii/吉祥解释的比较好
//http://blog.csdn.net/linhuanmars/article/details/20588511

public class SearchinRotatedSortedArrayII {
	
	public static void main(String[] args) {
		SearchinRotatedSortedArrayII sr=new SearchinRotatedSortedArrayII();
		int[] a={1,1,3,1};
		System.out.println(sr.search(a, 3));
	}
//这题难点就是比较难想出例子来
	public boolean search(int[] A, int target) {
		if(A.length==0){
			return false;
		}
		return go(A,0,A.length-1,target);

	}
	public boolean go(int[] A,int b,int e,int target){
		if(b>e){
			return false;
		}
		int mid=(b+e)/2;
		if(A[mid]==target){
			return true;
		}
		if(A[b]==A[mid]){
			if(A[mid]==A[e]){//如果中间等于左边并且中间等于右边的话，就无法确定去搜左边还是右边
				//所以只能全部遍历来搜，例子见吉祥的帖子
				for(int i=b;i<=e;i++){
					if(A[i]==target){
						return true;
					}
				}
				return false;
			}else{//如果中间等于左边但是中间不等于右边的话，则左边肯定是全部相同的数字，所以搜右边
				return go(A,mid+1,e,target);
			}
		}else{//入座中间不等于左边的话，则是原来那题的做法了
			if(A[mid]>A[b]){//左边肯定是升序
				if(target<A[mid]&&target>=A[b]){//如果target在左边的区间的话，搜左边
					return go(A,b,mid-1,target);
				}else{//否则搜右边
					return go(A,mid+1,e,target);
				}
				
			}else { //右边是升序
				if(target<A[e]&&target>A[mid]){//targer在右边的区间的话，搜右边
				    return go(A,mid+1,e,target);
				}else{
					return go(A,b,mid-1,target);
				}
			}
		}
	}

}
