import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/23311629
//我这样写可能空间达不到要求
//别人是更新数组时从后往前更新，因此改变这个数组不会影响到更新下个元素
public class PascalsTriangleII {
	
	public static void main(String[] args) {
		PascalsTriangleII ps=new PascalsTriangleII();
		ps.getRow4(2);
	}
	
	 public ArrayList<Integer> getRow(int rowIndex) {
	     ArrayList<Integer> al=new ArrayList<Integer>();
	     ArrayList<Integer> al2=new ArrayList<Integer>();
	     al.add(1);
	     if(rowIndex==0){
	    	 return al;
	     }
	     al.add(1);
	     if(rowIndex==1){
	    	 return al;
	     }
	     while(al2.size()!=rowIndex+1){
	         al2=new ArrayList<Integer>();//al2要重新new，因为下面用set的话前提是存在第i个元素
	         
	    	 al2.add(1);
	    	 for(int i=1;i<al.size();i++){
	    		 al2.add(i, al.get(i-1)+al.get(i));
	    	 }
	    	 al2.add(1);
	    	 al=al2;
	    	 
	     }
		 return al2;
	    }
	 public ArrayList<Integer> getRow2(int rowIndex) {//这样才是对的
	     ArrayList<Integer> al=new ArrayList<Integer>();
	     
	     al.add(1);
	     if(rowIndex==0){
	    	 return al;
	     }
	     al.add(1);
	     if(rowIndex==1){
	    	 return al;
	     }
	     while(al.size()!=rowIndex+1){
	         
	    	 for(int i=al.size()-1;i>0;i--){
	    		 al.set(i, al.get(i-1)+al.get(i));
	    	 }
	    	al.add(1);
	     }
		 return al;
	    }
	 //第二次写，还写不太出来，刚好是从右到左赋值可以，从左到右就不行
	 public ArrayList<Integer> getRow3(int rowIndex) {
		 ArrayList<Integer> al=new ArrayList<Integer>();
		 if(rowIndex==0){
			 al.add(1);
			 return al;
		 }
		 
		 al.add(1);
		 int cur=0;
		 while(cur<rowIndex){//
			 for(int i=al.size()-1;i>0;i--){
				 al.set(i,al.get(i)+al.get(i-1) );
			 }
			 al.add(1);
			 cur++;
		 }
		 return al;

	 }
	 //第三轮,调试了一次，accept了
	 public ArrayList<Integer> getRow4(int rowIndex) {
		 ArrayList<Integer> a=new ArrayList<Integer>();
		 a.add(1);
		 if(rowIndex==0){
			 return a;
		 }
		 a.add(1);
		 if(rowIndex==1){
			 return a;
		 }
		 int i=2;
		 while(i<=rowIndex){
			 for(int j=a.size()-1;j>0;j--){
				 a.set(j,a.get(j)+a.get(j-1));
			 }
			 
			 a.add(1);
			 i++;
		 }
		 return a;
	 }
	
}
