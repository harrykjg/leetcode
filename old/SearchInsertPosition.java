public class SearchInsertPosition {
//ͦ�򵥵ģ����ַ��������
	public int searchInsert(int[] A, int target) {
		if(A.length==0){
			return 0;
		}
		int x=go(A,target,0,A.length-1);
		return x;
	}
	public int go(int[] A,int target,int b,int e){
		if(b>e){
			return b;//������ʵ����ϸ���룬������ʣ2��Ԫ��ȥ���֣��������͵����������3�͵��ĸ�
			//��A[mid]�϶���ǰһ�������target����A[mid]�Ļ����ǿ϶��ǽ���mid+1��e֮�䡣Ȼ��
			//��ֻʣһ��Ԫ�أ������Ԫ�ش���target�������b��mid-1����ʱmid-1��С��b�ˣ�����
			//ȷʵ�ò���b��λ�ã������Ԫ��С��target�������mid+1��e֮�䣬��ʱmid+1�ʹ���e��
			//���룬ȷʵ�ò���mid+1����һ���b��λ�ã������Է���b��һ���Ե�
			
		}
		int mid=(b+e)/2;
		if(A[mid]==target){
			return mid;
		}
		if(A[mid]>target){
			return go(A,target,b,mid-1);
		}
		else{
			return go(A,target,mid+1,e);
		}
	}
//�Ƕ��ַ�
 public int searchInsert2(int[] A, int target) {
       if(A.length==0){
           return 0;
       }
       for(int i=0;i<A.length;i++){
           if(A[i]==target){
               return i;
           }
           if(i>0&&A[i]>target&&A[i-1]<target){
               return i;
           }
           if(A[0]>target){
               return 0;
           }
       }
       return A.length;
    }

}
