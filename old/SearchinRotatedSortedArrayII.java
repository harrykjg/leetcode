//http://jixiangsanbao.wordpress.com/2014/04/30/search-in-rotated-sorted-array-ii/������͵ıȽϺ�
//http://blog.csdn.net/linhuanmars/article/details/20588511

public class SearchinRotatedSortedArrayII {
	
	public static void main(String[] args) {
		SearchinRotatedSortedArrayII sr=new SearchinRotatedSortedArrayII();
		int[] a={1,1,3,1};
		System.out.println(sr.search(a, 3));
	}
//�����ѵ���ǱȽ������������
	public boolean search(int[] A, int target) {
		if(A.length==0){
			return false;
		}
		return go(A,0,A.length-1,target);

	}
	public boolean go(int[] A,int b,int e,int target){
		if(b>e){
			return false;
		}
		int mid=(b+e)/2;
		if(A[mid]==target){
			return true;
		}
		if(A[b]==A[mid]){
			if(A[mid]==A[e]){//����м������߲����м�����ұߵĻ������޷�ȷ��ȥ����߻����ұ�
				//����ֻ��ȫ���������ѣ����Ӽ����������
				for(int i=b;i<=e;i++){
					if(A[i]==target){
						return true;
					}
				}
				return false;
			}else{//����м������ߵ����м䲻�����ұߵĻ�������߿϶���ȫ����ͬ�����֣��������ұ�
				return go(A,mid+1,e,target);
			}
		}else{//�����м䲻������ߵĻ�������ԭ�������������
			if(A[mid]>A[b]){//��߿϶�������
				if(target<A[mid]&&target>=A[b]){//���target����ߵ�����Ļ��������
					return go(A,b,mid-1,target);
				}else{//�������ұ�
					return go(A,mid+1,e,target);
				}
				
			}else { //�ұ�������
				if(target<A[e]&&target>A[mid]){//targer���ұߵ�����Ļ������ұ�
				    return go(A,mid+1,e,target);
				}else{
					return go(A,b,mid-1,target);
				}
			}
		}
	}

}
