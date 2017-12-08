//http://blog.csdn.net/sbitswc/article/details/32665349
public class SortColors {
	public void sortColors(int[] A) { //别人的方法 
        
        int redSt = 0, blueSt = A.length-1;  
        int i=0;  
        while(i<blueSt+1)  
        	
        {  
            if(A[i]==0){  
                int temp = A[i];  //他这个就是swap
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
                blueSt--;  //注意他这里又没有i++了
                continue;  
            }  
            i++;  
        } 
	}
	//第二次写,用for循环还真不好写，得用while
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

