//http://blog.csdn.net/linhuanmars/article/details/20593391
//http://www.2cto.com/kf/201311/256472.html
//���ַ����м�ģ����ö��ַ������ұ߽�
public class SearchforaRange {
	
	public static void main(String[] args) {
		SearchforaRange sf=new SearchforaRange();
		int[] a={1,1,2};
		int[] s=sf.searchRange(a, 1);
		System.out.println(s[0]);
		System.out.println(s[1]);
	}
	
	
	
	int[] rs= {-1,-1};
	public int[] searchRange(int[] A, int target) {

		if(A.length==0){
			return rs;
		}
		int b=0;
		int e=A.length-1;
		bsearch(A,b,e,target);
		return rs;
		
	}
	public void bsearch(int[] A,int b,int e,int target){
		if(b>A.length-1||e<0||b>e){
			return;
		}
		int m=(b+e)/2;
		if(A[m]==target){
			if(m-1<0){//��ߵ���ͷ
				rs[0]=m;
			}
			else if(m-1>=0&&A[m-1]!=target&&rs[0]==-1){//��ߵ������䲻ͬ
				rs[0]=m;
			}
			else if(m-1<0&&rs[0]==-1){//����Ƿϵ�
				rs[0]=m;
			}
			else if(rs[0]==-1){//���û��ͷ����ߵ���������ͬ����ߵĽ��û��ֵ��
				bsearch(A,b,m-1,target);
			}
			if(m+1>A.length-1){
				rs[1]=m;
			}
			else if(m+1<=A.length-1&&A[m+1]!=target&&rs[1]==-1){
				rs[1]=m;
			}
			else if(m+1<=A.length-1&&rs[0]==-1){
				rs[1]=m;
			}
			else if(rs[1]==-1){
				bsearch(A,m+1,e,target);
			}
		}
		
		else if(A[m]<target&&b!=e){
			bsearch(A,m+1,e,target);
		}
		else if(A[m]>target&&b!=e){
			bsearch(A,b,m-1,target);
		}
		return;
		
	}
//�ڶ��ξ�ûд�ˣ��ѵ�һ�ε��޸���һ�£��ѷϵ��ж�ȥ��
	public void bsearch2(int[] A,int b,int e,int target){
		if(b>A.length-1||e<0||b>e){
			return;
		}
		int m=(b+e)/2;
		if(A[m]==target){
			if(m-1<0){//��ߵ���ͷ
				rs[0]=m;
			}
			else if(m-1>=0&&A[m-1]!=target){//��ߵ������䲻ͬ
				rs[0]=m;
			}
			
			else{
				bsearch(A,b,m-1,target);
			}
			if(m+1>A.length-1){
				rs[1]=m;
			}
			else if(m+1<=A.length-1&&A[m+1]!=target){
				rs[1]=m;
			}
			else {
				bsearch(A,m+1,e,target);
			}
		}
		
		else if(A[m]<target&&b!=e){
			bsearch(A,m+1,e,target);
		}
		else if(A[m]>target&&b!=e){
			bsearch(A,b,m-1,target);
		}
		return;
		
	}

}
