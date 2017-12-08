public class GasStation {
//http://blog.csdn.net/linhuanmars/article/details/22706553
	public static void main(String[] args) {
		
		GasStation gs=new GasStation();
		int[] a=new int[]{2,4};
		
		int[] b=new int[]{3,4};
		
		int r=gs.canCompleteCircuit3(a,b);
		System.out.println(r);
		

	}
//应该是自己想的方法吧，和code ganker他们的不一样吧，运行时间几乎一样
	static int  flag=0;
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		
		int start=0;
		int temp=0;
		while(start<gas.length){
			boolean b=check(start,gas,cost);
			if(b==true){
				
				return start;
			}
			temp=flag+1;
			if(temp>start){//假如1到2到3到4都行，到5就不行了，则2到5,3到5,4到5肯定也不行，
				//所以2345都不用检测了，所以直接跳到从5开始
				start=temp;
			}else{
				start++;
			}

			
		}
		return -1;
		
	}
	
	static boolean check(int s,int[] gas, int[] cost){
		int tank=0;
		
		for(int i=s;i<=gas.length-1;){
			tank+=gas[i];
			tank=tank-cost[i];
			if(tank<0){
				flag=i;//记录下不行的这个点
				return false;
			}
			i=(i+1)%gas.length;
			if(i==s){
				break;
			}
		}
		return true;
		
	}
	//第二次还是看了第一次的思路，再写一次,有点别扭感觉

	public  int canCompleteCircuit2(int[] gas, int[] cost) {
		for(int i=0;i<gas.length;i++){//i代表从第i个点开始检测
			
			int start=i;
			int count=0;//记录走了的步数
			int sum=0;
			while(count<gas.length){
				count++;
				if(sum+gas[start%gas.length]-cost[start%gas.length]<0){
					i=start;//记录下这个不行的点，下一步就是从这个start+1开始检测了，注意，这里
					break;//i指的是从第i个点到第i+1的点失败，比如说i=4点到i=5失败，那么应该
				}        //记录的是i=4，因为for循环会再加1，下一次就是从i=5开始检测了
				//开始还想这个i要不要写成i=start%gas.length，试一下gas={2,4}，cost={3,4}就知道
				//不行了。结果是i到了gas.length的时候，不取余的话到了下个for循环就结束了，说明
				//0到最后的途径都试过了不行，但取余的话就把i右变成从第一个元素开始检测了，无线循环
				//想想，假如有8个数，第6到第8都行，再到第一个也行，但到了第二就不行了，即
				//6,7,8,1,2这个序列都不行，按想想那么只有可能是从3,4,5开始才有可能行，但是这里
				//本来就先检测了3,4,5的，如果行了的话早就行了，所以不用担心这个i应该怎么变
				if(count==gas.length){
					return i;
				}
				sum+=gas[start%gas.length]-cost[start%gas.length];
				
				start++;
			}
			
		}
		return -1;
	}
	//第三轮,一次tle,然后调试了几次勉强行了
	 public int canCompleteCircuit3(int[] gas, int[] cost) {
	        int tank=0;
	        if(gas.length==0||cost.length==0){
	        	return -1;
	        }
	        
	        for(int i=0;i<gas.length;i++){//主要就是内外两层循环有点绕，起点终点的控制问题还有 
	        	int count=0;   //取余的问题
	        	int k=i;
	        	tank=gas[i];
	        	
	        	while(count<gas.length){
	        		
	        		if(tank<cost[k%gas.length]){
	        			if(k>i){
	        				i=k;//比如说从中间开始，绕道前面才不行，但是这个不行的点还是小于起点i
	        			}//的，那么就不用管，因为一斤该检测过了，只有这个不行的点大于i了才需要跳过
	        			break;
	        		}else{
	        			tank=tank-cost[k%gas.length]+gas[(k+1)%gas.length];
	        		}
	        		count++;
	        		k++;
	        	}
	        	if(count==gas.length){
	        		return i;
	        	}
	        }
	        return -1;
	    }

}
