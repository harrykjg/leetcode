//http://blog.csdn.net/linhuanmars/article/details/24343525


public class RemoveDuplicatesfromSortedArrayII {
	
	public static void main(String[] args) {
		RemoveDuplicatesfromSortedArrayII rd=new RemoveDuplicatesfromSortedArrayII();
		int[] a={1,1,2};
		rd.removeDuplicates2(a);
	}

	public int removeDuplicates(int[] A) {
		//���ǿ���code ganker�ĲŻᣬ���ֹؼ�����indexֵ��ָ�������һ����������λ�ã�
		//�����Ƿ���Ҫǰ�ƣ�����һ��ֵA[index]=A[i+1]�����Ǹ�ֵ������3�λ����ϣ���
		//�������ܱ�֤����������е�ֵ����ǰ��
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
	//�ڶ���д�ģ�������һ�βŹ����ֵֹ�
	public int removeDuplicates2(int[] A) {
		if(A.length<=2){
			return A.length;
		}
		int index=0;
		//˼���������һ����ͬ�Ļ����Ȱ�ǰ��������ֵ���������whileѭ���ж���ͬ��ֵ���Թ�����
		//���ǵ�һ��д���и����⣬����1��1,2������ӣ�����forѭ����i<A.length-1����i+=2֮��
		//i�ټ�1����ʱforѭ�����ټ�1���������һ������û���жϣ������Ҿͼ��˺����Ǹ�if�ж�:
		//���ԭ���鵹����һ��������ͬ�Ļ�����ô���forѭ����ʵ���������鶼�жϵ��ˣ�����ֱ��
		//����index�����������һ��������ͬ�Ļ�����ô���һ�����ֿ϶���ֻ��һ���ģ��ٰ���
		//�������飬����
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
	//1/10 һ�ι�
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
