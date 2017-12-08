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

	 int  num = -1;//不知道写的啥玩意
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
	//第二次写，调试了无数次才行，开始用2个变量，一直不行，后来看他们都是用3个的，也试了好几次
	public  int jump2(int[] A) {
		if(A.length<=1){
			return 0;
		}
		int step=0;
		int scope=0;//scope记录当前位置和当前点的值的和
		int pre=0;//pre记录这一步能走的范围
//比如3,4,3,2,5,4,3，开始步数是0，能走的范围也就是0，pre也是0，然后更l新scope，为3，进入下个循环
		//此时i=1，大于pre了，说明步数要加1，当前进了这一步后，当前这步能走的范围也要更新
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
	//第三次，知道了应该是要3个变量,但还是写不对
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

		for(int i=0;i<A.length&&i<=max;i++){//按自己的想法的话，就是这里i<=max这里写错了，开始
			                        //写的是i<=last
			
			
			if(i>last){
				step++;
				last=max;
			}
			max=Math.max(max, i+A[i]);//这一步如果放在if(i>last)之前就错了
			if(max>=A.length-1){
				step++;
				return step;
			}
		}
		return -1;
	}
	//第4次,还是不熟，但是居然一次accept了。。
	public  int jump4(int[] A) {
		if(A.length<=1){
			return 0;
		}
		int step=0;//记好，scope用来记录能走到的最远的范围，也用来判断是否能走到当前点，也用来判断
		int scope=0;//时候到了数组最后一位。last用来记录上个scope，超过的话就step++
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
