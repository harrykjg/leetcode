import java.util.ArrayList;
import java.util.Arrays;

//��һ����brute froce������time limit exceed

public class FourSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FourSum fs = new FourSum();
		// int[]
		// num={-497,-494,-484,-477,-453,-453,-444,-442,-428,-420,-401,-393,-392,-381,-357,-357,
		// -327,-323,-306,-285,-284,-263,-262,-254,-243,-234,-208,-170,-166,-162,-158,-136,
		// -133,-130,-119,-114,-101,-100,-86,-66,-65,-6,1,3,4,11,69,77,78,107,108,108,121,
		// 123,136,137,151,153,155,166,170,175,179,211,230,251,255,266,288,306,308,310
		// ,314,321,322,331,333,334,347,349,356,357,360,361,361,367,375,378,387,387,408,414,
		// 421,435,439,440,441,470,492};

		int[] num = {-1,0,-5,-2,-2,-4,0,1,-2};

		ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
		al = fs.fourSum3(num, -9);
		for (ArrayList<Integer> a : al) {
			for (int i : a) {
				System.out.print(i);

			}
			System.out.println();
		}

	}

	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	// public ArrayList<ArrayList<Integer>> fourSum(int[] num,int target){
	//
	// if(num.length<4){
	// return al;
	// }
	//
	// Arrays.sort(num);
	// for(int i=0;i<num.length-3;i++){
	// ArrayList<Integer> a=new ArrayList<Integer>();
	// a.add(num[i]);
	// find(i+1,target,num[i],1,a,num);
	//
	// }
	// return al;
	//
	// }
	//
	//
	// public void find(int begin,int target,int cursum,int
	// aldy,ArrayList<Integer> a,int[] num){
	// if(aldy==4){
	// return;
	// }
	//
	// for(int i=begin;i<num.length-3+aldy;i++){
	// ArrayList<Integer> a2=new ArrayList<Integer>();
	// a2.addAll(a);
	// a2.add(num[i]);
	// int temp=cursum+num[i];
	// // if(temp>=target&&aldy+1<4){
	// // break;
	// // }
	// if(temp==target&&aldy+1==4){
	//
	//
	// if(!al.contains(a2)){
	// al.add(a2);
	// return;
	// }
	// }
	// find(i+1,target,temp,aldy+1,a2,num);
	//
	//
	//
	// }
	// }

	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {

		if (num.length < 4) {
			return al;
		}
		Arrays.sort(num);
		for (int i = 0; i < num.length - 3; i++) {
			if (i != 0 && num[i] == num[i - 1]) {  
                continue;  
            } 
			for (int j = i + 1; j < num.length - 2; j++) {

				
				if (j!=i+1&&num[j] == num[j - 1]) { // ���������leetcode���п���200ms����
					continue;
				}
				find(num, target, num[i]+num[j], i, j);
			}
		}

		return al;

	}

	public void find(int[] num, int target, int cursum, int i, int j) {

		int begin=j+1;
		int end=num.length-1;
		
		while(begin<end&&begin<num.length&&end>=0){
			if (cursum + num[end] + num[begin] == target) {
				ArrayList<Integer> a = new ArrayList<Integer>();
				a.add(num[i]);
				a.add(num[j]);
				a.add(num[begin]);
				a.add(num[end]);
				if (!al.contains(a)) {
					al.add(a);
				
				}
				end--;//f��С�ˣ���ôb��ȻҪ++���п������ҵ�cursum + num[f] + num[begin] == 0
				begin++;
				//������while����Ϊ���������ڵ���ͬ�����֣�û�еĻ�leetcode�� time exceed
				while(end>begin&&num[begin]==num[begin-1]){
					begin++;      
				}
				while(end>begin&&num[end]==num[end+1]){
					end--;
				}
			}
			else if( num[end] + num[begin]+cursum >target){
				end--;
				while(begin>end&&num[end]==num[end+1]){//�������while�ǵڶ�������ʱ��
					end--;//����Ӧ�üӵģ�����֮���ǾͿ϶���������ظ������ˣ����ǰ���Ǹ�
				}         //if (!al.contains(a))���Բ�дҲ����
			}
			else{
				begin++;
				while(begin>end&&num[begin]==num[begin-1]){
					begin++;
				}
			}
		}
	}
	//�ڶ���д�����ǻ�������ȥ�ظ������֣��ᳬʱ�����ҳ��ıȽ϶�.�ؼ��ǳ��򶼴��ˡ���
	//���go�����Ͳ�Ӧ���ǵݹ飬�ľ��Ǹ�whileѭ�����������ϸ��Ҳ��������
	//�������ˣ���������ʱ�仹�ȵ�һ�εĳ�100ms����.���ǲ�һ��
	public ArrayList<ArrayList<Integer>> fourSum2(int[] num, int target) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(num.length<4){
			return al;
		}
		Arrays.sort(num);
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i=0;i<num.length-3;i++){
			if(i>0&&num[i]==num[i-1]){
				continue;
			}
			a.add(num[i]);
			for(int j=i+1;j<num.length-2;j++){
				if(j>i+1&&num[j]==num[j-1]){
					continue;
				}
				a.add(num[j]);
				go(num,target,num[i],num[j],j+1,num.length-1,a,al);
				a.remove(a.size()-1);
			}
			a.remove(a.size()-1);
		}
		return al;
	}
	private void go(int[]num,int target,int cur1,int cur2,int begin,int end,
			ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al){
		
		while(begin<end){
			int cursum=cur1+cur2+num[begin]+num[end];
			if(cursum==target){
				a.add(num[begin]);
				a.add(num[end]);
				al.add(new ArrayList<Integer>(a));
				a.remove(a.size()-1);
				a.remove(a.size()-1);
				while(end>begin&&num[begin]==num[begin+1]){
					begin++;
				}
				begin++;
				while(end>begin&&num[end]==num[end-1]){
					end--;
				}
				end--;
				
			}
			else if(cursum>target){
				      
				
				while(end>begin&&num[end]==num[end-1]){
					end--;
				}
				end--; 
//				go(num,target,cur1,cur2,begin,end,a,al); ֮ǰд���ǵݹ��ˣ������Ͳ�Ӧ���ǵݹ�
				
			}else{
				while(end>begin&&num[begin]==num[begin+1]){//ע������͵�һ��Ͳ�ͬ�ˣ�������-1
					begin++;        //��Ϊ��begin�ȼ�1�ˣ���������begin�����while֮��ż�1
				}
				begin++;
//				go(num,target,cur1,cur2,begin+1,end,a,al);
				
			}
		}
	}
	//�����֣�˼·�ǶԵģ����Ƿ���һЩС�������Ǹ�������ͬ�����Թ�����ı߽����������׳�����
	public ArrayList<ArrayList<Integer>> fourSum3(int[] num, int target) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(num.length<4){
			return al;
		}
		ArrayList<Integer> a=new ArrayList<Integer>();
		Arrays.sort(num);
		for(int i=0;i<num.length-3;i++){
			if(i>0&&num[i]==num[i-1]){
				continue;
			}
			a.add(num[i]);
			go2(a,i+1,num[i],num,al,target);
			a.remove(a.size()-1);
		}
		return al;
	}
	private void go2(ArrayList<Integer> a,int b,int cur,int[]num,
			ArrayList<ArrayList<Integer>> al,int target){
		
		for(int i=b;i<num.length-2;i++){
			if(i>b&&num[i]==num[i-1]){
				continue;
			}
			a.add(num[i]);
			
			int cur2=cur+num[i];
//			if(cur2>target){    //���if��Ӧ���У���ʼ�������ǰ��������������target����ô����
//				a.remove(a.size()-1);//�������Ǳ�ǰ������ģ�����4�������������ܵ���target�ˣ�����
//				break;              //break����ʵ���ǣ�����������Ȼ���ǰ�����󣬵����п��ܺ�����
//			}                  //����Ȼ���Ǹ���������4�������������п��ܻ����target��
			int left=i+1;
			int right=num.length-1;
			while(left<right){
				if(cur2+num[left]+num[right]==target){
					a.add(num[left]);
					a.add(num[right]);
					al.add(new ArrayList<Integer>(a));
					a.remove(a.size()-1);
					a.remove(a.size()-1);
					left++;
					right--;
					//����Ҫ�Ǻ��ˣ�����left<right���ǰ�������Ͳ��������������left++��right--�ˣ�
					//��һ�£����left�Ѿ�����һλ�ˣ�����Ҫ��֮ǰ��left�Ǹ����������ұ�һλ�Ƿ�
					//��ͬ������leftû�м�1֮ǰ��λ�ú������ұ�һ��λ���Ƿ���ͬ��д����Ӧ���ǿ�left-1
					//��leftλ�õģ�rightͬ������ʼ��ʱ���д����
					while(left<right&&num[left]==num[left-1]){
						left++;
					}
					while(left<right&&num[right]==num[right+1]){
						right--;
					}
					continue;
				}
				if(cur2+num[left]+num[right]>target){
					right--;
					while(left<right&&num[right]==num[right+1]){
						right--;
					}
					continue;
				}
				if(cur2+num[left]+num[right]<target){
					left++;
					while(left<right&&num[left-1]==num[left]){
						left++;
					}
					continue;
				}
			}
			a.remove(a.size()-1);
		}
	}

}
