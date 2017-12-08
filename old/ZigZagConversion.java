public class ZigZagConversion {
//http://jixiangsanbao.wordpress.com/2014/03/11/zigzag-conversion/
	public static void main(String[] args) {

		ZigZagConversion zz = new ZigZagConversion();
		String s = "abc";

		String ss = zz.convert(s, 2);
		System.out.println(ss);

	}

	public String convert(String s, int nRows) {

		if (s.length() == 0) {
			return s;
		}

		if (s.length() != 0 &&  nRows == 2) {

			int cc = s.length() / nRows;
			int ccc=s.length()%nRows;
			if(ccc!=0){
				cc=cc+1;
			}
			if(cc==0){
				cc=1;
			}

			char[][] mm = new char[nRows][cc];
			int kkk = 0;
			for (int i = 0; i < cc; i++) {
				for (int j = 0; j < nRows; j++) {
					if (kkk < s.length()) {

						mm[j][i] = s.charAt(kkk);
						kkk++;
					}
				}
			}
			int kk = 0;
			char[] ch = new char[s.length()];
			for (int i = 0; i < mm.length; i++) {
				for (int j = 0; j < mm[0].length; j++) {
					if (mm[i][j] != '\0' && kk < s.length()) {
						ch[kk] = mm[i][j];
						kk++;
					}
				}
			}
			String ss = new String(ch);
			return ss;
		} else {

			int col1 = s.length() / (nRows + 1);
			int col2 = s.length() % (nRows + 1);
			int col = 0;
			if (col2 == 0) {
				col = 2 * col1;
			} else {
				col = 2 * col1 + 1;
			}

			char[][] m = new char[nRows][col];
			int k = 0;
			for (int i = 0; i < col; i++) {
				for (int j = 0; j < nRows; j++) {

//					if (nRows == 2) {
//						if (i % 2 != 0 && j == 0 && k < s.length()) {
//							m[j][i] = s.charAt(k);
//							k++;
//						} else if (i % 2 == 0 && k < s.length()) {
//							m[j][i] = s.charAt(k);
//							k++;
//						}
//					} else {

						if (i % 2 != 0 && j == nRows / 2 && k < s.length()) {
							m[j][i] = s.charAt(k);
							k++;
						} else if (i % 2 == 0 && k < s.length()) {
							m[j][i] = s.charAt(k);
							k++;
						}
					}
//				}
			}

			int kk = 0;
			char[] ch = new char[s.length()];
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[0].length; j++) {
					if (m[i][j] != '\0' && kk < s.length()) {
						ch[kk] = m[i][j];
						kk++;
					}
				}
			}
			String ss = new String(ch);
			return ss;
		}
		

	}

}
