//http://blog.csdn.net/linhuanmars/article/details/20023993 ���ķ����ȽϺã������Ϲ���ȻҲ
//acceptl��

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
			if(A[i]==A[i+1]){ //��ʼ��������flag��һ�����ظ����˾�flag=true����Ҫ�滻��
//				flag=true;   
				continue;
			}
			else{
				index++;//index����Ψһ����������-1��ÿ����һ����ͬ��������index��1������
				//index��λ�þ���ɨ���ظ����ֺ󣬷��ֲ�ͬ�����֣��Ѹ��������ϵ�λ��
//				if(flag){//�����ͬ�Ļ�����flag=true�����һ���滻��index��λ��
					A[index]=A[i+1];
//				}
				//�������ʵ��������flag����Ϊ�����Ҫflag�Ļ�������û���ظ����ֳ���
					//��A��index��=A��i+1�������Լ��滻�Լ���û��Ӱ����������ֵĻ����滻
			}
		}
		return index+1;

	}

}
