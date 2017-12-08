//http://blog.csdn.net/linhuanmars/article/details/20023993 他的方法比较好，我这个瞎搞居然也
//acceptl了

public class RemoveDuplicatesfromSortedArray {
	
	public static void main(String[] args) {
		RemoveDuplicatesfromSortedArray rd=new RemoveDuplicatesfromSortedArray();
		int[] a={1,1,1,3};
		rd.removeDuplicates(a);
	}
	
	public int removeDuplicates(int[] A) {
		
		if(A.length==0){
			return 0;
		}
//		boolean flag=false;
		int index=0;
		for(int i=0;i<A.length-1;i++){ 
			if(A[i]==A[i+1]){ //开始想的是设个flag，一旦有重复的了就flag=true代表要替换了
//				flag=true;   
				continue;
			}
			else{
				index++;//index代表唯一的数的数量-1，每遇到一个不同的数字则index加1，这样
				//index的位置就是扫过重复数字后，发现不同的数字，把该数字填上的位置
//				if(flag){//如果不同的话并且flag=true则把那一个替换到index的位置
					A[index]=A[i+1];
//				}
				//最后发现其实根本不用flag，因为如果不要flag的话，并且没有重复数字出现
					//则A【index】=A【i+1】就是自己替换自己，没有影响如果，出现的话就替换
			}
		}
		return index+1;

	}

}
