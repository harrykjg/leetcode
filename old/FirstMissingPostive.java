import java.util.HashSet;

//http://www.cnblogs.com/panda_lin/p/first_missing_positive.html
//http://blog.csdn.net/linhuanmars/article/details/20884585
public class FirstMissingPostive {
	public static void main(String[] args) {
		FirstMissingPostive fm=new FirstMissingPostive();
		int[] a={1,1};
		System.out.println(fm.firstMissingPositive2(a));
		
	}
	 public int firstMissingPositive(int[] A) {
		 
		 if(A.length==0){
			 return 1;
		 }
		 if(A.length==1&&A[0]!=1){
			 return 1;
		 }
		 for(int i=0;i<A.length;i++){
			 if(A[i]==i+1){
				 continue;
			 }
			 //如3, 4, -1, 1。这个while挺巧妙的，这个while的条件就是只有第i个数为
			 //负数或者其范围大于数组的范围时才不管他，否则就要把第i个数的值一直搞到等于i+1为止
			 while(A[i]>0&&A[i]-1<A.length){
				 int temp=A[A[i]-1];
				 A[A[i]-1]=A[i];//i=0时，变成3,4,3,1
				 A[i]=temp;//变成-1,4,3,1
				 if(A[i]-1>=0&&A[i]-1<A.length&&A[A[i]-1]==A[i]){//处理[1,1]的情况
					 break;
				 }
			 }
		 }
		 for(int i=0;i<A.length;i++){
			 if(A[i]!=i+1){
				 return i+1;
			 }
		 }
		 return A.length+1;
	        
	    }
	 //第二次没有写，写也是得看答案才能写
	 //第三轮,开始想错了，想成是longest consecutive sequence了。基本想法是记得的.就是调试了好几次
	 public int firstMissingPositive2(int[] A) {
		 if(A.length==0){
			 return 1;
		 }
		 if(A.length==2&&A[1]==A[0]){//这个if是用来对付{1，,1}和{2,2}这个例子的,其实不写也行，
			 return A[0]==1?2:1;//后面能对付的
		 }
		 int i=0;
		 while(i<A.length){
			 if(A[i]-1==i||A[i]<0||A[i]-1>=A.length||A[i]-1<0){
				 i++;
				 continue;
			 }
			 
			 int temp=A[A[i]-1];
			 if(temp==A[i]){//这个就是用来对付2,2,2,2这种的情况，就是假如这两个要换的数相同的话，
				 i++; //那么换了也是白换，比如第一个2和第二个2换，那么换完之后i=0这个位置上还是
				 continue;//得继续处理，那么还是和第二个2换，就会死循环
			 }
			 A[A[i]-1]=A[i];
			 A[i]=temp;
			
		 }
		 for( i=0;i<A.length;i++){
			 if(A[i]!=i+1){
				 break;
			 }
		 }
		 return i+1;
	 }

}
