import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/23311629
//������д���ܿռ�ﲻ��Ҫ��
//�����Ǹ�������ʱ�Ӻ���ǰ���£���˸ı�������鲻��Ӱ�쵽�����¸�Ԫ��
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
	         al2=new ArrayList<Integer>();//al2Ҫ����new����Ϊ������set�Ļ�ǰ���Ǵ��ڵ�i��Ԫ��
	         
	    	 al2.add(1);
	    	 for(int i=1;i<al.size();i++){
	    		 al2.add(i, al.get(i-1)+al.get(i));
	    	 }
	    	 al2.add(1);
	    	 al=al2;
	    	 
	     }
		 return al2;
	    }
	 public ArrayList<Integer> getRow2(int rowIndex) {//�������ǶԵ�
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
	 //�ڶ���д����д��̫�������պ��Ǵ��ҵ���ֵ���ԣ������ҾͲ���
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
	 //������,������һ�Σ�accept��
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
