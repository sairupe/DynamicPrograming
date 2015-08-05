package matrixchain;

import java.util.Arrays;

/**
 * @author SyrianaZh
 * �������˷�
 * 2015-8-5
 */
public class MatrixChainMultiple {
	
	public static int counts = 0;
	
	// ������¼��
	// i~j������С����
	public static int[][] m = new int[7][7];
	// i~j���˶ϵ�λ��
	public static int[][] s = new int[7][7];
	/**
	 * ����ά����
	 * ע���㷨�����϶���Ai��ά����ʾ��һ������
	 * ��Ai = p[i-1] * p[i](i >= 1)
	 * �������p���������������
	 * p[i-1] - ��
	 * p[i] - ��
	 * ���¿����
	 * p[i-1] * p[k] * p[j];
	 */
	public static int[] p = new int[m.length];//[30, 35, 15, 5, 10, 20, 25]
	
	
	static{
		// ��ʼ�������¼��
		intialP(1, 30, 35);
		intialP(2, 35, 15);
		intialP(3, 15, 5);
		intialP(4, 5 , 10);
		intialP(5, 10, 20);
		intialP(6, 20, 25);
	}
	
	/**
	 * A1 : 30 * 35 |
	 * A2 : 35 * 15 | 
	 * A3 : 15 * 5  |
	 * A4 : 5  * 10 |
	 * A5 : 10 * 20 |
	 * A6 : 20 * 25 |
	 * 
	 * ��� ((A(AA))((AA)A))
	 */
	
	/**
	 * �ݹ���(�ظ�������̶�)
	 * 							1:4
	 * 		1:1		2:4		1:2		3:4		1:3		4:4
	 * 		....	
	 * �ֽ⣬�ȵõ�����Խ���֮�ͣ�Ȼ��ϲ�
	 */
	
	/**
	 * �ݹ��㷨
	 * @param i
	 * @param j
	 * @return
	 */
	public static int MatrixChainMinCountsRecursive(int i, int j){
		if(i == j){
			return 0;
		}
		// ��ʼ��һ��ֵ��������ΪINTEGER.MAXINTVALUE
		m[i][j] = Integer.MAX_VALUE;
		for(int k = i; k < j; k++){
			int t = MatrixChainMinCountsRecursive(i, k) + MatrixChainMinCountsRecursive(k + 1, j)
				  + p[i-1] * p[k] * p[j];
			counts++;
			if(t < m[i][j]){
				m[i][j] = t;
				s[i][j] = k;
			}
		}
		return m[i][j];
	}
	
	/**
	 * �����˾���P����С���˴���
	 * @param n N�������˾����ά��P
	 * @return ��С���˴���
	 */
	public static void MatrixChainUnRecursive(int n){
		// i�Ǿ��������꣬j�Ǿ���������
		for(int i = 0; i <= n; i++){
			m[i][i] = 0;// �Լ���������˴���Ϊ0
		}
		// ���վ���˻�������������˳���Ե����ϼ���m[1][n]
		for(int l = 2; l <= n; l++){// l��Ҫ�ָ�ľ������ĳ���
			// �ӵ�һ������A1��ʼ���������ƶ�
			// l = 2; ���һ��ѭ�� i=5, ��A5 * A6������������
			for(int i = 1; i <= n - l + 1; i++){// PS:(�������ﲻҲ���ǻ��ظ������𣿣�)
				int j = i + l - 1;// ����l��i����� l���ȵľ���j�������Ƕ���
				m[i][j] = Integer.MAX_VALUE;
				// �� i<k<j���������A[i:j] = A[i:k]A[k+1:j],k�Ƕϵ�λ�� 
				for(int k = i; k < j; k++){
					int t = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
					counts++;
					if(t < m[i][j]){
						m[i][j] = t;
						s[i][j] = k;
						printMatrix();
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		System.err.println("    ע���㷨�����϶���Ai��ά����ʾ��һ������\n����Ai = p[i-1] * p[i](i >= 1), �������p\n���������������" +
				"");
		System.out.println(Arrays.toString(p));
		int result = MatrixChainMinCountsRecursive(1, 6);
		System.out.println("=======>>> �ݹ������С���� Min Counts : " + result);
		System.out.println("=======>>> �ܼ������ Counts : " + counts);
		System.out.println("=======>>>");
		counts = 0;
		System.out.println("=======>>>");
		System.out.println("=======>>> ���������������λ�����");
		MatrixChainUnRecursive(6);
		System.out.println("=======>>> �ܼ������ Counts : " + counts);
		printBrokenK();
		printOptimalParens(1, 6);
	}
	
	/**
	 * ��ʼ��P�ľ���Ԫ��
	 * @param i
	 * @param p
	 * @param q
	 */
	public static void intialP(int i, int m , int n){
		p[i - 1] = m;
		p[i] = n;
	}
	
	public static void printMatrix(){
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m.length; j++){
				System.out.print(m[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("=============================");
	}
	
	public static void printBrokenK(){
		for(int i = 1; i < s.length; i++){
			for(int j = 1; j < s.length; j++){
				System.out.print(s[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("=============================");
	}
	
	/**
	 * ���A[i:j]��s[i][j]���Ͽ�ΪA[i,s[i][j]]��A[s[i][j]+1 j]
	 * @param ��ʼλ��
	 * @param ����λ��
	 */
	public static void printOptimalParens(int i, int j){
		if(i == j){
			System.out.print("A");
		}
		else{
			System.out.print("(");
			printOptimalParens(i, s[i][j]);
			printOptimalParens(s[i][j] + 1, j);
			System.out.print(")");
		}
			
	}
}
