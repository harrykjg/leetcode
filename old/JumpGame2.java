import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/21356187
//http://jixiangsanbao.wordpress.com/2014/06/03/jump-game-ii/
public class JumpGame2 {

	public static void main(String[] args) {

//		int[] a={8,2,4,4,4,9,5,2,5,8,8,0,8,6,9,1,1,6,3,5,1,2,6,6,0
//				,4,8,6,0,3,2,8,7,6,5,1,7,0,3,4,8,3,5,9,0,4,0,1,0,5,9,2,0,
//				7,0,2,1,0,8,2,5,1,2,3,9,7,4,7,0,0,1,8,5,6,7,5,1,9,9,3,5,0,7,5};
		int[] a={1,1,1,1};

		JumpGame2 jg=new JumpGame2();
		int k=jg.jump3(a);
		System.out.println(k);		
	}

	 int  num = -1;//��֪��д��ɶ����
	public  int jump(int[] A) {

		if(A.length==1){
			return 0;
		}		
		jump(A, 0, 0);
		return num;
	}
	
	public  void jump(int[] a,int times,int begin){
		if(begin>=a.length){
			return;
		}
		if(times>=num&&num!=-1){
			return;
		}	
		if(a[begin]>=a.length-1-begin){
			
			times+=1;
			if(num!=-1&&num>times){
				num=times;
				return;
			}else if(num==-1){
				num=times;
				return;
			}			
		}
		else{
			for(int i=a[begin];i>0;i--){
				
				jump(a,times+1,begin+i);
			}
		}
		
	}
	//�ڶ���д�������������β��У���ʼ��2��������һֱ���У����������Ƕ�����3���ģ�Ҳ���˺ü���
	public  int jump2(int[] A) {
		if(A.length<=1){
			return 0;
		}
		int step=0;
		int scope=0;//scope��¼��ǰλ�ú͵�ǰ���ֵ�ĺ�
		int pre=0;//pre��¼��һ�����ߵķ�Χ
//����3,4,3,2,5,4,3����ʼ������0�����ߵķ�ΧҲ����0��preҲ��0��Ȼ���l��scope��Ϊ3�������¸�ѭ��
		//��ʱi=1������pre�ˣ�˵������Ҫ��1����ǰ������һ���󣬵�ǰ�ⲽ���ߵķ�ΧҲҪ����
		for(int i=0;i<A.length&&i<=scope;i++){
			if(i>pre){
				step++;
				pre=scope;
			}
			
			if(i+A[i]>scope){
				
				scope=i+A[i];
				if(scope>=A.length-1){
					step++;
					break;
				}
			}
		}
		return step;
	}
	//�����Σ�֪����Ӧ����Ҫ3������,������д����
	public  int jump3(int[] A) {
		if(A.length<=1){
			return 0;
		}
		if(A[0]>=A.length-1){
		    return 1;
		}
		int max=A[0];
		int step=1;
		int last=A[0];

		for(int i=0;i<A.length&&i<=max;i++){//���Լ����뷨�Ļ�����������i<=max����д���ˣ���ʼ
			                        //д����i<=last
			
			
			if(i>last){
				step++;
				last=max;
			}
			max=Math.max(max, i+A[i]);//��һ���������if(i>last)֮ǰ�ʹ���
			if(max>=A.length-1){
				step++;
				return step;
			}
		}
		return -1;
	}
	//��4��,���ǲ��죬���Ǿ�Ȼһ��accept�ˡ���
	public  int jump4(int[] A) {
		if(A.length<=1){
			return 0;
		}
		int step=0;//�Ǻã�scope������¼���ߵ�����Զ�ķ�Χ��Ҳ�����ж��Ƿ����ߵ���ǰ�㣬Ҳ�����ж�
		int scope=0;//ʱ�����������һλ��last������¼�ϸ�scope�������Ļ���step++
		int last=0;
		for(int i=0;i<A.length&&i<=scope;i++){
			if(i>last){
				step++;
				last=scope;
			}
			if(A[i]+i>scope){
				scope=A[i]+i;
				
			}
			if(A[i]+i>=A.length-1){
				step++;
				return step;
			}
		}
		return -1;
	}
	
	
}
