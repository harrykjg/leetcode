//http://blog.csdn.net/sbitswc/article/details/32665349
public class SortColors {
	public void sortColors(int[] A) { //���˵ķ��� 
        
        int redSt = 0, blueSt = A.length-1;  
        int i=0;  
        while(i<blueSt+1)  
        	
        {  
            if(A[i]==0){  
                int temp = A[i];  //���������swap
                A[i] = A[redSt];  
                A[redSt] = temp;  
                redSt++;  
                i++;  
                continue;  
            }  
            if(A[i]==2){  
                int temp = A[i];  
                A[i] = A[blueSt];  
                A[blueSt] = temp;  
                blueSt--;  //ע����������û��i++��
                continue;  
            }  
            i++;  
        } 
	}
	//�ڶ���д,��forѭ�����治��д������while
	public void sortColors2(int[] A) {
		int w=0;
		int b=A.length-1;
		int i=0;
		while(i<=b){
			if(A[i]==0){                
				swap(A,i,w);
				w++;
				i++;
				continue;
			}
			if(A[i]==2){
				swap(A,i,b);
				b--;
				
				continue;
			}
			i++;
		}
	}
	
	private void swap(int[] A,int i,int j){
		int t=A[i];
		A[i]=A[j];
		A[j]=t;
		
	}
}

