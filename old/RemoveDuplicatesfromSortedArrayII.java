//http://blog.csdn.net/linhuanmars/article/details/24343525


public class RemoveDuplicatesfromSortedArrayII {
	
	public static void main(String[] args) {
		RemoveDuplicatesfromSortedArrayII rd=new RemoveDuplicatesfromSortedArrayII();
		int[] a={1,1,2};
		rd.removeDuplicates2(a);
	}

	public int removeDuplicates(int[] A) {
		//还是看了code ganker的才会，这种关键就是index值，指向的是下一个该填数的位置，
		//无论是否需要前移，都付一次值A[index]=A[i+1]（除非该值出现了3次或以上），
		//这样就能保证数组后面所有的值都会前移
		if(A.length<=2){
			return A.length;
		}
		int index=1;
		int count=1;
		for(int i=0;i<A.length-1;i++){
			if(A[i]==A[i+1]){
				count++;
				if(count>=3){
					continue;
				}else{
					A[index]=A[i+1];
					index++;
				}
			}
			else{
				count=1;
				A[index]=A[i+1];
				index++;
			}
		}
		return index;
	}
	//第二次写的，调试了一次才过，怪怪的
	public int removeDuplicates2(int[] A) {
		if(A.length<=2){
			return A.length;
		}
		int index=0;
		//思想就是遇到一串相同的话，先把前两个附上值，后面的用while循环判断相同的值，略过他们
		//但是第一次写会有个问题，比如1，1,2这个例子，由于for循环是i<A.length-1，我i+=2之后
		//i再减1，此时for循环会再加1，导致最后一个数字没法判断，于是我就加了后面那个if判断:
		//如果原数组倒数第一二个数相同的话，那么这个for循环其实是整个数组都判断的了，可以直接
		//返回index，如果倒数第一二个数不同的话，那么最后一个数字肯定是只有一个的，再把它
		//加上数组，返回
		for(int i=0;i<A.length-1;i++){
			if(A[i]!=A[i+1]){
				A[index]=A[i];
				index++;
				continue;
			}
			if(A[i]==A[i+1]){
				A[index]=A[i];
				A[index+1]=A[i];
				int temp=A[i];
				index+=2;
				i+=2;
				
				while(i<A.length&&A[i]==temp){
					i++;
				}
				i--;
			}
		}
		
		if(A.length>=3&&A[A.length-1]==A[A.length-2]){
			return index;
		}else{
			A[index]=A[A.length-1];
			index++;
			return index;
		}
	}
	//1/10 一次过
	public int removeDuplicates3(int[] A) {
        if(A.length==0){
            return 0;
        }
        int index=0;
        boolean flag=false;
        int i=0;
        while(i<A.length){
            if(i>0&&A[i]==A[i-1]&&flag){
                i++;
                continue;
            }
            if(i>0&&A[i]==A[i-1]&&flag==false){
                A[index]=A[i];
                index++;
                i++;
                flag=true;
                continue;
            }
            A[index]=A[i];
            index++;
            i++;
            flag=false;
        
            
        }
        return index;
    }

}
