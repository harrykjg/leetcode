public class SearchInsertPosition {
//挺简单的，二分法查就行了
	public int searchInsert(int[] A, int target) {
		if(A.length==0){
			return 0;
		}
		int x=go(A,target,0,A.length-1);
		return x;
	}
	public int go(int[] A,int target,int b,int e){
		if(b>e){
			return b;//这里其实得仔细想想，如果最后剩2个元素去二分，比如第五和第六个，或第3和第四个
			//则A[mid]肯定是前一个，如果target大于A[mid]的话，那肯定是进入mid+1和e之间。然后
			//就只剩一个元素：如果该元素大于target，则进入b和mid-1，此时mid-1就小于b了，想想
			//确实该插入b的位置；如果该元素小于target，则进入mid+1和e之间，此时mid+1就大于e了
			//想想，确实该插入mid+1（下一层的b）位置，若是以返回b是一定对的
			
		}
		int mid=(b+e)/2;
		if(A[mid]==target){
			return mid;
		}
		if(A[mid]>target){
			return go(A,target,b,mid-1);
		}
		else{
			return go(A,target,mid+1,e);
		}
	}
//非二分法
 public int searchInsert2(int[] A, int target) {
       if(A.length==0){
           return 0;
       }
       for(int i=0;i<A.length;i++){
           if(A[i]==target){
               return i;
           }
           if(i>0&&A[i]>target&&A[i-1]<target){
               return i;
           }
           if(A[0]>target){
               return 0;
           }
       }
       return A.length;
    }

}
