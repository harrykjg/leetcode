package old;
//http://blog.163.com/gjx_12358@126/blog/static/895363452014232191498/ ���˵�ĺ�
//����һ�£��������ɾ���Ȳ�����������word1���ϣ����濴���Ľ��͡�
//��˵��word1��ӵĻ�����word1���һ����ĸ������ĸ��word2��Ӧ����ĸ��ȣ���abcҪ���de������
//�����e��word1���abce��word����de����������ֻҪ��abc���d�����ˣ�abc����3������de��2�����1����
//�����dp[i][j-1]+1��ע�������dp[i][j]����⣬����˼�ǰ�word1��ǰi���ַ����word2��ǰj���ַ���
//     d e b a
//   a
//   b
//   c   x
//   a
//Ҫ��ͼ����£�����x��λ���ǣ������abc�Ӹ�e����abce����ôֻҪ��abc���d�����ˣ�����x��ֵΪ
//dp[i][j-1]+1,��x����Ǹ�ֵ+1��������֮��ɾ��Ҳ�������

public class EditDistance {
	public static void main(String[] args) {

		String s1 = "ab";
		String s2 = "acb";
		EditDistance ed = new EditDistance();
		System.out.println(ed.minDistance2(s1, s2));
	}

	public int minDistance(String word1, String word2) {
		if(word1.length()==0){
			return word2.length();
		}
		if(word2.length()==0){
			return word1.length();
		}
		int[][] dp=new int[word1.length()+1][word2.length()+1];//word1�����ŵģ�word2�Ǻ��ŵ�
		//��ʱ���Ƿ������ˣ�word1���У���������������Ļ������dp[i][j-1]��dp[i-1][j]��
		//�������෴�ˣ����ǵó��Ľ������һ������������Ϊ�滻��ȨֵҲ��1��Ե��
		for(int i=0;i<dp[0].length;i++){
			dp[0][i]=i;//��һ���յ��ַ����䵽word1�ĵ�i����ĸ�ľ������i
		}
		for(int i=0;i<dp.length;i++){
			dp[i][0]=i;//ͬ��
		}
		for(int i=1;i<dp.length;i++){//���п�ʼ��
			for(int j=1;j<dp[0].length;j++){
				int f=word1.charAt(j-1)==word2.charAt(i-1)?0:1;//����word2��������j������word1
				//������i��ע�������1����Ϊdp�ĳ���ʱ�ַ����ĳ��ȼ���1�ģ������ǰ�һ��word1��
				//һ��word2�����˶�ά����ĵ�һ�к͵�һ��
				//�����������ǰ�ַ���ȵĻ�����ֱ��dp[i][j]����dp[[i-1][j-1]�ˣ������ܱ�֤������
				//��С�ˣ�
				int min=Math.min(dp[i-1][j]+1, dp[i][j-1]+1);
				min=Math.min(min, dp[i-1][j-1]+f);
				dp[i][j]=min;
			}
		}

		return dp[word2.length()][word1.length()];
	}

	public int minDistance2(String word1, String word2) {
		if(word1.length()==0){
			return word2.length();
		}
		if(word2.length()==0){
			return word1.length();
		}
		int[][] dp=new int[word1.length()+1][word2.length()+1];
		for(int i=0;i<dp.length;i++){
			dp[0][i]=i;
		}
		for(int i=0;i<dp[0].length;i++){
			dp[i][0]=i;
		}
		for(int i=1;i<dp.length;i++){
			for(int j=1;j<dp[0].length;j++){
				if(word1.charAt(i-1)==word2.charAt(j-1)){
					dp[i][j]=dp[i-1][j-1];
				}else{
					dp[i][j]=Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j]))+1;
					
				}
			}
		}
		return dp[dp.length-1][dp[0].length-1];
		
	}
}
