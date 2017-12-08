import java.util.HashSet;
import java.util.Set;
//http://jixiangsanbao.wordpress.com/2014/06/04/single-number/

public class SingleNumber {

	public static void main(String[] args) {
//		int[] A={2,3,2,1,3,1,4,4,8,7,7};
		int[] A={2,2,1,2,1,1,4};
		System.out.println(single2(A));
		
	}
	//对的但会超时
	    public static int single(int[] A) {
	        
	        int k=0;
	        int temp=A[k];//就是用temp去记录数组里的每一个数，然后遍历数组，如果有与temp相同的
	        //即代表temp有重复的，那么temp就去试下一个数，如果temp是只有一个的，那么遍历for循环
	        //之后都找不到与其相同的，就return temp行了
	        
	        
	        for(int i=0;i<A.length;i++){
	            if(A[i]==temp&&i!=k){
	                temp=A[++k];
	                i=-1;
	            }
	        }
	        return temp;
	        
	        
	    }
	    //xor只能用在其他数都是只出现2次的时候才行
	    public static int single2(int[] A){
	    	
	    	int result=0;
	    	for(int i=0;i<A.length;i++){
	    		result^=A[i]; 

	    	}
	    	return result;
	    }
	    
  
	    
	}

