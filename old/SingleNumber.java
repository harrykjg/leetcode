import java.util.HashSet;
import java.util.Set;
//http://jixiangsanbao.wordpress.com/2014/06/04/single-number/

public class SingleNumber {

	public static void main(String[] args) {
//		int[] A={2,3,2,1,3,1,4,4,8,7,7};
		int[] A={2,2,1,2,1,1,4};
		System.out.println(single2(A));
		
	}
	//�Եĵ��ᳬʱ
	    public static int single(int[] A) {
	        
	        int k=0;
	        int temp=A[k];//������tempȥ��¼�������ÿһ������Ȼ��������飬�������temp��ͬ��
	        //������temp���ظ��ģ���ôtemp��ȥ����һ���������temp��ֻ��һ���ģ���ô����forѭ��
	        //֮���Ҳ���������ͬ�ģ���return temp����
	        
	        
	        for(int i=0;i<A.length;i++){
	            if(A[i]==temp&&i!=k){
	                temp=A[++k];
	                i=-1;
	            }
	        }
	        return temp;
	        
	        
	    }
	    //xorֻ����������������ֻ����2�ε�ʱ�����
	    public static int single2(int[] A){
	    	
	    	int result=0;
	    	for(int i=0;i<A.length;i++){
	    		result^=A[i]; 

	    	}
	    	return result;
	    }
	    
  
	    
	}

